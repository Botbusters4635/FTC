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

public class DualServoMechanism extends EctoMechanism {

    public DualServoMechanism(String moduleName, String moduleType, DualServoMechanismConfig config){
        super(moduleName, moduleType);
        this.config = config;
    }

    DualServoMechanismConfig config;
    ServoEx firstServo;
    ServoEx secondServo;

    public void set(double set, AngleUnit unit) {
        firstServo.turnToAngle(set / config.gearing, unit);
        secondServo.turnToAngle(set / config.gearing, unit);

    }

    public double getAngle(String servoId){
        if (Objects.equals(servoId, config.firstServoId)){
            return firstServo.getAngle();
        }else{
            return secondServo.getAngle();
        }
    }

    @Override
    public void initMechanism() {
        firstServo = new SimpleServo(hardwareMap, config.firstServoId, config.minServoAngle, config.maxServoAngle, AngleUnit.DEGREES);
        firstServo.setInverted(config.firstIsInverted);
        secondServo = new SimpleServo(hardwareMap, config.secondServoId, config.minServoAngle, config.maxServoAngle, AngleUnit.DEGREES);
        secondServo.setInverted(config.secondIsInverted);
    }

    @Override
    public void startMechanism() {;}

    @Override
    public void updateMechanism() {;}

    @Override
    public void stopMechanism() {;}


}