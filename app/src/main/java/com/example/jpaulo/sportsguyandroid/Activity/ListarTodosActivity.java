package com.example.jpaulo.sportsguyandroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
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
    private EventoAdapter myAdapter;
    EventoDAO eventoDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_completa);

        carregarElementos();

    }

    public void carregarElementos(){
        listaEventos = (ListView) findViewById(R.id.listaEventos);
        EventoDAO eventoDAO = new EventoDAO(this);
        List<Evento> eventos = eventoDAO.carregaDadosLista();
        myAdapter = new EventoAdapter(this, R.layout.item_evento, eventos);
        listaEventos.setAdapter(myAdapter);
        listaEventos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Evento evento = (Evento)parent.getItemAtPosition(position);
                Intent atualizarIntent = new Intent(ListarTodosActivity.this, AtualizarEventosActivity.class);
                atualizarIntent.putExtra("ID_EVENTO",evento.getIdEvento());
                startActivity(atualizarIntent);
            }
        });

    }
}
