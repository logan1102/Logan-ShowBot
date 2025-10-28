package frc.robot.drivetrain;

public interface DriveIO
{
	class DriveIOInputs
	{

		public DriveIOData data = new DriveIOData(0.0, 0.0, 0.0, 0.0, 0.0, 0.0);

	}

	record DriveIOData(double leftVelocity, double leftAppliedVolts, double leftCurrentAmps,

			double rightVelocity, double rightAppliedVolts, double rightCurrentAmps)
	{}

	default void updateInputs(DriveIOInputs inputs)
	{

	}

	public default void SetVoltage(double leftVolts, double rightVolts)
	{}

	public default void SetVelocity(double leftRadPerSec, double rightRadPerSec, double leftFFVolts,
			double rightFFVolts)
	{}

	default void stop()
	{};

}
