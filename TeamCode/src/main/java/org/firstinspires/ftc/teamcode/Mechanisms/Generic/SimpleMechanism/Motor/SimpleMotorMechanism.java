package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class SimpleMotorMechanism extends EctoMechanism {

    public SimpleMotorMechanism(String moduleName, String moduleType, SimpleMotorMechanismConfig config) {
        super(moduleName, moduleType);
        getConfig = config;
    }

    SimpleMotorMechanismConfig getConfig;
    MotorEx motor;
    Double getSet = 0.0;

    public void set(double set){
        getSet = set;
    }

    @Override
    public void initMechanism() {
        motor = new MotorEx(hardwareMap, getConfig.getMotorId, getConfig.getMotorCPR, getConfig.getMotorCPR);
        motor.setInverted(getConfig.getIsInverted);
        motor.setRunMode(Motor.RunMode.RawPower);
    }

    @Override
    public void startMechanism() {;}

    @Override
    public void updateMechanism() {
        motor.set(getSet);
    }

    @Override
    public void stopMechanism() {
        motor.set(0);
    }

}