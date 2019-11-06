package com.example.usofragmentosv3;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdicionalFragmento.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AdicionalFragmento extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AdicionalFragmento() {
        // Required empty public constructor
    }

    Button btnOperaciones,btnResultado;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_adicional_fragmento, container, false);
        btnOperaciones = view.findViewById(R.id.btnOperacion);
        btnResultado = view.findViewById(R.id.btnRespuesta);
        //cambia el frame por el fragment1
        btnOperaciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionalFragmento_Operaciones fragmentoDeOperaciones = new AdicionalFragmento_Operaciones();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorOperaciones, fragmentoDeOperaciones);
                transaction.commit();
            }
        });

        //boton 2
        btnResultado.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionalFragmento_Resultado fragmentoDeResultados = new AdicionalFragmento_Resultado();
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorOperaciones, fragmentoDeResultados);
                transaction.commit();
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