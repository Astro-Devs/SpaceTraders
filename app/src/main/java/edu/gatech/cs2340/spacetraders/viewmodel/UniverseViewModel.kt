package edu.gatech.cs2340.spacetraders.viewmodel

import android.arch.lifecycle.ViewModel
import edu.gatech.cs2340.spacetraders.model.ModelFacade

class UniverseViewModel : ViewModel() {

    var modelFacade : ModelFacade = ModelFacade.getInstance()

    fun populateUniverseView() {

    }
}