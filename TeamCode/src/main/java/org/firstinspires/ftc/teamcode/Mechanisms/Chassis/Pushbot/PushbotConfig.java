package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot;

public class PushbotConfig {

    public  PushbotConfig(String leftId, String rightId, double cpr, double rpm){

        getLeftId = leftId;
        getRightId = rightId;

        getCPR = cpr;
        getRPM = rpm;

    }

    public String getLeftId;
    public String getRightId;
    public static double getCPR;
    public static double getRPM;

}
