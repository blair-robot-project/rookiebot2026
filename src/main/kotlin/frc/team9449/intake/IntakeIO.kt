package frc.team9449.intake

import org.littletonrobotics.junction.AutoLog

interface IntakeIO {
    @AutoLog
    open class IntakeIOInputs {
        @JvmField var motorVelo: Double = 0.0

        @JvmField var motorVolt: Double = 0.0
    }

    fun updateInputs(inputs: IntakeIOInputs) {} // Will need to override
}
