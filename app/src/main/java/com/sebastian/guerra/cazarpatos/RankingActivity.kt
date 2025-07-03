package com.sebastian.guerra.cazarpatos

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class RankingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_ranking)
        /*ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }*/
        var jugadores = arrayListOf<Player>()
        jugadores.add(Player("Sebastian.Guerra",10))
        jugadores.add(Player("Jugador2",9))
        jugadores.add(Player("Jugador3",8))
        jugadores.add(Player("Jugador4",7))
        jugadores.add(Player("Jugador5",6))
        jugadores.add(Player("Jugador6",5))
        jugadores.add(Player("Jugador7",4))
        jugadores.add(Player("Jugador8",3))
        jugadores.add(Player("Jugador9",2))
        jugadores.add(Player("Jugador10",1))
        jugadores.sortByDescending { it.huntedDucks }

        val recyclerViewRanking: RecyclerView = findViewById(R.id.recyclerViewRanking);
        recyclerViewRanking.layoutManager = LinearLayoutManager(this);
        recyclerViewRanking.adapter = RankingAdapter(jugadores);
        recyclerViewRanking.setHasFixedSize(true);

    }
}

