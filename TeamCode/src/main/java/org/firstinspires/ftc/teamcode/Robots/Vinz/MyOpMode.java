package org.firstinspires.ftc.teamcode.Robots.Vinz;

import com.arcrobotics.ftclib.gamepad.GamepadEx;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.qualcomm.robotcore.hardware.Gamepad;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;

public class MyOpMode extends EctoOpMode{
    MechanismManager mechanismManager;
    Intake intake;
    GamepadEx gamepadEx;

    @Override
    public void initRobotClasses() {

       gamepadEx = new GamepadEx(gamepad1);
        mechanismManager = new MechanismManager();
        intake = new Intake("Intake", "Mechanism", RobotConfig.intakeConfig);
    }

    @Override
    public void initRobot() {
        mechanismManager.addMechanism(intake);
    }

    @Override
    public void startRobot() {

    }

    @Override
    public void updateRobot(Double timeStep) {
if(gamepadEx.getButton(GamepadKeys.Button.A)){
    intake.turnOn(1);
        }else if (!gamepadEx.getButton(GamepadKeys.Button.A)){
    intake.turnOn(.3);
}
    }
}