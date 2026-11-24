package frc.team9449
import frc.team9449.Subsystems.Indexer.IndexerIOHardware
import frc.team9449.Subsystems.Indexer.IndexerIOSim
import frc.team9449.Constants.Mode

object RobotContainer {
    val Indexer: IndexerIOSubsystem =
        IndexerIOSubsystem(
            when (Constants.CURRENT_MODE) {
                Mode.REAL -> IndexerIOHardware()
                Mode.SIM -> IndexerIOSim()
                Mode.REPLAY -> object : IndexerIO {}
            }
        )
}
