/*
//created by Neil rodriguez 16/12/2021
*/

package org.firstinspires.ftc.teamcode.Mechanisms.Arm;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Arm extends EctoMechanism {

    public Arm(String moduleName, String moduleType, ArmConfig config) {
        super(moduleName, moduleType);
        armConfig = config;
    }


    ArmConfig armConfig;
    MotorEx armMotor;
    PIDFController pidf;

    public void setPosition(int setPoint) {
        double output = pidf.calculate(armMotor.getCurrentPosition(), setPoint);
        armMotor.setVelocity(output);
    }


    @Override
    public void initMechanism() {

        pidf = new PIDFController(armConfig.p, armConfig.i, armConfig.d, armConfig.f);
        armMotor = new MotorEx(hardwareMap, armConfig.getArmMotorId, armConfig.getGobildaType);

        armMotor.setRunMode(Motor.RunMode.VelocityControl);

        armMotor.setPositionTolerance(armConfig.getErrorTolerance);

        pidf.setTolerance(5, 10);

    }

    @Override
    public void startMechanism() {}

    @Override
    public void updateMechanism() {}

    @Override
    public void stopMechanism() {
        armMotor.set(0);
    }

}



