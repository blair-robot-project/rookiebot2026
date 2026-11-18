package frc.team9449
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.Logger
import org.littletonrobotics.junction.AutoLog
class IndexerIOSubsystem(private val io: IndexerIO) : SubsystemBase() {
    private val inputs: IndexerIOInputsAutoLogged = IndexerIOInputsAutoLogged()

    override fun periodic() {
        io.updateInputs(inputs)
        Logger.processInputs("Indexer Inputs", inputs)
    }

     fun setVoltage(volts: Double) {
        io.setVoltage(volts)
    }

    fun stop(): Command =
        runOnce{
        io.setVoltage(0.0)
    }

    val beamBreakTripped: Boolean
        get() = inputs.beamBreakTripped
}
