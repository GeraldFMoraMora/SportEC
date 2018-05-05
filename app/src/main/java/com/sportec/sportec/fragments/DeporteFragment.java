package com.sportec.sportec.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;

import com.sportec.sportec.Informacion.Noticia;
import com.sportec.sportec.Informacion.NoticiaAdapter;
import com.sportec.sportec.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeporteFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeporteFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeporteFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private DeporteFragment.OnFragmentInteractionListener mListener;

    private GridView mGridView;
    private NoticiaAdapter mNoticiaAdapter;

    private ImageView mImagenNoticia;
    private TextView MTituloNoticia;

    public DeporteFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DeporteFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeporteFragment newInstance(String param1) {
        DeporteFragment fragment = new DeporteFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_deporte, container, false);
        /**view.findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
        @Override public void onClick(View view) {
        if (mListener != null) {
        mRegistro.setAge(mRegistro.getAge() + 1);
        mListener.onClickButton(mRegistro);
        }
        }
        });**/

        return view;
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
    }
    public static Noticia[] ITEMS = {
            new Noticia("Uno","uno", new java.util.Date(), R.mipmap.grid1),
            new Noticia("Dos","dos", new java.util.Date(),R.mipmap.grid2),
            new Noticia("Tres","tres", new java.util.Date(),R.mipmap.grid3),
            new Noticia("Cuatro","cuatro", new java.util.Date(),R.mipmap.grid4)
    };
    public static Noticia getItem(int id) {
        for (Noticia item : ITEMS) {
            if (item.getId() == id) {
                return item;
            }
        }
        return null;
    }
}
