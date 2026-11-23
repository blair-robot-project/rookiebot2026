package frc.team9449.auto
import com.ctre.phoenix6.swerve.SwerveRequest
import frc.robot.lib.BLine.FollowPath;
import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import org.littletonrobotics.junction.Logger;


class BLineRoutines {
    private val rotationController = PIDController(1.0, 0.0, 1.0)
    private val translationController = PIDController(2.0, 0.0, 0.0)
    private val crosstrackController = PIDController(0.2, 0.0, 0.0)

    private val applyRobotSpeeds = SwerveRequest.ApplyRobotSpeeds()

    fun connectToAdvantageScope() {
        FollowPath.setDoubleLoggingConsumer { value -> Logger.recordOutput(value.getFirst(), value.getSecond())}
        FollowPath.setBooleanLoggingConsumer { value -> Logger.recordOutput(value.getFirst(), value.getSecond())}
        FollowPath.setPoseLoggingConsumer { value -> Logger.recordOutput(value.getFirst(), value.getSecond())}
        FollowPath.setTranslationListLoggingConsumer { value -> Logger.recordOutput(value.getFirst().toString(), value.getSecond().contentToString())}
    }
    init {
        connectToAdvantageScope()
    }

    private fun score4oven(): Command {
        //builderReset
        //builderRegular
        return Commands.sequence(
            //align drive modules
            //score in oven
            //builderReset.build(Path("emily_4oven.json"))
            //start intake and stop intake event triggers
            //score in oven x2
        )
    }
    
}
