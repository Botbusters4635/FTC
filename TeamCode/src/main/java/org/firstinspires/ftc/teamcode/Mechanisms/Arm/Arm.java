/*
//created by Neil rodriguez 16/12/2021
*/

package org.firstinspires.ftc.teamcode.Mechanisms.Arm;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.ArmFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Arm extends EctoMechanism {

    public Arm(String moduleName, String moduleType, ArmConfig config) {
        super(moduleName, moduleType);
        armConfig = config;
    }


    ArmFeedforward feedforward;

    ArmConfig armConfig;
    MotorEx armMotor;
    PIDFController pidf;

    public double lastSetPoint = 0.0;

    public double getActualPosition() {
        return armMotor.getCurrentPosition();
    }

    public double getTargetPosition() {
        return lastSetPoint;
    }

    public boolean isAtTarget() {
        return pidf.atSetPoint();
    }

    public void setPosition(double setPoint) {
        lastSetPoint = setPoint;
        pidf.setSetPoint(setPoint);
    }


    @Override
    public void initMechanism() {


        pidf = new PIDFController(ArmConfig.p, ArmConfig.i, ArmConfig.d, ArmConfig.f);
        pidf.setTolerance(armConfig.getPositionErrorTolerance, armConfig.getVelocityErrorTolerance);

        armMotor = new MotorEx(hardwareMap, armConfig.getArmMotorId, armConfig.getGobildaType);
        armMotor.setRunMode(Motor.RunMode.RawPower);



    }

    @Override
    public void startMechanism() {
    }

    @Override
    public void updateMechanism() {

        pidf.setPIDF(ArmConfig.p, ArmConfig.i, ArmConfig.d, ArmConfig.f);


        double PIDoutput = pidf.calculate(
                armMotor.getCurrentPosition()
        );

        if (!pidf.atSetPoint()){
            armMotor.set(PIDoutput);
        }

    }

    @Override
    public void stopMechanism() {
        armMotor.set(0);
    }

}



