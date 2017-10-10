package com.example.jpaulo.sportsguyandroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.jpaulo.sportsguyandroid.R;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void menuPrincipal(View v)
    {
        switch (v.getId()){
            case R.id.btn_lista_completa:
                carregarIntent(ListarTodosActivity.class);
                break;
            case R.id.btn_cadastrar:
                carregarIntent(CadastrarEventoActivity.class);
                break;
        }
    }

    private void carregarIntent(Class classe){
        Intent intent = new Intent(MainActivity.this,classe);
        startActivity(intent);
    }
}
