package edu.gatech.cs2340.spacetraders.viewmodel

import android.arch.lifecycle.ViewModel
import edu.gatech.cs2340.spacetraders.entity.GameDifficulty
import edu.gatech.cs2340.spacetraders.entity.Player
import edu.gatech.cs2340.spacetraders.model.ModelFacade

/**
 * The ViewModel class which allows the Configuration
 * Activity to pass in its fields to the models
 */
class ConfigViewModel : ViewModel() {

    private var modelFacade: ModelFacade = ModelFacade.getInstance()

    /**
     *Checks if passed in data is valid and creates a Player and Game instance if so
     *
     * @param name passed in player name string
     * @param pilotPts passed in number of pilot attribute points
     * @param engineerPts passed in number of engineer attribute points
     * @param traderPts passed in number of trader attribute points
     * @param fighterPts passed in number of fighter attribute points
     * @param gameDiff passed in game difficulty enum
     * @return whether the passed in data is valid
     */
    fun onOk(
        name: String?, pilotPts: Int, engineerPts: Int, traderPts: Int, fighterPts: Int,
        gameDiff: GameDifficulty
    ): Boolean {
        return if (name != null && name.isNotEmpty() && (pilotPts + engineerPts + traderPts + fighterPts) == 16) {

            //call modelFacade functions to create game/player instance
            val arrSkill: IntArray = intArrayOf(pilotPts, engineerPts, traderPts, fighterPts)
            val player = Player(name, arrSkill)
            modelFacade.createGame(gameDiff, player)
            //

            true
        } else {
            false
        }
    }
}