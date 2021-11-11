package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;

public class SpinnerConfiguration {

    public SpinnerConfiguration(String spinnerMotorId, Motor.GoBILDA motorType) {
        getSpinnerMotorId = spinnerMotorId;
        getSpinnerMotorType = motorType;
    }

    String getSpinnerMotorId;
    Motor.GoBILDA getSpinnerMotorType;
}
