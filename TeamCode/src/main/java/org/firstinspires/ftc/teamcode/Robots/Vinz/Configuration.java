package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.MecanumConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.ManipulatorConfig;


public class Configuration {

    static class MechanismsConfig {
        static public MecanumConfig mecanumConfig = new MecanumConfig("frontLeftMotor", "backLeftMotor", "frontRightMotor", "backRightMotor", "imu", Motor.GoBILDA.RPM_223);
        static public ManipulatorConfig  manipulatorConfig = new ManipulatorConfig("manipulatorMotor", Motor.GoBILDA.RPM_435);
        static public ManipulatorConfig  armConfig = new ManipulatorConfig("armMotor", Motor.GoBILDA.RPM_435);

    }

    static class SensorsConfig {

    }


    static class ButtonsConfig {

        //Button Configuration For Manipulator Driver
        static class driver{
            static public GamepadKeys.Button intakeButton = GamepadKeys.Button.A;
            static public GamepadKeys.Button spinnerButton = GamepadKeys.Button.B;
            static public GamepadKeys.Button TODO = GamepadKeys.Button.X;
            static public GamepadKeys.Button TODO1 = GamepadKeys.Button.Y;

            static public GamepadKeys.Button TODO2 = GamepadKeys.Button.LEFT_BUMPER;
            static public GamepadKeys.Button TODO3 = GamepadKeys.Button.RIGHT_BUMPER;

            static public GamepadKeys.Trigger manipulatorIn = GamepadKeys.Trigger.LEFT_TRIGGER;
            static public GamepadKeys.Trigger manipulatorOut = GamepadKeys.Trigger.RIGHT_TRIGGER;

            static public GamepadKeys.Button TODO4 = GamepadKeys.Button.LEFT_STICK_BUTTON;
            static public GamepadKeys.Button TODO5 = GamepadKeys.Button.RIGHT_STICK_BUTTON;


            static public GamepadKeys.Button TODO6 = GamepadKeys.Button.DPAD_DOWN;
            static public GamepadKeys.Button TODO7 = GamepadKeys.Button.DPAD_LEFT;
            static public GamepadKeys.Button TODO8 = GamepadKeys.Button.DPAD_UP;
            static public GamepadKeys.Button TODO9 = GamepadKeys.Button.DPAD_RIGHT;

        }

        //Button Configuration For Manipulator Driver
        static class manipulator{
            static public GamepadKeys.Button intakeButton = GamepadKeys.Button.A;
            static public GamepadKeys.Button spinnerButton = GamepadKeys.Button.B;
            static public GamepadKeys.Button manipulatorButton = GamepadKeys.Button.X;
            static public GamepadKeys.Button TODO1 = GamepadKeys.Button.Y;

            static public GamepadKeys.Button TODO2 = GamepadKeys.Button.LEFT_BUMPER;
            static public GamepadKeys.Button TODO3 = GamepadKeys.Button.RIGHT_BUMPER;

            static public GamepadKeys.Button TODO4 = GamepadKeys.Button.LEFT_STICK_BUTTON;
            static public GamepadKeys.Button TODO5 = GamepadKeys.Button.RIGHT_STICK_BUTTON;

            static public GamepadKeys.Button TODO6 = GamepadKeys.Button.DPAD_DOWN;
            static public GamepadKeys.Button TODO7 = GamepadKeys.Button.DPAD_LEFT;
            static public GamepadKeys.Button TODO8 = GamepadKeys.Button.DPAD_UP;
            static public GamepadKeys.Button TODO9 = GamepadKeys.Button.DPAD_RIGHT;

        }


    }

}
