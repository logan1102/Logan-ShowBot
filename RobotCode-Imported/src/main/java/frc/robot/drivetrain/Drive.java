package frc.robot.drivetrain;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.AutoLogOutput;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Twist2d;
import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.math.kinematics.DifferentialDriveKinematics;
import edu.wpi.first.math.kinematics.DifferentialDriveWheelSpeeds;
import edu.wpi.first.units.measure.Velocity;
import edu.wpi.first.wpilibj.DriverStation;
import edu.wpi.first.wpilibj.DriverStation.Alliance;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import edu.wpi.first.wpilibj2.command.sysid.SysIdRoutine;
import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix6.controls.VelocityVoltage;

public class Drive extends SubsystemBase
{
	private double voltage;
	private double velocity;

	private final DifferentialDriveKinematics kinematics = new DifferentialDriveKinematics(0);
	private final DifferentialDrivePoseEstimator poseEstimator = new DifferentialDrivePoseEstimator(
			kinematics, new Rotation2d(), 0.0, 0.0, new Pose2d());
	private final SysIdRoutine sysId;
	private DriveIO io;

	private double lastLeftPositionMeters = 0.0;
	private double lastRightPositionMeters = 0.0;

	private VelocityVoltage velocityVoltage;

	public Drive(DriveIO io)
	{
		this.io = io;
		sysId = new SysIdRoutine(null, null);
	}

	public void periodic()
	{
		io.updateInputs(null);
		Logger.processInputs(null, null);
	}

	public Pose2d getPose()
	{
		return poseEstimator.getEstimatedPosition();
	}
	public Command setVoltageCommand(double leftVolts, double rightVolts){
		return Commands.run(()->setVoltage(leftVolts,rightVolts), this);
	}
	public void setVoltage(double leftVolts, double rightVolts)
	{
		io.SetVoltage(leftVolts, rightVolts);
	}

	public double getVoltage(){
		return voltage;
	}


	public void brake()
	{
		io.SetVoltage(0, 0);
	}

}