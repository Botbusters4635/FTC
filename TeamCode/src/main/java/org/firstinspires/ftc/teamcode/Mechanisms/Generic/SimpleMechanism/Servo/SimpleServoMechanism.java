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

public class SimpleServoMechanism extends EctoMechanism {

    public SimpleServoMechanism(String moduleName, String moduleType, SimpleServoMechanismConfig config){
        super(moduleName, moduleType);
        this.config = config;
    }

    SimpleServoMechanismConfig config;
    Vector<ServoEx> servos;
    Vector<String> servoIds = config.servoIds;

    public void set(double set, AngleUnit unit) {
        for (int i = 0; i < servoIds.size(); i++){
            servos.get(i).turnToAngle(set, unit);
        }
    }

    @Override
    public void initMechanism() {
        for (int i = 0; i < servoIds.size(); i++){
            servos.add(new SimpleServo(hardwareMap, servoIds.get(i), config.minServoAngle.get(i), config.maxServoAngle.get(i), AngleUnit.DEGREES));
            servos.get(i).setInverted(config.isInverted.get(i));
        }
    }

    @Override
    public void startMechanism() {;}

    @Override
    public void updateMechanism() {;}

    @Override
    public void stopMechanism() {;}


}
