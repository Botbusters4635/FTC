package org.firstinspires.ftc.teamcode.Robots.MiniHog.TeleOprated;

import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;

@TeleOp(name = "EncoderTester", group = "Testing")
public class EncoderTester extends EctoOpMode {

    MotorEx fL, fR, bL, bR;

    @Override
    public void initRobotClasses() {
        fL = new MotorEx(hardwareMap, "frontLeft");
        fR = new MotorEx(hardwareMap, "frontRight");
        bL = new MotorEx(hardwareMap, "backLeft");
        bR = new MotorEx(hardwareMap, "backRight");
    }

    @Override
    public void initRobot() {
        fL.setRunMode(MotorEx.RunMode.PositionControl);
        fR.setRunMode(MotorEx.RunMode.PositionControl);
        bL.setRunMode(MotorEx.RunMode.PositionControl);
        bR.setRunMode(MotorEx.RunMode.PositionControl);
    }

    @Override
    public void startRobot() {

    }

    @Override
    public void updateRobot(Double timeStep) {
        telemetry.addData("Front Left", fL.getCurrentPosition());
        telemetry.addData("Front Right", fR.getCurrentPosition());
        telemetry.addData("Back Left", bL.getCurrentPosition());
        telemetry.addData("Back Right", bR.getCurrentPosition());
        telemetry.update();
    }
}