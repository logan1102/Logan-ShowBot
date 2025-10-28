package frc.robot.shooter;

import org.littletonrobotics.junction.AutoLog;
import org.littletonrobotics.junction.AutoLogOutput;
import org.littletonrobotics.junction.Logger;

import com.ctre.phoenix.motorcontrol.TalonSRXControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import com.ctre.phoenix.motorcontrol.can.TalonSRXConfiguration;
import com.ctre.phoenix6.controls.VoltageOut;

import edu.wpi.first.math.estimator.DifferentialDrivePoseEstimator;
import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
public class Shooter extends SubsystemBase{

    private final ShooterIO io;
    private double voltage;
	private double velocity;
    private VoltageOut voltageRequest;
    //private final ShooterIOInputsAutoLogged inputs = new ShooterIOInputsAutoLogged();
    public Shooter(ShooterIO io){
        this.io = io;
        voltageRequest = new VoltageOut(0);
    }

    public void periodic(){
        io.updateInputs(null);
        Logger.processInputs(null, null);
    }
    public void setVoltage(double volts){
		io.SetVoltage(volts);

	}
    public Command setVoltageCommand(double volts){
        return Commands.run(()->setVoltage(volts), this);

    }
    public double getVoltage(){
        return voltage;
    }

    public void setVelocity(double radPerSec, double FFVolts ){
        io.SetVelocity(radPerSec, FFVolts);
    }
    public double getVelocity(){
        return velocity;
    }
    
}
