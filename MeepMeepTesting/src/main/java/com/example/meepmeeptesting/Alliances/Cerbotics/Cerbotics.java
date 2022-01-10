package com.example.meepmeeptesting.Alliances.Cerbotics;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.geometry.Vector2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Cerbotics {
  MeepMeep meepMeep = new MeepMeep(800);

  public RoadRunnerBotEntity cerbotics =
      new DefaultBotBuilder(meepMeep)
          .setConstraints(60, 60, Math.toRadians(180), Math.toRadians(180), 13)
          .setDimensions(12, 16)
          .followTrajectorySequence(
              drive ->
                  drive
                      .trajectorySequenceBuilder(new Pose2d(9.5, -63.7, Math.toRadians(0)))

                      // 1st Cycle
                      .setReversed(true)
                      .splineToLinearHeading(
                          new Pose2d(-2, -38, Math.toRadians(-55)), Math.toRadians(110))

                      // 2nd Cycle
                      .setReversed(false)
                      .splineTo(new Vector2d(17.5, -63), Math.toRadians(-1.5))
                      .splineTo(new Vector2d(43, -63), Math.toRadians(0))
                      .setReversed(true)
                      .splineTo(new Vector2d(18, -63), Math.toRadians(175)) // 170
                      .splineToSplineHeading(
                          new Pose2d(-2, -38, Math.toRadians(-55)), Math.toRadians(90))

                      // 3rd Cycle
                      .setReversed(false)
                      .splineTo(new Vector2d(17.5, -63), Math.toRadians(-1.5))
                      .splineTo(new Vector2d(46, -63), Math.toRadians(0))
                      .setReversed(true)
                      .splineTo(new Vector2d(18, -63), Math.toRadians(175)) // 170
                      .splineToSplineHeading(
                          new Pose2d(-2, -38, Math.toRadians(-55)), Math.toRadians(90))

                      // 4th Cycle
                      .setReversed(false)
                      .splineTo(new Vector2d(17.5, -63), Math.toRadians(-1.5))
                      .splineTo(new Vector2d(49, -63), Math.toRadians(0))
                      .setReversed(true)
                      .splineTo(new Vector2d(18, -63), Math.toRadians(175)) // 170
                      .splineToSplineHeading(
                          new Pose2d(-2, -38, Math.toRadians(-55)), Math.toRadians(90))

                      // 5th Cycle
                      .setReversed(false)
                      .splineTo(new Vector2d(17.5, -63), Math.toRadians(-1.5))
                      .splineTo(new Vector2d(49, -63), Math.toRadians(0))
                      .setReversed(true)
                      .splineTo(new Vector2d(18, -63), Math.toRadians(175)) // 170
                      .splineToSplineHeading(
                          new Pose2d(-2, -38, Math.toRadians(-55)), Math.toRadians(90))

                      // Ware House Parking
                      .setReversed(false)
                      .splineTo(new Vector2d(17.5, -63), Math.toRadians(-1.5))
                      .splineToConstantHeading(new Vector2d(39, -57), Math.toRadians(90))
                      .lineToConstantHeading(new Vector2d(39, -49))
                      .splineToConstantHeading(new Vector2d(55, -38), Math.toRadians(0))
                      .build());
}
