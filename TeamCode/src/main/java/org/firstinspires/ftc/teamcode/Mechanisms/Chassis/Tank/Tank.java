//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Tank;

//MotorLib
import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.MecanumDrive;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

//Team code
import org.firstinspires.ftc.teamcode.Core.Mechanism;

class Config
{
    public PIDFController pidConfig;

    public MotorEx fL, fR, bL, bR;
};

public class Tank extends Mechanism {
    public Tank(String modName, String modType, Config config) {
        super(modName, modType);
        this.config = config;
    }

    private Config config;

    @Override
    public void initMechanism() {
        config.
    }

    @Override
    public void stopMechanism() {

    }

    @Override
    public void homeMechanism() {

    }

    @Override
    public Boolean isFinished() {
        return null;
    }


}
