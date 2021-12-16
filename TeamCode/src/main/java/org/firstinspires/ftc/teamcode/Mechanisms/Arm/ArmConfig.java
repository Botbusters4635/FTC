package org.firstinspires.ftc.teamcode.Mechanisms.Arm;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public class ArmConfig {

    public ArmConfig(String armMotorId, double motorTicks, double errorTolerance, Motor.GoBILDA GobildaMotorType) {
        getArmMotorId = armMotorId;
        getGobildaType = GobildaMotorType;
        getMotorTicks = motorTicks;
        getErrorTolerance = errorTolerance;
    }

    double p = 0.05;
    double i = 0.00;
    double d = 0.00;
    double f = 0.00;


    String getArmMotorId;

    Double getErrorTolerance;

    Double getMotorTicks;

    Motor.GoBILDA getGobildaType;

}
