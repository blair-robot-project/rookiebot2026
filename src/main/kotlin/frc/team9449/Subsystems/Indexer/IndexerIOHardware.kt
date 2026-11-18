package frc.team9449.Subsystems.Indexer

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.math.util.Units
import frc.team9449.IndexerIO
import kotlin.math.absoluteValue


open class IndexerIOHardware(): IndexerIO {
    val indexerMotorID = 1
    //indexerMotorID set to 1 temporarily
    private val indexerMotor = SparkMax(indexerMotorID, SparkLowLevel.MotorType.kBrushless)
    override fun updateInputs(inputs: IndexerIO.IndexerIOInputs) {
        val indexMotorVolt = indexerMotor.busVoltage
        val indexMotorDuty = indexerMotor.appliedOutput
        inputs.appliedVolts = indexMotorVolt * indexMotorDuty
        inputs.tempCelsius = indexerMotor.motorTemperature
        inputs.velocityRadPerSec  = Units.rotationsPerMinuteToRadiansPerSecond( indexerMotor.encoder.velocity)
        inputs.currentAmps = indexerMotor.outputCurrent

    }
}