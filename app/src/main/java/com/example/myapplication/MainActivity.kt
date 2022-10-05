package com.example.myapplication


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.example.myapplication.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private var jogada: Int = 1
    private var lista = arrayListOf<Button>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)



        with(binding) { lista = arrayListOf<Button>(a, b, c, d, e, f, g, h, i) }


        for (x in 0..lista.size - 1) {
            lista[x].setOnClickListener {
                joga(it as Button)
            }
        }

        binding.btnRestart.setOnClickListener {
            restart()
        }
    }

    private fun joga(btn: Button) {
        if (jogada % 2 != 0) {
            btn.text = "x"
        } else {
            btn.text = "o"
        }
        btn.isClickable = false
        chekjogo()
        jogada++
    }

    private fun chekjogo() {
        if (jogada == 9) {
            "Empate".also { binding.Result.text = it }
            binding.btnRestart.visibility = View.VISIBLE
        }
        val numb = listOf<Int>(0, 3, 6)
        for (x in numb) {
            if (lista[x].text != "" && lista[x].text == lista[x + 1].text && lista[x + 1].text == lista[x + 2].text) {
                ganhador()
            }
        }
        for (x in 0..2) {
            if (lista[x].text != "" && lista[x].text == lista[x + 3].text && lista[x + 3].text == lista[x + 6].text) {
                ganhador()
            }
        }
        if ((lista[0].text != "" && lista[0].text == lista[4].text && lista[4].text == lista[8].text)
            || (lista[2].text != "" && lista[2].text == lista[4].text && lista[4].text == lista[6].text)
        ) {
            ganhador()
        }
    }

    private fun ganhador() {
        if (jogada % 2 != 0) {
            binding.Result.text = binding.jogador1.toString()
            for (x in 0..lista.size - 1) {
                lista[x].isClickable = false
            }
        } else {
            binding.Result.text = binding.jogador2.toString()
            for (x in 0..lista.size - 1) {
                lista[x].isClickable = false
            }
        }
        binding.btnRestart.visibility = View.VISIBLE
    }


    private fun restart() {
        for (x in 0..lista.size - 1) {
            lista[x].text = ""
            lista[x].isClickable = true
        }
        binding.Result.text = ""
        jogada = 1
        binding.btnRestart.visibility = View.GONE
    }

}
