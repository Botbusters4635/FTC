package com.example.meepmeeptesting.Alliances.Cerbotics;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class Botbusters {
  MeepMeep meepMeep = new MeepMeep(800);
  Pose2d startingPosition = new Pose2d(-31, -61, 0);
  Pose2d allianceShippingHub = new Pose2d(-28, -38, Math.toRadians(45));
  Pose2d spinner = new Pose2d(-52, -65.5, Math.toRadians(90));
  Pose2d wareHouseP1 = new Pose2d(-40, -65.5, Math.toRadians(0));
  Pose2d wareHouseP2 = new Pose2d(38, -65.5, Math.toRadians(0));

  public RoadRunnerBotEntity botbusters =
      new DefaultBotBuilder(meepMeep)
          // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
          .setConstraints(40, 37, 13.241691158998306, Math.toRadians(214.92695399999997), 10)
          .setDimensions(13.5, 15.5)
          .followTrajectorySequence(
              drive ->
                  drive
                      .trajectorySequenceBuilder(startingPosition)
                      .lineToSplineHeading(allianceShippingHub)
                      //
                      .lineToSplineHeading(spinner)
                      .lineToSplineHeading(wareHouseP1)
                      .waitSeconds(15)
                      .lineToSplineHeading(wareHouseP2)
                      .build());
}
