package frc.team9449.auto

import edu.wpi.first.math.controller.PIDController

class BLineRoutines {
    private val rotationController = PIDController(1.0, 0.0, 1.0)
    private val translationController = PIDController(2.0, 0.0, 0.0)
}