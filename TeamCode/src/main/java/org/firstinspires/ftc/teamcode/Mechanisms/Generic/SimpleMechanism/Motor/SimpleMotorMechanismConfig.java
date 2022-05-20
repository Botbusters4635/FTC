package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor;

import com.acmerobotics.dashboard.config.Config;

@Config
public class SimpleMotorMechanismConfig {

    public SimpleMotorMechanismConfig(String motorId,
                                      double motorCPR,
                                      double motorRPMs,
                                      boolean isInverted

    ){
        this.motorId = motorId;
        this.motorCPR = motorCPR;
        this.motorRPMs = motorRPMs;
        this.isInverted = isInverted;

    }

    String motorId;
    Double motorCPR;
    Double motorRPMs;
    Boolean isInverted;
}
