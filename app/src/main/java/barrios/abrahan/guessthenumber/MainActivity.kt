package barrios.abrahan.guessthenumber

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import kotlin.random.Random

class MainActivity : AppCompatActivity() {

    var minValue = 0
    var maxValue = 100
    var won = false
    var num: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val guessings: TextView = findViewById(R.id.guessings)
        val down: Button = findViewById(R.id.down)
        val up: Button = findViewById(R.id.up)
        val generate: Button = findViewById(R.id.generate)
        val guessed: Button = findViewById(R.id.guessed)

        generate.setOnClickListener {
            num = Random.nextInt(minValue, maxValue)
            guessings.setText(num.toString())
            generate.visibility = View.INVISIBLE
            guessed.visibility = View.VISIBLE
        }

        up.setOnClickListener {
            minValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser :(, Me ganaste !!!")
            }

        }

        down.setOnClickListener {
            maxValue = num
            if (checkingLimits()) {
                num = Random.nextInt(minValue, maxValue)
                guessings.setText(num.toString())
            } else {
                guessings.setText("No puede ser :(, Me ganaste !!!")
            }
        }

        guessed.setOnClickListener {
            if (!won) {
                guessings.setText("Adiviné, tu número es el " + num)
                guessed.setText("Volver a Jugar")
                won = true
            } else {
                generate.visibility = View.VISIBLE
                guessings.setText("Tan on generate to Start")
                guessed.visibility = View.GONE
                resetValues()
            }
        }

    }

    fun checkingLimits(): Boolean {
        return minValue != maxValue
    }

    fun resetValues() {
        minValue = 0
        maxValue = 100
        num = 0
        won = false
    }
}