package com.example.usofragmentosv3;

import android.content.Context;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.File;
import java.io.FileInputStream;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link MusicaFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class MusicaFragmento extends Fragment {

    private OnFragmentInteractionListener mListener;

    private MediaPlayer mediaplayer;
    private int playbackPosition = 0;
    TextView lblDuracion, lblPosicionActual,lblNombre;

    ImageButton btnPlay,btnPause,btnStop,btnReset,imagenButton;

    public MusicaFragmento() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_musica_fragmento, container, false);

        lblNombre = view.findViewById(R.id.lblNombre);
        btnPlay = view.findViewById(R.id.btnPlay);
        btnPause = view.findViewById(R.id.btnPause);
        btnReset = view.findViewById(R.id.btnReset);
        btnStop = view.findViewById(R.id.btnStop);
        imagenButton= view.findViewById(R.id.imgMusicaLista);

      //EXAMPLE  String myValue = this.getArguments().getString("message");
        if(getArguments()!=null) {
            String musica = this.getArguments().getString("musica");
            String nombre = this.getArguments().getString("nombre");
            try {
                playAudio(musica);
                //lblNombre.setText(nombre);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        //METODO ANTIGUO PARA ENVIAR ENTRE INTETS
        /*
        Bundle reproducir = this.getArguments().getExtras();
        if(reproducir != null){
            String musica = getIntent().getExtras().getString("musica");
            String nombre = getIntent().getExtras().getString("nombre");
            try {
                playAudio(musica);
                lblNombre.setText(nombre);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        */


        //Imagen que envia a intent
        imagenButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent mostrar = new Intent(getContext(),ListaMusica.class);
                startActivity(mostrar);
            }
        });

        //metodo de clickeo en boton
        btnPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    if(mediaplayer==null) {
                        playLocalAudio();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
        });
        btnPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaplayer!=null && mediaplayer.isPlaying()){
                    playbackPosition = mediaplayer.getCurrentPosition();
                    mediaplayer.pause();
                }
            }
        });
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaplayer!=null && !mediaplayer.isPlaying()){
                    mediaplayer.seekTo(playbackPosition);
                    mediaplayer.start();
                }
            }
        });
        btnStop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(mediaplayer!=null){
                    mediaplayer.stop();
                    playbackPosition = 0;
                    mediaplayer=null;
                }
            }
        });
        //FIN DE OPCIONES DE BOTON

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    //METODO QUE REPRODUCe LA CANCION DE LA LISTA FALTA TESTEAR???????????
    public void playAudio(String url) throws Exception{
        killMediaPlayer();
        String filePath = url;
        File file = new File(filePath);
        FileInputStream inputStream = new FileInputStream(file);
        mediaplayer = new MediaPlayer();
        mediaplayer.setDataSource(inputStream.getFD());
        inputStream.close();
        mediaplayer.prepare();
        mediaplayer.start();
    }
    /*
    public void verLista(View v){
        Intent cargarLista = new Intent(this, musicas.class);
        startActivity(cargarLista);
    }
*/

    /* NO FUNCIONA POR QUE NO TIENE APPCOMPATIVITY Y SI SE EXTIENDE EN LA CLASE GENERAR ERRROR DE RETORNO CON EL FRAGMET
    public void doClic(View v){
        switch (v.getId()){
            case R.id.btnPlay:
                try{
                    if(mediaplayer==null) {
                        playLocalAudio();
                    }
                }catch (Exception e){
                    e.printStackTrace();
                }
                break;
            case R.id.btnPause:
                if(mediaplayer!=null && mediaplayer.isPlaying()){
                    playbackPosition = mediaplayer.getCurrentPosition();
                    mediaplayer.pause();
                }
                break;
            case R.id.btnReset:
                if(mediaplayer!=null && !mediaplayer.isPlaying()){
                    mediaplayer.seekTo(playbackPosition);
                    mediaplayer.start();
                }
                break;
            case R.id.btnStop:
                if(mediaplayer!=null){
                    mediaplayer.stop();
                    playbackPosition = 0;
                    mediaplayer=null;
                }
                break;

        }
    }
    */


    //funcion de reproducir audio local tod ARCHIVO TIENE NOMBRE EN MINUS
    public void playLocalAudio() throws Exception{
        killMediaPlayer();
        mediaplayer = MediaPlayer.create(getContext(),R.raw.audio);
        mediaplayer.setAudioStreamType(AudioManager.STREAM_MUSIC);
        mediaplayer.start();
    }


    //Metodo para destruir la cancion al cerrar la app  se cambio dE PROTECTEC A PUBLIC
    @Override
    public void onDestroy() {
        killMediaPlayer();
        super.onDestroy();
    }

    private void killMediaPlayer(){
        if(mediaplayer!=null){
            try{
                mediaplayer.release();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }








    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
