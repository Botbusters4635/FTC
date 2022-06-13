package org.firstinspires.ftc.teamcode.Robots.Happy.TeleOperated;

import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.intakeConfig;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Robots.Happy.Configuration;

@TeleOp(name = "ServoTester", group = "Testing")
public class ServoTester extends EctoOpMode {

    Intake intake;

    public static GamepadEx driverGamepad;

    @Override
    public void initRobotClasses() {

        driverGamepad = new GamepadEx(gamepad1);

        intake = new Intake("intake", "Mechanism", intakeConfig);

    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(intake);
    }

    @Override
    public void startRobot() {}

    @Override
    public void updateRobot(Double timeStep) {

        if (driverGamepad.getButton(Configuration.Buttons.dPadUp)){
            intake.setServoPosition(50);
        }

        if (driverGamepad.getButton(Configuration.Buttons.dPadDown)){
            intake.setServoPosition(0);
        }

        telemetry.addData("Right Servo Angle:", intake.getRightServoPosition());
        telemetry.addData("Left Servo Angle:", intake.getLeftServoPosition());

    }
}

