package com.example.meepmeeptesting;

import com.example.meepmeeptesting.Alliances.Cerbotics.Botbusters;
import com.example.meepmeeptesting.Alliances.Cerbotics.Cerbotics;
import com.example.meepmeeptesting.Paths.AllStar;
import com.noahbres.meepmeep.MeepMeep;

public class MeepMeepTesting {
  public static void main(String[] args) {
    MeepMeep meepMeep = new MeepMeep(800);

    Botbusters botbusters = new Botbusters();
    Cerbotics cerbotics = new Cerbotics();

    AllStar allstar = new AllStar();

    meepMeep
        .setBackground(MeepMeep.Background.FIELD_FREIGHTFRENZY_ADI_DARK)
        .setDarkMode(true)
        .setBackgroundAlpha(0.80f)
        .addEntity(allstar.bot)
        .start();
  }
}
