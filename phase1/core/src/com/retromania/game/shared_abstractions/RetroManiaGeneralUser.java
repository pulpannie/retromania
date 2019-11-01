package com.retromania.game.shared_abstractions;

public class RetroManiaGeneralUser implements User {
    private String name;
    @Override
    public void setScore(int score) {
        this.score = score;
    }

    private int score = 0;
    public RetroManiaGeneralUser(String name){
        if (name.length()>3||name.length()<=0){
            throw new RuntimeException("Size is not standard");
        }
        this.name = name;
    }
    @Override
    public String getUserName() {
        return name;
    }

    @Override
    public Integer getScore() {
        return score;
    }
}
