package org.firstinspires.ftc.teamcode.Mechanisms.Shooter;

import com.acmerobotics.dashboard.config.Config;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;

import java.util.*;

@Config
public class ShooterConfig {
    public ShooterConfig(String motorId, double pulsesPerRevolution,
                         double velocityErrorTolerance,
                         double positionErrorTolerance,
                         double motorCPR,
                         double motorRPMs,
                         double numberOfMotors,
                         double rampRate,
                         double gearing,
                         boolean inInverted
    ){
        this.motorId = motorId;
        this.pulsesPerRevolution = pulsesPerRevolution;
        this.velocityErrorTolerance = velocityErrorTolerance;
        this.positionErrorTolerance = positionErrorTolerance;
        this.motorCPR = motorCPR;
        this.motorRPMs = motorRPMs;
//        this.numberOfMotors = numberOfMotors;
        this.rampRate = rampRate;
        this.gearing = gearing;
        this.isInverted = inInverted;
    }

    public static double p = 0.0;
    public static double i = 0.0;
    public static double d = 0.0;
    public static double f = 0.0;

    public static double kS = 0.0;
    public static double kV = 0.0;
    public static double kA = 0.0;

    public static boolean enableMotorPID;
    public static double maxCurrent;


    String motorId;
    Double pulsesPerRevolution;
    Double velocityErrorTolerance;
    Double positionErrorTolerance;
    Double motorCPR;
    Double motorRPMs;
    //    Double numberOfMotors;
    Double rampRate;
    Double gearing;
    Boolean isInverted;







}
