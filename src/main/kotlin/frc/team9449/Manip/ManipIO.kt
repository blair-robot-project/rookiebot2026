package frc.team9449.Manip
import edu.wpi.first.math.geometry.Rotation2d
import org.littletonrobotics.junction.AutoLog

interface ManipIO {
    @AutoLog
    open class ManipIOInputs {
        @JvmField var rollerVoltage = 0.0

        @JvmField var rollerVelo = 0.0

        @JvmField var wristAngle = Rotation2d()
    }
    fun updateInputs(inputs: ManipIOInputs) {}
    fun setRollerVolt(voltage: Double)
    fun setWristAngle(angle: Double)
}
