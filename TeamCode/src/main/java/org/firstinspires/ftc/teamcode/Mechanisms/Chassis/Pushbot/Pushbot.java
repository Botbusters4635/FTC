package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot;

import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Pushbot extends EctoMechanism {

    public Pushbot(String moduleName, String moduleType, PushbotConfig config){
        super(moduleName, moduleType);
        pushbotConfig = config;
    }

    PushbotConfig pushbotConfig;

    private MotorEx leftMotor;
    private MotorEx rightMotor;

    private MotorGroup leftMotors;
    private MotorGroup rightMotors;

    private MotorGroup allMotors;

    DifferentialDrive pushbot;

    public void setChassisMovement(double forwardSpeed, double turnSpeed){
        allMotors.setRunMode(MotorEx.RunMode.RawPower);
        pushbot.arcadeDrive(forwardSpeed, turnSpeed);
    }

    public void stopChassis(){
        pushbot.stop();
    }


    @Override
    public void initMechanism() {

        leftMotor = new MotorEx(hardwareMap, pushbotConfig.getLeftId);
        rightMotor = new MotorEx(hardwareMap, pushbotConfig.getRightId);

        leftMotors = new MotorGroup(leftMotor);
        rightMotors = new MotorGroup(rightMotor);

        allMotors = new MotorGroup(rightMotors, leftMotors);

        pushbot = new DifferentialDrive(leftMotors, rightMotors);

        allMotors.setRunMode(Motor.RunMode.RawPower);

    }

    @Override
    public void startMechanism() {

    }

    @Override
    public void updateMechanism() {

    }

    @Override
    public void stopMechanism() {
        stopChassis();
    }
}
