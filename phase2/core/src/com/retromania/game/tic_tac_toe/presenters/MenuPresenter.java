package com.retromania.game.tic_tac_toe.presenters;

import com.retromania.game.tic_tac_toe.utils.UserPreference;

/**
 * presenter for the MenuScreen class.
 * @author Hyokyung Kim
 */
public class MenuPresenter{
    protected static UserPreference userPreference = new UserPreference();

    /**
     * sets the user's cat preference to the opposite original preference.
     */
    public void reverseCat(){
        userPreference.setCat(!userPreference.getCat());
    }

    /**
     * increments the size of the game.
     */
    public void addSize(){
        userPreference.addSize();
    }

    /**
     * decrements the size of the game.
     */
    public void decreaseSize(){
        userPreference.decreaseSize();
    }

    /**
     * @return gets the game size.
     */
    public int getGameSize(){
        return userPreference.getGameSize();
    }

}
