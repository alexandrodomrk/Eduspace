package br.com.eduspaceandroid.cursoandroid.eduspace.Activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import br.com.eduspaceandroid.cursoandroid.eduspace.R;

public class CadastroActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro);
    }

    public void abrirCadastroUsuario (View view){
        Intent intent = new Intent(CadastroActivity.this,CadastroUsuarioActivity.class);
        startActivity(intent);
    }

    public void abrirCadastroSala(View view){
        Intent intent = new Intent(CadastroActivity.this,CadastroSalaActivity.class);
        startActivity(intent);
    }

    public void abrirCadastroCurso(View view){
        Intent intent = new Intent(CadastroActivity.this,CadastroCursoActivity.class);
        startActivity(intent);
    }
}
