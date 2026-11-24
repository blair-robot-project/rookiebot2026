package frc.team9449
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands
import edu.wpi.first.wpilibj2.command.SubsystemBase
import edu.wpi.first.wpilibj2.command.WaitCommand
import edu.wpi.first.wpilibj2.command.WaitUntilCommand
import org.littletonrobotics.junction.Logger
import org.littletonrobotics.junction.AutoLog
import java.util.function.BooleanSupplier
import java.util.function.Predicate.not

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
    fun indexNormal(BeamBreakTripped: BooleanSupplier): Command=
        Commands.sequence(
            runOnce{io.setVoltage(6.0)},
            TODO("Implement LaserCANs First Before BeamBreak Detection"),
            runOnce{io.setVoltage(0.0)}
        )

    fun indexReverse(BeamBreakTripped: BooleanSupplier): Command=
        Commands.sequence(
            runOnce{io.setVoltage(-6.0)},
            TODO("Implement LaserCANs First Before BeamBreak Detection"),
            runOnce{io.setVoltage(0.0)}
        )

}
