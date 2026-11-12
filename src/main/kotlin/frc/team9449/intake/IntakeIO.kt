package frc.team9449.intake

interface IntakeIO {
    @AutoLog
    open class IntakeIOInputs {

    }

    fun updateInputs(inputs: IntakeIOInputs) {}      // Will need to override

}
}
