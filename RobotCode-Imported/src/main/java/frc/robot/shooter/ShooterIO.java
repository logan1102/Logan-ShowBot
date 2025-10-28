package frc.robot.shooter;

public interface ShooterIO {


    class ShooterIOInputs{

        public ShooterIOData data = new ShooterIOData(0.0,0.0,0.0);
    }
	
    record ShooterIOData(
        double velocity,double appliedvolts,double currentAmps){}

    
    default void updateInputs(ShooterIOInputs inputs){

    }
    public default void SetVoltage(double volts){}
    
    public default void SetVelocity(double radPerSec,double FFVolts){}

}
