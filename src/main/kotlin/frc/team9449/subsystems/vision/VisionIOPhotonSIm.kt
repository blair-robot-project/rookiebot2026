package frc.team9449.subsystems.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import org.photonvision.simulation.VisionSystemSim
import java.util.function.Supplier

class VisionIOPhotonSim(
    Name: String,
    Offset: Pose3d,
    private val PoseSupplier: Supplier<Pose2d>
) : VisionIOPhoton(Name, Offset.minus(Pose3d())) {
    private val main: VisionSystemSim = VisionSystemSim("main")
    }