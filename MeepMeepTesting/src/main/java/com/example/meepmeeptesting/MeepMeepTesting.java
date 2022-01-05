package com.example.meepmeeptesting;

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.noahbres.meepmeep.MeepMeep;
import com.noahbres.meepmeep.roadrunner.DefaultBotBuilder;
import com.noahbres.meepmeep.roadrunner.entity.RoadRunnerBotEntity;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(800);
    Pose2d startingPosition = new Pose2d(12, -65.5, 0);
    Pose2d allianceShippingHub = new Pose2d(-12, -42, Math.toRadians(90));
    Pose2d spinner = new Pose2d(-52, -65.5, Math.toRadians(90));
    Pose2d wareHouseP1 = new Pose2d(-40, -65.5, Math.toRadians(0));
    Pose2d wareHouseP2 = new Pose2d(38, -65.5, Math.toRadians(0));

    RoadRunnerBotEntity myBot =
        new DefaultBotBuilder(meepMeep)
            // Set bot constraints: maxVel, maxAccel, maxAngVel, maxAngAccel, track width
            .setConstraints(40, 37, 13.241691158998306, Math.toRadians(214.92695399999997), 10)
            .followTrajectorySequence(
                drive ->
                    drive

                        // 1st Cycle
                        .trajectorySequenceBuilder(startingPosition)
                        .addDisplacementMarker(
                            () -> {
                              // Levantar Brazo (300)
                            })
                        .lineToSplineHeading(allianceShippingHub)
                        .addDisplacementMarker(
                            () -> {
                              // Prender Manipulador
                            })
                        .lineToSplineHeading(startingPosition)
                        .addDisplacementMarker(
                            () -> {
                              // Apagar Manipulador
                              // Bajar Brazo (150)
                            })
                        .forward(15)
                        .addDisplacementMarker(
                            () -> {
                              // Bajar Brazo (0)
                            })
                        .forward(15)
                        .addDisplacementMarker(
                            () -> {
                              // Iniciar El Intake
                              // Iniciar El Manipulador
                            })
                        .back(4)
                        .addDisplacementMarker(
                            () -> {
                              // Apagar El Intake
                              // Apagar El Manipulador
                              // Subir Brazo (150)
                            })
                        .back(26)
                        .addDisplacementMarker(
                            () -> {
                              // Subir El Brazo (300)
                            })

                        // 2nd Cycle
                        .lineToSplineHeading(allianceShippingHub)
                        .addDisplacementMarker(
                            () -> {
                              // Prender Manipulador
                            })
                        .lineToSplineHeading(startingPosition)
                        .addDisplacementMarker(
                            () -> {
                              // Apagar Manipulador
                              // Bajar Brazo (150)
                            })
                        .forward(15)
                        .addDisplacementMarker(
                            () -> {
                              // Bajar Brazo (0)
                            })
                        .forward(15)
                        .addDisplacementMarker(
                            () -> {
                              // Iniciar El Intake
                              // Iniciar El Manipulador
                            })
                        .back(4)
                        .addDisplacementMarker(
                            () -> {
                              // Apagar El Intake
                              // Apagar El Manipulador
                              // Subir Brazo (150)
                            })
                        .back(26)
                        .addDisplacementMarker(
                            () -> {
                              // Subir El Brazo (300)
                            })

                        // 3rd Cycle
                        .lineToSplineHeading(allianceShippingHub)
                        .addDisplacementMarker(
                            () -> {
                              // Prender Manipulador
                            })
                        .waitSeconds(0.2)
                        .addDisplacementMarker(
                            () -> {
                              // Apagar Manipulador
                            })

                            // Spinner
                        .lineToSplineHeading(spinner)
                        .addDisplacementMarker(
                            () -> {
                              // Apagar Manipulador
                            })
                        .waitSeconds(2)
                        .addDisplacementMarker(
                            () -> {
                              // Apagar Manipulador
                            })
                            // Park To Ware House
                        .lineToSplineHeading(wareHouseP1)
                        .lineToSplineHeading(wareHouseP2)
                        // FIN
                        .build());

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(myBot)
        .start();
  }
}
