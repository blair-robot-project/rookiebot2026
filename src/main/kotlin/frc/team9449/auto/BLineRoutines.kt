package frc.team9449.auto
import com.ctre.phoenix6.swerve.SwerveRequest
import frc.robot.lib.BLine.FollowPath;
import edu.wpi.first.math.controller.PIDController
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
    
}
