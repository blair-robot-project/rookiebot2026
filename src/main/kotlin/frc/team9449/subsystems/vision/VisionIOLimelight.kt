package frc.team449.subsystems.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import edu.wpi.first.math.geometry.Rotation3d
import edu.wpi.first.wpilibj.Timer
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
    private val pose_supplier: Supplier<Pose2d>,
    private val angularVelocitySupplier: Supplier<AngularVelocity3d>,
    offset: Pose3d
) : VisionIO {
    private val limelight = Limelight("Limelight")
    private var estimationMode = LimelightPoseEstimator.EstimationMode.MEGATAG2
    private var poseObservationTypes = PoseObservationType.MEGATAG2
    private val poseEstimator = limelight.createPoseEstimator(estimationMode)

    init {
        limelight.getSettings()
            .withLimelightLEDMode(LimelightSettings.LEDMode.PipelineControl)
            .withCameraOffset(Pose3d.kZero)
            .save()

        limelight.settings.withImuMode(LimelightSettings.ImuMode.ExternalImu)
            .save()
    }

    override fun updateInputs(inputs: VisionIO.VisionIOInputs) {
        limelight.settings
            .withRobotOrientation(
                Orientation3d(
                    Rotation3d(pose_supplier.get().rotation),
                    angularVelocitySupplier.get(),
                )
            )
            .save()


        val visionEstimate: Optional<PoseEstimate> =
            limelight.createPoseEstimator(LimelightPoseEstimator.EstimationMode.MEGATAG2)
                .poseEstimate

        val poseEstimate: Optional<PoseEstimate> =
            limelight.createPoseEstimator(LimelightPoseEstimator.EstimationMode.MEGATAG2)
                .poseEstimate

        if (visionEstimate.isPresent) {
            val visionEst = visionEstimate.get()

            inputs.connected = (Timer.getFPGATimestamp() - visionEst.timestampSeconds) < 0.25
            inputs.poseObservations = arrayOf(
                PoseObservations(
                    visionEst.pose,
                    visionEst.timestampSeconds,
                    visionEst.avgTagAmbiguity,
                    visionEst.tagCount,
                    visionEst.avgTagDist,
                    PoseObservationType.MEGATAG2,
                )
            )
        } else {
            inputs.connected = false
            inputs.poseObservations = emptyArray()
        }

        if (poseEstimate.isPresent) {
            val poseEst = poseEstimate.get()

            inputs.connected = (Timer.getFPGATimestamp() - poseEst.timestampSeconds) < 0.25
            inputs.poseObservations = arrayOf(
                PoseObservations(
                    poseEst.pose,
                    poseEst.timestampSeconds,
                    poseEst.avgTagAmbiguity,
                    poseEst.tagCount,
                    poseEst.avgTagDist,
                    PoseObservationType.MEGATAG2,
                )
            )
        } else {
            inputs.connected = false
            inputs.poseObservations = emptyArray()
        }

    }
}