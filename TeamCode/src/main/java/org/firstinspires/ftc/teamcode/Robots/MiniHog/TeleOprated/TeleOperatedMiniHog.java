package org.firstinspires.ftc.teamcode.Robots.MiniHog.TeleOprated;

import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.pushbotConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.servoTestConfig;

import android.graphics.Bitmap;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot.Pushbot;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor.SimpleMotorMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor.SimpleMotorMechanismConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.DualServoMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.DualServoMechanismConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.SingleServoMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.SingleServoMechanismConfig;
import org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOperatedHappy-MiniHog", group = "Working")
public class TeleOperatedMiniHog extends EctoOpMode {

  // Mechanisms
  Pushbot chassis;
  SimpleMotorMechanism arm;
  SimpleMotorMechanism intake;
  DualServoMechanism test;

  // Contrellers
  public static GamepadEx driverGamepad;

  @Override
  public void initRobotClasses() {
    // Controllers
    driverGamepad = new GamepadEx(gamepad1);

    chassis = new Pushbot("ChassisPushbot", "Mechanism", pushbotConfig);
    arm = new SimpleMotorMechanism("arm", "Mechanism", armConfig);
    test = new DualServoMechanism("test", "Mechanism", servoTestConfig);
    intake = new SimpleMotorMechanism("intake", "Mechanism", intakeConfig);

  }

  @Override
  public void initRobot() {
    mechanismManager.addMechanism(chassis);
    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(test);
    mechanismManager.addMechanism(intake);

  }

  @Override
  public void startRobot() {}

  @Override
  public void updateRobot(Double timeStep) {

    if (driverGamepad.getButton(Configuration.Buttons.rightBumper)){
      intake.set(12);
    }else{
      intake.set(0);
    }

    if (driverGamepad.getButton(Configuration.Buttons.dPadLeft)){

      test.set("test", (test.getAngle("test") + 1), AngleUnit.DEGREES);
      test.set("testTwo", (test.getAngle("testTwo") + 1), AngleUnit.DEGREES);
    }

    if (driverGamepad.getButton(Configuration.Buttons.dPadRight)){

      test.set("test", (test.getAngle("test") - 1), AngleUnit.DEGREES);
      test.set("testTwo", (test.getAngle("testTwo") - 1), AngleUnit.DEGREES);
    }


    if (driverGamepad.getButton(Configuration.Buttons.dPadUp)){
      arm.set(12);
    }else if (driverGamepad.getButton(Configuration.Buttons.dPadDown)){
      arm.set(-12);
    }else{
      arm.set(0.01);
    }

    if (driverGamepad.getButton(Configuration.Buttons.a)){
      chassis.usePIDController(true);
      chassis.movdeForward(1);
    }else if (driverGamepad.getLeftY() != 0 || driverGamepad.getRightX() != 0) {
      chassis.usePIDController(false);
      chassis.setChassisMovement(driverGamepad.getLeftY(), driverGamepad.getRightX());
    }else if (driverGamepad.getLeftY() == 0 && driverGamepad.getRightX() == 0){
      if (chassis.getVel() >= 0){
        chassis.setChassisMovement(-0.001, 0);
      }
      if (chassis.getVel() <= 0){
        chassis.setChassisMovement(0.001, 0);
      }
    }
    else{
      chassis.usePIDController(false);
      chassis.stopChassis();
    }
    telemetry.addData("rightMotor Pose: ", chassis.getEncoder());
    telemetry.update();

  }
}
