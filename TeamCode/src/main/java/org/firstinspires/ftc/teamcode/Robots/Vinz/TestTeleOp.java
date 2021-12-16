package org.firstinspires.ftc.teamcode.Robots.Vinz;

import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.mecanumConfig;

import android.widget.Spinner;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;


@TeleOp(name = "TestTeleOp")
public class TestTeleOp extends EctoOpMode {

    //Mechanisms
    Mecanum chassis;

    Manipulator manipulator;

    Arm arm;

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
        } else if (driverGamepad.getTrigger(Configuration.ButtonsConfig.driver.manipulatorIn) == 0) {
            manipulator.turnOn(
                    0
            );
        }

        if (driverGamepad.getTrigger(Configuration.ButtonsConfig.driver.manipulatorOut) != 0) {
            manipulator.turnOn(
                    driverGamepad.getTrigger
                            (Configuration.ButtonsConfig.driver.manipulatorOut) * -1
            );
        } else if (driverGamepad.getTrigger(Configuration.ButtonsConfig.driver.manipulatorOut) == 0) {
            manipulator.turnOn(
                    0
            );
        }

        //
        //Arm Buttons
        //
        if (manipulatorGamepad.getButton(Configuration.ButtonsConfig.manipulator.highLevel)) {
            arm.setPosition(1800);
        }

        if (manipulatorGamepad.getButton(Configuration.ButtonsConfig.manipulator.mediumLevel)) {
            arm.setPosition(900);
        }
    }
}