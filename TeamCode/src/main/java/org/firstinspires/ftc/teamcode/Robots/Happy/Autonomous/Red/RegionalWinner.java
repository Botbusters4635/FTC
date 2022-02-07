package org.firstinspires.ftc.teamcode.Robots.Happy.Autonomous.Red;

import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.spinnerConfig;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.robotcore.external.ClassFactory;
import org.firstinspires.ftc.robotcore.external.hardware.camera.WebcamName;
import org.firstinspires.ftc.robotcore.external.navigation.VuforiaLocalizer;
import org.firstinspires.ftc.robotcore.external.tfod.Recognition;
import org.firstinspires.ftc.robotcore.external.tfod.TFObjectDetector;
import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.trajectorysequence.TrajectorySequence;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;

import java.util.List;

@Autonomous(name = "TorreonsRegionalWinner")
public class RegionalWinner extends EctoOpMode {

  // VISION STUFF
  private static final String TFOD_MODEL_ASSET = "model_20220201_082128.tflite";
  private static final String[] LABELS = {"TSE"};
  private static final String VUFORIA_KEY =
      "AcPYhx//////AAABmUFaV20z2EmQkqbvhi5zgVyC8RDLUdcdScdfOzcbH3fA+pSEVZWlNIF+Ut/4VmqGbuk7uFqip+a3n8/B0pq1/CI+gVsV+uBZZ1CGA6DmLbDMwNu0jXJGH13oetb0LsQVjQXKVn5UNHDs3NSuZIElqucqsKVFjd4/UiYMTjV6mlkb9wtuZ2DXUThYi65NaqdEH9erho/RsjeoAhRbHBiHWZacLOT54Sv3TFt+QdWR9RvvDZewNSaP9bYCE8322fjmqdKd7qjT/jNEgJsM4G3qA9QqpOueIGbxKVJY5nN5vBLWruEdo/aH0C1pnxMzVTlQIFQjhALfyGHRCBSBWujGcXQq59onVkH/fnzg07TBLdFo ";
  int tfodActive = 0;

  private VuforiaLocalizer vuforia;
  private TFObjectDetector tfod;

  // Chassis
  SampleMecanumDrive drive;

  // Mechanisms
  Manipulator manipulator;
  Arm arm;
  Intake intake;
  Spinner spinner;

  // Trajectories
  TrajectorySequence trajectoryOne;
  TrajectorySequence trajectoryTwo;
  TrajectorySequence trajectoryThree;

  // Robot Positions
  Pose2d startPos;
  Pose2d allianceShippingHubPos;
  Pose2d allianceShippingHubP3Pos;
  Pose2d spinnerPos;
  Pose2d wareHouseP1Pos;
  Pose2d wareHouseP2Pos;

  // Arm Positions
  int low = 75;
  int medium = 150;
  int high = 275;
  int randomPosition = high;

  @Override
  public void initRobotClasses() {

    // Autonomous Init Process
    drive = new SampleMecanumDrive(hardwareMap);

    startPos = new Pose2d(12, -64, 0);
    allianceShippingHubPos = new Pose2d(-12, -42, Math.toRadians(90));
    allianceShippingHubP3Pos = new Pose2d(-8, -42, Math.toRadians(90));
    wareHouseP1Pos = new Pose2d(-20, -66);
    wareHouseP2Pos = new Pose2d(48, -66);
    spinnerPos = new Pose2d(-50, -64, Math.toRadians(90));

    drive.setPoseEstimate(startPos);

    initVuforia();
    initTfod();

    // Mechanisms
    arm = new Arm("arm", "Mechanism", armConfig);
    manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);
    intake = new Intake("intake", "Mechanism", intakeConfig);
    spinner = new Spinner("spinner", "Mechanism", spinnerConfig);
    mechanismManager.addMechanism(manipulator);
    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(intake);
    mechanismManager.addMechanism(spinner);

    // Inits Our Trajectory

    if (tfod != null) {
      tfod.activate();
      tfod.setZoom(1, 16.0 / 9.0);
    }

    if (tfod != null) {

      List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

      if (updatedRecognitions != null && !updatedRecognitions.isEmpty()) {

        telemetry.addData("# Object Detected", updatedRecognitions.size());
        Recognition newRecognition = updatedRecognitions.get(updatedRecognitions.size() - 1);

        if (newRecognition.getLeft() < 249 && newRecognition.getLeft() > 0) {
          randomPosition = low;
          telemetry.addData("Level", "1");
        }

        if (newRecognition.getLeft() < 399 && newRecognition.getLeft() > 250) {
          randomPosition = medium;
          telemetry.addData("Level", "2");
        }

        if (newRecognition.getLeft() < 800 && newRecognition.getLeft() > 400) {
          randomPosition = high;
          telemetry.addData("Level", "3");
        }
      }

      telemetry.update();
    }
  }

  @Override
  public void initRobot() {

    mechanismManager.addMechanism(manipulator);
    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(intake);
    mechanismManager.addMechanism(spinner);

    // Inits Our Trajectory

    if (tfod != null) {
      tfod.activate();
      tfod.setZoom(1, 16.0 / 9.0);
    }

    if (tfod != null) {

      List<Recognition> updatedRecognitions = tfod.getUpdatedRecognitions();

      if (updatedRecognitions != null && !updatedRecognitions.isEmpty()) {

        telemetry.addData("# Object Detected", updatedRecognitions.size());
        Recognition newRecognition = updatedRecognitions.get(updatedRecognitions.size() - 1);

        if (newRecognition.getLeft() < 249 && newRecognition.getLeft() > 0) {
          randomPosition = low;
          telemetry.addData("Level", "1");
        }

        if (newRecognition.getLeft() < 399 && newRecognition.getLeft() > 250) {
          randomPosition = medium;
          telemetry.addData("Level", "2");
        }

        if (newRecognition.getLeft() < 800 && newRecognition.getLeft() > 400) {
          randomPosition = high;
          telemetry.addData("Level", "3");
        }
      }

      telemetry.update();
    }

    // + TRAJ 1
    trajectoryOne =
        drive
            .trajectorySequenceBuilder(startPos)
            // 1st Cycle
            .addDisplacementMarker(
                () -> {
                  arm.setPosition(randomPosition);
                })
            .lineToSplineHeading(allianceShippingHubPos)
            .waitSeconds(0.2)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(1);
                })
            .waitSeconds(0.2)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOff();
                })
            .build();

    telemetry.addData(">", "Press Play to start op mode");
    telemetry.update();
  }

  @Override
  public void startRobot() {
    drive.followTrajectorySequenceAsync(trajectoryOne);
  }

  @Override
  public void updateRobot(Double timeStep) {

    if (tfodActive != 1) {
      tfod.deactivate();
      tfod.shutdown();
      tfodActive++;
    }

    telemetry.addData("Level", randomPosition);

    drive.update();
  }

  // + Inits Vision
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
