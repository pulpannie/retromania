package com.retromania.game.spaceship_shooter.individuals;

import java.util.List;

/**
 * The UFO repo responsible for storing a list of UFOs.
 * @author Thuy, Umid.
 */
public class UfoRepo {
    /**
     * The list of UFOs.
     */
    private List<UFO> ufos;

    /**
     * Initialized this UFO repo with a list of UFOs.
     *
     * @param ufos   the given list of UFOs.
     */
    public UfoRepo(List<UFO> ufos){
        this.ufos = ufos;
    }

    /**
     * return the list of UFOs storing in the UFO repo.
     *
     * @return the list of UFOs in the UFO repo.
     */
    List<UFO> getUfos(){return ufos;}
}
