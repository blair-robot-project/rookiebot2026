package frc.team9449.swerve

import edu.wpi.first.math.controller.PIDController
import edu.wpi.first.math.geometry.Translation2d
import edu.wpi.first.math.kinematics.ChassisSpeeds
import edu.wpi.first.math.kinematics.SwerveDriveKinematics
import edu.wpi.first.wpilibj2.command.Command
import edu.wpi.first.wpilibj2.command.SubsystemBase
import org.littletonrobotics.junction.Logger
import java.util.function.DoubleSupplier

// Make a DriveSubsystem class with four ModuleIO's as parameters.
class DriveSubsystem(
    private val ioFL: SwerveIO,
    private val ioFR: SwerveIO,
    private val ioBL: SwerveIO,
    private val ioBR: SwerveIO
) : SubsystemBase() {
    /**
     * Init Static
     * This just show Robot Information and Init System information.
     *
     * just a BG information:
     *
     * FL stand for Front Line
     *
     * FR stand for Fr. (Father) - a title of Catholic Priest
     *
     * BL stand for Blue Line - Boston's Metro Rail's one line information
     *
     * BR start for BackUp
     * */

    // When you initialize the class, make 4 ModuleIOInputsAutoLogged.
    private val inputsFL: SwerveIOInputsAutoLogged = SwerveIOInputsAutoLogged()
    private val inputsFR: SwerveIOInputsAutoLogged = SwerveIOInputsAutoLogged()
    private val inputsBL: SwerveIOInputsAutoLogged = SwerveIOInputsAutoLogged()
    private val inputsBR: SwerveIOInputsAutoLogged = SwerveIOInputsAutoLogged()

    // EDIT ME!
    val width = 2934.3
    val length = 1293.3

    // see location of each wheel from Center of the car.
    val locFL = Translation2d(width / 2, length / 2)
    val locFR = Translation2d(-width / 2, length / 2)
    val locBL = Translation2d(width / 2, -length / 2)
    val locBR = Translation2d(-width / 2, -length / 2)

    // enter kinematics
    val kinematics = SwerveDriveKinematics(
        locFL,
        locFR,
        locBL,
        locBR
    )

    // about PIDs.
    val setKP = 0.1
    val setKI = 0.1
    val setKD = 0.1

    val drivePIDFL: PIDController = PIDController(setKP, setKI, setKD)
    val drivePIDFR: PIDController = PIDController(setKP, setKI, setKD)
    val drivePIDBL: PIDController = PIDController(setKP, setKI, setKD)
    val drivePIDBR: PIDController = PIDController(setKP, setKI, setKD)
    val turnPIDFL: PIDController = PIDController(setKP, setKI, setKD)
    val turnPIDFR: PIDController = PIDController(setKP, setKI, setKD)
    val turnPIDBL: PIDController = PIDController(setKP, setKI, setKD)
    val turnPIDBR: PIDController = PIDController(setKP, setKI, setKD)

    /**
     * Init
     *
     * this init program make sure that it work exactly on the way we want, not try think -180 and 180 is different thingy
     * also ask IO to update inputs.
     * */

    init {
        turnPIDFL.enableContinuousInput(-Math.PI, Math.PI)
        turnPIDFL.setTolerance(0.1)
        turnPIDFR.enableContinuousInput(-Math.PI, Math.PI)
        turnPIDFR.setTolerance(0.1)
        turnPIDBL.enableContinuousInput(-Math.PI, Math.PI)
        turnPIDBL.setTolerance(0.1)
        turnPIDBR.enableContinuousInput(-Math.PI, Math.PI)
        turnPIDBR.setTolerance(0.1)
    }

    override fun periodic() {
        ioFL.updateInputs(inputsFL)
        ioFR.updateInputs(inputsFR)
        ioBL.updateInputs(inputsBL)
        ioBR.updateInputs(inputsBR)

        Logger.processInputs("SwerveFL", inputsFL)
        Logger.processInputs("SwerveFR", inputsFR)
        Logger.processInputs("SwerveBL", inputsBL)
        Logger.processInputs("SwerveBR", inputsBR)

        if (turnPIDFL.atSetpoint()) {
            ioFL.updateAppliedVoltageTurn(0.0)
        } else {
            turnPIDFL.calculate(inputsFL.angleTurn.radians)
        }

        if (drivePIDFL.atSetpoint()) {
            ioFL.updateAppliedVoltageTurn(0.0)
        } else {
            drivePIDFL.calculate(inputsFL.velocityDrive)
        }

        if (turnPIDFR.atSetpoint()) {
            ioFR.updateAppliedVoltageTurn(0.0)
        } else {
            turnPIDFR.calculate(inputsFR.angleTurn.radians)
        }

        if (drivePIDFR.atSetpoint()) {
            ioFR.updateAppliedVoltageTurn(0.0)
        } else {
            drivePIDFR.calculate(inputsFR.velocityDrive)
        }

        if (turnPIDBL.atSetpoint()) {
            ioBL.updateAppliedVoltageTurn(0.0)
        } else {
            turnPIDBL.calculate(inputsBL.angleTurn.radians)
        }

        if (drivePIDBL.atSetpoint()) {
            ioBL.updateAppliedVoltageTurn(0.0)
        } else {
            drivePIDBL.calculate(inputsBL.velocityDrive)
        }

        if (turnPIDBR.atSetpoint()) {
            ioBR.updateAppliedVoltageTurn(0.0)
        } else {
            turnPIDBR.calculate(inputsBR.angleTurn.radians)
        }

        if (drivePIDBR.atSetpoint()) {
            ioBR.updateAppliedVoltageTurn(0.0)
        } else {
            drivePIDBR.calculate(inputsBR.velocityDrive)
        }
    }

    /**
     * Command
     * */

    // Make a command to set the speeds of the modules with parameters for the forward speed, sideways speed, and rotation speed, all as DoubleSuppliers.
    fun updateSystem(
        forward: DoubleSupplier,
        sideways: DoubleSupplier,
        rotation: DoubleSupplier
    ): Command =
        runOnce {
            val driveForward = forward.asDouble
            val driveSideways = sideways.asDouble
            val driveRotation = rotation.asDouble

            // Use the kinematics calculator to convert this to module states.
            val speedInformation = ChassisSpeeds(
                driveForward,
                driveSideways,
                driveRotation
            )

            val moduleStates = kinematics.toSwerveModuleStates(speedInformation)

            // apply states
            val velocityFL = moduleStates[0].speedMetersPerSecond
            val angleFL = moduleStates[0].angle
            val velocityFR = moduleStates[1].speedMetersPerSecond
            val angleFR = moduleStates[1].angle
            val velocityBL = moduleStates[2].speedMetersPerSecond
            val angleBL = moduleStates[2].angle
            val velocityBR = moduleStates[3].speedMetersPerSecond
            val angleBR = moduleStates[3].angle

            // Use the output from the PID controllers to call the IO methods for each module to set the turn and drive motor voltages.
            drivePIDFL.setSetpoint(velocityFL)
            drivePIDFR.setSetpoint(velocityFR)
            drivePIDBL.setSetpoint(velocityBL)
            drivePIDBR.setSetpoint(velocityBR)

            turnPIDFL.setSetpoint(angleFL.radians)
            turnPIDFR.setSetpoint(angleFR.radians)
            turnPIDBL.setSetpoint(angleBL.radians)
            turnPIDBR.setSetpoint(angleBR.radians)
        }
}
