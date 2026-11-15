package frc.team9449
import com.ctre.phoenix6.Utils
import edu.wpi.first.math.geometry.Pose3d
object RobotContainer
val drivetrain = TunerConstants.createDrivetrain()

// Vision IO built from the drivetrain suppliers
private val visionIO: VisionIO = VisionIOLimelight(
    "limelight-right",
    Pose3d(),
    { drivetrain.state.Pose },
    { drivetrain.pigeon2.angularVelocityZWorldGetter.value }
)

// Vision subsystem
val visionSubsystem = VisionIOSubsystem(visionIO) { pose, timestamp, stdDevs ->
    drivetrain.addVisionMeasurement(pose, Utils.fpgaToCurrentTime(timestamp), stdDevs)
}

//  Pass-throughs so Robot.kt can call them
fun setVisionImuSyncInternal() = visionIO.setImuSyncInternal()
fun setVisionImuExternalAssist() = visionIO.setImuExternalAssist()
}