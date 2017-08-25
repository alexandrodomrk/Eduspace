package br.com.eduspaceandroid.cursoandroid.eduspace.Activity;

import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import br.com.eduspaceandroid.cursoandroid.eduspace.Adapter.TabAdapter;
import br.com.eduspaceandroid.cursoandroid.eduspace.R;
import br.com.eduspaceandroid.cursoandroid.eduspace.helper.SlidingTabLayout;

public class SlidingTabSalaActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private SlidingTabLayout slidingTabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sliding_tab_sala);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Sala");
        setSupportActionBar(toolbar);

        slidingTabLayout = (SlidingTabLayout) findViewById(R.id.stl_tabs);
        viewPager = (ViewPager) findViewById(R.id.vp_pagina);

        //configurar Slinding tab
        slidingTabLayout.setDistributeEvenly(true);
        slidingTabLayout.setSelectedIndicatorColors(ContextCompat.getColor(this, R.color.colorAccent));

        //configura Adapter
        TabAdapter tabAdapter = new TabAdapter(getSupportFragmentManager());
        viewPager.setAdapter(tabAdapter);

        slidingTabLayout.setViewPager(viewPager);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_consultar_sala, menu);
        return true;
    }
}



