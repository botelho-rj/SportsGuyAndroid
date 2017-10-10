package com.example.jpaulo.sportsguyandroid.DAO;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by jpaulo on 10/1/2017.
 */

public class Evento {

    private int ID;
    private String titulo;
    private String dtEvento;
    private String hrEvento;
    private String modalidade;

    public Evento() {
    }

    public Evento(int ID, String titulo, String dtEvento, String hrEvento, String modalidade) {
        this.ID = ID;
        this.titulo = titulo;
        this.dtEvento = dtEvento;
        this.hrEvento = hrEvento;
        this.modalidade = modalidade;
    }

    public int getID() {return ID;}

    public void setID(int ID) {this.ID = ID;}

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDtEvento() {
        return dtEvento;
    }

    public void setDtEvento(String dtEvento) {
        this.dtEvento = dtEvento;
    }

    public String getHrEvento() {return hrEvento; }

    public void setHrEvento(String hrEvento) {
        this.hrEvento = hrEvento;
    }

    public String getModalidade() { return modalidade; }

    public void setModalidade(String modalidade) { this.modalidade = modalidade; }

}