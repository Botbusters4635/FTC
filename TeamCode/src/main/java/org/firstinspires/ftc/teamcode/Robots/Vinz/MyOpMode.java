package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.eventloop.opmode.OpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

@TeleOp(name = "TeleyOp")

public class MyOpMode extends OpMode {

    MotorEx frontLeftMotor;
    MotorEx frontRightMotor;
    MotorEx backLeftMotor;
    MotorEx backRightMotor;

    MotorGroup motors;

    @Override
    public void init() {

    }

    @Override
    public void init_loop() {
        frontLeftMotor = new MotorEx(hardwareMap, "frontLeftMotor");
        frontRightMotor = new MotorEx(hardwareMap, "frontRightMotor");
        backLeftMotor = new MotorEx(hardwareMap, "backLeftMotor");
        backRightMotor = new MotorEx(hardwareMap, "backRightMotor");

        motors = new MotorGroup(frontLeftMotor, frontRightMotor, backLeftMotor, backRightMotor);
    }

    @Override
    public void loop() {
        motors.setRunMode(Motor.RunMode.RawPower);
        motors.set(1);
    }
}
