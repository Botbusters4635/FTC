//
// Created by Neil Rodriguez 11/11/21
//

package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Spinner extends EctoMechanism {

  public Spinner(String moduleName, String moduleType, SpinnerConfig config) {
    super(moduleName, moduleType);
    spinnerConfig = config;
  }

  SpinnerConfig spinnerConfig;

  Motor spinnerMotor;

  public void turnOn(double percentagePower) {
    spinnerMotor.set(percentagePower);
  }

  public void turnOff() {
    stopMechanism();
  }

  @Override
  public void initMechanism() {
    spinnerMotor =
        new Motor(hardwareMap, spinnerConfig.getSpinnerMotorId, spinnerConfig.getGobildaType);
    spinnerMotor.setRunMode(Motor.RunMode.RawPower);
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
