package com.example.jpaulo.sportsguyandroid.Activity;

/**
 * Created by jpaulo on 10/1/2017.
 */
import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.jpaulo.sportsguyandroid.DAO.Evento;
import com.example.jpaulo.sportsguyandroid.DAO.EventoDAO;
import com.example.jpaulo.sportsguyandroid.R;

import java.util.Calendar;

public class CadastrarEventoActivity extends Activity implements View.OnClickListener {

    private EditText tTitulo;
    private Spinner spinnerModalidade;
    private Button bData;
    private EditText tData;
    private Button bHora;
    private EditText tHora;
    private Button finalizar;
    private int dia;
    private int mes;
    private int ano;
    private int hr;
    private int min;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastrar_evento);

        tTitulo = (EditText) findViewById(R.id.titulo);
        spinnerModalidade = (Spinner) findViewById(R.id.spinner_modalidade);

        bData = (Button) findViewById(R.id.btn_data);
        tData = (EditText) findViewById(R.id.text_data);
        bHora = (Button) findViewById(R.id.btn_hora);
        tHora = (EditText) findViewById(R.id.text_hora);
        bData.setOnClickListener(this);
        bHora.setOnClickListener(this);

        finalizar = (Button) findViewById(R.id.btn_finalizar);

    }

    @Override
    public void onClick(View v) {
        if(v == bData){
            final Calendar c = Calendar.getInstance();
            dia = c.get(Calendar.DAY_OF_MONTH);
            mes = c.get(Calendar.MONTH);
            ano = c.get(Calendar.YEAR);

            DatePickerDialog datePicker = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int d, int me, int a) {
                    tData.setText(d+"/"+me+1+"/"+a);
                }
            }, dia, mes, ano);
            datePicker.show();
        }

        if(v == bHora){
            final Calendar c = Calendar.getInstance();
            hr = c.get(Calendar.HOUR_OF_DAY);
            min = c.get(Calendar.MINUTE);

            TimePickerDialog timePicker = new TimePickerDialog(this, new TimePickerDialog.OnTimeSetListener() {
                @Override
                public void onTimeSet(TimePicker view, int h, int m) {
                    tHora.setText(h+":"+m);
                }
            }, hr, min, false);
            timePicker.show();
        }
    }

    public void salvarEvento(View v){
        EventoDAO eventoDAO = new EventoDAO(this);
        Evento evento = new Evento();
        evento.setTitulo(tTitulo.getText().toString());
        evento.setModalidade(spinnerModalidade.getSelectedItem().toString());
        evento.setDtEvento(tData.getText().toString());
        evento.setHrEvento(tHora.getText().toString());

        long resultado = eventoDAO.insereDado(evento);

        if(resultado > 0){
            exibirMensagem("O Evento foi cadastro!");
        }
        else{
            exibirMensagem("Erro ao cadastrar o Evento.");
        }
    }

    private void exibirMensagem(String mensagem){
        Toast.makeText(this, mensagem, Toast.LENGTH_SHORT).show();
    }

}
