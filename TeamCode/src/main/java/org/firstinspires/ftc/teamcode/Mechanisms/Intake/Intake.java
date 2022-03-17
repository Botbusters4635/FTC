package org.firstinspires.ftc.teamcode.Mechanisms.Intake;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
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

  public void setServoPosition(double setPoint) {
    leftServo.turnToAngle(setPoint);
    rightServo.turnToAngle(setPoint);
  }

  public double getRightServoPosition() {
    return rightServo.getPosition();
  }

  public double getLeftServoPosition() {
    return leftServo.getPosition();
  }

  public void turnOff() {
    intakeMotor.set(0);
  }

  @Override
  public void initMechanism() {

    leftServo =
        new SimpleServo(
            hardwareMap,
            config.getLeftServoId,
            config.getMinServoAngle,
            config.getMaxServoAngle,
            AngleUnit.DEGREES);

    rightServo =
        new SimpleServo(
            hardwareMap,
            config.getRightServoId,
            config.getMinServoAngle,
            config.getMaxServoAngle,
            AngleUnit.DEGREES);

    leftServo.setInverted(true);

    intakeMotor = new Motor(hardwareMap, config.getIntakeMotorId, config.getGobildaType);
    intakeMotor.setRunMode(Motor.RunMode.RawPower);
  }

  @Override
  public void startMechanism() {
  }

  @Override
  public void updateMechanism() {}

  @Override
  public void stopMechanism() {
    intakeMotor.set(0);
  }
}
