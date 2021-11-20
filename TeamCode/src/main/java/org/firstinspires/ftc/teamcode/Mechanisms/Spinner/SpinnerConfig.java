/
// Created by Diego Villanueva 11/11/21
package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public class SpinnerConfig {

    public SpinnerConfig(String spinnerMotorId, Motor.GoBILDA GobildaMotorType) {
        getSpinnerMotorId = spinnerMotorId;
        getGobildaType = GobildaMotorType;
    }

    String getSpinnerMotorId;
    Motor.GoBILDA getGobildaType;

}