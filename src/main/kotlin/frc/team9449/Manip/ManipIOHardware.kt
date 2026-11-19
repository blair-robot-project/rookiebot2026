package frc.team9449.Manip

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.util.Units

open class ManipIOHardware(): ManipIO{
    val rollerMotorID = 1
    val wristMotorID = 2
    //Temp motor ID please replace
    private val rollerMotor = SparkMax(rollerMotorID,SparkLowLevel.MotorType.kBrushless )
    private val wristMotor = SparkMax(wristMotorID, SparkLowLevel.MotorType.kBrushless)
    override fun updateInputs(inputs: ManipIO.ManipIOInputs) {
        val manipMotorBusVolt = rollerMotor.busVoltage
        val manipMotorDutyCycle = rollerMotor.appliedOutput

        inputs.rollerVelo = Units.rotationsPerMinuteToRadiansPerSecond(rollerMotor.encoder.velocity)
        inputs.wristAngle = Rotation2d(wristMotor.encoder.position*(Math.PI*2))
        inputs.rollerVoltage= manipMotorBusVolt * manipMotorDutyCycle
    }

    override fun setRollerVolt(voltage: Double) {
        TODO("Not yet implemented")
    }

    override fun setWristAngle(angle: Double) {
        TODO("Not yet implemented")
    }
}

// Hello
