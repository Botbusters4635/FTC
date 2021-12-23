package org.firstinspires.ftc.teamcode.Mechanisms.Arm;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.Motor;

@Config
public class ArmConfig {

    public ArmConfig(String armMotorId, double motorTicks, double velocityErrorTolerance, double positionErrorTolerance, Motor.GoBILDA GobildaMotorType) {
        getArmMotorId = armMotorId;
        getGobildaType = GobildaMotorType;
        getMotorTicks = motorTicks;
        getPositionErrorTolerance = positionErrorTolerance;
        getVelocityErrorTolerance = velocityErrorTolerance;
    }

    public static double p = 0.00;
    public static double i = 0.00;
    public static double d = 0.00;
    public static double f = 0.00;


    String getArmMotorId;

    Double getVelocityErrorTolerance;

    Double getPositionErrorTolerance;

    Double getMotorTicks;

    Motor.GoBILDA getGobildaType;

}
