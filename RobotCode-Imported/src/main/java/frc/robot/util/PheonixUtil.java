package frc.robot.util;

import java.io.ObjectInputFilter.Status;
import java.util.function.Supplier;

import com.ctre.phoenix.ErrorCode;
import com.ctre.phoenix6.StatusCode;

public class PheonixUtil {
    
    public static void tryUntilOK(int maxAttempts, Supplier<StatusCode> command){
        for(int i = 0; i< maxAttempts; i++ ){
            var error = command.get();
            if(error.isOK()) break;
        }
    }

    public static void tryUntilOKV5(int maxAttempts, Supplier<ErrorCode> command){
        for(int i = 0; i< maxAttempts; i++ ){
            var error = command.get();
            if(error.equals(ErrorCode.OK)) break;
        }
    } 
}
