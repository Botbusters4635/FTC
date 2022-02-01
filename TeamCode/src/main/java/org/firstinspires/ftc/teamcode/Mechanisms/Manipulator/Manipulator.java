//
// Created by Neil Rodriguez 11/11/21
//

package org.firstinspires.ftc.teamcode.Mechanisms.Manipulator;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Manipulator extends EctoMechanism{

  public Manipulator(String moduleName, String moduleType, ManipulatorConfig config) {
    super(moduleName, moduleType);
    manipulatorConfig = config;
  }

  ManipulatorConfig manipulatorConfig;
  Motor intakeMotor;

  public void turnOn(double powerPecentage) {
    intakeMotor.set(powerPecentage);
  }

  public void turnOff() {
    intakeMotor.stopMotor();
  }

  @Override
  public void initMechanism() {
    intakeMotor =
        new Motor(
            hardwareMap, manipulatorConfig.getManipulatorMotorId, manipulatorConfig.getGobildaType);
    intakeMotor.setRunMode(Motor.RunMode.RawPower);
  }

  @Override
  public void startMechanism() {}

  @Override
  public void updateMechanism() {}

  @Override
  public void stopMechanism() {
    intakeMotor.set(0);
  }
}
