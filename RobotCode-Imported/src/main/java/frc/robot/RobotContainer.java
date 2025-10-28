package frc.robot;
import frc.robot.core.LogitechControllerButtons;
import frc.robot.drivetrain.Drive;
import frc.robot.drivetrain.DriveIO;
import frc.robot.drivetrain.DriveIOMotors;
import frc.robot.shooter.Shooter;
import frc.robot.shooter.ShooterIOMotors;
import frc.robot.spindexer.Spin;
import frc.robot.spindexer.SpinIOMotors;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;
import edu.wpi.first.wpilibj2.command.button.POVButton;
import frc.robot.drivetrain.DriveIO;
import frc.robot.drivetrain.DriveIOMotors;
/**
 * The container for the robot. Contains subsystems, OI devices, and commands.
 */
public class RobotContainer {

	private static RobotContainer instance;


	private final Drive drivetrain;
	private final Shooter shooter;
	private final Spin spindexer;

	private final ShuffleboardTab ShuffleboardTab;

	/**
	 * The container for the robot. Contains subsystems, OI devices, and commands.
	 */
	public RobotContainer() {
		instance = this;

		drivetrain = new Drive(new DriveIOMotors());
		shooter = new Shooter(new ShooterIOMotors() );
		spindexer = new Spin(new SpinIOMotors());


		ShuffleboardTab = Shuffleboard.getTab("Tab 1");

		// Instantiate subsystems

		// Configure the button bindings
		configurePrimaryBindings();
		configureSecondaryBindings();
	}

	private void configurePrimaryBindings() {
		 Joystick joystick = new Joystick(0); 

		 JoystickButton triggerRight = new JoystickButton(joystick, LogitechControllerButtons.triggerRight);
		 JoystickButton triggerLeft = new JoystickButton(joystick, LogitechControllerButtons.triggerLeft);

		 JoystickButton Autton = new JoystickButton(joystick, LogitechControllerButtons.a);
		 JoystickButton BButton = new JoystickButton(joystick, LogitechControllerButtons.b);


		 triggerRight.whileTrue(shooter.setVoltageCommand(6)).whileFalse(shooter.setVoltageCommand(0));
		 triggerLeft.whileTrue(spindexer.setVoltageCommand(6)).whileFalse(spindexer.setVoltageCommand(0));
		 
		 
		 drivetrain.setVoltageCommand(joystick.getY() * 6, joystick.getThrottle() * 6);
	}
	private void configureSecondaryBindings() {}

	@SuppressWarnings("unused")
	public static ShuffleboardTab getShuffleboardTab() {
		return instance.ShuffleboardTab;
	}
}
