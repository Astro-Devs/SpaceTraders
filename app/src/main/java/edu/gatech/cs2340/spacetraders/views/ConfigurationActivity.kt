package edu.gatech.cs2340.spacetraders.views

import android.arch.lifecycle.ViewModelProviders
import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import android.widget.EditText
import android.widget.Spinner
import android.widget.Toast
import edu.gatech.cs2340.spacetraders.R
import edu.gatech.cs2340.spacetraders.entity.GameDifficulty
import edu.gatech.cs2340.spacetraders.viewmodel.ConfigViewModel

/**
 * The Configuration Activity View class
 * that allows the user to input their desired
 * attributes to create a customized game
 */
class ConfigurationActivity : AppCompatActivity() {
    private lateinit var viewModel: ConfigViewModel

    private lateinit var nameField: EditText
    private lateinit var diffSpinner: Spinner
    private lateinit var pilotField: EditText
    private lateinit var fighterField: EditText
    private lateinit var traderField: EditText
    private lateinit var engineerField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config)

        nameField = findViewById(R.id.playerName)
        diffSpinner = findViewById(R.id.difficultySpinner)
        pilotField = findViewById(R.id.pilotPts)
        fighterField = findViewById(R.id.fighterPts)
        traderField = findViewById(R.id.traderPts)
        engineerField = findViewById(R.id.engineerPts)

        val diffAdapter = ArrayAdapter<GameDifficulty>(this, R.layout.white_spinner_item, GameDifficulty.values())
        diffAdapter.setDropDownViewResource(R.layout.better_spinner_dropdown_item)
        diffSpinner.adapter = diffAdapter


        viewModel = ViewModelProviders.of(this).get(ConfigViewModel::class.java)
    }


    /**
     * Button handler for the done game/player creation button
     *
     * Button handler checks for valid input from fields and creates game and player instance.
     * If invalid data, shows an error message.
     *
     * @param view the view to prevent the app from crashing, part of the method's default signature
     */
    fun onDonePressed(@Suppress("UNUSED_PARAMETER")view: View) {
        val name: String? = nameField.text.toString()
        val pilotPts: Int = Integer.parseInt(pilotField.text.toString())
        val engineerPts: Int = Integer.parseInt(engineerField.text.toString())
        val traderPts: Int = Integer.parseInt(traderField.text.toString())
        val fighterPts: Int = Integer.parseInt(fighterField.text.toString())
        val gameDiff: GameDifficulty = diffSpinner.selectedItem as GameDifficulty

        val isValid: Boolean = viewModel.onOk(name, pilotPts, engineerPts, traderPts, fighterPts, gameDiff)
        if (!isValid) {
            Toast.makeText(this, "Invalid attributes", Toast.LENGTH_LONG).show()
        } else {
            Toast.makeText(this, "New player created!", Toast.LENGTH_LONG).show()
            StartActivity.keepPlaying = false
            val shipIntent = Intent(applicationContext, ShipActivity::class.java)
            startActivityForResult(shipIntent, 0)
//            val inventoryIntent = Intent(applicationContext, BuyMarketActivity::class.java)
//            startActivityForResult(inventoryIntent, 0)
        }
    }

}
