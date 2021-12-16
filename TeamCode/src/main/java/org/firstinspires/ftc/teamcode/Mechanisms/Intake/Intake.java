package org.firstinspires.ftc.teamcode.Mechanisms.Intake;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Intake extends EctoMechanism {

    public Intake (String moduleName, String moduleType, IntakeConfig config) {
        super(moduleName, moduleType);
        intakeConfig = config;
    }

    IntakeConfig intakeConfig;
    Motor intakeMotor;

    public void turnOn(double powerPecentage){

        intakeMotor.set(powerPecentage);
    }

    @Override
    public void initMechanism() {
        intakeMotor = new Motor(hardwareMap, intakeConfig.getIntakeMotorId, intakeConfig.getGobildaType);
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
    }

    @Override
    public void startMechanism() {}

    @Override
    public void updateMechanism() {}

    @Override
    public void stopMechanism() {
        intakeMotor.set(0);
    }

}
