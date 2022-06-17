package org.firstinspires.ftc.teamcode.Robots.MiniHog.TeleOprated;

import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.chassisConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.clawConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.leftArmConfig;
import static org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration.Mechanisms.rightArmConfig;

import android.widget.SimpleExpandableListAdapter;

import com.arcrobotics.ftclib.gamepad.GamepadEx;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor.SimpleMotorMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo.DualServoMechanism;
import org.firstinspires.ftc.teamcode.Robots.MiniHog.Configuration;

@com.qualcomm.robotcore.eventloop.opmode.TeleOp(name = "TeleOperated")
public class TeleOperatedMiniHog extends EctoOpMode {

    // Mechanisms
    Mecanum chassis;
    SimpleMotorMechanism rightArm;
    SimpleMotorMechanism leftArm;
    DualServoMechanism claw;
    SimpleMotorMechanism intake;

    // Controller
    public static GamepadEx driverGamepad;
    public static GamepadEx mechanismGampad;

    double clawNum;

    @Override
    public void initRobotClasses() {

        // Controller
        driverGamepad = new GamepadEx(gamepad1);
        mechanismGampad = new GamepadEx(gamepad2);

        // Mechanisms
        chassis = new Mecanum("Mecanum", "Mechanism", chassisConfig);
        rightArm = new SimpleMotorMechanism("rightArm", "Mechanism", rightArmConfig);
        leftArm = new SimpleMotorMechanism("leftArm", "Mechanism", leftArmConfig);
        claw = new DualServoMechanism("Claw", "Mechanism", clawConfig);
        intake = new SimpleMotorMechanism("intake", "Mechanism", intakeConfig);

        clawNum = 0.0;

    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(chassis);
        mechanismManager.addMechanism(rightArm);
        mechanismManager.addMechanism(leftArm);
        mechanismManager.addMechanism(claw);
        mechanismManager.addMechanism(intake);
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

        if (driverGamepad.getLeftY() != 0 && driverGamepad.getButton(Configuration.Buttons.rightBumper)
                || driverGamepad.getLeftX() != 0 && driverGamepad.getButton(Configuration.Buttons.rightBumper)
                || driverGamepad.getRightX() != 0 && driverGamepad.getButton(Configuration.Buttons.rightBumper)) {
            chassis.setChassisMovement(
                    driverGamepad.getLeftX() * -.20,
                    driverGamepad.getLeftY() * -.20,
                    driverGamepad.getRightX() * -.20,
                    Mecanum.orientation.field);


        } else if (driverGamepad.getLeftY() != 0 && driverGamepad.getButton(Configuration.Buttons.leftBumper)
                || driverGamepad.getLeftX() != 0 && driverGamepad.getButton(Configuration.Buttons.leftBumper)
                || driverGamepad.getRightX() != 0 && driverGamepad.getButton(Configuration.Buttons.leftBumper)) {
            chassis.setChassisMovement(
                    driverGamepad.getLeftX() * -.20,
                    driverGamepad.getLeftY() * -.20,
                    driverGamepad.getRightX() * -.20,
                    Mecanum.orientation.field);


        } else if (driverGamepad.getLeftY() != 0
                || driverGamepad.getLeftX() != 0
                || driverGamepad.getRightX() != 0) {
            chassis.setChassisMovement(
                    driverGamepad.getLeftX() * -.6,
                    driverGamepad.getLeftY() * -.6,
                    driverGamepad.getRightX() * -.6,
                    Mecanum.orientation.field);


        }

        // + ARM BUTTON CONFIGURATION
        if (mechanismGampad.getLeftY() != 0) {
            rightArm.set(mechanismGampad.getLeftY() * 0.4);
            leftArm.set(mechanismGampad.getLeftY() * 0.4);
        } else {
            // PTR CONTROLLER GO
            rightArm.set(0.1    );
            leftArm.set(0.1);
        }

        claw.set("clawRight", clawNum, AngleUnit.DEGREES);
        claw.set("clawLeft", clawNum, AngleUnit.DEGREES);


        // + CLAW BUTTON CONFIGURATION
        if (mechanismGampad.getButton(Configuration.Buttons.a)) {
            // Opens The Claw
            clawNum = clawNum - 2;
        }
        if (mechanismGampad.getButton(Configuration.Buttons.b)) {
            // closes the Claw.
            clawNum = clawNum + 1;
        }
        if (mechanismGampad.getButton(Configuration.Buttons.x)) {
            clawNum = 0;
            claw.set("clawRight", 0, AngleUnit.DEGREES);
            claw.set("clawLeft", 0, AngleUnit.DEGREES);
        }
        if (mechanismGampad.getButton(Configuration.Buttons.y)) {
            clawNum = 140;
            claw.set("clawRight", 120, AngleUnit.DEGREES);
            claw.set("clawLeft", 120, AngleUnit.DEGREES);
        }

        //intake
        if (mechanismGampad.getButton(Configuration.Buttons.rightBumper)) {
            intake.set(1);
        } else if (mechanismGampad.getButton(Configuration.Buttons.leftBumper)) {
            intake.set(-1);
        } else {
            intake.set(0);
        }

        telemetry.update();

    }
}