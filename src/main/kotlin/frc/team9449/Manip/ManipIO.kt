package frc.team9449.Manip
import edu.wpi.first.math.geometry.Rotation2d
import org.littletonrobotics.junction.AutoLog

interface ManipIO {
    @AutoLog
    open class ManipIOInputs {
        @JvmField var rollerVoltage = 0.0

        @JvmField var rollerVelo = 0.0

    }
    fun updateInputs(inputs: ManipIOInputs) {}
    fun setRollerVolt(voltage: Double)
}
