package com.example.jpaulo.sportsguyandroid.Activity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ListView;

import com.example.jpaulo.sportsguyandroid.Adapter.EventoAdapter;
import com.example.jpaulo.sportsguyandroid.DAO.Evento;
import com.example.jpaulo.sportsguyandroid.DAO.EventoDAO;
import com.example.jpaulo.sportsguyandroid.R;

import java.util.List;

/**
 * Created by jpaulo on 10/2/2017.
 */

public class ListarTodosActivity extends Activity{

    private ListView listaEventos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_completa);

        listaEventos = (ListView) findViewById(R.id.listaEventos);
        EventoDAO eventoDAO = new EventoDAO(this);
        List<Evento> eventos = eventoDAO.carregaDadosLista();
        EventoAdapter myAdapter = new EventoAdapter(this, R.layout.item_evento,eventos);
        listaEventos.setAdapter(myAdapter);

    }


}
