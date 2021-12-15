package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;


@TeleOp(name = "StableOpMode")
public class TeleOperatedMode extends EctoOpMode {

    //Mechanisms
    Mecanum chassis;

    //Controllers
    GamepadEx driverController;

    @Override
    public void initRobotClasses() {

        //Mechanisms
        chassis = new Mecanum("ChassisMecanum", "Mechanism", Configuration.MechanismsConfig.mecanumConfig);

        //Controllers
        driverController = new GamepadEx(gamepad1);

    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(chassis);
    }

    @Override
    public void startRobot() {
    }

    @Override
    public void updateRobot(Double timeStep) {

        //Chassis Driver
        if (driverController.getLeftY() != 0 || driverController.getLeftX() != 0 || driverController.getRightX() != 0) {
            chassis.setChassisMovement(
                    driverController.getLeftX() * -1,
                    driverController.getLeftY() * -1,
                    driverController.getRightX() * -1,
                    Mecanum.orientation.field
            );
        }

    }
}
