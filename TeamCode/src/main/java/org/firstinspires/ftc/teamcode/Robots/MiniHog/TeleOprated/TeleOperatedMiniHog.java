package org.firstinspires.ftc.teamcode.Robots.MiniHog.TeleOprated;

import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.chassisConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.clawConfig;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.DualServoMechanism;
import org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOperated")
public class TeleOperatedMiniHog extends EctoOpMode {

    // Mechanisms
    Mecanum chassis;
    DualServoMechanism arm;
    DualServoMechanism claw;

    // Controller
    public static GamepadEx driverGamepad;

    @Override
    public void initRobotClasses() {

        // Controller
        driverGamepad = new GamepadEx(gamepad1);

        // Mechanisms
        chassis = new Mecanum("Mecanum", "Mechanism", chassisConfig);
        arm = new DualServoMechanism("Arm", "Mechanism", armConfig);
        claw = new DualServoMechanism("Claw", "Mechanism", clawConfig);
    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(chassis);
        mechanismManager.addMechanism(arm);
        mechanismManager.addMechanism(claw);
    }

    @Override
    public void startRobot() {
    }

    @Override
    public void updateRobot(Double timeStep) {

        // + ########################## + //
        // + ######## CHASSIS ######### + //
        // + ########################## + //

        // + CHASSIS BUTTON CONFIUGRATION
        if (driverGamepad.getLeftY() != 0
                || driverGamepad.getLeftX() != 0
                || driverGamepad.getRightX() != 0) {
            chassis.setChassisMovement(
                    driverGamepad.getLeftX(),
                    driverGamepad.getLeftY(),
                    driverGamepad.getRightX(),
                    Mecanum.orientation.field);
        }

        if (driverGamepad.getLeftY() == 0
                || driverGamepad.getLeftX() == 0
                || driverGamepad.getRightX() == 0) {
            chassis.setChassisMovement(
                    0.0,
                    0.0000000001,
                    0.0,
                    Mecanum.orientation.field);
        }


        if (driverGamepad.getLeftY() != 0 && driverGamepad.getButton(Configuration.Buttons.rightBumper)
                || driverGamepad.getLeftX() != 0 && driverGamepad.getButton(Configuration.Buttons.rightBumper)
                || driverGamepad.getRightX() != 0 && driverGamepad.getButton(Configuration.Buttons.rightBumper)) {
            chassis.setChassisMovement(
                    driverGamepad.getLeftX() * .325,
                    driverGamepad.getLeftY() * .325,
                    driverGamepad.getRightX() * .325,
                    Mecanum.orientation.field);
        }

        if (driverGamepad.getLeftY() != 0 && driverGamepad.getButton(Configuration.Buttons.leftBumper)
                || driverGamepad.getLeftX() != 0 && driverGamepad.getButton(Configuration.Buttons.leftBumper)
                || driverGamepad.getRightX() != 0 && driverGamepad.getButton(Configuration.Buttons.leftBumper)) {
            chassis.setChassisMovement(
                    driverGamepad.getLeftX() * .325,
                    driverGamepad.getLeftY() * .325,
                    driverGamepad.getRightX() * .325,
                    Mecanum.orientation.field);
        }

        // + ARM BUTTON CONFIGURATION
        if (driverGamepad.getButton(Configuration.Buttons.dPadUp)) {
            // Sets the arm to go up, since the servos are at coutinous rotation mode.
            arm.set("rightServo", 270, AngleUnit.DEGREES);
            arm.set("leftServo", 270, AngleUnit.DEGREES);

        } else if (driverGamepad.getButton(Configuration.Buttons.dPadDown)) {
            // Sets the arm to go down, since the servos are at coutinous rotation mode.
            arm.set("rightServo", 0, AngleUnit.DEGREES);
            arm.set("leftServo", 0, AngleUnit.DEGREES);
        } else {
            // Sets the servo to dont move.
            arm.set("rightServo", 135, AngleUnit.DEGREES);
            arm.set("leftServo", 135, AngleUnit.DEGREES);
        }

        // + CLAW BUTTON CONFIGURATION
        if (driverGamepad.getButton(Configuration.Buttons.a)) {

            // Opens the claw.
            claw.set("clawRight", 175, AngleUnit.DEGREES);
            claw.set("clawLeft", 175, AngleUnit.DEGREES);
        }
        if (driverGamepad.getButton(Configuration.Buttons.b)) {

            // Closes the claw.
            claw.set("clawRight", 100, AngleUnit.DEGREES);
            claw.set("clawLeft", 100, AngleUnit.DEGREES);
        }

    }
}