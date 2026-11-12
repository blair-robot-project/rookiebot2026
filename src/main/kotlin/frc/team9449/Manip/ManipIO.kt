package frc.team9449.Manip

import org.littletonrobotics.junction.AutoLog

interface ManipIO {
    @AutoLog
    open class ManipIOInputs {
        @JvmField var leftRollerVoltage = 0.0
        @JvmField var rightRollerVoltage = 0.0
    }
    fun updateInputs(inputs: ManipIOInputs) {}
    fun setRollerVolt(voltage: Double)

}