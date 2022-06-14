package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.Servo;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

import java.util.*;
import java.util.List;

public class SingleServoMechanism extends EctoMechanism {

    public SingleServoMechanism(String moduleName, String moduleType, SingleServoMechanismConfig config){
        super(moduleName, moduleType);
        this.config = config;
    }

    SingleServoMechanismConfig config;
    ServoEx servo;

    public void set(double set, AngleUnit unit) {
        servo.turnToAngle(set / config.gearing, unit);

    }

    public double getAngle(){
        return servo.getAngle() / config.gearing;
    }

    @Override
    public void initMechanism() {
        servo = new SimpleServo(hardwareMap, config.servoId, config.minServoAngle, config.maxServoAngle, AngleUnit.DEGREES);
        servo.setInverted(config.isInverted);
    }

    @Override
    public void startMechanism() {;}

    @Override
    public void updateMechanism() {;}

    @Override
    public void stopMechanism() {;}


}