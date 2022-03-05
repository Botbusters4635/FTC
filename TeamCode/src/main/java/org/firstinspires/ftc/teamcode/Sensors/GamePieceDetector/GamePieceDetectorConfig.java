package org.firstinspires.ftc.teamcode.Sensors.GamePieceDetector;

public class GamePieceDetectorConfig {

    public GamePieceDetectorConfig(String ColorSensorId, int minimalColorValue) {
        getColorSensorId = ColorSensorId;
        getMinimalColorValue = minimalColorValue;
    }

    int getMinimalColorValue;
    String getColorSensorId;

}
