package org.firstinspires.ftc.teamcode.Robots.Happy.Autonomous.Red;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoOpMode;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.trajectorysequence.TrajectorySequence;

@Autonomous(name = "testy")
public class Testy extends EctoOpMode {

  TrajectorySequence trajectoryInitializer;
  TrajectorySequence trajectory1;
  TrajectorySequence trajectory2;
  TrajectorySequence trajectory3;

  enum State {
    INITTRAJECT,
    TRAJP1,
    TRAJP2,
    TRAJP3,
  }

  State currentState = State.INITTRAJECT;

  double randomNum;

  Pose2d startPos = new Pose2d(12, -64, 0);

  SampleMecanumDrive drive;

  @Override
  public void initRobotClasses() {
    drive = new SampleMecanumDrive(hardwareMap);
    drive.setPoseEstimate(startPos);

    trajectoryInitializer = drive.trajectorySequenceBuilder(startPos).forward(5).build();

    trajectory1 = drive.trajectorySequenceBuilder(startPos).forward(10).build();

    trajectory2 = drive.trajectorySequenceBuilder(startPos).back(10).build();

    trajectory3 = drive.trajectorySequenceBuilder(startPos).strafeLeft(10).build();

  }

  @Override
  public void initRobot() {
    randomNum = Math.random();
  }

  @Override
  public void startRobot() {
    drive.followTrajectorySequenceAsync(trajectoryInitializer);
  }

  @Override
  public void updateRobot(Double timeStep) {
    telemetry.addData("randomNum:", randomNum);

    switch (currentState) {
      case INITTRAJECT:
        if (!drive.isBusy()) {
          if (randomNum >= 0.0 && randomNum < 0.33) {
            currentState = State.TRAJP1;
            drive.followTrajectorySequenceAsync(trajectory1);

          } else if (randomNum >= 0.33 && randomNum < 0.66) {
            currentState = State.TRAJP2;
            drive.followTrajectorySequenceAsync(trajectory2);

          } else if (randomNum >= 0.66 && randomNum < 1) {
            currentState = State.TRAJP3;
            drive.followTrajectorySequenceAsync(trajectory3);
          }
        }
        break;

      case TRAJP1:
        if (!drive.isBusy()) {
          telemetry.addData("TRAJ 1 DONE", "1");
        }
        break;

      case TRAJP2:
        if (!drive.isBusy()) {
          telemetry.addData("TRAJ 2 DONE", "2");
        }
        break;

      case TRAJP3:
        if (!drive.isBusy()) {
          telemetry.addData("TRAJ 3 DONE", "3");
        }
        break;
    }

    drive.update();
  }
}
