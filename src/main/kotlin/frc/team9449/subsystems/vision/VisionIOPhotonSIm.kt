package frc.team9449.subsystems.vision

import edu.wpi.first.apriltag.AprilTag
import edu.wpi.first.apriltag.AprilTagFieldLayout
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import edu.wpi.first.math.geometry.Rotation2d
import frc.team449.subsystems.vision.VisionIO
import frc.team9449.VisionConstants
import org.photonvision.PhotonCamera
import org.photonvision.simulation.PhotonCameraSim
import org.photonvision.simulation.SimCameraProperties
import org.photonvision.simulation.VisionSystemSim
import java.util.function.Supplier

class VisionIOPhotonSim(
    Name: String,
    Offset: Pose3d,
    private val PoseSupplier: Supplier<Pose2d>
) : VisionIOPhoton(Name, Offset.minus(Pose3d())) {

    private var PhotonCameraSim: PhotonCameraSim = PhotonCameraSim(PhotonCamera("camera"))
    private var main: VisionSystemSim = VisionSystemSim("main")

    init {
        main.addAprilTags(VisionConstants.REBUILT_FIELD_LAYOUT)

        val simCameraProperties: SimCameraProperties = SimCameraProperties()
        simCameraProperties.setCalibration(1280, 800, Rotation2d(99.41))
        simCameraProperties.setCalibError(0.0, 0.0)
        simCameraProperties.setFPS(120.0)
        simCameraProperties.setAvgLatencyMs(15.0)
        simCameraProperties.setLatencyStdDevMs(5.0)

        PhotonCameraSim = PhotonCameraSim(photonCamera, simCameraProperties, VisionConstants.REBUILT_FIELD_LAYOUT)
        main.addCamera(PhotonCameraSim(photonCamera), Offset.minus(Pose3d()))
        }

    override fun updateInputs(inputs: VisionIO.VisionIOInputs) {
        main.update(PoseSupplier.get())
        super.updateInputs(inputs)

        }
    }