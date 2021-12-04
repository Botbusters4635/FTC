package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;


@TeleOp(name = "TeleOp")
public class RobotTeleOp extends EctoOpMode {

    //Mechanisms
    Mecanum chassis;
    Arm arm;
    Intake intake;
    Spinner spinner;

    //Controllers
    GamepadEx driverController;

    @Override
    public void initRobotClasses() {

        //Mechanisms
        chassis = new Mecanum("ChassisMecanum", "Mechanism", RobotConfig.mecanumConfig);
        arm = new Arm("Arm", "Mechanism", RobotConfig.armConfig);
        intake = new Intake("Intake", "Mechanism", RobotConfig.intakeConfig);
        spinner = new Spinner("Spinner", "Mechanism", RobotConfig.spinnerConfig);

        //Controllers
        driverController = new GamepadEx(gamepad1);

    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(chassis);
        mechanismManager.addMechanism(arm);
        mechanismManager.addMechanism(spinner);
        mechanismManager.addMechanism(intake);
    }

    @Override
    public void startRobot() {
    }

    @Override
    public void updateRobot(Double timeStep) {

        //Chassis Driver
        if (driverController.getLeftY() != 0 || driverController.getLeftX() != 0 || driverController.getRightX() != 0) {
            chassis.setChassisMovement(
                    driverController.getLeftX(),
                    driverController.getLeftY(),
                    driverController.getRightX()
            );
        }

        //Manipulator Driver

        //Sets Arm Position To Angle 49 When  A is Pressed
        if(driverController.getButton(GamepadKeys.Button.A)){
            arm.setPosition(49);
        }

        //Sets Arm Position To Angle 100 When B is Pressed
        if(driverController.getButton(GamepadKeys.Button.B)){
            arm.setPosition(100);
        }

        //Sets Spinner On When X Is Pressed
        if(driverController.getButton(GamepadKeys.Button.X)){
            spinner.turnOn(1);
        }

        //Turns On Intake When Y Is Pressed,
        //And Turns Off When Its Not Pressed
        if(driverController.getButton(GamepadKeys.Button.Y)){
            intake.turnOn(1);
        }else if (!driverController.getButton(GamepadKeys.Button.Y)){
            intake.turnOn(.3);
        }
    }
}
