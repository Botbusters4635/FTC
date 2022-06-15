package org.firstinspires.ftc.teamcode.Robots.MiniHog.TeleOprated;

import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.chassisConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.clawConfig;
//import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.chassisConfig;
//import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.servoTestConfig;

import android.graphics.Bitmap;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot.Pushbot;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor.SimpleMotorMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.DualServoMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.DualServoMechanismConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.SingleServoMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.SingleServoMechanismConfig;
import org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOperatedHappy-MiniHog", group = "Working")
public class TeleOperatedMiniHog extends EctoOpMode {

  // Mechanisms
  Mecanum chassis;
  DualServoMechanism arm;
  DualServoMechanism claw;

  // Contreller
  public static GamepadEx driverGamepad;

  @Override
  public void initRobotClasses() {
    // Controllers
    driverGamepad = new GamepadEx(gamepad1);

    chassis = new Mecanum("Mecanum", "Mechanism", chassisConfig);
    arm = new DualServoMechanism("arm", "Mechanism", armConfig);
    claw = new DualServoMechanism("claw", "Mechanism", clawConfig);
  }

  @Override
  public void initRobot() {
//    mechanismManager.addMechanism(chassis);
    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(claw);

  }

  @Override
  public void startRobot() {}

  @Override
  public void updateRobot(Double timeStep) {

    if (driverGamepad.getButton(Configuration.Buttons.dPadUp)){
      arm.set("rightServo", 270, AngleUnit.DEGREES);
      arm.set("leftServo", 270, AngleUnit.DEGREES);

    }else if (driverGamepad.getButton(Configuration.Buttons.dPadDown)){
      arm.set("rightServo", 0, AngleUnit.DEGREES);
      arm.set("leftServo", 0, AngleUnit.DEGREES);
    }else{
      arm.set("rightServo", 135, AngleUnit.DEGREES);
      arm.set("leftServo", 135, AngleUnit.DEGREES);
    }


    if (driverGamepad.getButton(Configuration.Buttons.a)){
      claw.set("clawRight", 175, AngleUnit.DEGREES);
      claw.set("clawLeft", 175, AngleUnit.DEGREES);

    }
    if (driverGamepad.getButton(Configuration.Buttons.b)){
      claw.set("clawRight", 100, AngleUnit.DEGREES);
      claw.set("clawLeft", 100, AngleUnit.DEGREES);

    }


    if (driverGamepad.getButton(org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Buttons.back)) {
      chassis.resetHeading();
    }

    if (driverGamepad.getLeftY() != 0 && driverGamepad.getButton(org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Buttons.rightBumper)
            || driverGamepad.getLeftX() != 0
            && driverGamepad.getButton(org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Buttons.rightBumper)
            || driverGamepad.getRightX() != 0
            && driverGamepad.getButton(org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Buttons.rightBumper)) {

      chassis.setChassisMovement(
              driverGamepad.getLeftX() * -0.325,
              driverGamepad.getLeftY() * -0.325,
              driverGamepad.getRightX() * -0.325,
              Mecanum.orientation.field);

    } else if (driverGamepad.getLeftY() != 0
            && driverGamepad.getButton(org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Buttons.leftBumper)
            || driverGamepad.getLeftX() != 0
            && driverGamepad.getButton(org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Buttons.leftBumper)
            || driverGamepad.getRightX() != 0
            && driverGamepad.getButton(org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Buttons.leftBumper)) {

      chassis.setChassisMovement(
              driverGamepad.getLeftX() * -0.5,
              driverGamepad.getLeftY() * -0.5,
              driverGamepad.getRightX() * -0.5,
              Mecanum.orientation.field);

    } else if (driverGamepad.getLeftY() != 0
            || driverGamepad.getLeftX() != 0
            || driverGamepad.getRightX() != 0) {

      chassis.setChassisMovement(
              driverGamepad.getLeftX() * -1,
              driverGamepad.getLeftY() * -1,
              driverGamepad.getRightX() * -1,
              Mecanum.orientation.field);

    } else {

      chassis.stopChassis();
    }
  }
}
