package org.firstinspires.ftc.teamcode.TFTest;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import java.util.List;

@TeleOp(name = "TFOD")
public class TEST extends LinearOpMode {

  private static final String TFOD_MODEL_ASSET = "model_20220201_082128.tflite";
  private static final String[] LABELS = {"TSE"};
  private static final String VUFORIA_KEY = "AcPYhx//////AAABmUFaV20z2EmQkqbvhi5zgVyC8RDLUdcdScdfOzcbH3fA+pSEVZWlNIF+Ut/4VmqGbuk7uFqip+a3n8/B0pq1/CI+gVsV+uBZZ1CGA6DmLbDMwNu0jXJGH13oetb0LsQVjQXKVn5UNHDs3NSuZIElqucqsKVFjd4/UiYMTjV6mlkb9wtuZ2DXUThYi65NaqdEH9erho/RsjeoAhRbHBiHWZacLOT54Sv3TFt+QdWR9RvvDZewNSaP9bYCE8322fjmqdKd7qjT/jNEgJsM4G3qA9QqpOueIGbxKVJY5nN5vBLWruEdo/aH0C1pnxMzVTlQIFQjhALfyGHRCBSBWujGcXQq59onVkH/fnzg07TBLdFo ";

  private VuforiaLocalizer vuforia;
  private TFObjectDetector tfod;

  @Override
  public void runOpMode() {

    initVuforia();
    initTfod();

    if (tfod != null) {
      tfod.activate();
      tfod.setZoom(1, 16.0 / 9.0);
    }

    telemetry.addData(">", "Press Play to start op mode");
    telemetry.update();

    //Before Play Clicked


    waitForStart();

    if (opModeIsActive()) {
      while (opModeIsActive()) {
        if (tfod != null) {

          List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
          if (updatedRecognitions != null) {

            telemetry.addData("# Object Detected", updatedRecognitions.size());

//            int i = 0;

            for (Recognition recognition : updatedRecognitions) {

              if (recognition.getLeft() < 100  && recognition.getLeft() > 0){
                telemetry.addData("Level", "1");
              }

              if (recognition.getLeft() < 300 && recognition.getLeft() > 200){
                telemetry.addData("Level", "2");
              }

              if (recognition.getLeft() < 700 && recognition.getLeft() > 400){
                telemetry.addData("Level", "3");
              }

//              telemetry.addData(String.format("label (%d)", i), recognition.getLabel());

//              telemetry.addData(
//                  String.format("  left,top (%d)", i),
//                  "%.03f , %.03f",
//                  recognition.getLeft(),
//                  recognition.getTop());
//
//              telemetry.addData(
//                  String.format("  right,bottom (%d)", i),
//                  "%.03f , %.03f",
//                  recognition.getRight(),
//                  recognition.getBottom());

//              i++;

            }

            telemetry.update();

          }
        }
      }
    }
  }

  private void initVuforia() {

    VuforiaLocalizer.Parameters parameters = new VuforiaLocalizer.Parameters();
    parameters.vuforiaLicenseKey = VUFORIA_KEY;
    parameters.cameraName = hardwareMap.get(WebcamName.class, "Webcam");
    vuforia = ClassFactory.getInstance().createVuforia(parameters);

  }

  private void initTfod() {

    int tfodMonitorViewId =
        hardwareMap
            .appContext
            .getResources()
            .getIdentifier("tfodMonitorViewId", "id", hardwareMap.appContext.getPackageName());

    TFObjectDetector.Parameters tfodParameters = new TFObjectDetector.Parameters(tfodMonitorViewId);
    tfodParameters.minResultConfidence = 0.8f;
    tfodParameters.isModelTensorFlow2 = true;
    tfodParameters.inputSize = 320;
    tfod = ClassFactory.getInstance().createTFObjectDetector(tfodParameters, vuforia);
    tfod.loadModelFromAsset(TFOD_MODEL_ASSET, LABELS);

  }
}
