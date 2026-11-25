package frc.team449.subsystems.vision

import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.geometry.Pose3d
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.util.struct.Struct
import edu.wpi.first.util.struct.StructSerializable
import org.littletonrobotics.junction.AutoLog
import java.nio.ByteBuffer

interface VisionIO {
    @AutoLog
    open class VisionIOInputs {
        @JvmField var connected: Boolean = false
        @JvmField var latestTargetObservations: TargetObservations = TargetObservations(Rotation2d.kZero, Rotation2d.kZero)
        @JvmField var poseObservations: Array<PoseObservations> = emptyArray()
        @JvmField var tagIds = IntArray(0)
    }
    fun updateInputs(inputs: VisionIOInputs) {}
}

data class TargetObservations(
    val x_rot: Rotation2d,
    val y_rot: Rotation2d
) : StructSerializable {
    companion object {
        @JvmField
        val struct: Struct<TargetObservations> = object : Struct<TargetObservations> {
            override fun getTypeClass(): Class<TargetObservations> {
                return TargetObservations::class.java
            }

            override fun getTypeName(): kotlin.String {
                return "TargetObservations"
            }

            override fun getSize(): Int {
                return Rotation2d.struct.size
            }

            override fun getSchema(): kotlin.String {
                return "Double x_rotation;Double y_rotation"
            }

            override fun pack(buffer: ByteBuffer, value: TargetObservations) {
                Rotation2d.struct.pack(buffer, value.x_rot)
                Rotation2d.struct.pack(buffer, value.y_rot)
            }

            override fun unpack(buffer: ByteBuffer): TargetObservations {
                val new_X_ROT = Rotation2d.struct.unpack(buffer)
                val new_Y_ROT = Rotation2d.struct.unpack(buffer)
                return TargetObservations(new_X_ROT, new_Y_ROT)
            }

        }
    }
}


data class PoseObservations(
    val pose: Pose3d,
    val timestamp: Double,
    val ambiguity: Double,
    val tagCount: Int,
    val avgTagDist: Double,
    val obsType: PoseObservationType
) : StructSerializable {
    companion object {
        @JvmField
        val struct: Struct<PoseObservations> = object : Struct<PoseObservations> {
            override fun getTypeClass() : Class<PoseObservations> {
                return PoseObservations::class.java
            }

            override fun getTypeName(): kotlin.String {
                return "PoseObservations"
            }

            override fun getSize(): Int {
                return Rotation2d.struct.size
            }

            override fun getSchema(): kotlin.String {
                return "Pose3d pose;Double timestamp;Double ambiguity;Int tagCount;Double avgTagDist;PoseObservationType obsType"
            }

            override fun pack(buffer: ByteBuffer, value: PoseObservations) {
                buffer.putDouble(value.timestamp)
                buffer.putDouble(value.ambiguity)
                buffer.putDouble(value.avgTagDist)
                buffer.putInt(value.tagCount)
                Pose3d.struct.pack(buffer, value.pose)
            }

            override fun unpack(byteBuffer: ByteBuffer) : PoseObservations {
                val newTimeStamp = byteBuffer.getDouble()
                val newamgibuity = byteBuffer.getDouble()
                val newavgTagDist = byteBuffer.getDouble()
                val newtagCount = byteBuffer.getInt()
                val newpose = Pose3d.struct.unpack(byteBuffer)
                return PoseObservations(newpose, newTimeStamp, newamgibuity, newtagCount, newavgTagDist, PoseObservationType.PHOTONVISION)
            }
        }
    }
}

enum class PoseObservationType {
    MEGATAG1,
    MEGATAG2,
    PHOTONVISION
}

