package com.pool8

import com.badlogic.gdx.Game
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.glutils.ShapeRenderer
import com.badlogic.gdx.utils.Align
import com.pool8.rendering.BallRenderer
import com.pool8.rendering.TableRenderer
import com.pool8.screens.MenuScreen

/** Owns the shared GPU/audio resources; screens just borrow them. */
class PoolGame : Game() {
    lateinit var shape: ShapeRenderer
    lateinit var batch: SpriteBatch
    lateinit var font: BitmapFont
    lateinit var settings: Settings
    lateinit var audio: GameAudio
    lateinit var tableRenderer: TableRenderer
    lateinit var ballRenderer: BallRenderer
    private val glyph = GlyphLayout()

    override fun create() {
        shape = ShapeRenderer()
        batch = SpriteBatch()
        font = BitmapFont()
        font.setUseIntegerPositions(false)
        font.region.texture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear)
        settings = Settings()
        audio = GameAudio(settings)
        tableRenderer = TableRenderer()
        ballRenderer = BallRenderer()
        setScreen(MenuScreen(this))
    }

    /** Must be called between batch.begin()/end(). y is the TOP of the text. */
    fun text(str: String, x: Float, y: Float, scale: Float, color: Color, align: Int = Align.left, width: Float = 0f) {
        font.data.setScale(scale)
        font.setColor(color)
        font.draw(batch, str, x, y, width, align, false)
    }

    fun centerText(str: String, cx: Float, y: Float, scale: Float, color: Color) =
        text(str, cx - 300f, y, scale, color, Align.center, 600f)

    fun textWidth(str: String, scale: Float): Float {
        font.data.setScale(scale)
        glyph.setText(font, str)
        return glyph.width
    }

    fun capH(scale: Float): Float {
        font.data.setScale(scale)
        return font.data.capHeight
    }

    override fun dispose() {
        super.dispose()
        shape.dispose(); batch.dispose(); font.dispose()
        audio.dispose(); tableRenderer.dispose(); ballRenderer.dispose()
    }
}
