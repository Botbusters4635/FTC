package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor;

import com.acmerobotics.dashboard.config.Config;

@Config
public class SimpleMotorMechanismConfig {

    public SimpleMotorMechanismConfig(String motorId,
                                      double motorCPR,
                                      double motorRPMs,
                                      double gearing,
                                      boolean isInverted

    ){
        this.motorId = motorId;
        this.motorCPR = motorCPR;
        this.motorRPMs = motorRPMs;
        this.gearing = gearing;
        this.isInverted = isInverted;

    }

    String motorId;
    Double motorCPR;
    Double motorRPMs;
    Double gearing;
    Boolean isInverted;
}
