package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;

@TeleOp(name = "EncoderTester")
public class EncoderTester extends EctoOpMode {

    MotorEx fL, fR, bL, bR;

    MotorEx arm;

    @Override
    public void initRobotClasses() {
        fL = new MotorEx(hardwareMap, "fL");
        fR = new MotorEx(hardwareMap, "fR");
        bL = new MotorEx(hardwareMap, "bL");
        bR = new MotorEx(hardwareMap, "bR");

        arm = new MotorEx(hardwareMap, "arm");
    }

    @Override
    public void initRobot() {
        fL.setRunMode(MotorEx.RunMode.PositionControl);
        fR.setRunMode(MotorEx.RunMode.PositionControl);
        bL.setRunMode(MotorEx.RunMode.PositionControl);
        bR.setRunMode(MotorEx.RunMode.PositionControl);

        arm.setRunMode(Motor.RunMode.PositionControl);
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
        telemetry.addData("Arm", arm.getCurrentPosition());
        telemetry.update();
    }
}
