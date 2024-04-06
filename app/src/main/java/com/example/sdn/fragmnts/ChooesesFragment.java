package com.example.sdn.fragmnts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.example.sdn.R;
import com.example.sdn.data.Expense;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.time.LocalTime;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooesesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooesesFragment extends Fragment {
    private ImageButton h,c ,c2 , c3 ,f , p ;
    private FloatingActionButton back;
    private Expense expense;
    private LocalTime now ;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ChooesesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ChooesesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ChooesesFragment newInstance(String param1, String param2) {
        ChooesesFragment fragment = new ChooesesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_chooeses, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        now = LocalTime.now();
        h = getView().findViewById(R.id.House);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              expense.setType("House");
              expense.setTime(now.toString());
              gotoBudget();
            }
        });
        c = getView().findViewById(R.id.Car);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expense.setType("Car");
                expense.setTime(now.toString());
                gotoBudget();
            }
        });
        c2 = getView().findViewById(R.id.Communications);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expense.setType("Communiations");
                expense.setTime(now.toString());
                gotoBudget();
            }
        });
        c3 = getView().findViewById(R.id.Clothes);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expense.setType("Clothes");
                expense.setTime(now.toString());
                gotoBudget();
            }
        });
        f = getView().findViewById(R.id.Fixing);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expense.setType("Fixing");
                expense.setTime(now.toString());
                gotoBudget();
            }
        });
        p = getView().findViewById(R.id.Phone);
        p.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                expense.setType("Phone");
                expense.setTime(now.toString());
                gotoBudget();
            }
        });
        back =getView().findViewById(R.id.Back);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoBudget();
            }


        });
    }
    private void gotoBudget() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new ListFragment());
        ft.commit();
    }
}