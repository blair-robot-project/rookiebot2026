package frc.team9449.intake

import edu.wpi.first.wpilibj2.command.SubsystemBase

class IntakeIOSubsystem(private val io: IntakeIO) : SubsystemBase() {
    private val inputs: IntakeIOInputsAutoLogged = IntakeIOInputsAutoLogged()
    override fun periodic() { // Update stuff later
        io.updateInputs(inputs)
    }
}
