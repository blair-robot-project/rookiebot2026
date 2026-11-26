package frc.team9449.Manip

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.util.Units
import edu.wpi.first.units.measure.Voltage

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

    override fun setRollerVolt(voltage: Double) {
        setRollerVolt(voltage)
    }
}

// Hello
