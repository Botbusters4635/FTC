package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum.Mecanum;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank.Tank;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;

@TeleOp(name = "TeleOp")
class RobotTeleOp extends EctoOpMode {

    MechanismManager mechanismManager;

    Mecanum chassis = new Mecanum("ChassisMecanum", "Mechanism", RobotConfig.mecanumConfig);
    Spinner spinner = new Spinner("Spinner", "Mechanism", RobotConfig.spinnerConfig);

    GamepadEx driverGamepad = new GamepadEx(gamepad1);
    GamepadEx manipulatorGamepad = new GamepadEx(gamepad2);

    @Override
    public void startRobot() {
        mechanismManager.addMechanism(chassis);
        mechanismManager.addMechanism(spinner);
        //mechanismManager.addMechanism(arm);
        //mechanismManager.addMechanism(intake);
    }

    @Override
    public void initRobot() {

    }

    @Override
    public void updateRobot(Double timeStep) {

        //Chassis Driver
        if (driverGamepad.getLeftY() != 0 || driverGamepad.getRightY() != 0){
        }

        //Manipulator Driver
        if (manipulatorGamepad.gamepad.dpad_down){
            spinner.turnOn(0.8);
        }
    }
}
