package org.firstinspires.ftc.teamcode.Core.Manager;
import android.os.SystemClock;

import com.qualcomm.robotcore.hardware.HardwareMap;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.Mechanism;

import java.util.ArrayList;

public class MechanismManager {
    //CONSTRUCTORS
    MechanismManager(Telemetry telemetry, HardwareMap hardwareMap) {
        this.hardwareMap = hardwareMap;
        this.telemetry = telemetry;
    }

    //VARIABLES
    private  HardwareMap hardwareMap;
    private Telemetry telemetry;
    ArrayList<Mechanism> mechanisms;
    ArrayList<Double> lastTimeRunned;

    //VOIDS/ FUNCTIONS
    void addMechanism(Mechanism mechanism){
        mechanisms.add(mechanism);
        lastTimeRunned.add(0.0);
    }

    void initMechanisms() {
        for (int currentIndex = 0; currentIndex < mechanisms.size(); currentIndex++) {
            mechanisms.get(currentIndex).setupMechanism(hardwareMap, telemetry);
            mechanisms.get(currentIndex).initMechanism();
            lastTimeRunned.set(currentIndex, (double) SystemClock.elapsedRealtime());
        }
    }

    void startMechanisms() {
        for (int currentIndex = 0; currentIndex < mechanisms.size(); currentIndex++) {
            mechanisms.get(currentIndex).startMechanism();
        }
    }

    void updateMechanisms(){
        for (int currentIndex = 0; currentIndex < mechanisms.size(); currentIndex++) {
            Double timeStep = SystemClock.elapsedRealtime() / 1000.0 - lastTimeRunned.get(currentIndex);
            lastTimeRunned.set(currentIndex, (double) SystemClock.elapsedRealtime());
            mechanisms.get(currentIndex).updateMechanism();
        }
    }

    void stopMechanisms() {
        for (int currentIndex = 0; currentIndex < mechanisms.size(); currentIndex++) {
            mechanisms.get(currentIndex).stopMechanism();
        }
    }


}
