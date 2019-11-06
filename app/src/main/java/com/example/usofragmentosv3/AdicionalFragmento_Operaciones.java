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
import android.widget.RadioButton;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link AdicionalFragmento_Operaciones.OnFragmentInteractionListener} interface
 * to handle interaction events.
 */
public class AdicionalFragmento_Operaciones extends Fragment {

    private OnFragmentInteractionListener mListener;

    public AdicionalFragmento_Operaciones() {
        // Required empty public constructor
    }
    RadioButton rbtSuma,rbtResta, rbtMulti,rbtDiv;
    TextView txtNumero1,txtNumero2,txtRespuesta;
    Button btnEnviarOperacion;
    Integer resultado,num1,num2;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_adicional_fragmento__operaciones,container,false);
        txtNumero1 = view.findViewById(R.id.txtNumero1);
        txtNumero2 = view.findViewById(R.id.txtNumero2);
        txtRespuesta=view.findViewById(R.id.txtRespuestaAEnviar);
        btnEnviarOperacion = view.findViewById(R.id.btnEnviarOperacion);

        //Declaramos los radiobuttons
        rbtSuma = view.findViewById(R.id.rbtSuma);
        rbtResta = view.findViewById(R.id.rbtResta);
        rbtMulti = view.findViewById(R.id.rbtMultiplicacion);
        rbtDiv = view.findViewById(R.id.rbtDivision);

        //metodo de clickeo en boton
        btnEnviarOperacion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdicionalFragmento_Resultado fragmentoResultado = new AdicionalFragmento_Resultado();
                Bundle args = new Bundle();
                if(rbtSuma.isChecked()){
                    int num1 = Integer.parseInt((txtNumero1.getText().toString()));
                    int num2 = Integer.parseInt((txtNumero2.getText().toString()));
                    resultado = num1+num2;
                }else if(rbtResta.isChecked()){
                    int num1 = Integer.parseInt(String.valueOf(txtNumero1.getText()));
                    int num2 = Integer.parseInt(String.valueOf(txtNumero2.getText()));
                    resultado = num1-num2;
                }else if(rbtMulti.isChecked()){
                    int num1 = Integer.parseInt(String.valueOf(txtNumero1.getText()));
                    int num2 = Integer.parseInt(String.valueOf(txtNumero2.getText()));
                    resultado = num1*num2;
                }else if(rbtDiv.isChecked()){
                    int num1 = Integer.parseInt(String.valueOf(txtNumero1.getText()));
                    int num2 = Integer.parseInt(String.valueOf(txtNumero2.getText()));
                    resultado = num1/num2;
                }else{
                    resultado=-1;
                }
                args.putString("resultado",resultado.toString());
                fragmentoResultado.setArguments(args);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.contenedorOperaciones, fragmentoResultado);
                transaction.commit();
            }
        });

        //find de metodo
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
