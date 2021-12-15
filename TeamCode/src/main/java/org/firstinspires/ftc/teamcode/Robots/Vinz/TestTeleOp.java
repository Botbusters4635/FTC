package org.firstinspires.ftc.teamcode.Robots.Vinz;

import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.mecanumConfig;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;


@TeleOp(name = "TestTeleOp")
public class TestTeleOp extends EctoOpMode {

    //Mechanisms
    Mecanum chassis;

    Manipulator manipulator;

    //Controllers
    public static GamepadEx driverGamepad;

    @Override
    public void initRobotClasses() {

        //Controllers
        driverGamepad = new GamepadEx(gamepad1);

        //Mechanisms
        chassis = new Mecanum("ChassisMecanum", "Mechanism", mecanumConfig);
        manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);

    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(chassis);
        mechanismManager.addMechanism(manipulator);
    }

    @Override
    public void startRobot() {
    }

    @Override
    public void updateRobot(Double timeStep) {


        telemetry.addData("Relative Heading", chassis.imu.getHeading());
        telemetry.addData("Absolute Heading", chassis.imu.getAbsoluteHeading());

        telemetry.addData("Left X", driverGamepad.getLeftX());
        telemetry.addData("Right X", driverGamepad.getRightX());

        telemetry.addData("Left Y", driverGamepad.getLeftY());


        //Chassis Driver
//        if (driverGamepad.getLeftY() != 0 || driverGamepad.getLeftX() != 0 || driverGamepad.getRightX() != 0) {
//            chassis.setChassisMovement(
//                    driverGamepad.getLeftX() * -1,
//                    driverGamepad.getLeftY() * -1,
//                    driverGamepad.getRightX() * -1,
//                    Mecanum.orientation.field
//            );
//        }

        if (driverGamepad.getLeftY() != 0 ) {
            manipulator.turnOn(driverGamepad.getLeftY());
        }


    }
}
