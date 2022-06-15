package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.robotcore.external.navigation.AngleUnit;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Utils.Sensors.IntegratedIMU;
import org.firstinspires.ftc.teamcode.Core.Utils.SlewRateLimiter.RateLimiter;

public class Pushbot extends EctoMechanism {

  public Pushbot(String moduleName, String moduleType, PushbotConfig config) {
    super(moduleName, moduleType);
    pushbotConfig = config;
  }

  PushbotConfig pushbotConfig;
  PIDFController pidf;
  PIDFController yawPid;

  public IntegratedIMU imu;

  boolean usePids;

  private MotorEx leftMotor;
  private MotorEx rightMotor;

  private MotorGroup leftMotors;
  private MotorGroup rightMotors;

  private MotorGroup allMotors;

  public RateLimiter rateLimiter;

  DifferentialDrive pushbot;

  public double ticksToMeters(double ticks){
    return (ticks / pushbotConfig.ticksPerRev) * pushbotConfig.wheelCircumference;
  }

  public double metersToTicks(double meters){
    return (meters / pushbotConfig.wheelCircumference) * pushbotConfig.ticksPerRev;
  }

  public void usePIDController (boolean usePids){
    this.usePids = usePids;
  }
  public double getEncoder(){
    return rightMotor.getCurrentPosition();
  }

  public double getPose(){
    return ticksToMeters(rightMotor.getCurrentPosition());
  }

  public void movdeForward(double setPointInMeters) {
    allMotors.resetEncoder();
    double setPoint = metersToTicks(setPointInMeters);
    pidf.setSetPoint(setPoint);
  }

  public void turnToAngle(double angle){
    yawPid.setSetPoint(angle);
  }

  public void setChassisMovement(double forwardSpeed, double turnSpeed) {
    allMotors.setRunMode(MotorEx.RunMode.RawPower);
    pushbot.arcadeDrive(-forwardSpeed, turnSpeed);
  }

  public double getVel(){
    return (rightMotors.getVelocity() + -leftMotors.getVelocity()) / 2;
  }

  public void stopChassis() {
    pushbot.stop();
  }

  @Override
  public void initMechanism() {

    pidf = new PIDFController(PushbotConfig.p, PushbotConfig.i, PushbotConfig.d, PushbotConfig.f);
    pidf.setTolerance(30);

    yawPid = new PIDFController(PushbotConfig.yawP, PushbotConfig.yawI, PushbotConfig.yawD, PushbotConfig.yawF);
    yawPid.setTolerance(0.1);

    imu = new IntegratedIMU(hardwareMap, pushbotConfig.imuId);
    imu.initSensor();

    leftMotor = new MotorEx(hardwareMap, pushbotConfig.getLeftId);
    rightMotor = new MotorEx(hardwareMap, pushbotConfig.getRightId);

    leftMotors = new MotorGroup(leftMotor);
    rightMotors = new MotorGroup(rightMotor);

    allMotors = new MotorGroup(rightMotor, leftMotor);

    pushbot = new DifferentialDrive(leftMotors, rightMotors);

    allMotors.setRunMode(Motor.RunMode.RawPower);
    rateLimiter = new RateLimiter(PushbotConfig.rateLimit, 0.0, 1.0);
  }

  @Override
  public void startMechanism() {}

  @Override
  public void updateMechanism() {
    if (usePids){
      double PIDoutput = pidf.calculate(rightMotor.getCurrentPosition());
      double yawOut = yawPid.calculate(imu.getAbsoluteHeading());
      double output = rateLimiter.calculate(PIDoutput);

      pidf.setPIDF(PushbotConfig.p, PushbotConfig.i, PushbotConfig.d, PushbotConfig.f);
      yawPid.setPIDF(PushbotConfig.yawP, PushbotConfig.yawI, PushbotConfig.yawD, PushbotConfig.yawF);

      if (!pidf.atSetPoint()) {
        rightMotors.set(output);
        leftMotors.set(-output);
      }
    }else{
      ;
    }


  }

  @Override
  public void stopMechanism() {
    stopChassis();
  }
}
