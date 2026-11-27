package frc.team9449.Manip

import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.AutoLog
import org.littletonrobotics.junction.Logger

class ManipSubsystem(private val io: ManipIO) : SubsystemBase(){
    private val inputs: ManipIOInputsAutoLogged = ManipIOInputsAutoLogged()

    override fun periodic(){
        io.updateInputs(inputs)
        Logger.processInputs("Manip Inputs",inputs)
    }

}
