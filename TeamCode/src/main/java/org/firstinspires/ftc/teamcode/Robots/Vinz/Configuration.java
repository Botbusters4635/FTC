//
// Created by Neil Rodriguez 11/5/2021
//

package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Mechanisms.Arm.ArmConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.MecanumConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.IntakeConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.ManipulatorConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.SpinnerConfig;


public class Configuration {

    public static class Mechanisms {

        static public MecanumConfig mecanumConfig = new MecanumConfig("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor", "imu", Motor.GoBILDA.RPM_223);
        static public ManipulatorConfig  manipulatorConfig = new ManipulatorConfig("manipulatorMotor", Motor.GoBILDA.RPM_435);
        static public ArmConfig armConfig = new ArmConfig("armMotor", 1451.5, 13.5, 30, Motor.GoBILDA.RPM_117);
        static public IntakeConfig intakeConfig = new IntakeConfig("intakeMotor", Motor.GoBILDA.RPM_435);
        static public SpinnerConfig spinnerConfig = new SpinnerConfig("spinnerMotor", Motor.GoBILDA.RPM_435);

    }

    public static class Sensors {

    }

    public static class Buttons {

            static public GamepadKeys.Button a = GamepadKeys.Button.A;
            static public GamepadKeys.Button b = GamepadKeys.Button.B;
            static public GamepadKeys.Button x = GamepadKeys.Button.X;
            static public GamepadKeys.Button y = GamepadKeys.Button.Y;

            static public GamepadKeys.Button leftBumper = GamepadKeys.Button.LEFT_BUMPER;
            static public GamepadKeys.Button rightBumper = GamepadKeys.Button.RIGHT_BUMPER;

            static public GamepadKeys.Trigger leftTrigger = GamepadKeys.Trigger.LEFT_TRIGGER;
            static public GamepadKeys.Trigger rightTrigger = GamepadKeys.Trigger.RIGHT_TRIGGER;

            static public GamepadKeys.Button leftStickButton = GamepadKeys.Button.LEFT_STICK_BUTTON;
            static public GamepadKeys.Button rightStickButton = GamepadKeys.Button.RIGHT_STICK_BUTTON;

            static public GamepadKeys.Button dPadDown = GamepadKeys.Button.DPAD_DOWN;
            static public GamepadKeys.Button dPadLeft = GamepadKeys.Button.DPAD_LEFT;
            static public GamepadKeys.Button dPadUp = GamepadKeys.Button.DPAD_UP;
            static public GamepadKeys.Button dPadRight = GamepadKeys.Button.DPAD_RIGHT;

    }

}
