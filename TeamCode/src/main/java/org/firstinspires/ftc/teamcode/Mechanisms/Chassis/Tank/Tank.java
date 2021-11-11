//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank;

//MotorLib
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

//Team code
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Tank extends EctoMechanism {

    public Tank(String modName, String modType, TankConfig config) {
        super(modName, modType);
        tankConfig = config;
    }

    TankConfig tankConfig;

    public Motor frontLeft, backLeft;
    public Motor frontRight, backRight;

    public MotorGroup leftMotors;
    public MotorGroup rightMotors;

    public MotorGroup allMotors;

    public DifferentialDrive tank;

    PIDFController pidfController = new PIDFController(tankConfig.p, tankConfig.i, tankConfig.d, tankConfig.f);

    public void setMotorPower(float left, float right) {
        allMotors.setRunMode(Motor.RunMode.RawPower);
        tank.tankDrive(left, right);
    }

    @Override
    public void initMechanism() {

        allMotors.setRunMode(Motor.RunMode.RawPower);
        pidfController.setTolerance(tankConfig.positionTolerance, tankConfig.velocityTolerance);

        //Motor Setup
        frontLeft = new MotorEx(hardwareMap, tankConfig.getfrontLeftId, tankConfig.getGobildaType);
        backLeft = new MotorEx(hardwareMap, tankConfig.getbackLeftId, tankConfig.getGobildaType);
        frontRight = new MotorEx(hardwareMap, tankConfig.getfrontRightId, tankConfig.getGobildaType);
        backRight = new MotorEx(hardwareMap, tankConfig.getbackRightId, tankConfig.getGobildaType);

        leftMotors = new MotorGroup(frontLeft, backLeft);
        rightMotors = new MotorGroup(frontRight, backRight);

        allMotors = new MotorGroup(frontLeft, frontRight, backRight, backLeft);

        tank = new DifferentialDrive(leftMotors, rightMotors);
    }



    @Override
    public void startMechanism() {}

    @Override
    public void updateMechanism() {
    //Ododmetria para cuando quiera
    }

    @Override
    public void stopMechanism() {
        tank.stop();
    }

//    @Override
//    public void homeMechanism() {
//PARA CUANDO NECECITES LO DE POSISCION
//        tankConfig.allMotors.setRunMode(Motor.RunMode.PositionControl);
//        tankConfig.allMotors.setTargetPosition(tankConfig.homePosition);
//
//        while (!tankConfig.allMotors.atTargetPosition()){
//            tankConfig.allMotors.set(0.75);
//        }
//
//    }


}
