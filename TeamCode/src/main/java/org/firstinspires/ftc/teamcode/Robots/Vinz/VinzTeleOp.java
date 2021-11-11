package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;
import org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank.Tank;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;

@TeleOp(name = "TeleOp")
class VinzTeleOp extends EctoOpMode {

    RobotConfig config;
    MechanismManager mechanismManager;

    Tank chassis = new Tank("Chassis-Tank", "Mechanism", config.tankConfig);
    Spinner spinner = new Spinner("Spinner", "Mechanism", config.spinnerConfig);

    GamepadEx driverGamepad = new GamepadEx(gamepad1);

    @Override
    public void startRobot() {
        mechanismManager.addMechanism(chassis);
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
            chassis.setMotorPower(gamepad1.left_stick_x, gamepad1.right_stick_x);
        }

        //Manipulator Driver

    }
}
