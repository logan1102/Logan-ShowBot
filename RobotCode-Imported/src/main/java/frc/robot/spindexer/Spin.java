package frc.robot.spindexer;

import java.security.cert.CertPathValidatorException.Reason;

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

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix6.controls.VelocityVoltage;
import com.ctre.phoenix6.controls.VoltageOut;

public class Spin extends SubsystemBase
{
	private double voltage;
	private double velocity;
	private VoltageOut voltageRequest;
	private SpinIO io;
	private SpinIOMotors motor;

	public Spin(SpinIO io)
	{
		this.io = io;
		voltageRequest = new VoltageOut(0);
		motor = new SpinIOMotors();
	}

	public void periodic()
	{
		io.updateInputs(null);
		Logger.processInputs(null, null);
	}

	public void setVoltage(double volts)
	{
		motor.spinMotor.setControl(voltageRequest.withOutput(volts));
	}
	public Command setVoltageCommand(double volts){
		return Commands.run(() -> setVoltage(volts), this);
	}

	public void brake()
	{
		io.SetVoltage(0);
	}
	public double getVoltage(){
		return velocity;
	}
	
}
