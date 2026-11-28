package frc.team9449

import edu.wpi.first.wpilibj2.command.Command
import org.littletonrobotics.junction.LoggedRobot

class Robot: LoggedRobot() {
    private lateinit var robotContainer: RobotContainer
    private var autonomousCommand: Command? = null

    override fun robotInit() {
        robotContainer = RobotContainer
        robotContainer.routines.autoChooser(robotContainer.autoChooser)
    }
}