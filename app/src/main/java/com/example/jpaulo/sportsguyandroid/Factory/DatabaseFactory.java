package com.example.jpaulo.sportsguyandroid.Factory;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.jpaulo.sportsguyandroid.Util.BancoUtil;

/**
 * Created by jpaulo on 10/1/2017.
 */

public class DatabaseFactory extends SQLiteOpenHelper {

    public DatabaseFactory(Context context){
        super(context, BancoUtil.NOME_BANCO,null,BancoUtil.VERSAO);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String  sql = "CREATE TABLE "+ BancoUtil.TABELA_USUARIO+"("
                + BancoUtil.ID_USUARIO+ " integer primary key autoincrement,"
                + BancoUtil.LOGIN_USUARIO + " text,"
                + BancoUtil.SENHA_USUARIO + " text"
                +")";
        db.execSQL(sql);


        sql = "CREATE TABLE "+ BancoUtil.TABELA_EVENTO+"("
                + BancoUtil.ID_EVENTO + " integer primary key autoincrement,"
                + BancoUtil.TITULO_EVENTO + " text,"
                + BancoUtil.MODALIDADE_EVENTO + " text,"
                + BancoUtil.DATA_EVENTO + " text,"
                + BancoUtil.HORA_EVENTO + " text)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_USUARIO);
        db.execSQL("DROP TABLE IF EXISTS " + BancoUtil.TABELA_EVENTO);

        onCreate(db);
    }
}
