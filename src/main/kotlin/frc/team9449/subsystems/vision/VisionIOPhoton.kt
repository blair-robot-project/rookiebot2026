package frc.team9449.subsystems.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.geometry.Transform3d
import frc.team449.subsystems.vision.PoseObservationType
import frc.team449.subsystems.vision.PoseObservations
import frc.team449.subsystems.vision.TargetObservations
import frc.team449.subsystems.vision.VisionIO
import frc.team449.subsystems.vision.VisionIOLimelight
import frc.team9449.VisionConstants
import org.photonvision.PhotonCamera

open class VisionIOPhoton(Name: String, val Offset: Transform3d) : VisionIO {
    protected val photonCamera: PhotonCamera = PhotonCamera(Name)

    override fun updateInputs(inputs: VisionIO.VisionIOInputs) {
        if (photonCamera.isConnected) {
            inputs.connected = photonCamera.isConnected
        }

        val tags = mutableSetOf<Int>()
        val pose_observations = mutableSetOf<PoseObservations>()

        for (result in photonCamera.allUnreadResults) {
            if (result.hasTargets()) {
                inputs.latestTargetObservations = TargetObservations(
                    Rotation2d.fromDegrees(result.bestTarget.yaw),
                    Rotation2d.fromDegrees(result.bestTarget.pitch)
                )
            } else {
                inputs.latestTargetObservations = TargetObservations(Rotation2d.kZero, Rotation2d.kZero)
            }

            if (result.multiTagResult.isPresent) {
                val multiTagResult = result.multiTagResult.get()

                //vectors
                val robotToCamera = Offset
                val cameraToRobot = robotToCamera.inverse()
                val fieldToCamera: Transform3d = multiTagResult.estimatedPose.best
                val fieldToRobot = fieldToCamera.plus(cameraToRobot)
                val robotPose = Pose3d(fieldToRobot.translation, fieldToRobot.rotation)

                var sum = 0.0
                for (target in result.targets) {
                    val distance = target.bestCameraToTarget.translation.norm
                    sum += distance
                    tags.add(target.fiducialId)
                }
                val avgDist = sum/(result.targets.size)

                pose_observations.add(
                    PoseObservations(
                        robotPose,
                        result.timestampSeconds,
                        multiTagResult.estimatedPose.ambiguity,
                        multiTagResult.fiducialIDsUsed.size,
                        avgDist,
                        PoseObservationType.PHOTONVISION
                    )
                )

            } else if (result.targets.isNotEmpty()) {
                val target = result.targets[0]

                val pose = VisionConstants.REBUILT_FIELD_LAYOUT.getTagPose(target.fiducialId)

                if (pose.isPresent) {
                    val robotToCamera = Offset
                    val cameraToRobot = robotToCamera.inverse()
                    val fieldToCamera: Transform3d = target.bestCameraToTarget
                    val fieldToRobot = fieldToCamera.plus(cameraToRobot)
                    val robotPose = Pose3d(fieldToRobot.translation, fieldToRobot.rotation)

                    tags.add(target.fiducialId)
                }
            }
        }
    }
}