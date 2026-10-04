package com.pool8

import com.badlogic.gdx.Gdx

class Settings {
    private val p = Gdx.app.getPreferences("pool8")

    var sound: Boolean
        get() = p.getBoolean("sound", true)
        set(v) { p.putBoolean("sound", v); p.flush() }

    var vibration: Boolean
        get() = p.getBoolean("vibration", true)
        set(v) { p.putBoolean("vibration", v); p.flush() }

    /** 0 = short, 1 = medium, 2 = full length. */
    var aimLen: Int
        get() = p.getInteger("aimLen", 1)
        set(v) { p.putInteger("aimLen", v); p.flush() }

    fun guideLength(): Float = when (aimLen) {
        0 -> 90f
        1 -> 200f
        else -> 100000f
    }
}
