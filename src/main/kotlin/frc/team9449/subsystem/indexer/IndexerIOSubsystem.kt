package frc.team9449
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.Logger
class IndexerIOSubsystem(private val io: IndexerIO) : SubsystemBase() {

    private val inputs = IndexerIOInputsAutoLogged()

    override fun periodic() {
        io.updateInputs(inputs)
        Logger.processInputs("Indexer", inputs)
    }

    fun setVoltage(volts: Double) {
        io.setVoltage(volts)
    }

    fun stop() {
        io.setVoltage(0.0)
    }

    val beamBreakTripped: Boolean
        get() = inputs.beamBreakTripped
}
