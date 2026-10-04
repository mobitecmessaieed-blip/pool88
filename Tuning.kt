package com.pool8

/** All physics/feel constants in one place. Units: centimetres, seconds. */
object Tuning {
    const val FIXED_DT = 1f / 120f          // physics step
    const val MIN_SPEED = 60f               // cue-ball speed at 0% power (cm/s)
    const val MAX_SPEED = 850f              // cue-ball speed at 100% power
    const val BALL_RESTITUTION = 0.95f      // ball-ball bounciness
    const val CUSHION_RESTITUTION = 0.78f   // speed kept along the normal after a rail hit
    const val CUSHION_TANGENT_KEEP = 0.96f  // speed kept along the rail after a hit
    const val ROLL_DECEL = 28f              // constant rolling deceleration (cm/s^2)
    const val ROLL_DRAG = 0.55f             // speed-proportional drag (1/s)
    const val STOP_SPEED = 3f               // below this a ball stops (cm/s)
    const val POCKET_FORGIVE = 2f           // extra capture radius around pockets
    const val FOLLOW_FACTOR = 0.55f         // top/back spin strength vs shot speed
    const val SIDE_FACTOR = 0.30f           // side spin strength vs shot speed
    const val SQUIRT = 0.025f               // cue deflection (rad) at full side spin
    const val SIDE_RAIL_K = 0.35f           // how much side spin kicks the ball off a rail
    const val SIDE_BALL_K = 0.12f           // how much side spin kicks cue ball after ball contact
    const val FOLLOW_DECAY = 1.8f           // spin fades with time (1/s)
    const val SIDE_DECAY = 1.2f
    const val FINE_RANGE = 0.0524f          // fine-aim slider range, +/- 3 degrees
}
