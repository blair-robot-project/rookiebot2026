package frc.team9449.Subsystems.Indexer

import com.revrobotics.spark.SparkBase
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.math.util.Units
import frc.team9449.IndexerIO
import kotlin.math.absoluteValue


open class IndexerIOHardware(): IndexerIO {
    val indexerMotorID = 9
    private val indexerMotor = SparkMax(indexerMotorID, SparkLowLevel.MotorType.kBrushless)

    init{
        indexerMotor.apply {indexerMotorConfig}
    }
    //indexerMotorID set to 9 temporarily
    override fun updateInputs(inputs: IndexerIO.IndexerIOInputs) {
        val indexMotorVolt = indexerMotor.busVoltage
        val indexMotorDuty = indexerMotor.appliedOutput
        inputs.appliedVolts = indexMotorVolt * indexMotorDuty
        inputs.tempCelsius = indexerMotor.motorTemperature
        inputs.velocityRadPerSec  = Units.rotationsPerMinuteToRadiansPerSecond( indexerMotor.encoder.velocity)
        inputs.currentAmps = indexerMotor.outputCurrent

    }
    companion object{
        val indexerMotorConfig: SparkBaseConfig = SparkMaxConfig()
            .idleMode(SparkBaseConfig.IdleMode.kCoast)
            .smartCurrentLimit(40)

    }
}