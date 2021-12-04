package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;


@TeleOp(name = "TeleOp")
public class RobotTeleOp extends EctoOpMode {

    //Mechanisms
    Mecanum chassis;
    Arm arm;

    //Controllers
    GamepadEx driverController;

    @Override
    public void initRobotClasses() {

        //Mechanisms
        chassis = new Mecanum("ChassisMecanum", "Mechanism", RobotConfig.mecanumConfig);
        arm = new Arm("Arm","Mechanism",RobotConfig.armConfig);

        //Controllers
        driverController = new GamepadEx(gamepad1);

    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(chassis);
        mechanismManager.addMechanism(arm);
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
        if(driverController.getButton( GamepadKeys.Button.A)){
            arm.setPosition(49);
        }
        if(driverController.getButton(GamepadKeys.Button.B)){
            arm.setPosition(100);
        }


    }
}
