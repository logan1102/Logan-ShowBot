package frc.robot.shooter;

import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix6.configs.CurrentLimitsConfigs;
import com.revrobotics.jni.CANSparkJNI;
import com.revrobotics.spark.ClosedLoopSlot;
import com.revrobotics.spark.SparkClosedLoopController;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.ControlType;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import frc.robot.constants.Ports;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj.motorcontrol.Spark;
import frc.robot.constants.Ports;
import static frc.robot.util.PheonixUtil.tryUntilOKV5;
import com.revrobotics.spark.SparkClosedLoopController;
public class ShooterIOMotors implements ShooterIO{
	private final SparkMax leftShooter;
	private final SparkMax rightShooter;
	private final	SparkClosedLoopController controller;


	public ShooterIOMotors(){
		var config = new SparkMaxConfig();
		leftShooter = new SparkMax(Ports.SHOOTER_MOTOR_LEFT, MotorType.kBrushless);
		rightShooter = new SparkMax(Ports.SHOOTER_MOTOR_RIGHT,MotorType.kBrushless);
		 
		controller = leftShooter.getClosedLoopController();
		config.follow(leftShooter);


		config.idleMode(IdleMode.kBrake).smartCurrentLimit(ShooterConstants.currentLimit).voltageCompensation(12.0);

		
	}
	
	public void setVelocity(double radPerSec, double FFVolts){
		controller.setReference(radPerSec, ControlType.kVelocity, ClosedLoopSlot.kSlot0, FFVolts);
	}
}
