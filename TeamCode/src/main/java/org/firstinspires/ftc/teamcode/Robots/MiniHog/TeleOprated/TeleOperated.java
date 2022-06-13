package org.firstinspires.ftc.teamcode.Robots.MiniHog.TeleOprated;

import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.pushbotConfig;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot.Pushbot;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOperated-MiniHog", group = "Working")
public class TeleOperated extends EctoOpMode {

  // Mechanisms
  Pushbot chassis;

  // Contrellers
  public static GamepadEx driverGamepad;

  @Override
  public void initRobotClasses() {
    // Controllers
    driverGamepad = new GamepadEx(gamepad1);

    chassis = new Pushbot("ChassisPushbot", "Mechanism", pushbotConfig);
  }

  @Override
  public void initRobot() {
    mechanismManager.addMechanism(chassis);
  }

  @Override
  public void startRobot() {}

  @Override
  public void updateRobot(Double timeStep) {

    if (driverGamepad.getLeftY() != 0 || driverGamepad.getRightX() != 0) {
      chassis.setChassisMovement(driverGamepad.getLeftY(), driverGamepad.getRightX());
    } else {
      chassis.stopChassis();
    }
  }
}
