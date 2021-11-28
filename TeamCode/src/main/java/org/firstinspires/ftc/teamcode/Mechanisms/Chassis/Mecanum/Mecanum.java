//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Mecanum extends EctoMechanism {

    public Mecanum(String moduleName, String moduleType, MecanumConfig config) {
        super(moduleName, moduleType);
        mecanumConfig = config;
    }

    MecanumConfig mecanumConfig;

    public Motor frontLeft;
    public Motor backLeft;
    public Motor frontRight;
    public Motor backRight;

    public MotorGroup allMotors;

    public MecanumDrive mecanum;

    public void setChassisMovement(double strafeSpeed, double forwardSpeed, double turnSpeed) {
        allMotors.setRunMode(Motor.RunMode.RawPower);
        mecanum.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
    }

    @Override
    public void initMechanism() {

        frontLeft = new MotorEx(hardwareMap, mecanumConfig.getfrontLeftId , mecanumConfig.getGobildaType);
        backLeft = new MotorEx(hardwareMap, mecanumConfig.getbackLeftId, mecanumConfig.getGobildaType);
        frontRight = new MotorEx(hardwareMap, mecanumConfig.getfrontRightId, mecanumConfig.getGobildaType);
        backRight = new MotorEx(hardwareMap, mecanumConfig.getbackRightId, mecanumConfig.getGobildaType);

        allMotors.setRunMode(Motor.RunMode.RawPower);



        allMotors = new MotorGroup(frontLeft, frontRight, backRight, backLeft);

        //motor Setup
        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);
    }

    @Override
    public void startMechanism() {}

    @Override
    public void updateMechanism() {}

    @Override
    public void stopMechanism() {
        mecanum.stop();
    }

}
