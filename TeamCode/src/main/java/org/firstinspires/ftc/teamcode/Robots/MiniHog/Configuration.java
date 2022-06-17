package org.firstinspires.ftc.teamcode.Robots.MiniHog;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.MecanumConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot.PushbotConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor.SimpleMotorMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor.SimpleMotorMechanismConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.DualServoMechanismConfig;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.SingleServoMechanismConfig;
@Config
public class Configuration {

    public static class Mechanisms {
        public static MecanumConfig chassisConfig = new MecanumConfig("frontLeft", "backLeft", "frontRight", "backRight", "imu", Motor.GoBILDA.RPM_223);
        public static SimpleMotorMechanismConfig rightArmConfig = new SimpleMotorMechanismConfig("rightArm", 288, 125, true);
        public static SimpleMotorMechanismConfig leftArmConfig = new SimpleMotorMechanismConfig("leftArm", 288, 125, false);
        public static DualServoMechanismConfig clawConfig = new DualServoMechanismConfig("clawRight", "clawLeft", 270, 0, 1, false, true);
        public static SimpleMotorMechanismConfig intakeConfig = new SimpleMotorMechanismConfig("intakeMotor", 288, 125,true);
    }

    public static double setRightServo = 0;
    public static double setLeftServo = 0;

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
