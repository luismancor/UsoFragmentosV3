package com.example.usofragmentosv3;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ContactosFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class ContactosFragmento extends Fragment {

    private OnFragmentInteractionListener mListener;

    public ContactosFragmento() {
        // Required empty public constructor
    }

    ListView lstContactos;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_contactos_fragmento, container, false);
        lstContactos = view.findViewById(R.id.lstContactos);
        String[]values = new String[20];
        for(int i=0; i<values.length;i++){
            values[i]= "Usuario"+i;
        }
        //creamos el adaptador para el array
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getContext(),android.R.layout.simple_list_item_1, android.R.id.text1, values);
        lstContactos.setAdapter(adapter);

        //darle funcion de click a cada item de lista
        lstContactos.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int i, long id) {
                int posicion =i;
                String valor = lstContactos.getItemAtPosition(posicion).toString();
                Intent mostrar = new Intent(getContext(),DetallesContacto.class);
                mostrar.putExtra("nombre",valor);
                startActivity(mostrar);
            }
        });
        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
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
