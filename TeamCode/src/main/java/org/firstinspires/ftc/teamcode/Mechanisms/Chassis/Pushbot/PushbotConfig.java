package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot;

import com.acmerobotics.dashboard.config.Config;

@Config
public class PushbotConfig {

    public  PushbotConfig(String leftId, String rightId, double cpr, double rpm){

        getLeftId = leftId;
        getRightId = rightId;

        getCPR = cpr;
        getRPM = rpm;

    }

    public static double p = 0.1;
    public static double i = 0.0;
    public static double d = 0.0;
    public static double f = 0.0;

    public String getLeftId;
    public String getRightId;
    public static double getCPR;
    public static double getRPM;

}
