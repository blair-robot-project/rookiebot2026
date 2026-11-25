package frc.team449.subsystems.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.geometry.Rotation3d
import edu.wpi.first.wpilibj.Timer
import limelight.Limelight
import limelight.networktables.AngularVelocity3d
import limelight.networktables.LimelightPoseEstimator
import limelight.networktables.LimelightResults
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


        val visionEstimate: Optional<LimelightResults> =
            limelight.data.results

        val poseEstimate: Optional<PoseEstimate> = poseEstimator.getPoseEstimate()

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

            if (visionEstimate.isPresent) {
                val results = visionEstimate.get()
                if (results.targets_Fiducials != null) {
                    val X_ROT = Rotation2d.fromDegrees(results.targets_Fiducials[0].tx)
                    val Y_ROT = Rotation2d.fromDegrees(results.targets_Fiducials[0].ty)

                    inputs.latestTargetObservations = TargetObservations(X_ROT, Y_ROT)
                    val ListofIDs = mutableSetOf<Int>()
                    for (i in results.targets_Fiducials) {
                        ListofIDs.add(i.fiducialID.toInt())
                    }
                    inputs.tagIds = ListofIDs.toIntArray()
                } else {
                    inputs.latestTargetObservationa = Rotation2d(0.0, 0.0)
                    inputs.tagIds = IntArray(0)
                }
            }

        } else {
            inputs.connected = false
            inputs.poseObservations = emptyArray()
        }
    }
}