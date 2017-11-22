package com.example.jpaulo.sportsguyandroid.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
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

public class AtualizarEventosActivity extends Activity {

    private int ID_EVENTO;
    private EventoDAO eventoDAO;
    private Evento evento;

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
        setContentView(R.layout.activity_atualizar_eventos);

        Intent intent = getIntent();
        if(intent.hasExtra("ID_EVENTO")){
            ID_EVENTO = intent.getIntExtra("ID_EVENTO",0);
        }
        eventoDAO = new EventoDAO(this);
        evento = eventoDAO.carregaEventoPorID(ID_EVENTO);

        tTitulo = (EditText) findViewById(R.id.titulo);
        spinnerModalidade = (Spinner) findViewById(R.id.spinner_modalidade);
        bData = (Button) findViewById(R.id.btn_data);
        tData = (EditText) findViewById(R.id.text_data);
        bHora = (Button) findViewById(R.id.btn_hora);
        tHora = (EditText) findViewById(R.id.text_hora);
        bData.setOnClickListener((View.OnClickListener) this);
        bHora.setOnClickListener((View.OnClickListener) this);

        tTitulo.setText(evento.getTitulo());
        selectSpinnerItemByValue(spinnerModalidade, evento.getModalidade());
        tData.setText(evento.getDtEvento());
        tHora.setText(evento.getHrEvento());

    }

    public void atualizarEvento(View v){
        evento.setTitulo(tTitulo.getText().toString());
        evento.setModalidade(spinnerModalidade.getSelectedItem().toString());
        evento.setDtEvento(tData.getText().toString());
        evento.setHrEvento(tHora.getText().toString());

        if(eventoDAO.atualizarEvento(evento))
            Toast.makeText(AtualizarEventosActivity.this, "Evento atuaizado com sucesso.", Toast.LENGTH_SHORT).show();
        else
            Toast.makeText(AtualizarEventosActivity.this, "Erro ao atualizar Evento.", Toast.LENGTH_SHORT).show();
        telaInicial();
    }

    public void removerEvento(View v){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Remover Evento");
        builder.setMessage("Tem certeza que deseja remover este evento do sistema?");
        builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        eventoDAO.deletaRegistro(ID_EVENTO);
                        Toast.makeText(AtualizarEventosActivity.this, "Evento removido com sucesso.", Toast.LENGTH_SHORT).show();
                        telaInicial();
                    }
                });
        builder.setNegativeButton("Não", null);

        AlertDialog dialog = builder.create();
        dialog.show();
    }


    private void telaInicial() {
        Intent telaInicial = new Intent(AtualizarEventosActivity.this,MainActivity.class);
        startActivity(telaInicial);
        finish();
    }

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

    public static void selectSpinnerItemByValue(Spinner spnr, String value) {
        ArrayAdapter adapter = (ArrayAdapter) spnr.getAdapter();
        for (int position = 0; position < adapter.getCount(); position++) {
            if(adapter.getItem(position).toString().equals(value)) {
                spnr.setSelection(position);
                return;
            }
        }
    }
}
