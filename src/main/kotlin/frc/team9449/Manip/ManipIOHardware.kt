package frc.team9449.Manip

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.util.Units
import edu.wpi.first.units.measure.Voltage
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode
import com.revrobotics.spark.config.SparkMaxConfig
import com.revrobotics.PersistMode
import com.revrobotics.ResetMode

open class ManipIOHardware(): ManipIO{
    val rollerMotorID = 1
    //Temp motor ID please replace
    private val rollerMotor = SparkMax(rollerMotorID,SparkLowLevel.MotorType.kBrushless )
    override fun updateInputs(inputs: ManipIO.ManipIOInputs) {
        val manipMotorBusVolt = rollerMotor.busVoltage
        val manipMotorDutyCycle = rollerMotor.appliedOutput

        inputs.rollerVelo = Units.rotationsPerMinuteToRadiansPerSecond(rollerMotor.encoder.velocity)
        inputs.rollerVoltage= manipMotorBusVolt * manipMotorDutyCycle
    }

    companion object {
        val rollerConfig = SparkMaxConfig().apply {
            inverted(false)
            idleMode(IdleMode.kBrake)
            smartCurrentLimit(40)
        }
    }

    init {
        rollerMotor.configure(
            rollerConfig,
            ResetMode.kResetSafeParameters,
            PersistMode.kPersistParameters
        )
    }


    override fun setRollerVolt(voltage: Double) {
        setRollerVolt(voltage)
    }
}

// Hello
