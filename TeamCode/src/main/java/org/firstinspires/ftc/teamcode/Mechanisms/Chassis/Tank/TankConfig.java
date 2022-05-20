package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public class TankConfig {

    public TankConfig(String frontLeftId, String backLeftId, String frontRightId, String backRightId, Motor.GoBILDA GobildaMotorType) {

        getfrontLeftId = frontLeftId;
        getfrontRightId = frontRightId;
        getbackLeftId = backLeftId;
        getbackRightId = backRightId;

        getGobildaType = GobildaMotorType;
    }

    public String getfrontLeftId;
    public String getfrontRightId;
    public String getbackLeftId;
    public String getbackRightId;
    public static Motor.GoBILDA getGobildaType;

    public int positionTolerance = 0;
    public int velocityTolerance = 0;

    public int p = 0;
    public int i = 0;
    public int d = 0;
    public int f = 0;

}

