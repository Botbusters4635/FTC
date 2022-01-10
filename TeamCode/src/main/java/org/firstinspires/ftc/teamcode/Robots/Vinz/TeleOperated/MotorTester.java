package org.firstinspires.ftc.teamcode.Robots.Vinz.TeleOperated;

import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.manipulatorConfig;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration;

@TeleOp(name = "MotorTester")
public class MotorTester extends EctoOpMode {

  Manipulator manipulator;
  Intake intake;

  // Controllers
  public static GamepadEx driverGamepad;
  public static GamepadEx manipulatorGamepad;

  @Override
  public void initRobotClasses() {

    // Controllers
    driverGamepad = new GamepadEx(gamepad1);

    // Mechanisms
    intake = new Intake("intake", "Mechanism", intakeConfig);
    manipulator = new Manipulator("manipulator", "Mechanism", manipulatorConfig);
  }

  @Override
  public void initRobot() {
    mechanismManager.addMechanism(intake);
    mechanismManager.addMechanism(manipulator);
  }

  @Override
  public void startRobot() {}

  @Override
  public void updateRobot(Double timeStep) {

    telemetry.addData("LeftY", driverGamepad.getLeftY());
    telemetry.addData("RightY", driverGamepad.getRightY());

    telemetry.addData("RightBumper", driverGamepad.getGamepadButton(Configuration.Buttons.rightBumper));
    telemetry.addData("LeftBumper", driverGamepad.getGamepadButton(Configuration.Buttons.leftBumper));

    telemetry.update();

    if (driverGamepad.getLeftY() != 0) {
      manipulator.turnOn(driverGamepad.getLeftY());
    } else {
      manipulator.turnOn(0);
    }

    if (driverGamepad.getRightY() != 0) {
      intake.turnOn(driverGamepad.getRightY());
    } else {
      intake.turnOn(0);
    }
  }
}
