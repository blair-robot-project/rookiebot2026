package frc.team9449.Subsystems.Indexer

import com.revrobotics.sim.SparkMaxSim
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.math.system.plant.DCMotor
import edu.wpi.first.math.system.plant.LinearSystemId
import edu.wpi.first.math.util.Units
import edu.wpi.first.wpilibj.simulation.DCMotorSim
import edu.wpi.first.wpilibj.simulation.FlywheelSim
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.Commands.runOnce
import frc.team9449.IndexerIO

class IndexerIOSim: IndexerIOHardware() {
    val indexerMotorSimID = 1
    //Motor ID set to 1 temporarly
    val indexerMotorSim = SparkMaxSim(SparkMax(indexerMotorSimID, SparkLowLevel.MotorType.kBrushless), DCMotor.getNEO(1))

    override fun updateInputs(inputs: IndexerIO.IndexerIOInputs) {
        val indexMotorVolt = indexerMotorSim.busVoltage
        val indexMotorDuty = indexerMotorSim.appliedOutput
        inputs.appliedVolts = indexMotorVolt * indexMotorDuty
        inputs.velocityRadPerSec  = Units.rotationsPerMinuteToRadiansPerSecond( indexerMotorSim.velocity)
        inputs.currentAmps = indexerMotorSim.motorCurrent
    }
    val FlywheelSim = FlywheelSim(LinearSystemId.createFlywheelSystem(
        DCMotor.getNEO(1),
        1.0,//Change Later Random Number
        1.0), //Random Number Change Later
        DCMotor.getNEO(1),
        1.0 //Random number Change Later
    )
    fun periodic(){
        FlywheelSim.setAngularVelocity(indexerMotorSim.velocity)
    }


}