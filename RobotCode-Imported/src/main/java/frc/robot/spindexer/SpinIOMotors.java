package frc.robot.spindexer;

import com.ctre.phoenix.motorcontrol.DemandType;
import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix6.configs.TalonFXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;
import com.ctre.phoenix6.hardware.TalonFX;

import edu.wpi.first.math.util.Units;
import frc.robot.constants.Ports;

import static frc.robot.util.PheonixUtil.tryUntilOKV5;

public class SpinIOMotors implements SpinIO
{

	public final TalonFX spinMotor;
	private VoltageOut voltageRequest;

	public SpinIOMotors()
	{
		var config = new TalonFXConfiguration();
		spinMotor = new TalonFX(Ports.SPINDEXER);
		voltageRequest =  new VoltageOut(0);
	}

	public void setVoltage(double volts)
	{
		spinMotor.setControl(voltageRequest.withOutput(volts));
	}

}
