package org.firstinspires.ftc.teamcode.Core.BaseClasses;

import org.firstinspires.ftc.teamcode.Core.Module;

public abstract class Mechanism extends Module {

    private String name;

    public Mechanism(String modName, String modType) {
        super(modName, modType);
        Module mod = new Module(name, "Mechanism");
    }



    //Every mechanism should be able to do this functions
    public abstract void setUpMechanism();
    public abstract void stopMechanism();
    public abstract void homeMechanism();
    public abstract Boolean isFinished();
}