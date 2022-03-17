//
// Created by Neil Rodriguez 11/5/2021
//

package org.firstinspires.ftc.teamcode.Robots.Happy;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Mechanisms.Arm.ArmConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Capper.CapperConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.MecanumConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.IntakeConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.ManipulatorConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.SpinnerConfig;
import org.firstinspires.ftc.teamcode.Sensors.GamePieceDetector.GamePieceDetectorConfig;

public class Configuration {

  public static class Mechanisms {

    public static class RumbleEffets {

      public static Gamepad.RumbleEffect endGameRumble =
          new Gamepad.RumbleEffect.Builder()
              .addStep(0.0, 1.0, 500)
              .addStep(0.0, 0.0, 300)
              .addStep(1.0, 0.0, 250)
              .addStep(0.0, 0.0, 250)
              .addStep(1.0, 0.0, 250)
              .addStep(0.0, 0.0, 300)
              .addStep(1.0, 0.0, 250)
              .addStep(0.0, 0.0, 250)
              .addStep(1.0, 0.0, 250)
              .build();

    }

    public static class Positions {

      public static class arm {

        public static int highPosition = 300;
        public static int midPosition = 175;
        public static int lowPosition = 105;

        public enum States {
          highPosition,
          midPosition,
          lowPosition,
          homePosition,
        }

      }

      public static class intake {
        public static double down = 50;
        public static double up = 0;
      }

    }

    public static MecanumConfig mecanumConfig =
        new MecanumConfig(
            "frontLeftMotor",
            "backLeftMotor",
            "frontRightMotor",
            "backRightMotor",
            "imu",
            Motor.GoBILDA.RPM_223);

    public static ManipulatorConfig manipulatorConfig =
        new ManipulatorConfig("manipulatorMotor", Motor.GoBILDA.RPM_435);

    public static ArmConfig armConfig =
        new ArmConfig("armMotor", 1451.5, 13.5, 30, Motor.GoBILDA.RPM_117);

    public static IntakeConfig intakeConfig =
        new IntakeConfig(
            "intakeMotor", "leftIntakeServo", "rightIntakeServo", 0, 270, Motor.GoBILDA.RPM_435);

    public static SpinnerConfig spinnerConfig =
        new SpinnerConfig("spinnerMotor", Motor.GoBILDA.RPM_435);

    public static CapperConfig capperConfig = new CapperConfig("capperServo", 0, 270);
  }

  public static class Sensors {

    public static GamePieceDetectorConfig gamePieceDetectorConfig =
        new GamePieceDetectorConfig("colorSensor", 400);
  }

  public static class Buttons {

    public static GamepadKeys.Button a = GamepadKeys.Button.A;
    public static GamepadKeys.Button b = GamepadKeys.Button.B;
    public static GamepadKeys.Button x = GamepadKeys.Button.X;
    public static GamepadKeys.Button y = GamepadKeys.Button.Y;

    public static GamepadKeys.Button leftBumper = GamepadKeys.Button.LEFT_BUMPER;
    public static GamepadKeys.Button rightBumper = GamepadKeys.Button.RIGHT_BUMPER;

    public static GamepadKeys.Trigger leftTrigger = GamepadKeys.Trigger.LEFT_TRIGGER;
    public static GamepadKeys.Trigger rightTrigger = GamepadKeys.Trigger.RIGHT_TRIGGER;

    public static GamepadKeys.Button leftStickButton = GamepadKeys.Button.LEFT_STICK_BUTTON;
    public static GamepadKeys.Button rightStickButton = GamepadKeys.Button.RIGHT_STICK_BUTTON;

    public static GamepadKeys.Button dPadDown = GamepadKeys.Button.DPAD_DOWN;
    public static GamepadKeys.Button dPadLeft = GamepadKeys.Button.DPAD_LEFT;
    public static GamepadKeys.Button dPadUp = GamepadKeys.Button.DPAD_UP;
    public static GamepadKeys.Button dPadRight = GamepadKeys.Button.DPAD_RIGHT;

    public static GamepadKeys.Button start = GamepadKeys.Button.START;
    public static GamepadKeys.Button back = GamepadKeys.Button.BACK;
  }
}
