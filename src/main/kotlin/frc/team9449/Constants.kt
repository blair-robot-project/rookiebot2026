package frc.team9449

import edu.wpi.first.wpilibj.RobotBase

object Constants {
    enum class Mode {
        REAL,
        SIM,
        REPLAY
    }

    val CURRENT_MODE: Mode = if (RobotBase.isReal()) Mode.REAL else Mode.SIM
    object IndexerConstants{
        const val INDEXER_MOTOR_ID = 9 //Temporary
        const val LASER_CAN_BOTTOM_ID = 10 //Temporary
        const val LASER_CAN_SIDE_ID = 11 //Temporary
        const val LASER_CAN_SIDE_X = 1 //Temporary
        const val LASER_CAN_SIDE_Y =1 //Temporary
        const val LASER_CAN_SIDE_H =1 //Temp
        const val LASER_CAN_SIDE_W =1 //Temp
        const val LASER_CAN_BOTTOM_X = 1 //Temporary
        const val LASER_CAN_BOTTOM_Y =1 //Temporary
        const val LASER_CAN_BOTTOM_H =1 //Temp
        const val LASER_CAN_BOTTOM_W =1 //Temp

    }
}