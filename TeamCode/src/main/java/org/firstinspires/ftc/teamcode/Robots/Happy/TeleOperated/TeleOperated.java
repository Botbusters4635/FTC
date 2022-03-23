package org.firstinspires.ftc.teamcode.Robots.Happy.TeleOperated;

import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.capperConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.mecanumConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.spinnerConfig;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;
import com.qualcomm.robotcore.util.ElapsedTime;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Capper.Capper;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;
import org.firstinspires.ftc.teamcode.Robots.Happy.Configuration;

@TeleOp(name = "TeleOperated", group = "Working")
public class TeleOperated extends EctoOpMode {

  // Mechanisms
  Mecanum chassis;
  Manipulator manipulator;
  Arm arm;
  Intake intake;
  Spinner spinner;
  Capper capper;

  Configuration.Mechanisms.Positions.arm.States currentArmState;



  // Controllers
  public static GamepadEx driverGamepad;
  public static GamepadEx manipulatorGamepad;

  ElapsedTime runtime = new ElapsedTime();
  boolean rumbleHasNotHappened = false;

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

    // State Machines
    currentArmState = Configuration.Mechanisms.Positions.arm.States.homePosition;
  }

  @Override
  public void initRobot() {
    mechanismManager.addMechanism(chassis);
    mechanismManager.addMechanism(manipulator);
    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(intake);
    mechanismManager.addMechanism(spinner);
    mechanismManager.addMechanism(capper);
  }

  @Override
  public void startRobot() {
    intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
  }

  @Override
  public void updateRobot(Double timeStep) {

    chassis.setState(EctoMechanism.State.Off);
    manipulator.setState(EctoMechanism.State.Off);
    arm.setState(EctoMechanism.State.Off);
    intake.setState(EctoMechanism.State.Off);
    spinner.setState(EctoMechanism.State.Off);
    capper.setState(EctoMechanism.State.Off);

    // + ########################## + //
    // + ######## CHASSIS ######### + //
    // + ########################## + //

    // + CHASSIS BUTTON CONFIGURATION
    if (driverGamepad.getButton(Configuration.Buttons.back)) {
      chassis.resetHeading();
    }

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
      currentArmState = Configuration.Mechanisms.Positions.arm.States.highPosition;

    } else if (driverGamepad.getButton(Configuration.Buttons.b)) {

      arm.setPosition(Configuration.Mechanisms.Positions.arm.midPosition);
      currentArmState = Configuration.Mechanisms.Positions.arm.States.midPosition;

    } else if (driverGamepad.getButton(Configuration.Buttons.a)) {

      arm.setPosition(Configuration.Mechanisms.Positions.arm.lowPosition);
      currentArmState = Configuration.Mechanisms.Positions.arm.States.lowPosition;

    } else if (driverGamepad.getButton(Configuration.Buttons.x)) {

      intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
      arm.setHomePosition();
      currentArmState = Configuration.Mechanisms.Positions.arm.States.homePosition;
    }
    if (driverGamepad.getButton(Configuration.Buttons.start)) {

      arm.resetEncoder();
    }

    // + INTAKE && MANIPULATOR BUTTON CONFIGURATION
    if (driverGamepad.getButton(Configuration.Buttons.dPadUp)) {

      manipulator.turnOn(1);
      manipulator.setState(EctoMechanism.State.On);
      intake.turnOff();

    } else {

      intake.turnOff();
      manipulator.turnOff();
    }

    if (currentArmState == Configuration.Mechanisms.Positions.arm.States.homePosition) {
      intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
    }

    if (driverGamepad.getButton(Configuration.Buttons.dPadRight)) {

      if (currentArmState == Configuration.Mechanisms.Positions.arm.States.homePosition) {
        manipulator.turnOn(-1);
        intake.turnOn(-1);
      } else {
        intake.turnOff();
        manipulator.turnOff();
      }

    } else if (driverGamepad.getButton(Configuration.Buttons.dPadLeft)) {

      if (manipulator.getState() == EctoMechanism.State.On) {
        intake.turnOff();
      } else {
        intake.turnOn(1);
      }

    } else {

      manipulator.turnOff();
      intake.turnOff();
    }

    // + ########################## + //
    // + ###### Manipulator ####### + //
    // + ########################## + //

    // + SPINNER BUTTON CONFIGURATION
    if (manipulatorGamepad.getTrigger(Configuration.Buttons.rightTrigger) != 0) {
      spinner.turnOn(manipulatorGamepad.getTrigger(Configuration.Buttons.rightTrigger) - 0.25);
    } else if (manipulatorGamepad.getTrigger(Configuration.Buttons.leftTrigger) != 0) {
      spinner.turnOn(manipulatorGamepad.getTrigger(Configuration.Buttons.leftTrigger) * - 0.25);
    } else {
      spinner.turnOff();
    }

    // + INTAKE BUTTON CONFIGURATION
    if (manipulatorGamepad.getButton(Configuration.Buttons.dPadUp)) {
      intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
    }

    if (manipulatorGamepad.getButton(Configuration.Buttons.dPadDown)) {
      intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
    }

    // + CAPPER BUTTON CONFIGURATION
    if (manipulatorGamepad.getButton(Configuration.Buttons.rightBumper)) {

      capper.up();

    } else if (manipulatorGamepad.getButton(Configuration.Buttons.leftBumper)) {

      capper.down();
    }

    telemetry.addData("Arm Position: ", arm.getActualPosition());

    telemetry.update();
  }
}
