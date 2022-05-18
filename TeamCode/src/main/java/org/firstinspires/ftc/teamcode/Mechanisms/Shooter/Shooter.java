
package org.firstinspires.ftc.teamcode.Mechanisms.Shooter;


import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.armConfig;

import java.util.*;
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.controller.wpilibcontroller.SimpleMotorFeedforward;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import org.firstinspires.ftc.robotcore.external.navigation.CurrentUnit;
import com.sun.tools.javac.comp.Check;

import org.checkerframework.checker.units.qual.Current;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.slf4j.Logger;

public class Shooter extends EctoMechanism {

    public Shooter(String moduleName, String moduleType, ShooterConfig config){
        super(moduleName, moduleType);
        this.config = config;
    }

    ShooterConfig config;
    MotorEx motor;
    Boolean usePID;
    PIDFController pidfController;
    SimpleMotorFeedforward ff;
    Double setVel;
    Boolean enableMotorPID = ShooterConfig.enableMotorPID;
    Double maxCurrent = ShooterConfig.maxCurrent;

    public double lastSetPoint = 0;

    public double getCurrentPosition() {
        return motor.getCurrentPosition();
    }

    public double getVel(){
        return motor.getVelocity() / config.gearing;
    }

    public void set(double vel) {
        setVel = (vel / config.gearing);
    }

    public void resetEncoder() {
        motor.encoder.reset();
    }

    public void usePIDController(Boolean set){
        usePID = set;
    }

//    public double getMotorCurrent(){
//        return testMotor.getCurrent(CurrentUnit.MILLIAMPS);
//    }


    @Override
    public void initMechanism() {

        motor = new MotorEx(hardwareMap, config.motorId, config.motorCPR, config.motorRPMs);
        motor.setInverted(config.isInverted);

        if (enableMotorPID) {
            motor.setRunMode(Motor.RunMode.VelocityControl);
            motor.setFeedforwardCoefficients(ShooterConfig.kS, ShooterConfig.kV, ShooterConfig.kA);
            motor.setVeloCoefficients(ShooterConfig.p, ShooterConfig.i, ShooterConfig.d);
        }else {
            pidfController = new PIDFController(ShooterConfig.p, ShooterConfig.i, ShooterConfig.d, ShooterConfig.f);
            pidfController.setTolerance(config.velocityErrorTolerance, config.positionErrorTolerance);
            ff = new SimpleMotorFeedforward(ShooterConfig.kV, ShooterConfig.kS, ShooterConfig.kA);

            motor.setRunMode(Motor.RunMode.RawPower);
        }



    }
    @Override
    public void startMechanism() {
        motor.set(0);
    }
    @Override
    public void updateMechanism() {
        if (!usePID) {
            if (enableMotorPID) {
                motor.set(setVel);
            }else{
                double pidOut = pidfController.calculate(motor.getCurrentPosition() / config.gearing);
                double ffOut = ff.calculate(motor.getVelocity() / config.gearing);
                motor.set(pidOut + ffOut);
            }
        }
    }
    @Override
    public void stopMechanism() {motor.stopMotor();}

}