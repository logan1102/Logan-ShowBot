package frc.robot.drivetrain;

import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix6.configs.Slot0Configs;

import static frc.robot.util.PheonixUtil.tryUntilOKV5;

import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.motorcontrol.Talon;
import frc.robot.constants.Ports;
import frc.robot.drivetrain.DriveConstants;
public class DriveIOMotors implements DriveIO
{
	private final TalonSRX leftLeader;
	private final TalonSRX leftFollower;
	private final TalonSRX rightLeader;
	private final TalonSRX rightFollower;

	private TalonSRXConfiguration config = new TalonSRXConfiguration();

	public DriveIOMotors()
	{
		var config = new TalonSRXConfiguration();
		leftLeader = new TalonSRX(Ports.LEFT_DRIVE_LEADER);
		leftFollower = new TalonSRX(Ports.LEFT_DRIVE_FOLLOWER);
		rightLeader = new TalonSRX(Ports.RIGHT_DRIVE_LEADER);
		rightFollower = new TalonSRX(Ports.RIGHT_DRIVE_FOLLOWER);

		leftFollower.follow(leftLeader);
		rightFollower.follow(rightLeader);

		leftFollower.setInverted(true);
		rightFollower.setInverted(true);

		config.peakCurrentLimit = DriveConstants.currentLimit;
		config.continuousCurrentLimit = DriveConstants.currentLimit - 15;
		config.peakCurrentDuration = 0;
		config.voltageCompSaturation = 12;

		tryUntilOKV5(5, () -> leftLeader.configAllSettings(config));
		tryUntilOKV5(5, () -> leftFollower.configAllSettings(config));
		tryUntilOKV5(5, () -> rightLeader.configAllSettings(config));
		tryUntilOKV5(5, () -> rightLeader.configAllSettings(config));
	}

	public void setVoltage(double leftVolts, double rightVolts)
	{
		leftLeader.set(TalonSRXControlMode.PercentOutput, leftVolts / 12);
		rightLeader.set(TalonSRXControlMode.PercentOutput, rightVolts / 12);
	}

	public void SetVelocity(double leftRadPerSec, double rightRadPerSec, double leftVolts,
			double rightVolts)
	{
		leftLeader.set(TalonSRXControlMode.Velocity, Units.radiansToRotations(leftRadPerSec) / 10,
				DemandType.ArbitraryFeedForward, leftVolts / 12);
		rightLeader.set(TalonSRXControlMode.Velocity, Units.radiansToRotations(rightRadPerSec) / 10,
				DemandType.ArbitraryFeedForward, rightVolts / 12);
	}

	public double getLeftVelocity()
	{
		return leftLeader.getActiveTrajectoryVelocity();
	}

	public double getRightVelocity()
	{
		return rightLeader.getActiveTrajectoryVelocity();
	}

}
