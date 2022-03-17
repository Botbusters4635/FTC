package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(600);

    Pose2d startingPosition = new Pose2d(-36, -64, 0);

    Pose2d allianceShippingPosition = new Pose2d(-36, -23, Math.toRadians(0));

    Pose2d spinnerPosition = new Pose2d(-59, -55, Math.toRadians(90));

    Pose2d spinnerDuckPositioning = new Pose2d(-55, -40, Math.toRadians(270));
    Pose2d spinnerDuckPosition = new Pose2d(-55, -58, Math.toRadians(270));

//    Pose2d spinnerDuckPositioning = new Pose2d(-36, -64, Math.toRadians(220));
//    Pose2d spinnerDuckPosition = new Pose2d(-62, -64, Math.toRadians(220));

    Pose2d wareHouseP1 = new Pose2d(-39, 2, Math.toRadians(0));
    Pose2d wareHouseP2 = new Pose2d(10, 2, Math.toRadians(0));
    Pose2d wareHouseP3 = new Pose2d(10, -62, Math.toRadians(0));
    Pose2d wareHouseP4 = new Pose2d(40, -77, Math.toRadians(0));
    Pose2d wareHouseP5 = new Pose2d(44, -54, Math.toRadians(90));

    RoadRunnerBotEntity bot =
        new DefaultBotBuilder(meepMeep)
            .setConstraints(40, 37, 12.5, Math.toRadians(214.92695399999997), 10)
            .setDimensions(13.5, 15.5)
            .followTrajectorySequence(
                drive ->
                    drive
                        .trajectorySequenceBuilder(startingPosition)
                        .lineToLinearHeading(allianceShippingPosition)
                        .forward(11)
                        .lineToLinearHeading(spinnerPosition)
                        .strafeLeft(0.1)
                        .waitSeconds(4.5)
                        .lineToLinearHeading(spinnerDuckPositioning)
                        .lineToLinearHeading(spinnerDuckPosition)
                        .lineToLinearHeading(allianceShippingPosition)
                        .forward(11)
                        .back(12)
                        .lineToLinearHeading(wareHouseP1)
                        .lineToLinearHeading(wareHouseP2)
                        .lineToLinearHeading(wareHouseP3)
                        .strafeRight(15)
                        .lineToLinearHeading(wareHouseP4)
                        .lineToLinearHeading(wareHouseP5)
                        .build());

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(bot)
        .start();
  }
}
