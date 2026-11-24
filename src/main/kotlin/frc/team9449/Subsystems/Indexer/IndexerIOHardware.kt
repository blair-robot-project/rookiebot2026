package frc.team9449.Subsystems.Indexer

import au.grapplerobotics.LaserCan
import au.grapplerobotics.interfaces.LaserCanInterface
import com.revrobotics.spark.SparkBase
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.math.util.Units
import frc.team9449.IndexerIO
import kotlin.math.absoluteValue
import frc.team9449.Constants.IndexerConstants


open class IndexerIOHardware(): IndexerIO {

    private val indexerMotor = SparkMax(IndexerConstants.INDEXER_MOTOR_ID, SparkLowLevel.MotorType.kBrushless)
    val sideLaserCAN: LaserCanInterface= LaserCan(IndexerConstants.LASER_CAN_SIDE_ID)
    val bottomLaserCAN: LaserCanInterface= LaserCan(IndexerConstants.LASER_CAN_BOTTOM_ID)
    init{
        indexerMotor.apply {indexerMotorConfig}
            sideLaserCAN.setRegionOfInterest(LaserCanInterface.RegionOfInterest(IndexerConstants.LASER_CAN_SIDE_X,IndexerConstants.LASER_CAN_SIDE_Y,IndexerConstants.LASER_CAN_SIDE_W,IndexerConstants.LASER_CAN_SIDE_H))
            bottomLaserCAN.setRegionOfInterest(LaserCanInterface.RegionOfInterest(IndexerConstants.LASER_CAN_BOTTOM_X,IndexerConstants.LASER_CAN_BOTTOM_Y,IndexerConstants.LASER_CAN_BOTTOM_W,IndexerConstants.LASER_CAN_BOTTOM_H,))
            sideLaserCAN.setRangingMode(LaserCanInterface.RangingMode.SHORT)
            bottomLaserCAN.setRangingMode(LaserCanInterface.RangingMode.SHORT)
            sideLaserCAN.setTimingBudget(LaserCanInterface.TimingBudget.TIMING_BUDGET_20MS)
            bottomLaserCAN.setTimingBudget(LaserCanInterface.TimingBudget.TIMING_BUDGET_20MS)


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