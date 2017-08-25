package br.com.eduspaceandroid.cursoandroid.eduspace.helper;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;

/**
 * Created by Alex on 20/07/2017.
 */
public class Preferencias {

    private Context contexto;
    private SharedPreferences preferences;
    private String NOME_ARQUIVO= "eduspace.preferencias";
    private int MODE=0;
    private SharedPreferences.Editor editor;
    private String CHAVE_NOME = "nome";
    private String CHAVE_EMAIL = "telefone";
    private String CHAVE_SENHA="senha";


        public Preferencias(Context contextoParametro){
            contexto=contextoParametro;
            preferences = contexto.getSharedPreferences(NOME_ARQUIVO,MODE);
            editor=preferences.edit();

        }

    public void salvarUsuarioPreferencias (String nome, String email, String senha){

        editor.putString("nome",CHAVE_NOME);
        editor.putString("email",CHAVE_EMAIL);
        editor.putString("senha",CHAVE_SENHA);
        editor.commit();

    }
    public HashMap<String, String> getDadosUsusarios(){
        HashMap<String, String> dadosUsuarios = new HashMap<>();
        dadosUsuarios.put(CHAVE_NOME, preferences.getString(CHAVE_NOME,null));
        dadosUsuarios.put(CHAVE_EMAIL, preferences.getString(CHAVE_EMAIL, null));
        dadosUsuarios.put(CHAVE_SENHA, preferences.getString(CHAVE_SENHA, null));

        return dadosUsuarios;

    }


}
