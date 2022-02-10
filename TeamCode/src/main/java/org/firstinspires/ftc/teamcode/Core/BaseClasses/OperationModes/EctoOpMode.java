//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes;

import com.qualcomm.robotcore.eventloop.opmode.OpMode;

import org.firstinspires.ftc.teamcode.Core.Managers.MechanismManager;

abstract public class EctoOpMode extends OpMode {

    Double lastTimeRunned = 0.0;

    int updateRate = 10; //Milliseconds

    public MechanismManager mechanismManager;

    @Override
    public final void init() {
        mechanismManager = new MechanismManager();
        initRobotClasses();
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
    public final void start() {
        lastTimeRunned = getRuntime();
        mechanismManager.startMechanisms();
        startRobot();

    }

    @Override
    public final void loop() {
        mechanismManager.updateMechanisms();
        double timeStep = getRuntime() - lastTimeRunned;
        lastTimeRunned = getRuntime();
        updateRobot(timeStep);
    }


    /*
    - Runs Once Press Stop
    - Stops Everything.
    */
    public final void stop() {
        mechanismManager.stopMechanisms();
    }


    /*
    - Runs Once You Select The OP Mode On The Driver Station
    - Used To Create The Objects Used In The Tele-Op Mode
    */
    abstract public void initRobotClasses();


    /*
    - Runs When You Click "Init" On The Driver Station
    - Used To Add Mechanism To The Mechanism Manager
    */
    abstract public void initRobot();


    /*
    - Runs When You click "Start" On The Driver Station
    - Used To Move Certain Mechanims To Their Starting Point
    */
    abstract public void startRobot();


    /*
    - Runs After You Click "Start" On The Driver Station
    - Used To Get The Input From The Controllers and "Control" The Mechanisms
    - Runs Every 10 Miliseconds
    */
    abstract public void updateRobot(Double timeStep);

}
