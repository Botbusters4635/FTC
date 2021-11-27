package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank.Tank;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;

@TeleOp(name = "TeleOp")
public class RobotTeleOp extends EctoOpMode {

    MechanismManager mechanismManager;

    Mecanum chassis = new Mecanum("ChassisMecanum", "Mechanism", RobotConfig.mecanumConfig);

    Spinner spinner = new Spinner("Spinner", "Mechanism", RobotConfig.spinnerConfig);

    GamepadEx driverController = new GamepadEx(gamepad1);
    GamepadEx manipulatorController = new GamepadEx(gamepad2);

    @Override
    public void startRobot() {
        mechanismManager.addMechanism(chassis);
        mechanismManager.addMechanism(spinner);
//        mechanismManager.addMechanism(arm);
//        mechanismManager.addMechanism(intake);
    }

    @Override
    public void initRobot() {

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

        //Manipulator Driver
        if (manipulatorController.gamepad.dpad_down){
            spinner.turnOn(0.8);
        }
      
    }
}
