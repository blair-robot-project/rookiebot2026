package frc.team9449.intake

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax

class IntakeIOHardware(ROLLER_ID: Int) : IntakeIO {
    override fun updateInputs(inputs: IntakeIO.IntakeIOInputs) {
        // Add inputs later
    }
    val rollerMotor = SparkMax(ROLLER_ID, SparkLowLevel.MotorType.kBrushless)
    companion object {}
}
