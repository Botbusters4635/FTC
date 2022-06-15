//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Mecanum;

import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.gamepad.GamepadKeys;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.hardware.bosch.BNO055IMU;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Utils.Sensors.IntegratedIMU;

public class Mecanum extends EctoMechanism {

    public enum orientation {
        robot,
        field
    }


    public Mecanum(String moduleName, String moduleType, MecanumConfig config) {
        super(moduleName, moduleType);
        mecanumConfig = config;
    }

    public IntegratedIMU imu;

    private MotorEx frontLeft;
    private MotorEx backLeft;
    private MotorEx frontRight;
    private MotorEx backRight;

    private MotorGroup allMotors;


    private final MecanumConfig mecanumConfig;

    private MecanumDrive mecanum;


    public void setChassisMovement(double strafeSpeed, double forwardSpeed, double turnSpeed, orientation robotOrentationType) {
        allMotors.setRunMode(Motor.RunMode.RawPower);

        switch (robotOrentationType) {
            case field:
                mecanum.driveFieldCentric(strafeSpeed, forwardSpeed, turnSpeed, imu.getHeading());
                break;
            case robot:
                mecanum.driveRobotCentric(strafeSpeed, forwardSpeed, turnSpeed);
        }

    }

    public void resetHeading(){
        imu.reset();
    }

    public void stopChassis(){
        mecanum.stop();
    }

    public void applyBreak(double set){
        if (frontLeft.getVelocity() >= 0){
            frontLeft.set(set);
        }else{
            frontLeft.set(-set);
        }

        if (frontRight.getVelocity() <= 0){
            frontRight.set(set);
        }else{
            frontRight.set(-set);
        }

        if (backLeft.getVelocity() >= 0){
            backLeft.set(set);
        }else{
            backLeft.set(-set);
        }

        if (backRight.getVelocity() <= 0){
            backRight.set(set);
        }else{
            backRight.set(-set);
        }
    }

    public void headAlways(int errorTolerance, GamepadKeys button) {

        if (imu.getHeading() < errorTolerance * -1 && imu.getHeading() < 0) {
            setChassisMovement(0.0, 0.0, 1, orientation.robot);
        }

        if (imu.getHeading() > errorTolerance && imu.getHeading() > 0) {
            setChassisMovement(0.0, 0.0, -1, orientation.robot);
        }

    }

    @Override
    public void initMechanism() {

        imu = new IntegratedIMU(hardwareMap, mecanumConfig.getIMUId);
        imu.initSensor();

        frontLeft = new MotorEx(hardwareMap, mecanumConfig.getfrontLeftId, mecanumConfig.getGobildaType);
        backLeft = new MotorEx(hardwareMap, mecanumConfig.getbackLeftId, mecanumConfig.getGobildaType);
        frontRight = new MotorEx(hardwareMap, mecanumConfig.getfrontRightId, mecanumConfig.getGobildaType);
        backRight = new MotorEx(hardwareMap, mecanumConfig.getbackRightId, mecanumConfig.getGobildaType);

        allMotors = new MotorGroup(frontLeft, frontRight, backRight, backLeft);

        mecanum = new MecanumDrive(frontLeft, frontRight, backLeft, backRight);

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