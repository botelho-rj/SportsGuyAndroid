package com.example.jpaulo.sportsguyandroid.DAO;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by jpaulo on 10/1/2017.
 */

public class Evento {

    private int idEvento;
    private String titulo;
    private String modalidade;
    private String dtEvento;
    private String hrEvento;


    public Evento() {
    }

    public Evento(int idEvento, String titulo, String modalidade, String dtEvento, String hrEvento) {
        this.idEvento = idEvento;
        this.titulo = titulo;
        this.modalidade = modalidade;
        this.dtEvento = dtEvento;
        this.hrEvento = hrEvento;

    }

    public int getIdEvento() {return idEvento;}

    public void setIdEvento(int idEvento) {this.idEvento = idEvento;}

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
