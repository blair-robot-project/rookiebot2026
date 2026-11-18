package frc.team449.subsystems.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import limelight.Limelight
import limelight.networktables.AngularVelocity3d
import limelight.networktables.LimelightPoseEstimator
import limelight.networktables.LimelightSettings
import limelight.networktables.PoseEstimate
import java.util.Optional
import java.util.function.Supplier

class VisionIOLimelight(
    private val limelight_name: String,
    private val limelight_pos: Pose3d,
    private val pose_supplier: Supplier<Pose2d>,
    private val angularVelocitySupplier: Supplier<AngularVelocity3d>
) : VisionIO {
    init {
        val limelight: Limelight = Limelight("limelight")
        limelight.getSettings()
            .withLimelightLEDMode(LimelightSettings.LEDMode.PipelineControl)
            .withCameraOffset(Pose3d.kZero)
            .save()

        val visionEstimate: Optional<PoseEstimate> = limelight.createPoseEstimator(LimelightPoseEstimator.EstimationMode.MEGATAG2)
            .getPoseEstimate()
        }
    }
