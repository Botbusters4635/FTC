package org.firstinspires.ftc.teamcode.Robots.Happy.Autonomous.Red;

import static org.firstinspires.ftc.teamcode.Robots.Happy.Configuration.Mechanisms.armConfig;
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
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;

import java.util.List;

@Autonomous(name = "42-Pointer")
public class Left42PointerAuto extends EctoOpMode {

  enum RobotState {
    Running,
    Idle
  }

  RobotState currentRobotState = RobotState.Running;

  // + VISION STUFF
  private static final String TFOD_MODEL_ASSET = "model_20220201_082128.tflite";
  private static final String[] LABELS = {"TSE"};
  private static final String VUFORIA_KEY =
      "AcPYhx//////AAABmUFaV20z2EmQkqbvhi5zgVyC8RDLUdcdScdfOzcbH3fA+pSEVZWlNIF+Ut/4VmqGbuk7uFqip+a3n8/B0pq1/CI+gVsV+uBZZ1CGA6DmLbDMwNu0jXJGH13oetb0LsQVjQXKVn5UNHDs3NSuZIElqucqsKVFjd4/UiYMTjV6mlkb9wtuZ2DXUThYi65NaqdEH9erho/RsjeoAhRbHBiHWZacLOT54Sv3TFt+QdWR9RvvDZewNSaP9bYCE8322fjmqdKd7qjT/jNEgJsM4G3qA9QqpOueIGbxKVJY5nN5vBLWruEdo/aH0C1pnxMzVTlQIFQjhALfyGHRCBSBWujGcXQq59onVkH/fnzg07TBLdFo ";

  private VuforiaLocalizer vuforia;
  private TFObjectDetector tfod;

  // + TRAJECTORIES
  TrajectorySequence trajectoryInitializer;
  TrajectorySequence levelOne;
  TrajectorySequence levelTwo;
  TrajectorySequence levelThree;

  // + Positions
  Pose2d startingPosition = new Pose2d(-35, -64, 0);
  Pose2d allianceShippingHub = new Pose2d(-10, -48, Math.toRadians(90));
  Pose2d spinnerPos = new Pose2d(-50, -64, Math.toRadians(90));
  Pose2d storageUnitPos = new Pose2d(-60, -35, Math.toRadians(90));
  SampleMecanumDrive drive;

  // + Mechanisms
  Arm arm;
  Manipulator manipulator;
  Spinner spinner;
  //  Intake intake;

  // + Arm Positions
  int low = 75;
  int medium = 150;
  int high = 275;
  int randomPosition = high;

  @Override
  public void initRobotClasses() {

    drive = new SampleMecanumDrive(hardwareMap);
    drive.setPoseEstimate(startingPosition);

    initVuforia();
    initTfod();

    trajectoryInitializer =
        drive
            .trajectorySequenceBuilder(startingPosition)
            .lineToLinearHeading(allianceShippingHub)
            .build();

    levelOne =
        drive
            .trajectorySequenceBuilder(allianceShippingHub)
            .addDisplacementMarker(
                () -> {
                  arm.setPosition(low);
                })
            .forward(0.1)
            .forward(0.1)
            .waitSeconds(1)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.6);
                })
            .lineToLinearHeading(spinnerPos)
            .addDisplacementMarker(
                () -> {
                  arm.setHomePosition();
                  manipulator.turnOff();
                  spinner.turnOn(0.6);
                })
            .waitSeconds(4)
            .lineToLinearHeading(storageUnitPos)
            .addDisplacementMarker(
                () -> {
                  spinner.turnOff();
                })
            .build();

    levelTwo =
        drive
            .trajectorySequenceBuilder(allianceShippingHub)
            .addDisplacementMarker(
                () -> {
                  arm.setPosition(medium);
                })
            .forward(0.1)
            .forward(0.1)
            .waitSeconds(1)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.6);
                })
            .lineToLinearHeading(spinnerPos)
            .addDisplacementMarker(
                () -> {
                  arm.setHomePosition();
                  manipulator.turnOff();
                  spinner.turnOn(0.6);
                })
            .waitSeconds(4)
            .lineToLinearHeading(storageUnitPos)
            .addDisplacementMarker(
                () -> {
                  spinner.turnOff();
                })
            .build();

    levelThree =
        drive
            .trajectorySequenceBuilder(allianceShippingHub)
            .addDisplacementMarker(
                () -> {
                  arm.setPosition(high);
                })
            .forward(0.1)
            .forward(0.1)
            .waitSeconds(1)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.6);
                })
            .lineToLinearHeading(spinnerPos)
            .addDisplacementMarker(
                () -> {
                  arm.setHomePosition();
                  manipulator.turnOff();
                  spinner.turnOn(0.6);
                })
            .waitSeconds(4)
            .lineToLinearHeading(storageUnitPos)
            .addDisplacementMarker(
                () -> {
                  spinner.turnOff();
                })
            .build();

    arm = new Arm("arm", "Mechanism", armConfig);
    manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);
    spinner = new Spinner("spinner", "Mechanism", spinnerConfig);

    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(manipulator);
    mechanismManager.addMechanism(spinner);
  }

  @Override
  public void initRobot() {

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

    telemetry.addData(">", " Robot Built");
  }

  @Override
  public void startRobot() {
    drive.followTrajectorySequenceAsync(trajectoryInitializer);
  }

  @Override
  public void updateRobot(Double timeStep) {

    if (!drive.isBusy()) {
      if (randomPosition == low) {

        drive.followTrajectorySequenceAsync(levelOne);

      } else if (randomPosition == medium) {

        drive.followTrajectorySequenceAsync(levelTwo);

      } else if (randomPosition == high) {

        drive.followTrajectorySequenceAsync(levelThree);
      }
    }

    drive.update();
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
