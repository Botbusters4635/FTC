package org.firstinspires.ftc.teamcode.Mechanisms.Generic.SimpleMechanism.Servo;


import com.acmerobotics.dashboard.config.Config;

import java.util.List;
import java.util.Vector;


@Config
public class SimpleServoMechanismConfig {
    public SimpleServoMechanismConfig(Vector<String> servoIds,
                                      double numberOfServos,
                                      Vector<Double> maxServoAngle,
                                      Vector<Double> minServoAngle,
                                      double gearing,
                                      Vector<Boolean> isInverted
    ){
        this.servoIds = servoIds;
        this.numberOfServos = numberOfServos;
        this.maxServoAngle = maxServoAngle;
        this.minServoAngle = minServoAngle;
        this.gearing = gearing;
        this.isInverted = isInverted;

    }

    Vector<String> servoIds;
    Double numberOfServos;
    Vector<Double> maxServoAngle;
    Vector<Double> minServoAngle;
    Double gearing;
    Vector<Boolean> isInverted;

}
