package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;


@TeleOp(name = "TeleOp")
public class RobotTeleOp extends EctoOpMode {

    MechanismManager mechanismManager;

    Mecanum chassis;

    GamepadEx driverController;

    @Override
    public void startRobot() {

        mechanismManager.addMechanism(chassis);

    }

    @Override
    public void initRobot() {
        mechanismManager = new MechanismManager(telemetry, hardwareMap);

        chassis = new Mecanum("ChassisMecanum", "Mechanism", RobotConfig.mecanumConfig);

        driverController = new GamepadEx(gamepad1);

    }

    @Override
    public void updateRobot(Double timeStep) {

        //Chassis Driver
        if (driverController.getLeftY() != 0 || driverController.getLeftX() != 0 || driverController.getRightX() != 0){
            chassis.setChassisMovement(
                    driverController.getLeftX(),
                    driverController.getLeftY(),
                    driverController.getRightX()
            );
        }


    }
}
