package org.firstinspires.ftc.teamcode.Robots.Vinz;

import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.mecanumConfig;

import com.acmerobotics.dashboard.FtcDashboard;
import com.arcrobotics.ftclib.gamepad.GamepadEx;

import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.Telemetry;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;

import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;

@TeleOp(name = "TestTeleOp")
public class TestTeleOp extends EctoOpMode {

    FtcDashboard dashboard = FtcDashboard.getInstance();
    Telemetry dashboardTelemetry = dashboard.getTelemetry();

    //Mechanisms
    Mecanum chassis;

    Manipulator manipulator;

    Arm arm;

//    0.00259

    Intake intake;

    Spinner spinner;

    //Controllers
    public static GamepadEx driverGamepad;
    public static GamepadEx manipulatorGamepad;

    @Override
    public void initRobotClasses() {

        //Controllers
        manipulatorGamepad = new GamepadEx(gamepad2);
        driverGamepad = new GamepadEx(gamepad1);

        //Mechanisms
        chassis = new Mecanum("ChassisMecanum", "Mechanism", mecanumConfig);
        arm = new Arm("arm", "Mechanism", armConfig);
        manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);

    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(chassis);
        mechanismManager.addMechanism(manipulator);
        mechanismManager.addMechanism(arm);
    }

    @Override
    public void startRobot() {
    }

    @Override
    public void updateRobot(Double timeStep) {


        dashboardTelemetry.addData("Actual Position", arm.getActualPosition());
        dashboardTelemetry.addData("Target Position", arm.getTargetPosition());

        dashboardTelemetry.addData("Tolerable Target Position", arm.isAtTarget());


        dashboardTelemetry.update();

        ///////////////////////////
        //////// Chassis /////////
        //////////////////////////

        //
        //Chassis Driver
        //
        if (driverGamepad.getLeftY() != 0 || driverGamepad.getLeftX() != 0 || driverGamepad.getRightX() != 0) {
            chassis.setChassisMovement(
                    driverGamepad.getLeftX() * -1,
                    driverGamepad.getLeftY() * -1,
                    driverGamepad.getRightX() * -1,
                    Mecanum.orientation.field
            );
        } else {
            chassis.stopChassis();
        }

        ///////////////////////////
        /////// Manipulator //////
        //////////////////////////

        //
        //Manipulator
        //
        if (driverGamepad.getTrigger(Configuration.ButtonsConfig.driver.manipulatorIn) != 0) {
            manipulator.turnOn(
                    driverGamepad.getTrigger
                            (Configuration.ButtonsConfig.driver.manipulatorIn) * 1
            );
        } else {
            manipulator.turnOff();
        }

        if (driverGamepad.getTrigger(Configuration.ButtonsConfig.driver.manipulatorOut) != 0) {
            manipulator.turnOn(
                    driverGamepad.getTrigger
                            (Configuration.ButtonsConfig.driver.manipulatorOut) * -1
            );
        } else {
            manipulator.turnOff();
        }

        //
        //Arm
        //
        if (manipulatorGamepad.getButton(Configuration.ButtonsConfig.manipulator.highLevel)) {
            arm.setPosition(300);
        }

        if (manipulatorGamepad.getButton(Configuration.ButtonsConfig.manipulator.mediumLevel)) {
            arm.setPosition(150);
        }
        if (manipulatorGamepad.getButton(Configuration.ButtonsConfig.manipulator.lowLevel)) {
            arm.setPosition(50);
        }

        if (manipulatorGamepad.getButton(Configuration.ButtonsConfig.manipulator.homeLevel)) {
            arm.setPosition(0);
        }

        if (manipulatorGamepad.getButton(Configuration.ButtonsConfig.manipulator.adjustmentUp)){
            arm.setPosition(arm.lastSetPoint + 1);
        }

        if (manipulatorGamepad.getButton(Configuration.ButtonsConfig.manipulator.adjustmentUp)){
            arm.setPosition(arm.lastSetPoint + -1);
        }
    }
}