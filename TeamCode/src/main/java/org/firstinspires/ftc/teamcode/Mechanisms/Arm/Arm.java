package org.firstinspires.ftc.teamcode.Mechanisms.Arm;

import com.arcrobotics.ftclib.hardware.motors.Motor;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;


public class Arm extends EctoMechanism {

    public Arm (String moduleName, String moduleType, org.firstinspires.ftc.teamcode.Mechanisms.Arm.ArmConfig config) {
        super(moduleName, moduleType);
        armConfig = config;
    }

    ArmConfig armConfig;
    Motor armMotor;

    public  void setPosition(double angle) {
        double e = armConfig.getMotorTicks / 360;
        angle = angle * e;
        armMotor.setTargetPosition((int) angle);
    }


    @Override
    public void initMechanism() {
        armMotor = new Motor(hardwareMap, armConfig.getArmMotorId, armConfig.getGobildaType);
        armMotor.setRunMode(Motor.RunMode.PositionControl);
        armMotor.setPositionCoefficient(armConfig.p);
        armMotor.setPositionTolerance(armConfig.getErrorTolerance);
    }

    @Override
    public void startMechanism() {}

    @Override
    public void updateMechanism() {}

    @Override
    public void stopMechanism() {
        armMotor.set(0);
    }

}



