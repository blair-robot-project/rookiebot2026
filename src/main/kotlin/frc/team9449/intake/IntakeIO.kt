package frc.team9449.intake

import org.littletonrobotics.junction.AutoLog

interface IntakeIO {
    @AutoLog
    open class IntakeIOInputs {

    }

    fun updateInputs(inputs: IntakeIOInputs) {}      // Will need to override
}
}
