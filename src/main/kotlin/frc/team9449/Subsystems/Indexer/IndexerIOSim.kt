package frc.team9449.Subsystems.Indexer

import com.revrobotics.sim.SparkMaxSim
import com.revrobotics.spark.SparkLowLevel
import com.revrobotics.spark.SparkMax
import edu.wpi.first.math.system.plant.DCMotor
import edu.wpi.first.math.system.plant.LinearSystemId
import edu.wpi.first.wpilibj.simulation.DCMotorSim

class IndexerIOSim: IndexerIOHardware() {
    val indexMotorSimID = 1
    //Motor ID set to 1 temporarly
    val indexMotorSim = DCMotorSim(
        LinearSystemId.createDCMotorSystem(DCMotor.getNEO(1),
            1.0, // Change This later It is a random Number
            1.0// Change This later It is a random Number
        ),
        DCMotor.getNEO(1)
    )
}