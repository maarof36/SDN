package com.example.sdn.fragmnts;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.util.Dumpable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdn.R;
import com.example.sdn.data.Expense;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BudgetTrackingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetTrackingFragment extends Fragment {
    private FloatingActionButton ProfileBt ,ListBt;
    private Button goBt;
    private TextView spent;
    private Expense expense;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public BudgetTrackingFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment BudgetTrackingFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static BudgetTrackingFragment newInstance(String param1, String param2) {
        BudgetTrackingFragment fragment = new BudgetTrackingFragment();
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
        return inflater.inflate(R.layout.fragment_budget_tracking, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        spent = getView().findViewById(R.id.S);
        goBt = getView().findViewById(R.id.Go);
        goBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (spent!=null) {
                    expense.setPrice(Double.parseDouble(spent.getText().toString()));
                    gotoChoose();
                }
                else Toast.makeText(getActivity(), "enter your expense", Toast.LENGTH_SHORT).show();
            }


        });
        ProfileBt = getView().findViewById(R.id.FABtoProfile);
        ProfileBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoprofile();
            }
        });
        ListBt = getView().findViewById(R.id.FABtoInfo);
        ListBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gotoList();
            }
        });
}
    private void gotoChoose() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new ChooesesFragment());
        ft.commit();
    }
    private void gotoprofile() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new ProfileFragment());
        ft.commit();
    }

    private void gotoList() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new ListFragment());
        ft.commit();
    }
    }