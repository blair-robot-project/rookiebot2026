package frc.team9449.intake

import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.AutoLog
import org.littletonrobotics.junction.Logger

class IntakeIOSubsystem(private val io: IntakeIO) : SubsystemBase() {
    private val inputs: IntakeIOInputsAutoLogged = IntakeIOInputsAutoLogged()
    override fun periodic() { // Update stuff later
        io.updateInputs(inputs)
        Logger.processInputs("Intake Inputs", inputs)
    }
}
