//cc
package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Motor;

import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class SimpleMotorMechanism extends EctoMechanism {

    public SimpleMotorMechanism(String moduleName, String moduleType, SimpleMotorMechanismConfig config) {
        super(moduleName, moduleType);
        this.config = config;
    }

    SimpleMotorMechanismConfig config;
    MotorEx motor;
    Double set;

    public void set(double set){
        this.set = set;
    }

    @Override
    public void initMechanism() {
        motor = new MotorEx(hardwareMap, config.motorId, config.motorCPR, config.motorRPMs);
        motor.setInverted(config.isInverted);
        motor.setRunMode(Motor.RunMode.RawPower);
    }

    @Override
    public void startMechanism() {motor.set(0);}

    @Override
    public void updateMechanism() {motor.set(set);}

    @Override
    public void stopMechanism() {motor.set(0);}

}
