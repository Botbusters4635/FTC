package org.firstinspires.ftc.teamcode.Sensors.GamePieceDetector;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.EctoMechanism;
import org.firstinspires.ftc.teamcode.Core.Utils.Sensors.ColorDetectorSensor;

public class GamePieceDetector extends EctoMechanism {

  public GamePieceDetector(String moduleName, String moduleType, GamePieceDetectorConfig config) {
    super(moduleName, moduleType);
    this.config = config;
  }

  GamePieceDetectorConfig config;
  ColorDetectorSensor colorSensor;

  int colorValue = 0;

  public boolean gamePieceDetected() {
      return colorValue > config.getMinimalColorValue;
  }

  @Override
  public void initMechanism() {
    colorSensor = new ColorDetectorSensor(hardwareMap, config.getColorSensorId);
  }

  @Override
  public void startMechanism() {}

  @Override
  public void updateMechanism() {
    colorValue = colorSensor.red();
  }

  @Override
  public void stopMechanism() {}
}
