package frc.team9449.intake

import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax

class IntakeIOHardware(ROLLER_ID: Int, PIVOT_ID1: Int, PIVOT_ID2: Int) : IntakeIO {
    override fun updateInputs(inputs: IntakeIO.IntakeIOInputs) {
        // Add inputs later
    }
    val rollerMotor = SparkMax(ROLLER_ID, SparkLowLevel.MotorType.kBrushless)
    val pivotMotor1 = SparkMax(PIVOT_ID1, SparkLowLevel.MotorType.kBrushless)
    val pivotMotor2 = SparkMax(PIVOT_ID2, SparkLowLevel.MotorType.kBrushless)
    companion object {}
}
