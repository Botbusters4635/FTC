//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core;

import android.util.Log;

public class Module {
    private String modName;
    private String modType;


    public Module(String modName, String modType) {
        this.modName = modName;
        this.modType = modType;
        Log.i("Creating " + modType + " ", "Module name: " + modName);
    }


}
