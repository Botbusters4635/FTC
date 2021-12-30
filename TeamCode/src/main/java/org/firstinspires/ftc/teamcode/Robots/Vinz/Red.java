//
// Created by Neil Rodriguez 12/28/2021
//

package org.firstinspires.ftc.teamcode.Robots.Vinz;

import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.armConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.intakeConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.manipulatorConfig;
import static org.firstinspires.ftc.teamcode.Robots.Vinz.Configuration.Mechanisms.spinnerConfig;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;

import org.firstinspires.ftc.teamcode.Core.BaseClasses.OperationModes.EctoLinearOpMode;
import org.firstinspires.ftc.teamcode.Core.Utils.EctoPathFollowing.drive.SampleMecanumDrive;
import org.firstinspires.ftc.teamcode.Mechanisms.Arm.Arm;
import org.firstinspires.ftc.teamcode.Mechanisms.Intake.Intake;
import org.firstinspires.ftc.teamcode.Mechanisms.Manipulator.Manipulator;
import org.firstinspires.ftc.teamcode.Mechanisms.Spinner.Spinner;

@Autonomous(name = "Red Autonomous")
public class Red extends EctoLinearOpMode {

  SampleMecanumDrive chassis;

  Manipulator manipulator;

  Arm arm;

  Intake intake;

  Spinner spinner;

  Trajectory strafeToASH;


  Pose2d startingPosition;

  @Override
  public void initRobotClasses() {
    chassis = new SampleMecanumDrive(hardwareMap);
    startingPosition = new Pose2d(12, -65.5, 0);

    arm = new Arm("arm", "Mechanism", armConfig);
    manipulator = new Manipulator("Manipulator", "Mechanism", manipulatorConfig);
    intake = new Intake("intake", "Mechanism", intakeConfig);
    spinner = new Spinner("spinner", "Mechanism", spinnerConfig);
  }

  @Override
  protected void initTrajectories() {
    chassis.setPoseEstimate(startingPosition);

    strafeToASH =
        chassis
            .trajectoryBuilder(startingPosition)
            .lineToSplineHeading(new Pose2d(-12.5, -37.5, Math.toRadians(90)))
            .build();


  }

  @Override
  protected void initRobot() {

    mechanismManager.addMechanism(manipulator);

    mechanismManager.addMechanism(arm);

    mechanismManager.addMechanism(intake);

    mechanismManager.addMechanism(spinner);
  }

  @Override
  public void startRobotTrajectories() {

    manipulator.turnOn(1);
  }
}
