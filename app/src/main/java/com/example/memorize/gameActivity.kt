package com.example.memorize

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ImageButton
import java.util.*

class gameActivity : AppCompatActivity(), View.OnClickListener {

    var buttonsArray = arrayOf(arrayOf<ImageButton>())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        var row1Buttons = arrayOf(
            findViewById(R.id.imageButton1),
            findViewById(R.id.imageButton2),
            findViewById(R.id.imageButton3),
            findViewById(R.id.imageButton4),
            findViewById<ImageButton>(R.id.imageButton5)
        )

        var row2Buttons = arrayOf(
            findViewById(R.id.imageButton6),
            findViewById(R.id.imageButton7),
            findViewById(R.id.imageButton8),
            findViewById(R.id.imageButton9),
            findViewById<ImageButton>(R.id.imageButton10)
        )

        var row3Buttons = arrayOf(
            findViewById(R.id.imageButton11),
            findViewById(R.id.imageButton12),
            findViewById(R.id.imageButton13),
            findViewById(R.id.imageButton14),
            findViewById<ImageButton>(R.id.imageButton15)
        )

        var row4Buttons = arrayOf(
            findViewById(R.id.imageButton16),
            findViewById(R.id.imageButton17),
            findViewById(R.id.imageButton18),
            findViewById(R.id.imageButton19),
            findViewById<ImageButton>(R.id.imageButton20)
        )

        var row5Buttons = arrayOf(
            findViewById(R.id.imageButton21),
            findViewById(R.id.imageButton22),
            findViewById(R.id.imageButton23),
            findViewById(R.id.imageButton24),
            findViewById<ImageButton>(R.id.imageButton25)
        )

        buttonsArray = arrayOf(row1Buttons,row2Buttons,row3Buttons,row4Buttons,row5Buttons)

        for(array in buttonsArray) {
         for (button in array){
             button.setOnClickListener(this)
         }
            generate()
        }
    }

    fun generate(){
        var size = Random().nextInt(5)

        for((index, buttons) in buttonsArray.withIndex()) {
            when (5){
                0 -> {
                    if(index == 0 || index == 4){
                        for(buttonset in buttons){
                            buttonset.visibility = View.GONE
                        }
                    }else{
                        buttons[0].visibility = View.GONE
                        buttons[4].visibility = View.GONE
                    }
                }
                1 -> {
                    if(index == 3 || index == 4){
                        for (buttonset in buttons){
                            buttonset.visibility = View.GONE
                        }
                    }else{
                        buttons[4].visibility = View.GONE
                    }
                }
                2 -> {
                    if(index == 4){
                        for (buttonSet in buttons){
                            buttonSet.visibility = View.GONE
                        }
                    }else{
                        buttons[0].visibility = View.GONE
                        buttons[4].visibility = View.GONE
                    }
                }

                3 -> {
                    buttons[0].visibility = View.GONE
                }
                4 -> {
                    if(index == 4){
                        for(buttonSet in buttons){
                            buttonSet.visibility = View.GONE
                        }
                    }
                }
            }
        }
    }

    override fun onClick(p0: View?) {
        TODO("Not yet implemented")
    }
}

