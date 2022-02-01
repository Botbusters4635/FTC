package org.firstinspires.ftc.teamcode.TFTest;

import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.eventloop.opmode.TeleOp;

import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;

import java.util.List;

@TeleOp(name = "Concept: TensorFlow Object Detection Webcam", group = "Concept")
public class TF extends LinearOpMode {

  private static final String TFOD_MODEL_ASSET = "model_20220201_082128.tflite";
  private static final String[] LABELS = {"TSE"};

  /**
   * {@link #tfod} is the variable we will use to store our instance of the TensorFlow Object
   * Detection engine.
   */

  private TFObjectDetector tfod;

  @Override
  public void runOpMode() {
    initTfod();

    /**
     * Activate TensorFlow Object Detection before we wait for the start command. Do it here so that
     * the Camera Stream window will have the TensorFlow annotations visible.
     */
    if (tfod != null) {
      tfod.activate();
      // The TensorFlow software will scale the input images from the camera to a lower resolution.
      // This can result in lower detection accuracy at longer distances (> 55cm or 22").
      // If your target is at distance greater than 50 cm (20") you can adjust the magnification
      // value
      // to artificially zoom in to the center of image.  For best results, the "aspectRatio"
      // argument
      // should be set to the value of the images used to create the TensorFlow Object Detection
      // model
      // (typically 16/9).
      tfod.setZoom(1, 16.0 / 9.0);
    }

    /** Wait for the game to begin */
    telemetry.addData(">", "Press Play to start op mode");
    telemetry.update();
    waitForStart();

    if (opModeIsActive()) {
      while (opModeIsActive()) {
        if (tfod != null) {
          // getUpdatedRecognitions() will return null if no new information is available since
          // the last time that call was made.
          List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();
          if (updatedRecognitions != null) {
            telemetry.addData("# Object Detected", updatedRecognitions.size());
            // step through the list of recognitions and display boundary info.
            int i = 0;
            for (Recognition recognition : updatedRecognitions) {
              telemetry.addData(String.format("label (%d)", i), recognition.getLabel());
              telemetry.addData(
                  String.format("  left,top (%d)", i),
                  "%.03f , %.03f",
                  recognition.getLeft(),
                  recognition.getTop());
              telemetry.addData(
                  String.format("  right,bottom (%d)", i),
                  "%.03f , %.03f",
                  recognition.getRight(),
                  recognition.getBottom());
              i++;
            }
            telemetry.update();
          }
        }
      }
    }
  }

  /** Initialize the TensorFlow Object Detection engine. */
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
    tfod.loadModelFromFile(TFOD_MODEL_ASSET, LABELS);
  }
}
