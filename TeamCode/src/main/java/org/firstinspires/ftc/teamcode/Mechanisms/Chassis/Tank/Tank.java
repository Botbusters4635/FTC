//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank;

//MotorLib
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

//Team code
import org.firstinspires.ftc.teamcode.Core.Mechanism;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Tank extends Mechanism {

    public static class TankConfig {

        public MotorEx fL, fR;
        public MotorEx bL, bR;

        public int numberOfMotorsPerSide = 2;

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

    TankConfig config;

    public List<MotorEx> leftMotors = new ArrayList<MotorEx>();
    public List<MotorEx> rightMotors = new ArrayList<MotorEx>();

    PIDFController pidf = new PIDFController(config.p, config.i, config.d, config.f);

    @Override
    public void setUpMechanism() {
        leftMotors.add(config.fR);
        leftMotors.add(config.fL);

        rightMotors.add(config.bR);
        rightMotors.add(config.bL);

        pidf.setTolerance(config.positionTolerance, config.velocityTolerance);


    }

    @Override
    public void stopMechanism() {
        for(int i=0;  i<config.numberOfMotorsPerSide;  i++){
            leftMotors.get(i).stopMotor();
            rightMotors.get(i).stopMotor();
        }
    }

    @Override
    public void homeMechanism() {

        for(int i=0; i<config.numberOfMotorsPerSide; i++){
            leftMotors.get(i).setTargetPosition(0);
            rightMotors.get(i).setTargetPosition(0);
        }

        while (!pidf.atSetPoint()){

            double output = pidf.calculate(
                    leftMotors.get(0).getCurrentPosition()
            );

            for(int i=0;i<config.numberOfMotorsPerSide;i++){
                leftMotors.get(i).setVelocity(output);
                rightMotors.get(i).setVelocity(output);
            }

        }




    }

    @Override
    public Boolean isFinished(Boolean state) {
        return state;
    }


}
