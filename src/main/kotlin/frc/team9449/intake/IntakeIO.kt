package frc.team9449.intake

import org.littletonrobotics.junction.AutoLog

interface IntakeIO {
    @AutoLog
    open class IntakeIOInputs {
        @JvmField var rollerVelo: Double = 0.0

        @JvmField var rollerVolt: Double = 0.0
        @JvmField var pivotVelo: Double = 0.0

        @JvmField var pivotVolt: Double = 0.0
    }

    fun updateInputs(inputs: IntakeIOInputs) {} // Will need to override
    fun setPivotPos(pos: Double) {}
    fun setRollerVolt(volt: Double) {}
}
