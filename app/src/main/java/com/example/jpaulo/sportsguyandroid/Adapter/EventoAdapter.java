package com.example.jpaulo.sportsguyandroid.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.jpaulo.sportsguyandroid.DAO.Evento;
import com.example.jpaulo.sportsguyandroid.R;

import java.util.List;

/**
 * Created by jpaulo on 10/1/2017.
 */

public class EventoAdapter extends ArrayAdapter<Evento> {

    private int resource;
    private List<Evento> eventos;

    public EventoAdapter(Context context, int resource, List<Evento> objects) {
        super(context, resource, objects);
        this.resource = resource;
        eventos = objects;
    }


    @Override
    public View getView(int position, View currentView, ViewGroup parent){
        View mView = currentView;

        if(mView == null){
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            mView = inflater.inflate(resource,null,false);
        }

        Evento evento = eventos.get(position);

        TextView textTitulo = (TextView) mView.findViewById(R.id.titulo);
        TextView textData = (TextView) mView.findViewById(R.id.text_data);
        TextView textModalidade = (TextView) mView.findViewById(R.id.textModalidade);
        TextView textHora = (TextView) mView.findViewById(R.id.text_hora);

        if(textTitulo != null){
            textTitulo.setText(evento.getTitulo());
        }

        if(textModalidade != null){
            textModalidade.setText(evento.getModalidade());
        }

        if(textData != null){
            textData.setText(evento.getDtEvento());
        }

        if(textHora != null){
            textHora.setText(evento.getHrEvento());
        }
        return mView;
    }

}
