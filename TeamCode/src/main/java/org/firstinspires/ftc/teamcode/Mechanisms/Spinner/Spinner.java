package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Spinner extends EctoMechanism {

    public Spinner(String moduleName, String moduleType, SpinnerConfiguration config) {
        super(moduleName, moduleType);
        spinnerConfig = config;
    }

    SpinnerConfiguration spinnerConfig;
    Motor spinnerMotor;

    public void turnOnMotor() {
        spinnerMotor.set(1);
    }

    @Override
    public void initMechanism() {
        spinnerMotor.setRunMode(Motor.RunMode.RawPower);
        spinnerMotor = new Motor(hardwareMap, spinnerConfig.getSpinnerMotorId, spinnerConfig.getSpinnerMotorType);
    }

    @Override
    public void startMechanism() {}

    @Override
    public void updateMechanism() {}

    @Override
    public void stopMechanism() {
        spinnerMotor.set(0);
    }

}
