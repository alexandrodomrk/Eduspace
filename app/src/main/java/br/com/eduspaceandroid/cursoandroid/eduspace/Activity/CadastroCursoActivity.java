package br.com.eduspaceandroid.cursoandroid.eduspace.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import br.com.eduspaceandroid.cursoandroid.eduspace.R;
import br.com.eduspaceandroid.cursoandroid.eduspace.model.Curso;
import br.com.eduspaceandroid.cursoandroid.eduspace.model.Sala;

public class CadastroCursoActivity extends AppCompatActivity {
    private EditText nomeCurso;
    private ListView lv_Curso;
    private Toolbar toolbarCurso;

    private List<Curso> listCurso = new ArrayList<Curso>();
    private ArrayAdapter<Curso> arrayAdapterCurso;

    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase;

    private Curso cursoSelecionado;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cadastro_curso);

        nomeCurso=(EditText)findViewById(R.id.et_NomeCurso);
        lv_Curso=(ListView)findViewById(R.id.lv_Curso);
        toolbarCurso=(Toolbar)findViewById(R.id.toolbar);
        toolbarCurso.setTitle("Curso");
        setSupportActionBar(toolbarCurso);
        inicializarFirebase();
        EventoDatabase();

        lv_Curso.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                cursoSelecionado = (Curso)parent.getItemAtPosition(position);
                 nomeCurso.setText(cursoSelecionado.getNome());

            }
        });
    }
    private void inicializarFirebase(){
        FirebaseApp.initializeApp(CadastroCursoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference =firebaseDatabase.getReference();
    }
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_cadastro_curso,menu);
        return super.onCreateOptionsMenu(menu);
    }

    private void EventoDatabase(){
        databaseReference.child("Curso").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                listCurso.clear();
                for (DataSnapshot objdataSnapshot:dataSnapshot.getChildren()){
                    Curso curso = objdataSnapshot.getValue(Curso.class);
                    listCurso.add(curso);
                }
                arrayAdapterCurso=new ArrayAdapter<Curso>(CadastroCursoActivity.this,
                        android.R.layout.simple_list_item_1,
                        listCurso);
                lv_Curso.setAdapter(arrayAdapterCurso);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    public boolean onOptionsItemSelected(MenuItem item) {
        try {

            int id = item.getItemId();
            if (id == R.id.menu_novo) {

                Curso curso = new Curso();
                curso.setId(UUID.randomUUID().toString());
                curso.setNome(nomeCurso.getText().toString());
                databaseReference.child("Curso").child(curso.getId()).setValue(curso);
                limparCampos();

            } else if (id == R.id.menu_atualizar) {
                Curso curso = new Curso();
                curso.setId(cursoSelecionado.getId());
                curso. setNome(nomeCurso.getText().toString());
                databaseReference.child("Curso").child(curso.getId()).setValue(curso);
                limparCampos();

            } else if (id == R.id.menu_deletar) {
                Curso curso = new Curso();
                curso.setId(cursoSelecionado.getId());
                databaseReference.child("Curso").child(curso.getId()).removeValue();
                limparCampos();
            }

        }catch (Exception e){
            e.printStackTrace();
        }
        return true;
    }
    private void limparCampos(){
        nomeCurso.setText("");
    }

}
