package frc.team9449.arm

import edu.wpi.first.wpilibj2.command.SubsystemBase

class ArmSubsystem(private val io: ArmIO) : SubsystemBase(){
    private val inputs: ArmIOInputsAutoLogged=ArmIOInputsAutoLogged()
    override fun periodic() {           // Update These Later
        io.updateInputs(inputs)
    }
}
