package br.com.eduspaceandroid.cursoandroid.eduspace.Activity;

import android.support.v4.view.MenuItemCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import br.com.eduspaceandroid.cursoandroid.eduspace.R;

public class ConsultarCursoActivity extends AppCompatActivity implements SearchView.OnQueryTextListener {

    private Toolbar toolbar;
    private SearchView pesquisar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_curso);

        toolbar=(Toolbar)findViewById(R.id.toolbar);
        toolbar.setTitle("Consultar Curso");
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_consulta_curso,menu);

        MenuItem searchItem = menu.findItem(R.id.item_pesquisar);
        SearchView pesquisar = (SearchView)
                MenuItemCompat.getActionView(searchItem);
        pesquisar.setOnQueryTextListener((SearchView.OnQueryTextListener) this);
        return true;
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return false;
    }

    @Override
    public boolean onQueryTextChange(String newText) {
        return false;
    }
}
