package org.firstinspires.ftc.teamcode.Mechanisms.Intake;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Intake extends EctoMechanism {

  public Intake(String moduleName, String moduleType, IntakeConfig config) {
    super(moduleName, moduleType);
    this.config = config;
  }

  IntakeConfig config;
  Motor intakeMotor;

  ServoEx leftServo;
  ServoEx rightServo;

  public void turnOn(double powerPecentage) {

    intakeMotor.set(powerPecentage);

  }

  public void setPosition(double setPoint) {

    leftServo.setPosition(setPoint);
    rightServo.setPosition(setPoint);

  }

  public void turnOff() {
    intakeMotor.set(0);
  }

  @Override
  public void initMechanism() {

    leftServo = new SimpleServo(hardwareMap, config.getLeftServoId, 0, 360);
    rightServo = new SimpleServo(hardwareMap, config.getLeftServoId, 0, 360);
    intakeMotor = new Motor(hardwareMap, config.getIntakeMotorId, config.getGobildaType);
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
