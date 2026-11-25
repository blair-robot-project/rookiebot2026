package frc.team9449.arm

import com.revrobotics.sim.SparkMaxSim
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.system.plant.DCMotor
import edu.wpi.first.math.util.Units

class ArmIOSim: ArmIOHardware() {
    val armMotor = 6

    //temp motor ID please fix
    val armMotorSim = SparkMaxSim(SparkMax(armMotorID, SparkLowLevel.MotorType.kBrushless), DCMotor.getNEO(1))
    override fun updateInputs(inputs: ArmIO.ArmIOInputs) {
        val armMotorBusVolt = armMotorSim.busVoltage
        val armMotorDutyCycle = armMotorSim.appliedOutput

        inputs.armVelo = Units.rotationsPerMinuteToRadiansPerSecond(armMotorSim.velocity)
        inputs.armAngle = Rotation2d(armMotorSim.position * (Math.PI * 2))
        inputs.armVolt = armMotorBusVolt * armMotorDutyCycle
    }
}

