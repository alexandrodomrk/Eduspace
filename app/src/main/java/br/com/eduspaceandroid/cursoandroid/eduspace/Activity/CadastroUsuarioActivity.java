package br.com.eduspaceandroid.cursoandroid.eduspace.Activity;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthActionCodeException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.auth.FirebaseAuthWeakPasswordException;
import com.google.firebase.auth.FirebaseUser;

import br.com.eduspaceandroid.cursoandroid.eduspace.R;
import br.com.eduspaceandroid.cursoandroid.eduspace.config.ConfiguracaoFirebase;
import br.com.eduspaceandroid.cursoandroid.eduspace.model.Usuario;

public class CadastroUsuarioActivity extends AppCompatActivity {
    private TextView nome;
    private TextView email;
    private TextView senha;
    private Button botaocadastrar;
    private Usuario usuario;
    private FirebaseAuth autenticacao;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_usuario);

        nome = (TextView) findViewById(R.id.edit_cadastro_nome);
        email = (TextView) findViewById(R.id.edit_cadastro_email);
        senha = (TextView) findViewById(R.id.edit_cadastro_senha);
        botaocadastrar = (Button) findViewById(R.id.btCadastrar);

        botaocadastrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                usuario = new Usuario();
                usuario.setNome(nome.getText().toString());
                usuario.setEmail(email.getText().toString());
                usuario.setSenha(senha.getText().toString());
                cadastrarUsuario();

               autenticacao.signOut();





            }
        });
    }

      private void cadastrarUsuario(){
          autenticacao= ConfiguracaoFirebase.getFirebaseAutenticacao();
          autenticacao.createUserWithEmailAndPassword(
                  usuario.getEmail(),
                  usuario.getSenha()
          ).addOnCompleteListener(CadastroUsuarioActivity.this, new OnCompleteListener<AuthResult>() {
              @Override
              public void onComplete(@NonNull Task<AuthResult> task) {
                  if (task.isSuccessful()){
                      abriTelaLogin();
                      Toast.makeText(CadastroUsuarioActivity.this,"Sucesso ao cadastrar usuário",Toast.LENGTH_SHORT).show();
                      FirebaseUser usuarioFirebase = task.getResult().getUser();
                      usuario.setId(usuarioFirebase.getUid());
                      usuario.salvar();
                      autenticacao.signOut();
                      finish();

                  }else {
                      String erroExcecao="";

                      try {
                          throw task.getException();
                      } catch (FirebaseAuthWeakPasswordException e) {
                         erroExcecao = "Digite uma senha mais forte, contendo mais caracteres e com letras e numeros";
                      } catch (FirebaseAuthInvalidCredentialsException e) {
                          erroExcecao = "O email digitado é inválido, digite um novo email!";
                      } catch (FirebaseAuthUserCollisionException e) {
                          erroExcecao = "Este email já esta em uso no app!";
                      } catch (Exception e) {
                          erroExcecao="Ao cadastrar usuário!";
                          e.printStackTrace();
                      }


                      Toast.makeText(CadastroUsuarioActivity.this,"Erro: "+erroExcecao,Toast.LENGTH_SHORT).show();

                  }
              }
          });


    }
  private void abriTelaLogin(){
      Intent intent =new Intent(CadastroUsuarioActivity.this, LoginActivity.class);
      startActivity(intent);
      finish();

  }
    }

