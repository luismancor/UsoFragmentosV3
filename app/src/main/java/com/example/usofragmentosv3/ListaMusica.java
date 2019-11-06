package com.example.usofragmentosv3;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentTransaction;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class ListaMusica extends AppCompatActivity implements MusicaFragmento.OnFragmentInteractionListener{
    private ListView lstMusicas;
    private List<String> item = null;
    private String ruta = Environment.getExternalStorageDirectory() + "/Download/";


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lista_musica);


        item = new ArrayList<String>();
        final List<String> item = new ArrayList<String>();

        //version sdk 23(android 6.1) para arriba hay que conceder permisos desde codigo adicionalmente al androidmanifest
        final int READ_EXTERNAL_STORAGE_PERMISSION_CODE = 123;
        String state = Environment.getExternalStorageState();
        if(ContextCompat.checkSelfPermission(this, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_DENIED){
            requestPermissions(new String[]{Manifest.permission.READ_EXTERNAL_STORAGE}, READ_EXTERNAL_STORAGE_PERMISSION_CODE);
        }


        File f = new File(ruta);
        File[] files = f.listFiles();


        for (int i = 0; i < files.length; i++)
        {
            File archivos = files[i];
            if (archivos.isDirectory()) {
                //no agregamos ningun directorio a la lista
            }else if(archivos.getName().endsWith(".mp3")){
                //metodo alterno agregar files mp3 https://stackoverflow.com/questions/39461954/list-all-mp3-files-in-android
                item.add(archivos.getName());
            }


        }

        final ListView lstMusicas = findViewById(R.id.lstMusicas);
        ArrayAdapter<String> fileList = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, item);
        lstMusicas.setAdapter(fileList);

        //Punt23 ??????????????
        lstMusicas.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> a, View v, int position, long id) {
                int itemPosition     = position;
                String  itemValue    = (String) lstMusicas.getItemAtPosition(position);

                MusicaFragmento fragmento = new MusicaFragmento();
                Bundle args = new Bundle();
                args.putString("nombre", itemValue);
                args.putString("musica", ruta + itemValue);
                fragmento.setArguments(args);

                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorMusicas, fragmento);
                transaction.commit();

                /*
                args.putSerializable(TAG_MY_CLASS, myClass);
                Fragment toFragment = new ToFragment();
                toFragment.setArguments(args);
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.body, toFragment, TAG_TO_FRAGMENT)
                        .addToBackStack(TAG_TO_FRAGMENT).commit();
                */

                /*
                Intent enviarMusica = new Intent(getApplicationContext(),MusicaFragmento.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                enviarMusica.putExtra("musica", ruta + itemValue);
                enviarMusica.putExtra("nombre", itemValue);
                startActivity(enviarMusica);
                */

                finish();
            }
        });




    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}

