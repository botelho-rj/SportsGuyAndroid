package com.example.jpaulo.sportsguyandroid.DAO;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.jpaulo.sportsguyandroid.Factory.DatabaseFactory;
import com.example.jpaulo.sportsguyandroid.Util.BancoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jpaulo on 10/1/2017.
 */

public class EventoDAO {

    private SQLiteDatabase db;
    private DatabaseFactory banco;

    public EventoDAO(Context context) {
        banco = new DatabaseFactory(context);
    }

    public long insereDado(Evento evento) {
        ContentValues valores;
        long resultado;

        db = banco.getWritableDatabase();
        valores = new ContentValues();
        valores.put(BancoUtil.TITULO_EVENTO, evento.getTitulo());
        valores.put(BancoUtil.MODALIDADE_EVENTO, evento.getModalidade());
        valores.put(BancoUtil.DATA_EVENTO, evento.getDtEvento());
        valores.put(BancoUtil.HORA_EVENTO, evento.getHrEvento());

        resultado = db.insert(BancoUtil.TABELA_EVENTO, null, valores);
        db.close();

        return resultado;

    }

    public Evento carregaEventoPorID(int id){
        Cursor cursor;
        String[] campos = {BancoUtil.ID_EVENTO, BancoUtil.TITULO_EVENTO, BancoUtil.MODALIDADE_EVENTO, BancoUtil.DATA_EVENTO, BancoUtil.HORA_EVENTO};
        String where = BancoUtil.ID_EVENTO + " = " + id;
        db = banco.getReadableDatabase();

        cursor = db.query(BancoUtil.TABELA_EVENTO, campos, where, null, null, null , null, null);

        Evento evento = new Evento();
        if(cursor != null){
            cursor.moveToFirst();

            int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_EVENTO));
            String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TITULO_EVENTO));
            String modalidade = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.MODALIDADE_EVENTO));
            String dta = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DATA_EVENTO));
            String hra = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.HORA_EVENTO));

            evento.setID(ID);
            evento.setTitulo(titulo);
            evento.setModalidade(modalidade);
            evento.setDtEvento(dta);
            evento.setHrEvento(hra);

        }
        db.close();
        return evento;

    }


    public Cursor carregaDados() {
        Cursor cursor;
        String[] campos = {BancoUtil.ID_EVENTO, BancoUtil.TITULO_EVENTO, BancoUtil.MODALIDADE_EVENTO, BancoUtil.DATA_EVENTO, BancoUtil.HORA_EVENTO};
        db = banco.getReadableDatabase();

        cursor = db.query(BancoUtil.TABELA_EVENTO, campos, null, null, null, null, null, null);

        if (cursor != null) {
            cursor.moveToFirst();
        }
        db.close();
        return cursor;
    }

    public List<Evento> carregaDadosLista() {
        Cursor cursor;

        cursor = carregaDados();

        List<Evento> eventos = new ArrayList<>();

        try {
            if(cursor.getCount() > 0) {
                do {
                    Evento evento = new Evento();
                    int ID = cursor.getInt(cursor.getColumnIndexOrThrow(BancoUtil.ID_EVENTO));
                    String titulo = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.TITULO_EVENTO));
                    String modalidade = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.MODALIDADE_EVENTO));
                    String dtEvento = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.DATA_EVENTO));
                    String hrEvento = cursor.getString(cursor.getColumnIndexOrThrow(BancoUtil.HORA_EVENTO));

                    evento.setID(ID);
                    evento.setTitulo(titulo);
                    evento.setModalidade(modalidade);
                    evento.setDtEvento(dtEvento);
                    evento.setHrEvento(hrEvento);

                    eventos.add(evento);
                } while (cursor.moveToNext());
            }
        } finally {
            cursor.close();
        }

        return eventos;
    }

    public void deletaRegistro(int id){
        String where = BancoUtil.ID_EVENTO + " = " + id;
        db =  banco.getReadableDatabase();

        db.delete(BancoUtil.TABELA_EVENTO,where,null);
        db.close();
    }


    public boolean atualizarEvento(Evento evento){
        ContentValues valores = new ContentValues();
        String where = BancoUtil.ID_EVENTO + " = "+ evento.getID();

        db = banco.getWritableDatabase();

        valores.put(BancoUtil.TITULO_EVENTO, evento.getTitulo());
        valores.put(BancoUtil.MODALIDADE_EVENTO, evento.getModalidade());
        valores.put(BancoUtil.DATA_EVENTO, evento.getDtEvento());
        valores.put(BancoUtil.HORA_EVENTO, evento.getHrEvento());

        int result = db.update(BancoUtil.TABELA_EVENTO, valores, where, null);
        db.close();
        if(result >0)
            return true;
        else
            return false;
    }

}
