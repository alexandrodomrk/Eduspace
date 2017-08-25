package br.com.eduspaceandroid.cursoandroid.eduspace.Activity;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;

import br.com.eduspaceandroid.cursoandroid.eduspace.R;
import br.com.eduspaceandroid.cursoandroid.eduspace.config.ConfiguracaoFirebase;
import br.com.eduspaceandroid.cursoandroid.eduspace.model.Usuario;

public class LoginActivity extends AppCompatActivity {

    private EditText email;
    private EditText senha;
    private Button botaoLogar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText)findViewById(R.id.edit_login_email);
        senha=(EditText)findViewById(R.id.edit_login_senha);
        botaoLogar=(Button)findViewById(R.id.bt_logar);

        botaoLogar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario= new Usuario();
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                if(email.getText().equals("") && senha.getText().equals("")){
                    Toast.makeText(LoginActivity.this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
                }else{
                    validarLogin();
                }

                }
        });
            }

    private void validarLogin(){

        autenticacao= ConfiguracaoFirebase.getFirebaseAutenticacao();
        autenticacao.signInWithEmailAndPassword(
                usuario.getEmail(),
                usuario.getSenha()
        ).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()) {
                   abrirTelaCadastro();
                    Toast.makeText(LoginActivity.this, "Sucesso ao fazer login", Toast.LENGTH_SHORT).show();

                } else {

                    String erroExcecao="";

                    try {
                        throw task.getException();
                    } catch (FirebaseAuthInvalidUserException e) {
                        erroExcecao = "Email não existe, consulte o Administrador";
                    } catch (FirebaseAuthInvalidCredentialsException e) {
                        erroExcecao = "Senha inválida, consulte o Administrador";
                    } catch (Exception e) {
                        erroExcecao="ao fazer login!";
                        e.printStackTrace();
                    }

                    Toast.makeText(LoginActivity.this,"Erro: "+erroExcecao,Toast.LENGTH_SHORT).show();

                }
            }
        });
        }
     private void abrirTelaCadastro(){
     Intent intent= new Intent(LoginActivity.this, CadastroActivity.class);
     startActivity(intent);
     finish();
   }
    }










