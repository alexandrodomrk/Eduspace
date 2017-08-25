package br.com.eduspaceandroid.cursoandroid.eduspace.helper;

import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 20/07/2017.
 */
public class Permissao {

    public static boolean validadaPermissoes(int requestCode, Activity activity, String[] permissoes) {

        if (Build.VERSION.SDK_INT >= 23) {

            List<String> listaPermissoes = new ArrayList<String>();

            for (String permissao : permissoes) {

                Boolean validaPermissao = ContextCompat.checkSelfPermission(activity,
                        permissao) == PackageManager.PERMISSION_GRANTED;

                if (!validaPermissao) listaPermissoes.add(permissao);

            }
            if (listaPermissoes.isEmpty()) return true;

            String[] novasPermissao = new String[listaPermissoes.size()];
            listaPermissoes.toArray(novasPermissao);
            //solicitar Permissao
            ActivityCompat.requestPermissions(activity, novasPermissao, requestCode);
        }
        return true;
    }
}




