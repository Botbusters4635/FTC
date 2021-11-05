//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank;

//MotorLib
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;
import com.qualcomm.robotcore.hardware.HardwareMap;

//Team code
import org.firstinspires.ftc.teamcode.Core.BaseClasses.Mechanism;

public class Tank extends Mechanism {

    public static class TankConfig {

        public Motor frontLeft, backLeft;
        public Motor frontRight, backRight;

        public MotorGroup leftMotors = new MotorGroup(frontLeft, backLeft);
        public MotorGroup rightMotors = new MotorGroup(frontRight, backRight);

        public MotorGroup allMotors = new MotorGroup(frontLeft, frontRight, backRight, backLeft);

        public DifferentialDrive tank = new DifferentialDrive(leftMotors, rightMotors);

        public int positionTolerance = 0;
        public int velocityTolerance = 0;

        public int p = 0;
        public int i = 0;
        public int d = 0;
        public int f = 0;

    }

    public Tank(String modName, String modType) {
        super(modName, modType);
    }

    TankConfig tankConfig;


    PIDFController pidfController = new PIDFController(tankConfig.p, tankConfig.i, tankConfig.d, tankConfig.f);

    public void setPercentage(double left, double right) {
        tankConfig.allMotors.setRunMode(Motor.RunMode.RawPower);
        tankConfig.tank.tankDrive(left, right);
    }

    @Override
    public void initMechanism() {
        tankConfig.allMotors.setRunMode(Motor.RunMode.RawPower);
        pidfController.setTolerance(tankConfig.positionTolerance, tankConfig.velocityTolerance);

    }

    @Override
    public void startMechanism() {

    }

    @Override
    public void updateMechanism() {

    }

    @Override
    public void stopMechanism() {
        tankConfig.tank.stop();
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
