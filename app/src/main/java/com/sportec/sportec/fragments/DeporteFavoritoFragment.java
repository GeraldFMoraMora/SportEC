package com.sportec.sportec.fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.sportec.sportec.R;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link DeporteFavoritoFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link DeporteFavoritoFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DeporteFavoritoFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private DeporteFavoritoFragment.OnFragmentInteractionListener mListener;

    public DeporteFavoritoFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment DeporteFavoritoFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static DeporteFavoritoFragment newInstance(String param1) {
        DeporteFavoritoFragment fragment = new DeporteFavoritoFragment();
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
        View view = inflater.inflate(R.layout.fragment_deporte_favorito, container, false);
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
}
