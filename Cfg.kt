package com.pool8

/** World units are centimetres. A regulation 9ft table playfield is ~254 x 127 cm. */
object Cfg {
    const val PLAY_W = 254f
    const val PLAY_H = 127f
    const val BALL_R = 2.86f
    /** Cushion (6) + wooden rail (12) around the playfield. */
    const val BORDER = 18f
    const val CUSHION = 6f
    const val CORNER_POCKET_R = 5.9f
    const val SIDE_POCKET_R = 5.4f

    const val WORLD_W = PLAY_W + 2 * BORDER
    const val WORLD_H = PLAY_H + 2 * BORDER
}
