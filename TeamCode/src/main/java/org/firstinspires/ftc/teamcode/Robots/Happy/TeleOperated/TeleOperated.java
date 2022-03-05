package org.firstinspires.ftc.teamcode.Robots.Happy.TeleOperated;

import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.capperConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.mecanumConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.spinnerConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Sensors.gamePieceDetectorConfig;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Capper.Capper;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;
import org.firstinspires.ftc.teamcode.Robots.Happy.Configuration;
import org.firstinspires.ftc.teamcode.Sensors.GamePieceDetector.GamePieceDetector;

@TeleOp(name = "TeleOperated", group = "Working")
public class TeleOperated extends EctoOpMode {

  // Mechanisms
  Mecanum chassis;
  Manipulator manipulator;
  Arm arm;
  Intake intake;
  Spinner spinner;
  Capper capper;

  // Sensors
  GamePieceDetector gamePieceDetector;

  // Controllers
  public static GamepadEx driverGamepad;
  public static GamepadEx manipulatorGamepad;

  @Override
  public void initRobotClasses() {

    // Controllers
    manipulatorGamepad = new GamepadEx(gamepad2);
    driverGamepad = new GamepadEx(gamepad1);

    // Mechanisms
    chassis = new Mecanum("ChassisMecanum", "Mechanism", mecanumConfig);
    arm = new Arm("arm", "Mechanism", armConfig);
    manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);
    intake = new Intake("intake", "Mechanism", intakeConfig);
    spinner = new Spinner("spinner", "Mechanism", spinnerConfig);
    capper = new Capper("capper", "Mechanism", capperConfig);

    // Sensors
    gamePieceDetector =
        new GamePieceDetector("manipulatorColorSensor", "Sensor", gamePieceDetectorConfig);

  }

  @Override
  public void initRobot() {
    mechanismManager.addMechanism(chassis);
    mechanismManager.addMechanism(manipulator);
    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(intake);
    mechanismManager.addMechanism(spinner);
    mechanismManager.addMechanism(capper);

    mechanismManager.addMechanism(gamePieceDetector);
  }

  @Override
  public void startRobot() {
    intake.setPosition(Configuration.Mechanisms.Positions.intake.down);
  }

  @Override
  public void updateRobot(Double timeStep) {

    // + CHASSIS BUTTON CONFIGURATION
    if (driverGamepad.getLeftY() != 0 && driverGamepad.getButton(Configuration.Buttons.rightBumper)
        || driverGamepad.getLeftX() != 0
            && driverGamepad.getButton(Configuration.Buttons.rightBumper)
        || driverGamepad.getRightX() != 0
            && driverGamepad.getButton(Configuration.Buttons.rightBumper)) {

      chassis.setChassisMovement(
          driverGamepad.getLeftX() * -0.325,
          driverGamepad.getLeftY() * -0.325,
          driverGamepad.getRightX() * -0.325,
          Mecanum.orientation.field);

    } else if (driverGamepad.getLeftY() != 0
            && driverGamepad.getButton(Configuration.Buttons.leftBumper)
        || driverGamepad.getLeftX() != 0
            && driverGamepad.getButton(Configuration.Buttons.leftBumper)
        || driverGamepad.getRightX() != 0
            && driverGamepad.getButton(Configuration.Buttons.leftBumper)) {

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

    // + ARM BUTTON CONFIGURATION
    if (driverGamepad.getButton(Configuration.Buttons.y)) {

      arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);
      intake.setPosition(Configuration.Mechanisms.Positions.intake.up);

    } else if (driverGamepad.getButton(Configuration.Buttons.b)) {

      arm.setPosition(Configuration.Mechanisms.Positions.arm.midPosition);
      intake.setPosition(Configuration.Mechanisms.Positions.intake.up);

    } else if (driverGamepad.getButton(Configuration.Buttons.a)) {

      arm.setPosition(Configuration.Mechanisms.Positions.arm.lowPosition);
      intake.setPosition(Configuration.Mechanisms.Positions.intake.up);

    } else if (driverGamepad.getButton(Configuration.Buttons.x)) {

      arm.setHomePosition();
      intake.setPosition(Configuration.Mechanisms.Positions.intake.down);

    }
    if (driverGamepad.getButton(Configuration.Buttons.start)) {

      arm.resetEncoder();
    }

    // + INTAKE && MANIPULATOR BUTTON CONFIGURATION
    if (driverGamepad.getButton(Configuration.Buttons.dPadUp)) {

      manipulator.turnOn(1);
      intake.turnOff();

    } else {

      intake.turnOff();
      manipulator.turnOff();

    }

    if (driverGamepad.getButton(Configuration.Buttons.dPadRight)) {

      if (gamePieceDetector.gamePieceDetected()) {

        arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);

      } else {

        manipulator.turnOn(-1);
        intake.turnOn(-1);
      }

    } else if (driverGamepad.getButton(Configuration.Buttons.dPadLeft)) {

      intake.turnOn(1);

    } else {

      manipulator.turnOff();
      intake.turnOff();
    }

    // + SPINNER BUTTON CONFIGURATION
    if (driverGamepad.getTrigger(Configuration.Buttons.rightTrigger) != 0) {

      spinner.turnOn(driverGamepad.getTrigger(Configuration.Buttons.rightTrigger));

    } else if (driverGamepad.getTrigger(Configuration.Buttons.leftTrigger) != 0) {

      spinner.turnOn(driverGamepad.getTrigger(Configuration.Buttons.leftTrigger) * -1);

    } else {

      spinner.turnOff();
    }



    telemetry.addData("Arm Position: ", arm.getActualPosition());
    telemetry.addData("Game Piece Detected: ", gamePieceDetector.gamePieceDetected());
    telemetry.update();
  }
}
