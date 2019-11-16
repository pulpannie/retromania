package com.retromania.game.spaceship_shooter.individuals;

import java.util.List;

public class UfoRepo {
    List<UFO> ufos;
    public UfoRepo(List<UFO> ufos){
        this.ufos = ufos;
    }

    public List<UFO> getUfos(){return ufos;}
}
