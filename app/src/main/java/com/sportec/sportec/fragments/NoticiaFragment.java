package com.sportec.sportec.fragments;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sportec.sportec.R;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoticiaFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoticiaFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";

    // TODO: Rename and change types of parameters
    private String mParam1;

    private TextView mRegisterTextView;

    private OnFragmentInteractionListener mListener;

    public NoticiaFragment() {
        // Required empty public constructor
    }

    public static NoticiaFragment newInstance(String json) {
        NoticiaFragment fragment = new NoticiaFragment();
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
        View view = inflater.inflate(R.layout.fragment_noticias, container, false);
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

    public interface OnFragmentInteractionListener {
    }
}
