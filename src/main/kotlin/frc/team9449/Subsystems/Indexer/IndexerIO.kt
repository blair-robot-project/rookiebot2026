package frc.team9449

import org.littletonrobotics.junction.AutoLog

interface IndexerIO {
    @AutoLog
    open class IndexerIOInputs {
        @JvmField var positionRad: Double = 0.0

        @JvmField var velocityRadPerSec: Double = 0.0

        @JvmField var appliedVolts: Double = 0.0

        @JvmField var currentAmps: Double = 0.0

        @JvmField var tempCelsius: Double = 0.0

        @JvmField var beamBreakTripped: Boolean = false

        @JvmField var LaserCANConnected: Boolean = false
    }
    fun updateInputs(inputs: IndexerIOInputs) {}
    fun setVoltage(volts: Double) {}
}
