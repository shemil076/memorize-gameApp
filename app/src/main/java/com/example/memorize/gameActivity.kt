package com.example.memorize

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.view.isVisible
import java.util.*
import kotlin.random.Random.Default.nextInt

class gameActivity : AppCompatActivity(), View.OnClickListener {

    var buttonsArray = arrayOf(arrayOf<ImageButton>())
    var showedButtons = mutableListOf<String>()
    var selections = mutableListOf<String>()
    var correctCount = 0
    var pressedCount = 0
    var buttonCount  = 25

    lateinit var labelScore : TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        val row1Buttons = arrayOf(
            findViewById(R.id.imageButton1),
            findViewById(R.id.imageButton2),
            findViewById(R.id.imageButton3),
            findViewById(R.id.imageButton4),
            findViewById<ImageButton>(R.id.imageButton5)
        )

        val row2Buttons = arrayOf(
            findViewById(R.id.imageButton6),
            findViewById(R.id.imageButton7),
            findViewById(R.id.imageButton8),
            findViewById(R.id.imageButton9),
            findViewById<ImageButton>(R.id.imageButton10)
        )

        val row3Buttons = arrayOf(
            findViewById(R.id.imageButton11),
            findViewById(R.id.imageButton12),
            findViewById(R.id.imageButton13),
            findViewById(R.id.imageButton14),
            findViewById<ImageButton>(R.id.imageButton15)
        )

        val row4Buttons = arrayOf(
            findViewById(R.id.imageButton16),
            findViewById(R.id.imageButton17),
            findViewById(R.id.imageButton18),
            findViewById(R.id.imageButton19),
            findViewById<ImageButton>(R.id.imageButton20)
        )

        val row5Buttons = arrayOf(
            findViewById(R.id.imageButton21),
            findViewById(R.id.imageButton22),
            findViewById(R.id.imageButton23),
            findViewById(R.id.imageButton24),
            findViewById<ImageButton>(R.id.imageButton25)
        )

        labelScore = findViewById(R.id.score)
        buttonsArray = arrayOf(row1Buttons,row2Buttons,row3Buttons,row4Buttons,row5Buttons)

        for(array in buttonsArray) {
         for (button in array){
             button.setOnClickListener(this)
             button.setImageResource(R.drawable.defaultmark)
         }
        }
            generate()
    }

    fun generate(){
        val size = Random().nextInt(5)

        for((index, buttons) in buttonsArray.withIndex()) {
            Log.d("size" , size.toString())
            when (size){
                0 -> {      // 3 x 3
                    if(index == 0 || index == 4){
                        for(buttonSet in buttons){
                            buttonSet.visibility = View.GONE
                        }
                    }else{
                        buttons[0].visibility = View.GONE
                        buttons[4].visibility = View.GONE
                    }
                    buttonCount = 9
                }
                1 -> {  // 3 x 4
                    if(index == 3 || index == 4){
                        for (buttonset in buttons){
                            buttonset.visibility = View.GONE
                        }
                    }else{
                        buttons[4].visibility = View.GONE
                    }
                    buttonCount = 12
                }
                2 -> {      //4 x 3
                    if(index == 4){
                        for (buttonSet in buttons){
                            buttonSet.visibility = View.GONE
                        }
                    }else{
                        buttons[3].visibility = View.GONE
                        buttons[4].visibility = View.GONE
                    }
                    buttonCount = 12
                }

                3 -> {          // 5 x 4
                    buttons[4].visibility = View.GONE
                    buttonCount = 20
                }
                4 -> {      // 4 x 5
                    if(index == 4){
                        for(buttonSet in buttons){
                            buttonSet.visibility = View.GONE
                        }
                    }
                    buttonCount = 20
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        if(pressedCount < buttonCount) {
            pressedCount += 1
        }

        val pressedButton = view?.let { resources.getResourceEntryName(it.id) }
        Log.d("pressed button: ","$pressedButton")
        if (selections.contains(pressedButton)){
            correctCount += 1
            for (buttons in buttonsArray){
                for (button in buttons){
                    if (resources.getResourceEntryName(button.id) == pressedButton){
                        button.setImageResource(R.drawable.check)
                    }
                }
            }
        }else{
            for (buttons in buttonsArray){
                for (button in buttons){
                    if (resources.getResourceEntryName(button.id) == pressedButton){
                        button.setImageResource(R.drawable.remove)
                    }
                }
            }
        }

        labelScore.text = "Correct/All Selections : $correctCount/$pressedCount"
    }

    fun showPatter() {
        showedButtons.clear()
        for (buttons in buttonsArray) {
            for (buttonSet in buttons) {
                if(buttonSet.isVisible){
                    showedButtons.add(resources.getResourceEntryName(buttonSet.id))
                }
            }
        }
        showedButtons.shuffle()

        val size = 3 + Random().nextInt(showedButtons.size)
        for(i in 0..size){
            selections.add(showedButtons[i])
            var element = showedButtons[i]
            for(buttons in buttonsArray){
                for(button in buttons){
                    if(resources.getResourceEntryName(button.id) == element){
                        button.setImageResource(R.drawable.check)
                    }
                }
            }
        }
    }

    fun clear() {
        for(buttons in buttonsArray){
            for(button in buttons){
                button.setImageResource(R.drawable.defaultmark)
            }
        }
    }
}

