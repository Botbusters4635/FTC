package org.firstinspires.ftc.teamcode.Mechanisms.Capper;

import com.arcrobotics.ftclib.hardware.ServoEx;
import com.arcrobotics.ftclib.hardware.SimpleServo;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;

public class Capper extends EctoMechanism {

  public Capper(String moduleName, String moduleType, CapperConfig config) {
    super(moduleName, moduleType);
    this.config = config;
  }

  CapperConfig config;
  ServoEx capperServo;

  int targetPosition = 0;

  public void up() {
    targetPosition++;
    capperServo.setPosition(targetPosition);
  }

  public void down() {
    targetPosition--;
    capperServo.setPosition(targetPosition);
  }


  @Override
  public void initMechanism() {
    capperServo = new SimpleServo(hardwareMap, config.getCapperServoId, 0, 360);
  }

  @Override
  public void startMechanism() {}

  @Override
  public void updateMechanism() {}

  @Override
  public void stopMechanism() {}

}
