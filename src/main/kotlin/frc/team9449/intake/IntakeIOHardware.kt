package frc.team9449.intake

import com.revrobotics.RelativeEncoder
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.units.Units


class IntakeIOHardware(ROLLER_ID: Int, PIVOT_ID1: Int, PIVOT_ID2: Int) : IntakeIO {
    override fun updateInputs(inputs: IntakeIO.IntakeIOInputs) {
        inputs.rollerVelo = rollerEncoder.velocity
        inputs.rollerVolt = rollerMotor.busVoltage
        inputs.pivotVelo = pivotEncoder1.velocity
        inputs.pivotVolt = pivotMotor1.busVoltage
    }
    val rollerMotor = SparkMax(ROLLER_ID, SparkLowLevel.MotorType.kBrushless)
    var rollerEncoder: RelativeEncoder = rollerMotor.getEncoder()
    val pivotMotor1 = SparkMax(PIVOT_ID1, SparkLowLevel.MotorType.kBrushless)
    var pivotEncoder1: RelativeEncoder = pivotMotor1.getEncoder()
    val pivotMotor2 = SparkMax(PIVOT_ID2, SparkLowLevel.MotorType.kBrushless)
    var pivotEncoder2: RelativeEncoder = pivotMotor2.getEncoder()

    override fun setPivotPos(pos: Double) {
        
    }
    override fun setRollerVolt(volt: Double) {
        rollerMotor.setVoltage(volt)
    }
    companion object {}
}
