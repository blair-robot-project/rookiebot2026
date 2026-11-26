package frc.team9449.Manip

import edu.wpi.first.math.geometry.Rotation2d
import edu.wpi.first.math.system.plant.DCMotor
import edu.wpi.first.math.system.plant.LinearSystemId
import edu.wpi.first.wpilibj.simulation.DCMotorSim

class ManipIOSim: ManipIOHardware() {

    //Temporary IDs Please change Later
    val rollerMotorSim: DCMotorSim =
        DCMotorSim(
            LinearSystemId.createDCMotorSystem(
                DCMotor.getNEO(1),
                0.0, //Change this later
                0.0, //Change this later
            ),
            DCMotor.getNEO(1),
    )
    override fun updateInputs(inputs: ManipIO.ManipIOInputs) {
        inputs.rollerVelo = rollerMotorSim.angularVelocityRadPerSec
        inputs.rollerVoltage = rollerMotorSim.inputVoltage


    }

}