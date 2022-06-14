package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo;


import com.acmerobotics.dashboard.config.Config;

import java.util.List;
import java.util.Vector;


@Config
public class SingleServoMechanismConfig {
    public SingleServoMechanismConfig(String servoId,
                                      double maxServoAngle,
                                      double minServoAngle,
                                      double gearing,
                                      boolean isInverted
    ){
        this.servoId = servoId;
        this.maxServoAngle = maxServoAngle;
        this.minServoAngle = minServoAngle;
        this.gearing = gearing;
        this.isInverted = isInverted;

    }

    String servoId;
    double maxServoAngle;
    public double minServoAngle;
    public double gearing;
    Boolean isInverted;

}