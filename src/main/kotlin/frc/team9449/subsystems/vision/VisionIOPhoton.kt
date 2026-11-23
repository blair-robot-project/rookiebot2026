package frc.team9449.subsystems.vision

import edu.wpi.first.math.geometry.Pose3d
import frc.team449.subsystems.vision.VisionIO
import org.photonvision.PhotonCamera

open class VisionIOPhoton(Name: String, Offset: Pose3d) : VisionIO {
    protected val photoncamera: PhotonCamera = PhotonCamera(Name)
}