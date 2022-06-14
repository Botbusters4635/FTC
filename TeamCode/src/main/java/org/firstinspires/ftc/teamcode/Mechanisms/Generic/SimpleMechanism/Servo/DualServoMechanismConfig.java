package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo;


import com.acmerobotics.dashboard.config.Config;

import java.util.List;
import java.util.Vector;


@Config
public class DualServoMechanismConfig {
    public DualServoMechanismConfig(String firstServoId,
                                      String secondServoId,
                                      double maxServoAngle,
                                      double minServoAngle,
                                      double gearing,
                                      boolean firstIsInverted,
                                      boolean secondIsInverted
    ){
        this.firstServoId = firstServoId;
        this.secondServoId = secondServoId;
        this.maxServoAngle = maxServoAngle;
        this.minServoAngle = minServoAngle;
        this.gearing = gearing;
        this.firstIsInverted = firstIsInverted;
        this.secondIsInverted = secondIsInverted;

    }

    String firstServoId;
    String secondServoId;
    double maxServoAngle;
    public double minServoAngle;
    public double gearing;
    Boolean firstIsInverted;
    Boolean secondIsInverted;



}