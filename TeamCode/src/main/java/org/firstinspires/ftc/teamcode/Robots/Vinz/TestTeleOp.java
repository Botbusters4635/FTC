package org.firstinspires.ftc.teamcode.Robots.Vinz;

import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.MechanismsConfig.mecanumConfig;

import android.widget.Spinner;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
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

    Manipulator arm;

    Intake intake;

    Spinner spinner;

    //Controllers
    public static GamepadEx driverGamepad;

    @Override
    public void initRobotClasses() {

        //Controllers
        driverGamepad = new GamepadEx(gamepad1);

        //Mechanisms
        chassis = new Mecanum("ChassisMecanum", "Mechanism", mecanumConfig);
        arm = new Manipulator("arm", "Mechanism", manipulatorConfig);
        manipulator = new Manipulator("Manipulator", "Mechanism", armConfig);

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
        //Chassis Driver
//        if (driverGamepad.getLeftY() != 0 || driverGamepad.getLeftX() != 0 || driverGamepad.getRightX() != 0) {
//            chassis.setChassisMovement(
//                    driverGamepad.getLeftX() * -1,
//                    driverGamepad.getLeftY() * -1,
//                    driverGamepad.getRightX() * -1,
//                    Mecanum.orientation.field
//            );
//        }


        //arm
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


        //manipulator
        if (driverGamepad.getLeftY() != 0 ) {
            arm.turnOn(
                    driverGamepad.getLeftY() * 1
            );
        } else if (driverGamepad.getLeftY() == 0 ) {
            arm.turnOn(0);
        }

    }
}
