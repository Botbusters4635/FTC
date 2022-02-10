package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(600);

    Pose2d startingPosition = new Pose2d(-36, -64, 0);
    Pose2d wareHouseP2 = new Pose2d(48, -64);
    Pose2d wareHouseP1 = new Pose2d(-32, -64);
    Pose2d storageUnitPos = new Pose2d(-60,-35, Math.toRadians(90));

    Pose2d allianceShippingHub = new Pose2d(-12, -42, Math.toRadians(90));

    Pose2d spinner = new Pose2d(-48, -64, Math.toRadians(90));

    RoadRunnerBotEntity bot =
        new DefaultBotBuilder(meepMeep)
            .setConstraints(40, 37, 13.241691158998306, Math.toRadians(214.92695399999997), 10)
            .setDimensions(13.5, 15.5)
            .followTrajectorySequence(
                drive ->
                    drive
                        .trajectorySequenceBuilder(startingPosition)
                        .lineToLinearHeading(allianceShippingHub)
                        .lineToLinearHeading(spinner)
                            .lineToLinearHeading(storageUnitPos)

                            .build());

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(bot)
        .start();
  }
}
