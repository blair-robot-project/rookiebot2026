package frc.team9449.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import edu.wpi.first.units.measure.AngularVelocity
import java.util.function.Supplier
import edu.wpi.first.math.geometry.Pose3d
import org.littletonrobotics.junction.LogTable
import org.littletonrobotics.junction.inputs.LoggableInputs

class VisionIOLimelight(name: String, position: Pose3d, poseSupplier: Supplier<Pose2d>, angVeloSupplier: Supplier<AngularVelocity>): VisionIO{

    class VisionIOInputs : LoggableInputs {
        var connected = false
        var hasPose = false
        var pose = Pose3d()
        var timestampSeconds = 0.0
        var tagCount = 0
        var avgTagDistMeters = 0.0

        override fun toLog(table: LogTable) {
            table.put("Connected", connected)
            table.put("HasPose", hasPose)
            table.put("Pose", Pose3d.struct, pose)
            table.put("TimestampSeconds", timestampSeconds)
            table.put("TagCount", tagCount)
            table.put("AvgTagDistMeters", avgTagDistMeters)
        }

        override fun fromLog(table: LogTable) {
            connected = table.get("Connected", connected)
            hasPose = table.get("HasPose", hasPose)
            pose = table.get("Pose", Pose3d.struct, pose)
            timestampSeconds = table.get("TimestampSeconds", timestampSeconds)
            tagCount = table.get("TagCount", tagCount)
            avgTagDistMeters = table.get("AvgTagDistMeters", avgTagDistMeters)
        }
    }

    interface VisionIO {

        fun updateInputs(inputs: VisionIOInputs) {}
        fun setImuSyncInternal() {}
        fun setImuExternalAssist() {}
    }
}
