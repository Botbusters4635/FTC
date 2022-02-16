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

@Autonomous(name = "Red-Right-54-Pointer", group = "Red")
public class right54Pointer extends EctoOpMode {

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

  // + Positions
  Pose2d startingPosition = new Pose2d(12, -64, Math.toRadians(0));
  Pose2d allianceShippingHub = new Pose2d(-12, -45, Math.toRadians(90));
  Pose2d spinnerPosition = new Pose2d(-60, -64, Math.toRadians(90));
  Pose2d allianceSkipper = new Pose2d(-35, -35, Math.toRadians(90));
  Pose2d storageUnitPosition = new Pose2d(-60, -35, Math.toRadians(90));
  Pose2d wareHouseIntakePosition = new Pose2d(40, -64, Math.toRadians(0));

  // + Mechanisms
  Arm arm;
  Manipulator manipulator;
  Spinner spinner;
  Intake intake;

  SampleMecanumDrive drive;

  // + Arm Positions
  int low = Configuration.Mechanisms.armPositions.lowPosition;
  int medium = Configuration.Mechanisms.armPositions.midPosition;
  int high = Configuration.Mechanisms.armPositions.highPosition;
  int randomPosition = medium;

  @Override
  public void initRobotClasses() {

    drive = new SampleMecanumDrive(hardwareMap);
    drive.setPoseEstimate(startingPosition);

    initVuforia();
    initTfod();

    // + TRAJS GO HERE
    trajectoryInitializer =
        drive

            .trajectorySequenceBuilder(startingPosition)

            // + First Cycle
            .lineToLinearHeading(allianceShippingHub)
            .waitSeconds(0.2)
            .lineToLinearHeading(startingPosition)
            .lineToLinearHeading(wareHouseIntakePosition)
            .forward(2)
            .lineToLinearHeading(startingPosition)

            // + Second Cycle
            .lineToLinearHeading(allianceShippingHub)
            .waitSeconds(0.2)

            .lineToLinearHeading(startingPosition)
            .lineToLinearHeading(wareHouseIntakePosition)
            .forward(4)
            .waitSeconds(0.1)
            .lineToLinearHeading(startingPosition)

            // + Third Cycle
            .lineToLinearHeading(allianceShippingHub)
            .waitSeconds(0.2)
            .lineToLinearHeading(startingPosition)
            .lineToLinearHeading(wareHouseIntakePosition)
            .forward(6)
            .waitSeconds(0.1)
            .lineToLinearHeading(startingPosition)

            // + Fourth Cycle
            .lineToLinearHeading(allianceShippingHub)
            .waitSeconds(0.2)
            .lineToLinearHeading(startingPosition)

            // + Parking
            .lineToLinearHeading(wareHouseIntakePosition)
            .forward(6)

            // + FIN
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
    arm.resetEncoder();
    tfod.shutdown();
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
