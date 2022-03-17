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

  public void up() {
    capperServo.rotateByAngle(2);
  }

  public void down() {
        capperServo.rotateByAngle(-2);
  }

  @Override
  public void initMechanism() {

    capperServo =
        new SimpleServo(
            hardwareMap, config.getCapperServoId, config.getMinServoAngle, config.getMaxServoAngle);

    capperServo.setInverted(true);

  }

  @Override
  public void startMechanism() {}

  @Override
  public void updateMechanism() {}

  @Override
  public void stopMechanism() {}
}
