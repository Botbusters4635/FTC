//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core.BaseClasses;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;

abstract public class EctoOpMode extends OpMode {

    Double lastTimeRunned = 0.0;

    int updateRate = 10; //Milliseconds

    public MechanismManager mechanismManager = new MechanismManager();

    @Override
    public final void start() {
        lastTimeRunned = getRuntime();
        mechanismManager.startMechanisms();
        startRobot();
    }

    @Override
    public final void init() {
    }

    @Override
    public final void init_loop() {
        initRobot();
        telemetry.setMsTransmissionInterval(updateRate);
        mechanismManager.telemetry = telemetry;
        mechanismManager.hardwareMap = hardwareMap;
        mechanismManager.initMechanisms();
    }

    @Override
    public final void loop() {
        mechanismManager.updateMechanisms();
        double timeStep = getRuntime() - lastTimeRunned;
        lastTimeRunned = getRuntime();
        updateRobot(timeStep);
    }

    public final void stop() {
        mechanismManager.stopMechanisms();
    }


    abstract public void startRobot();

    abstract public void initRobot(); //Init

    abstract public void updateRobot(Double timeStep);

}
