package org.firstinspires.ftc.teamcode.Mechanisms.Arm;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;

@Config
public class ArmConfig {

    public ArmConfig(String armMotorId, double pulsesPerRevolution, double velocityErrorTolerance, double positionErrorTolerance, Motor.GoBILDA GobildaMotorType) {
        getArmMotorId = armMotorId;
        getGobildaType = GobildaMotorType;
        getPulsesPerRevolution = pulsesPerRevolution;
        getPositionErrorTolerance = positionErrorTolerance;
        getVelocityErrorTolerance = velocityErrorTolerance;
    }

    public static double p = 0.00;
    public static double i = 0.00;
    public static double d = 0.00;
    public static double f = 0.00;

    public static double kCos = 0.00;


    String getArmMotorId;

    Double getVelocityErrorTolerance;

    Double getPositionErrorTolerance;

    Double getPulsesPerRevolution;

    Motor.GoBILDA getGobildaType;

}
