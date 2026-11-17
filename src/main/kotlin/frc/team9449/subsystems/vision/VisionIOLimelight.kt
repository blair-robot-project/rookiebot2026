package frc.team449.subsystems.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import limelight.networktables.AngularVelocity3d
import java.util.function.Supplier

class VisionIOLimelight(
    private val limelight_name: String,
    private val limelight_pos: Pose3d,
    private val pose_supplier: Supplier<Pose2d>,
    private val angularVelocitySupplier: Supplier<AngularVelocity3d>
) : VisionIO {
}