package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.GameDifficulty
import edu.gatech.cs2340.spacetraders.viewmodel.ConfigViewModel

/**
 * The Configuration Activity View class
 * that allows the user to input their desired
 * attributes to create a customized game
 */
class ConfigurationActivity : AppCompatActivity() {
    private lateinit var viewModel : ConfigViewModel

    private lateinit var nameField : EditText
    private lateinit var diffSpinner : Spinner
    private lateinit var pilotField : EditText
    private lateinit var fighterField : EditText
    private lateinit var traderField : EditText
    private lateinit var engineerField : EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        nameField = findViewById(R.id.playerName)
        diffSpinner = findViewById(R.id.difficultySpinner)
        pilotField = findViewById(R.id.pilotPts)
        fighterField = findViewById(R.id.fighterPts)
        traderField = findViewById(R.id.traderPts)
        engineerField = findViewById(R.id.engineerPts)

        var diffAdapter : ArrayAdapter<GameDifficulty> = ArrayAdapter<GameDifficulty>(this, android.R.layout.simple_spinner_item, GameDifficulty.values())
        diffAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        diffSpinner.setAdapter(diffAdapter)



        viewModel = ViewModelProviders.of(this).get(ConfigViewModel::class.java)
    }

    /**
     * Button handler for the done game/player creation button
     *
     * Button handler checks for valid input from fields and creates game and player instance.
     * If invalid data, shows an error message.
     *
     * @param view the button that was pressed
     */
    fun onDonePressed(view : View) {
        var name : String? = nameField.getText().toString()
        var pilotPts : Int = Integer.parseInt(pilotField.getText().toString())
        var engineerPts : Int = Integer.parseInt(engineerField.getText().toString())
        var traderPts : Int =  Integer.parseInt(traderField.getText().toString())
        var fighterPts : Int = Integer.parseInt(fighterField.getText().toString())
        var gameDiff : GameDifficulty = diffSpinner.getSelectedItem() as GameDifficulty

        var isValid : Boolean = viewModel.onOk(name, pilotPts , engineerPts, traderPts, fighterPts, gameDiff)
        if(!isValid) {
            Toast.makeText(this, "Invalid attributes", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "New player created!", Toast.LENGTH_LONG).show()
        }
    }

}
