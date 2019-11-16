package com.retromania.game.spaceship_shooter.individuals;

public class UfoManagerFacade {
    UfoDrawer drawer;
    UfoMover mover;
    UfoRepo repo;

    public UfoManagerFacade(UfoDrawer drawer, UfoMover mover, UfoRepo repo){
        this.drawer = drawer;
        this.mover = mover;
        this.repo = repo;
    }
}
