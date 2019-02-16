package edu.gatech.cs2340.spacetraders.viewmodel

import edu.gatech.cs2340.spacetraders.entity.GameDifficulty
import edu.gatech.cs2340.spacetraders.model.ModelFacade

class ConfigViewModel() {

    var modelFacade : ModelFacade = ModelFacade.getInstance()

    fun onOk(name : String, pilotPts : Int, engineerPts : Int, traderPts : Int, fighterPts : Int,
                     gameDiff : GameDifficulty) : Boolean {
        if (name != null && name.length >= 1 && (pilotPts + engineerPts + traderPts + fighterPts) == 16) {
            //call modelFacade functions to create game/player instance
            //modelFacade.createGame(...)
            //
            return true
        } else {
            return false
        }
    }
}