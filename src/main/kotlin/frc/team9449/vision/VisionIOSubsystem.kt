package frc.team9449

import edu.wpi.first.math.Matrix
import edu.wpi.first.math.VecBuilder
import edu.wpi.first.math.geometry.Pose2d
import edu.wpi.first.math.numbers.N1
import edu.wpi.first.math.numbers.N3
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.Logger

class VisionIOSubsystem(
    private val io: VisionIO,
    private val addVisionMeasurementConsumer: (Pose2d, Double, Matrix<N3, N1>) -> Unit
) : SubsystemBase() {

    private val inputs = VisionIOInputs()


    private val stdDevs: Matrix<N3, N1> = VecBuilder.fill(0.5, 0.5, 9999999.0)

    override fun periodic() {
        io.updateInputs(inputs)
        Logger.processInputs("Vision", inputs)

        if (inputs.hasPose) {
            addVisionMeasurementConsumer(
                inputs.pose.toPose2d(),
                inputs.timestampSeconds,
                stdDevs
            )
        }
    }
}