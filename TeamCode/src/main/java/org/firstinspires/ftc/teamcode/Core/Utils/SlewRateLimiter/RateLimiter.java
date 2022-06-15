package org.firstinspires.ftc.teamcode.Core.Utils.SlewRateLimiter;

import static com.arcrobotics.ftclib.util.MathUtils.clamp;

import com.qualcomm.robotcore.util.ElapsedTime;

import java.util.concurrent.TimeUnit;

public class RateLimiter {

    public RateLimiter(double rateLimit, double initalValue, double modifier){
        this.rateLimit = rateLimit;
        this.modifier = modifier;
        this.initalValue = initalValue;
        this.prevTime = time.now(TimeUnit.MILLISECONDS);

    };

    double rateLimit, initalValue, prevTime;
    ElapsedTime time = new ElapsedTime();
    double prevVal;
    double modifier;

    public void setRateLimit(double rateLimit){
        this.rateLimit = rateLimit;
    }

    public void setRateModifier(double modifier){
        this.modifier = modifier;
    }

    public double calculate(double input) {
        rateLimit *= modifier;
        double currentTime = time.now(TimeUnit.MILLISECONDS);
        double elapsedTime = currentTime - prevTime;
        prevVal += clamp((input - prevVal), (-rateLimit * elapsedTime), (rateLimit*elapsedTime));
        prevTime = currentTime;
        return prevVal;

    }

    public void reset(double value){
        prevVal = value;
        prevTime = time.now(TimeUnit.MILLISECONDS);
    }
}