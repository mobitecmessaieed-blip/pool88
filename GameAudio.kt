package com.pool8

import com.badlogic.gdx.Gdx
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.utils.Disposable
import java.nio.ByteBuffer
import java.nio.ByteOrder
import kotlin.math.exp
import kotlin.math.sin

/** Procedurally generated sound effects (no audio assets needed). */
object Synth {
    const val RATE = 22050
    private const val TAU = 6.2831855f
    private val rnd = java.util.Random(11)
    private fun noise() = rnd.nextFloat() * 2f - 1f

    private fun gen(sec: Float, f: (Float) -> Float): ShortArray {
        val n = (sec * RATE).toInt()
        val out = ShortArray(n)
        for (i in 0 until n) {
            val v = f(i.toFloat() / RATE).coerceIn(-1f, 1f)
            out[i] = (v * 28000f).toInt().toShort()
        }
        return out
    }

    fun cue() = gen(0.10f) { t -> (noise() * 0.45f + sin(TAU * 480f * t) * 0.7f) * exp(-t * 45f) }
    fun clack() = gen(0.08f) { t ->
        (sin(TAU * 2800f * t) * 0.55f + sin(TAU * 4300f * t) * 0.3f + noise() * 0.12f) * exp(-t * 85f)
    }
    fun cushion() = gen(0.12f) { t -> (sin(TAU * 150f * t) * 0.8f + noise() * 0.2f) * exp(-t * 38f) }
    fun pocket() = gen(0.35f) { t ->
        (sin(TAU * (520f - 700f * t) * t) * 0.5f + noise() * 0.15f * exp(-t * 30f)) * exp(-t * 9f)
    }

    fun wav(s: ShortArray): ByteArray {
        val dataLen = s.size * 2
        val bb = ByteBuffer.allocate(44 + dataLen).order(ByteOrder.LITTLE_ENDIAN)
        bb.put("RIFF".toByteArray()); bb.putInt(36 + dataLen)
        bb.put("WAVE".toByteArray()); bb.put("fmt ".toByteArray())
        bb.putInt(16); bb.putShort(1.toShort()); bb.putShort(1.toShort())
        bb.putInt(RATE); bb.putInt(RATE * 2); bb.putShort(2.toShort()); bb.putShort(16.toShort())
        bb.put("data".toByteArray()); bb.putInt(dataLen)
        for (v in s) bb.putShort(v)
        return bb.array()
    }
}

class GameAudio(private val settings: Settings) : Disposable {
    private val cue = make("cue", Synth.cue())
    private val clack = make("clack", Synth.clack())
    private val cushion = make("cushion", Synth.cushion())
    private val pocket = make("pocket", Synth.pocket())

    private fun make(name: String, data: ShortArray): Sound {
        val fh = Gdx.files.local("sfx_$name.wav")
        fh.writeBytes(Synth.wav(data), false)
        return Gdx.audio.newSound(fh)
    }

    fun cueHit(power: Float) { if (settings.sound) cue.play(0.4f + 0.6f * power) }
    fun clack(impact: Float) { if (settings.sound) clack.play((impact / 350f).coerceIn(0.12f, 1f)) }
    fun cushion(impact: Float) { if (settings.sound) cushion.play((impact / 350f).coerceIn(0.1f, 0.9f)) }
    fun pocket() { if (settings.sound) pocket.play(0.8f) }
    fun vibrate(ms: Int) { if (settings.vibration) Gdx.input.vibrate(ms) }

    override fun dispose() {
        cue.dispose(); clack.dispose(); cushion.dispose(); pocket.dispose()
    }
}
