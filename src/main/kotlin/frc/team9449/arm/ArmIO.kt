package frc.team9449.arm

import org.littletonrobotics.junction.AutoLog
import edu.wpi.first.math.geometry.Rotation2d

interface ArmIO {
    @AutoLog
    open class ArmIOInputs {

        @JvmField var armVelo: Double = 0.0

        @JvmField var armVolt: Double = 0.0

        @JvmField var armAngle: Rotation2d = Rotation2d()
    }
    fun updateInputs(input: ArmIOInputs)
    fun setArmVolt(armVolt: Double)
    fun setArmAngle(armAngle: Double)
}
