
package org.firstinspires.ftc.teamcode.Mechanisms.Spinner;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Mechanisms.intake.IntakeConfig;

public class Intake extends EctoMechanism {

    public Intake (String moduleName, String moduleType, org.firstinspires.ftc.teamcode.Mechanisms.intake.IntakeConfig config) {
        super(moduleName, moduleType);
        intakeConfig = config;
    }

    IntakeConfig intakeConfig;
    Motor intakeMotor;

    public void turnOn(){

            intakeMotor.set(1);

    }

    @Override
    public void initMechanism() {
        intakeMotor.setRunMode(Motor.RunMode.RawPower);
        intakeMotor = new Motor(hardwareMap, intakeConfig.getIntakeMotorId, intakeConfig.getGobildaType);
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
