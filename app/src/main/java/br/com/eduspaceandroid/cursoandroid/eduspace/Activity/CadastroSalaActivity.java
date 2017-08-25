package br.com.eduspaceandroid.cursoandroid.eduspace.Activity;

import android.database.Cursor;
import android.renderscript.Sampler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import android.widget.Toolbar;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.lang.ref.Reference;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.eduspaceandroid.cursoandroid.eduspace.R;
import br.com.eduspaceandroid.cursoandroid.eduspace.config.ConfiguracaoFirebase;
import br.com.eduspaceandroid.cursoandroid.eduspace.model.Sala;

public class CadastroSalaActivity extends AppCompatActivity {

    private EditText numero;
    private EditText capacidade;
    private ListView lv_Sala;
    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase;

    private List<Sala> listSala = new ArrayList<Sala>();
    private ArrayAdapter<Sala> arrayAdapterSala;

    private Sala salaSelecionada;

   private android.support.v7.widget.Toolbar toolbar;

    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_sala);
        try {

            toolbar = (android.support.v7.widget.Toolbar) findViewById(R.id.toolbar);
            toolbar.setTitle("Sala");
            setSupportActionBar(toolbar);
            numero = (EditText) findViewById(R.id.editNumero);
            capacidade = (EditText) findViewById(R.id.editCapacidade);
            lv_Sala = (ListView) findViewById(R.id.listViewSala);
            inicializarFirebase();

            eventoDatabase();
            lv_Sala.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    salaSelecionada = (Sala) parent.getItemAtPosition(position);
                   int numero=(salaSelecionada.getNumero());
                   int capacidade=(salaSelecionada.getCapacidade());
                }

            });
        }catch (Exception e){
            e.printStackTrace();
        }

    }



    private void eventoDatabase() {
        databaseReference.child("Sala").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listSala.clear();
                for (DataSnapshot objSnapshot:dataSnapshot.getChildren()){
                    Sala sala= objSnapshot.getValue(Sala.class);
                    listSala.add(sala);
                }
                arrayAdapterSala=new ArrayAdapter<Sala>(CadastroSalaActivity.this,
                        android.R.layout.simple_list_item_1,
                        listSala);
                lv_Sala.setAdapter(arrayAdapterSala);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    private void inicializarFirebase(){
         FirebaseApp.initializeApp(CadastroSalaActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
         databaseReference =firebaseDatabase.getReference();
     }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_sala,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override

    public boolean onOptionsItemSelected(MenuItem item) {
        try {

            int id = item.getItemId();
            if (id == R.id.menu_novo) {

                Sala s = new Sala();
                s.setId(UUID.randomUUID().toString());
                s.setNumero(Integer.parseInt(numero.getText().toString()));
                s.setCapacidade(Integer.parseInt(capacidade.getText().toString()));
                databaseReference.child("Sala").child(s.getId()).setValue(s);
                limparCampos();
            } else if (id == R.id.menu_atualizar) {
                Sala s = new Sala();
                s.setId(salaSelecionada.getId());
                s.setNumero(Integer.parseInt(numero.getText().toString().trim()));
                s.setCapacidade(Integer.parseInt(capacidade.getText().toString().trim()));
                databaseReference.child("Sala").child(s.getId()).setValue(s);
                limparCampos();
            } else if (id == R.id.menu_deletar) {
                Sala s = new Sala();
                s.setId(salaSelecionada.getId());
                databaseReference.child("Sala").child(s.getId()).removeValue();
                limparCampos();
            }

            }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    private void limparCampos(){
        numero.setText("");
        capacidade.setText(" ");
    }
}










