package edu.gatech.cs2340.spacetraders.viewmodel

import android.arch.lifecycle.AndroidViewModel
import edu.gatech.cs2340.spacetraders.entity.GameDifficulty
import edu.gatech.cs2340.spacetraders.entity.Player
import edu.gatech.cs2340.spacetraders.model.ModelFacade

class ConfigViewModel() {

    var modelFacade : ModelFacade = ModelFacade.getInstance()

    fun onOk(name : String, pilotPts : Int, engineerPts : Int, traderPts : Int, fighterPts : Int,
                     gameDiff : GameDifficulty) : Boolean {
        if (name != null && name.length >= 1 && (pilotPts + engineerPts + traderPts + fighterPts) == 16) {

            //call modelFacade functions to create game/player instance
            var arrSkill : IntArray = intArrayOf(pilotPts, engineerPts, traderPts, fighterPts)
            var player: Player = Player(name, arrSkill)
            modelFacade.createGame(gameDiff, player)
            //

            return true
        } else {
            return false
        }
    }
}