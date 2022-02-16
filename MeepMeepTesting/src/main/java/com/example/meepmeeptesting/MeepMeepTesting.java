package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(600);

    Pose2d startingPosition = new Pose2d(12, -64, Math.toRadians(0));
    Pose2d allianceShippingHub = new Pose2d(-12, -45, Math.toRadians(90));
    Pose2d spinnerPosition = new Pose2d(-60, -64, Math.toRadians(90));
    Pose2d allianceSkipper = new Pose2d(-42, -45, Math.toRadians(90));
    Pose2d storageUnitPosition = new Pose2d(-60, -40, Math.toRadians(90));
    Pose2d wareHouseIntakePosition = new Pose2d(42, -64, Math.toRadians(0));

    RoadRunnerBotEntity bot =
        new DefaultBotBuilder(meepMeep)
            .setConstraints(40, 37, 12.5, Math.toRadians(214.92695399999997), 10)
            .setDimensions(13.5, 15.5)
            .followTrajectorySequence(
                drive ->
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
                        .build());

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(bot)
        .start();
  }
}
