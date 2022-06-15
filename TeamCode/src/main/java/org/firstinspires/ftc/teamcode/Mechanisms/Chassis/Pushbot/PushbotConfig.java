package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot;

import com.acmerobotics.dashboard.config.Config;

@Config
public class PushbotConfig {

    public  PushbotConfig(String leftId, String rightId, double cpr, double rpm, double wheelCircumference){

        getLeftId = leftId;
        getRightId = rightId;

        getCPR = cpr;
        getRPM = rpm;
        this.wheelCircumference = wheelCircumference;
        this.ticksPerRev = cpr;

    }

    public static double p = 0.0005;
    public static double i = 0.0;
    public static double d = 0.0;
    public static double f = 0.0;

    public static double rateLimit = 0.5;

    public String getLeftId;
    public String getRightId;
    public static double getCPR;
    public static double getRPM;
    public static double wheelCircumference;
    public static double ticksPerRev;

}
