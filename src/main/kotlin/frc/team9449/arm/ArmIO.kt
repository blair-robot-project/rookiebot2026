package frc.team9449.arm

import org.littletonrobotics.junction.AutoLog

interface ArmIO {
    @AutoLog
    open class ArmIOInputs {
        @JvmField var armVelo: Double = 0.0

        @JvmField var armVolt: Double = 0.0

        @JvmField var armAngle: Double = 0.0
    }
    fun updateInputs(input: ArmIOInputs)
    fun setArmVolt(armVolt: Double)
    fun setArmAngle(armAngle: Double)
}
