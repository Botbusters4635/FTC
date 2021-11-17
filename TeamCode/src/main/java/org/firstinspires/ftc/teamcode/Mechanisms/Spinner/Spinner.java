//
// Created by Diego Villanueva 11/11/21
//

package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.intake.SpinnerConfig;

public class Spinner extends EctoMechanism {

    public Spinner(String moduleName, String moduleType, org.firstinspires.ftc.teamcode.Mechanisms.intake.SpinnerConfig config) {
        super(moduleName, moduleType);
        spinnerConfig = config;
    }

    SpinnerConfig spinnerConfig;
    Motor spinnerMotor;

    public void turnOnMotor() {
        spinnerMotor.set(1);
    }

    @Override
    public void initMechanism() {
        spinnerMotor.setRunMode(Motor.RunMode.RawPower);
        spinnerMotor = new Motor(hardwareMap, spinnerConfig.getSpinnerMotorId, spinnerConfig.getGobildaType);
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
