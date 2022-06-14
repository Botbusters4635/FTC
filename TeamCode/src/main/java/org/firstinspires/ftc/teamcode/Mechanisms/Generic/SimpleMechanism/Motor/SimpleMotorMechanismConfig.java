package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor;

import com.acmerobotics.dashboard.config.Config;

@Config
public class SimpleMotorMechanismConfig {

    public SimpleMotorMechanismConfig(String motorId,
                                      double motorCPR,
                                      double motorRPMs,
                                      boolean isInverted

    ){
        getMotorId = motorId;
        getMotorCPR = motorCPR;
        getMotorRPMs = motorRPMs;
        getIsInverted = isInverted;

    }

    String getMotorId;
    double getMotorCPR;
    double getMotorRPMs;
    boolean getIsInverted;
}