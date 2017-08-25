package br.com.eduspaceandroid.cursoandroid.eduspace.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import br.com.eduspaceandroid.cursoandroid.eduspace.R;
import br.com.eduspaceandroid.cursoandroid.eduspace.model.Sala;

public class AgendamentoActivity extends AppCompatActivity {
    private Toolbar toolbarAgendamento;
    private Spinner spinnerSala;
    private Spinner spinnerCurso;
    private Sala sala;
    private ArrayAdapter<Sala> arrayAdapterSala;

    private DatabaseReference databaseReference ;
    private FirebaseDatabase firebaseDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_agendamento);

        toolbarAgendamento=(Toolbar)findViewById(R.id.toolbar);
        toolbarAgendamento.setTitle("Agendamento");
        setSupportActionBar(toolbarAgendamento);

        spinnerSala=(Spinner)findViewById(R.id.spinnerSala);

;       inicializarFirebase();
        //carregar dados


    }


    private void inicializarFirebase() {
        FirebaseApp.initializeApp(AgendamentoActivity.this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        firebaseDatabase.setPersistenceEnabled(true);
        databaseReference =firebaseDatabase.getReference();
    }



    public boolean onCreateOptionsMenu(Menu menu){
         getMenuInflater().inflate(R.menu.menu_agendamento,menu);
         return super.onCreateOptionsMenu(menu);



     }

}
