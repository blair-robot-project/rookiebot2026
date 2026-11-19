package frc.team449.subsystems.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import edu.wpi.first.math.geometry.Rotation3d
import edu.wpi.first.units.Units
import edu.wpi.first.units.Units.Inches
import edu.wpi.first.units.measure.Distance
import limelight.Limelight
import limelight.networktables.AngularVelocity3d
import limelight.networktables.LimelightPoseEstimator
import limelight.networktables.LimelightSettings
import limelight.networktables.Orientation3d
import limelight.networktables.PoseEstimate
import java.util.Optional
import java.util.function.Supplier

class VisionIOLimelight(
    name: String,
    private val poseSupplier: Supplier<Pose2d>,
    private val angularVelocitySupplier: Supplier<AngularVelocity3d>,
    offset: Pose3d,
) : VisionIO {
    private val limelight: Limelight = Limelight("limelight")
    private val estimationMode = LimelightPoseEstimator.EstimationMode.MEGATAG2
    private val poseObservationType = PoseObservationType.MEGATAG2
    private val poseEstimator = limelight.createPoseEstimator(estimationMode)

    init {
        val cameraOffset: Distance = Inches.of((5.0))
        limelight.getSettings()
            .withLimelightLEDMode(LimelightSettings.LEDMode.PipelineControl)
            .withCameraOffset(Pose3d.kZero)
            .save()

        val poseEstimator = limelight.createPoseEstimator(LimelightPoseEstimator.EstimationMode.MEGATAG2)
        }

    override fun updateInputs(inputs: VisionIO.VisionIOInputs) {
        limelight.getSettings()
            .withRobotOrientation(
                Orientation3d(
                    Rotation3d(poseSupplier.get().rotation),
                    angularVelocitySupplier.get(),
                )
            )
            .save()
        val visionEstimate: Optional<PoseEstimate> =
            limelight.createPoseEstimator(LimelightPoseEstimator.EstimationMode.MEGATAG2)
                .getPoseEstimate()
        }
    }


