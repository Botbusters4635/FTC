//
// Created by Neil Rodriguez 10/28/2021
//

package org.firstinspires.ftc.teamcode.Core;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public abstract class Mechanism extends Module{
    private String name;

    public Mechanism(String modName, String modType) {
        super(modName, modType);
        Module mod = new Module(name, "Mechanism");
    }


    //Every mechanism should be able to do this functions
    public abstract void robotInit();
    public abstract void disableInit();

    public abstract void robotUpdate();
    public abstract void disableUpdate();
}
