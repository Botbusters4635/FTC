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
import org.firstinspires.ftc.teamcode.Robots.Happy.Configuration;

import java.util.List;

@Autonomous(name = "Red46Pointer", group = "Red")
public class Red46Pointer extends EctoOpMode {

  private enum RobotState {
    Running,
    IdleMode
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

  // + POSITIONS
  Pose2d startingPosition = new Pose2d(-36, -64, 0);

  Pose2d allianceShippingPosition = new Pose2d(-36, -23, Math.toRadians(0));

  Pose2d spinnerPosition = new Pose2d(-60.5, -55, Math.toRadians(90));

  Pose2d spinnerDuckPositioning = new Pose2d(-55, -40, Math.toRadians(270));
  Pose2d spinnerDuckPosition = new Pose2d(-55, -58, Math.toRadians(270));

  Pose2d storageUnitPosition = new Pose2d(-64, -35, Math.toRadians(90));
  Pose2d wareHouseP1 = new Pose2d(-39, 2, Math.toRadians(0));
  Pose2d wareHouseP2 = new Pose2d(14, 2, Math.toRadians(0));
  Pose2d wareHouseP3 = new Pose2d(14, -62, Math.toRadians(0));

  // + Mechanisms
  Arm arm;
  Manipulator manipulator;
  Spinner spinner;
  Intake intake;

  SampleMecanumDrive drive;

  // + Arm Positions
  int low = 120;
  int medium = 230;
  int high = 315;
  int randomPosition = medium;

  // + Voltages
  double spinnerPower = 0.7;

  // + Distances
  double levelOneForwardnessPreloaded = 8.5;
  double levelOneForwardnessDuck = 11;

  double levelTwoForwardnessPreloaded = 10;
  double levelTwoForwardnessDuck = 11;

  double levelThreeForwardnessPreloaded = 10.25;
  double levelThreeForwardnessDuck = 10.0;

  @Override
  public void initRobotClasses() {

    drive = new SampleMecanumDrive(hardwareMap);
    drive.setPoseEstimate(startingPosition);

    initVuforia();
    initTfod();

    trajectoryInitializer = drive.trajectorySequenceBuilder(startingPosition).strafeLeft(1).build();

    levelOne =
        drive
            .trajectorySequenceBuilder(startingPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                })
            .addTemporalMarker(
                0.5,
                () -> {
                  arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);
                })
            .lineToLinearHeading(allianceShippingPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
                  arm.setHomePosition();
                })
            .forward(levelOneForwardnessPreloaded)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.7);
                  spinner.turnOn(0.7);
                })
            .lineToLinearHeading(spinnerPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                  arm.setHomePosition();
                  manipulator.turnOff();
                })
            .strafeLeft(0.1)
            .waitSeconds(2)
            .lineToLinearHeading(spinnerDuckPositioning)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(-1);
                  intake.turnOn(-1);
                  spinner.turnOff();
                })
            .lineToLinearHeading(spinnerDuckPosition)
            .waitSeconds(1)
            .addDisplacementMarker(
                () -> {
                  arm.resetEncoder();
                  manipulator.turnOff();
                  intake.turnOff();
                  arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);
                })
            .lineToLinearHeading(allianceShippingPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
                })
            .forward(levelOneForwardnessDuck)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.7);
                })
            .back(13.5)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOff();
                  arm.setHomePosition();
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                })
            .lineToLinearHeading(storageUnitPosition)
            .build();

    levelTwo =
        drive
            .trajectorySequenceBuilder(startingPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                })
            .addTemporalMarker(
                0.5,
                () -> {
                  arm.setPosition(Configuration.Mechanisms.Positions.arm.midPosition);
                })
            .addTemporalMarker(
                1,
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
                })
            .lineToLinearHeading(allianceShippingPosition)
                .strafeLeft(3)
            .forward(levelTwoForwardnessPreloaded)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.6);
                  spinner.turnOn(spinnerPower);
                })
            .lineToLinearHeading(spinnerPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                  arm.setHomePosition();
                  manipulator.turnOff();
                })
            .strafeLeft(0.1)
            .waitSeconds(2)
            .lineToLinearHeading(spinnerDuckPositioning)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(-1);
                  intake.turnOn(-1);
                  spinner.turnOff();
                })
            .lineToLinearHeading(spinnerDuckPosition)
            .waitSeconds(1)
            .addDisplacementMarker(
                () -> {
                  arm.resetEncoder();
                  manipulator.turnOff();
                  intake.turnOff();
                  arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);
                })
            .lineToLinearHeading(allianceShippingPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
                })
            .forward(levelTwoForwardnessDuck)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.7);
                })
            .back(13.5)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOff();
                  arm.setHomePosition();
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                })
            .lineToLinearHeading(storageUnitPosition)
            .build();

    levelThree =
        drive
            .trajectorySequenceBuilder(startingPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                })
            .addTemporalMarker(
                0.5,
                () -> {
                  arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);
                })
            .addTemporalMarker(
                1,
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.up);
                })
            .lineToLinearHeading(allianceShippingPosition)
            .forward(levelThreeForwardnessPreloaded)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOn(0.7);
                  spinner.turnOn(0.7);
                })
            .lineToLinearHeading(spinnerPosition)
            .addDisplacementMarker(
                () -> {
                  intake.setServoPosition(Configuration.Mechanisms.Positions.intake.down);
                  arm.setHomePosition();
                  manipulator.turnOff();
                })
            .strafeLeft(0.1)
            .waitSeconds(2)
            .lineToLinearHeading(spinnerDuckPositioning)
            .addDisplacementMarker(
                () -> {
                  arm.resetEncoder();
                  manipulator.turnOn(-1);
                  intake.turnOn(-1);
                  spinner.turnOff();
                })
            .lineToLinearHeading(spinnerDuckPosition)
            .waitSeconds(1)
            .addDisplacementMarker(
                () -> {
                  manipulator.turnOff();
                  intake.turnOff();
                  arm.setPosition(Configuration.Mechanisms.Positions.arm.highPosition);
                })
            .lineToLinearHeading(storageUnitPosition)
            .build();

    arm = new Arm("arm", "Mechanism", armConfig);
    manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);
    spinner = new Spinner("spinner", "Mechanism", spinnerConfig);
    intake = new Intake("intake", "Mechanism", intakeConfig);

    mechanismManager.addMechanism(arm);
    mechanismManager.addMechanism(manipulator);
    mechanismManager.addMechanism(spinner);
    mechanismManager.addMechanism(intake);
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
          randomPosition = high;
          telemetry.addData("Level", "3");
        }

        if (newRecognition.getLeft() < 399 && newRecognition.getLeft() > 250) {
          randomPosition = medium;
          telemetry.addData("Level", "2");
        }

        if (newRecognition.getLeft() < 800 && newRecognition.getLeft() > 400) {

          randomPosition = low;
          telemetry.addData("Level", "1");
        }
      }

      telemetry.update();
    }

    telemetry.addData(">", " Robot Built");
  }

  @Override
  public void startRobot() {
    arm.resetEncoder();
    drive.followTrajectorySequenceAsync(trajectoryInitializer);
  }

  @Override
  public void updateRobot(Double timeStep) {

    switch (currentRobotState) {
      case Running:
        if (!drive.isBusy()) {

          if (randomPosition == low) {

            drive.followTrajectorySequenceAsync(levelOne);
            currentRobotState = RobotState.IdleMode;

          } else if (randomPosition == medium) {

            drive.followTrajectorySequenceAsync(levelTwo);
            currentRobotState = RobotState.IdleMode;

          } else if (randomPosition == high) {

            drive.followTrajectorySequenceAsync(levelThree);
            currentRobotState = RobotState.IdleMode;
          }
        }

        break;

      case IdleMode:
        if (!drive.isBusy()) {
          mechanismManager.stopMechanisms();
        }
        break;
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
