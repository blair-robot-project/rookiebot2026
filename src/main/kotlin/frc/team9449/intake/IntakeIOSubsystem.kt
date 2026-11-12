package frc.team9449.intake

class IntakeSubsystem(private val io:IntakeIO): SubsystemBase() {
    private val inputs: IntakeIOInputsAutologged = IntakeIOInputsAutologged()
    override fun periodic() {             // Update stuff later
        io.updateInputs(inputs)
    }
}
