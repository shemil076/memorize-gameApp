package com.example.memorize

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.view.View
import android.view.View.VISIBLE
import android.widget.ImageButton
import android.widget.TextView
import androidx.core.os.HandlerCompat.postDelayed
import androidx.core.view.isVisible
import java.util.*
import kotlin.concurrent.schedule

class gameActivity : AppCompatActivity(), View.OnClickListener {

    var buttonsArray = arrayOf(arrayOf<ImageButton>())
    var showedButtons = mutableListOf<String>()
    var selections = mutableListOf<String>()
    var correctCount = 0
    var pressedCount = 0
    var showButtonCount = 0
    var clickedCount = 0
    var seconds = 5
    var start = false
//    var startTimer = true



    private lateinit var labelScore : TextView
    lateinit var timer:Timer
    lateinit var status : TextView
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
        status = findViewById(R.id.status)
        buttonsArray = arrayOf(row1Buttons,row2Buttons,row3Buttons,row4Buttons,row5Buttons)

        for(array in buttonsArray) {
         for (button in array){
             button.setOnClickListener(this)
             button.setImageResource(R.drawable.defaultmark)
         }
        }
        showGrid()
    }

    fun showGrid(){
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

                }
                1 -> {  // 3 x 4
                    if(index == 3 || index == 4){
                        for (buttonset in buttons){
                            buttonset.visibility = View.GONE
                        }
                    }else{
                        buttons[4].visibility = View.GONE
                    }

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

                }

                3 -> {          // 5 x 4
                    buttons[4].visibility = View.GONE

                }
                4 -> {      // 4 x 5
                    if(index == 4){
                        for(buttonSet in buttons){
                            buttonSet.visibility = View.GONE
                        }
                    }

                }
            }
        }

        timer(1)
    }

    @SuppressLint("SetTextI18n")
    override fun onClick(view: View?) {
        if (start){
            clickedCount += 1
            pressedCount += 1
            var found = false
            val clickedButton = view?.let { resources.getResourceEntryName(it.id) }

            for (id in selections){
                if (clickedButton == id){
                    found = true
                }
            }

            if (found){
                correctCount += 1


                for (buttons in buttonsArray){
                    for (button in buttons){
                        if (resources.getResourceEntryName(button.id) == clickedButton){
                            button.setImageResource(R.drawable.check)
                        }
                    }
                }
                if (clickedButton != null) {
                    hide(clickedButton)
                }
            }else{
                for (buttons in buttonsArray){
                    for(button in buttons){
                        if (resources.getResourceEntryName(button.id) == clickedButton){
                            button.setImageResource(R.drawable.remove)
                        }
                    }
                }
                if (clickedButton != null) {
                    hide(clickedButton)
                }
            }

            labelScore.text = "SCORE = $correctCount / $clickedCount"

            if (pressedCount >= selections.size){
                again()
            }
        }
    }

    fun showPattern() {
        showedButtons.clear()

        for(buttons in buttonsArray){
            for(button in buttons){
                if(button.visibility == VISIBLE){
                    showedButtons.add(resources.getResourceEntryName(button.id))
                }
            }
        }

        showedButtons.shuffle()

        val patterns = kotlin.random.Random.nextInt(3,showedButtons.size)

        for (n in 0..patterns) {
            selections.add(showedButtons[n])
            val buttonName = showedButtons[n]
            for(arr in buttonsArray){
                for (buttons in arr){
                    if (resources.getResourceEntryName(buttons.id) == buttonName) {
                        buttons.setImageResource(R.drawable.check)
                    }
                }
            }
        }

        seconds += 5
        timer(2)
    }

    fun clear() {
        for(buttons in buttonsArray){
            for(button in buttons){
                button.setImageResource(R.drawable.defaultmark)
            }
        }
        pressedCount = 0
    }



    fun hide(idName: String) {
        val handler = Handler()
        handler.postDelayed({
            for(buttons in buttonsArray){
                for(button in buttons){
                    if (resources.getResourceEntryName(button.id) == idName){
                        button.setImageResource(R.drawable.defaultmark)
                    }
                }
            }
        }, 2000)
    }
    fun timer(option : Int) {
        var startTimer = true
        Timer().scheduleAtFixedRate(object : TimerTask() {
            @SuppressLint("SetTextI18n")
            override fun run() {
                if (startTimer){
                    seconds -= 1
                    runOnUiThread{
                        when (option) {
                            1 -> {
                                if (seconds == 0){
                                    status.text ="FOCUS ON.."
                                    showPattern()
                                    startTimer = false
                                }
                            }
                            2 -> {
                                if (seconds == 0){
                                    status.text ="GO ON.."
                                    clear()
                                    start = true
                                    startTimer = false
                                }
                            }
                        }
                    }
                }
            }
        },0,1000)
    }

    fun again(){
        start = false
        clear()

        for(buttons in buttonsArray){
            for(button in buttons){
                button.visibility = View.VISIBLE
            }
        }

        selections.clear()
        seconds += 5
        showGrid()
    }

}

