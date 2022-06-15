package org.firstinspires.ftc.teamcode.Robots.MiniHog.TeleOprated;

import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.pushbotConfig;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot.Pushbot;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor.SimpleMotorMechanism;
import org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOperated")
public class TeleOperatedMiniHog extends EctoOpMode {

  // Mechanisms
  Pushbot chassis;
  SimpleMotorMechanism arm;
  SimpleMotorMechanism intake;

  // Controllers
  public static GamepadEx driverGamepad;

  @Override
  public void initRobotClasses() {

    // Controllers
    driverGamepad = new GamepadEx(gamepad1);

    chassis = new Pushbot("ChassisPushbot", "Mechanism", pushbotConfig);
    arm = new SimpleMotorMechanism("arm", "Mechanism", armConfig);
    intake = new SimpleMotorMechanism("intake", "Mechanism", intakeConfig);
  }

  @Override
  public void initRobot() {
    mechanismManager.addMechanism(chassis);
    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(intake);
  }

  @Override
  public void startRobot() {}

  @Override
  public void updateRobot(Double timeStep) {

    // + ########################## + //
    // + ######## CHASSIS ######### + //
    // + ########################## + //

    // + CHASSIS BUTTON CONFIGURATION
    if (driverGamepad.getButton(Configuration.Buttons.x)){
      chassis.usePIDController(true);
      chassis.turnToAngle(90);
    } else if (driverGamepad.getButton(Configuration.Buttons.y)){
      chassis.usePIDController(true);
      chassis.turnToAngle(-90);
    } else if (driverGamepad.getButton(Configuration.Buttons.a)){
      chassis.usePIDController(true);
      chassis.movdeForward(1);
    } else if (driverGamepad.getButton(Configuration.Buttons.b)){
      chassis.usePIDController(true);
      chassis.movdeForward(-1);
    } else if (driverGamepad.getLeftY() != 0 || driverGamepad.getRightX() != 0) {

      chassis.usePIDController(false);
      chassis.setChassisMovement(driverGamepad.getLeftY(), driverGamepad.getRightX());

    } else if (driverGamepad.getLeftY() == 0 && driverGamepad.getRightX() == 0) {

      if (chassis.getVel() >= 0) {
        chassis.usePIDController(false);
        chassis.setChassisMovement(-0.001, 0);
      }

      if (chassis.getVel() <= 0) {
        chassis.usePIDController(false);
        chassis.setChassisMovement(0.001, 0);
      }

    } else {
      chassis.usePIDController(false);
      chassis.stopChassis();
    }

    // + INTAKE BUTTON CONFIGURATION
    if (driverGamepad.getButton(Configuration.Buttons.rightBumper)) {
      intake.set(1);
    } else if (driverGamepad.getButton(Configuration.Buttons.leftBumper)) {
      intake.set(-1);
    } else {
      intake.set(0.5);
    }

    // + ARM BUTTON CONFIGURATION
    if (driverGamepad.getButton(Configuration.Buttons.dPadUp)) {
      arm.set(1);
    } else if (driverGamepad.getButton(Configuration.Buttons.dPadDown)) {
      arm.set(-1);
    } else {
      // PTR CONTROLLER GO BRRR
      arm.set(0.01);
    }

    telemetry.addData("rightMotor encoder: ", chassis.getEncoder());
    telemetry.addData("rightMotor meter: ", chassis.getPose());
    telemetry.addData("imu angle: ", chassis.getHeading());
    telemetry.update();
  }
}
