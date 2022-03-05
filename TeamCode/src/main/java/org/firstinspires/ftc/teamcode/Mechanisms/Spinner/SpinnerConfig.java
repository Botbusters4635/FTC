package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public class SpinnerConfig {

    public SpinnerConfig(String spinnerMotorId, Motor.GoBILDA gobildaMotorType) {
        getSpinnerMotorId = spinnerMotorId;
        getGobildaType = gobildaMotorType;
    }

    String getSpinnerMotorId;
    Motor.GoBILDA getGobildaType;

}