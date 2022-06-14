package org.firstinspires.ftc.teamcode.Mechanisms.Chassis.Pushbot;

import com.arcrobotics.ftclib.controller.PIDFController;
import com.arcrobotics.ftclib.drivebase.DifferentialDrive;
import com.arcrobotics.ftclib.hardware.motors.Motor;
import com.arcrobotics.ftclib.hardware.motors.MotorEx;
import com.arcrobotics.ftclib.hardware.motors.MotorGroup;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Utils.Sensors.IntegratedIMU;

public class Pushbot extends EctoMechanism {

  public Pushbot(String moduleName, String moduleType, PushbotConfig config) {
    super(moduleName, moduleType);
    pushbotConfig = config;
  }

  PushbotConfig pushbotConfig;
  PIDFController pidf;
  boolean usePids;

  private MotorEx leftMotor;
  private MotorEx rightMotor;

  private MotorGroup leftMotors;
  private MotorGroup rightMotors;

  private MotorGroup allMotors;

  DifferentialDrive pushbot;

  public void usePIDController (boolean usePids){
    this.usePids = usePids;
  }

  public void movdeForward(double setPoint) {
    pidf.setSetPoint(setPoint);
  }

  public void setChassisMovement(double forwardSpeed, double turnSpeed) {
    allMotors.setRunMode(MotorEx.RunMode.RawPower);
    pushbot.arcadeDrive(-forwardSpeed, turnSpeed);
  }

  public void stopChassis() {
    pushbot.stop();
  }

  @Override
  public void initMechanism() {

    pidf = new PIDFController(PushbotConfig.p, PushbotConfig.i, PushbotConfig.d, PushbotConfig.f);
    pidf.setTolerance(30);

    leftMotor = new MotorEx(hardwareMap, pushbotConfig.getLeftId);
    rightMotor = new MotorEx(hardwareMap, pushbotConfig.getRightId);

    leftMotors = new MotorGroup(leftMotor);
    rightMotors = new MotorGroup(rightMotor);

    allMotors = new MotorGroup(rightMotor, leftMotor);

    pushbot = new DifferentialDrive(leftMotors, rightMotors);

    allMotors.setRunMode(Motor.RunMode.RawPower);
  }

  @Override
  public void startMechanism() {}

  @Override
  public void updateMechanism() {
    if (usePids){
      double PIDoutput = pidf.calculate(rightMotor.getCurrentPosition());

      pidf.setPIDF(PushbotConfig.p, PushbotConfig.i, PushbotConfig.d, PushbotConfig.f);

      if (!pidf.atSetPoint()) {
        rightMotors.set(PIDoutput * -1);
        leftMotors.set(PIDoutput);
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
