package frc.robot.spindexer;

import frc.robot.drivetrain.DriveIO.DriveIOData;
import frc.robot.drivetrain.DriveIO.DriveIOInputs;

public interface SpinIO
{
	public SpinIOData data = new SpinIOData(0.0, 0.0, 0.0);

	record SpinIOData(double velocity, double appliedVolts, double currentAmps)
	{}

	default void updateInputs(SpinIOData inputs)
	{

	}

	public default void SetVoltage(double volts)
	{}



	default void stop()
	{};


}
