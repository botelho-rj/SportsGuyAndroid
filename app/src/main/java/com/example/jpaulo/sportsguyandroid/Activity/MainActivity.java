package com.example.jpaulo.sportsguyandroid.Activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.jpaulo.sportsguyandroid.DAO.Usuario;
import com.example.jpaulo.sportsguyandroid.DAO.UsuarioDAO;
import com.example.jpaulo.sportsguyandroid.R;

public class MainActivity extends Activity {

    public static Usuario usuarioLogado;
    private TextView textWelcome;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textWelcome = (TextView) findViewById(R.id.textWelcome);

        Intent result = getIntent();
        long id = result.getLongExtra("ID_USUARIO",0);
        if(usuarioLogado == null)
            usuarioLogado = new UsuarioDAO(this).carregaUsuarioPorID(id);

        textWelcome.setText("Olá! " + usuarioLogado.getLogin());
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

    public void fazerLogout(View v){
        usuarioLogado = null;
        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
        startActivity(intent);
    }

    private void carregarIntent(Class classe){
        Intent intent = new Intent(MainActivity.this,classe);
        startActivity(intent);
    }
}
