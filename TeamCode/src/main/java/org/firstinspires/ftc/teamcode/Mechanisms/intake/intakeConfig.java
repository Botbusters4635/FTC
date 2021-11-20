/
// Created by Diego Villanueva 11/11/21
//
package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public class IntakeConfig {

    public IntakeConfig(String IntakeMotorId, Motor.GoBILDA GobildaMotorType) {
        getIntakeMotorId = IntakeMotorId;
        getGobildaType = GobildaMotorType;
    }

    String getIntakeMotorId;
    Motor.GoBILDA getGobildaType;

}

