package frc.team9449.arm

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import com.revrobotics.spark.config.SparkBaseConfig
import com.revrobotics.spark.config.SparkMaxConfig
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.util.Units
import edu.wpi.first.units.Units.Radians

open class ArmIOHardware(): ArmIO {


    val armMotorID = 6

    // temp motor ID please replace
    private val armMotor = SparkMax(armMotorID, SparkLowLevel.MotorType.kBrushless)
    init{
        armMotor.apply{armMotorConfig}
    }
    override fun updateInputs(inputs: ArmIO.ArmIOInputs) {
        val armMotorBusVolt = armMotor.busVoltage
        val armMotorDutyCycle = armMotor.appliedOutput

        inputs.armVelo = Units.rotationsPerMinuteToRadiansPerSecond(armMotor.encoder.velocity)
        inputs.armAngle = Rotation2d(armMotor.encoder.position * (Math.PI * 2))
        inputs.armVolt = armMotorBusVolt * armMotorDutyCycle
    }

    override fun setArmVolt(volt: Double) {
        setArmVolt(volt)
    }
    override fun setArmAngle(angle: Double) {
        setArmAngle(angle)
    }
    companion object {
        val armMotorConfig: SparkBaseConfig = SparkMaxConfig().idleMode(SparkBaseConfig.IdleMode.kBrake).smartCurrentLimit(40)
    }
}
