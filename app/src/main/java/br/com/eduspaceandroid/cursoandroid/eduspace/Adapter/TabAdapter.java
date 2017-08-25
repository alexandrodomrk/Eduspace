package br.com.eduspaceandroid.cursoandroid.eduspace.Adapter;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import br.com.eduspaceandroid.cursoandroid.eduspace.fragment.ManhaFragment;
import br.com.eduspaceandroid.cursoandroid.eduspace.fragment.NoiteFragment;
import br.com.eduspaceandroid.cursoandroid.eduspace.fragment.TardeFragment;

public class TabAdapter extends FragmentStatePagerAdapter {
    private String[] tituloAbas= {"MANHÃ","TARDE","NOITE"};
    public TabAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int position) {
        Fragment fragment = null;
        switch (position){
            case 0:
                fragment=new ManhaFragment();
                break;
            case 1:
                fragment=new TardeFragment();
                break;
            case 2:
                fragment=new NoiteFragment();
                break;
        }

        return fragment;
    }

    @Override
    public int getCount() {
        return tituloAbas.length;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return tituloAbas[position];
    }
}
