package com.example.sdn.Main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sdn.R;
import com.example.sdn.data.Expense;
import com.example.sdn.data.FirebaseServices;
import com.example.sdn.fragments.ChooeseFragment;
import com.example.sdn.fragments.InfoFragment;
import com.example.sdn.fragments.ProfileFragment;
import com.example.sdn.fragments.barFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link BudgetTrackingFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class BudgetTrackingFragment extends Fragment {
    private FloatingActionButton ProfileBt ,ListBt;
    private Button goBt;
    private FrameLayout fm;
    private BottomNavigationView miniicon;
    private FirebaseServices fbs;
    private ImageButton gbtn;
    private TextView spent;

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
      //  miniicon = getActivity().findViewById(R.id.navbarhome);
        //if(miniicon.getSelectedItemId()==R.id.home) getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new BudgetTrackingFragment()).commit();
        //miniicon.setOnNavigationItemSelectedListener(item -> {
          //  switch (item.getItemId()) {
                //case R.id.home:
                  //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new BudgetTrackingFragment()).commit();
                  //  return true;
               // case R.id.list:
                   // getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new InfoFragment()).commit();
                   // return true;
                //case R.id.profile:
                  //  getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout, new ProfileFragment()).commit();
                  //  return true;
            //    default:
          //          return false;
        //   }
       // });
        spent = getView().findViewById(R.id.S);
        goBt = getView().findViewById(R.id.Go);
        gbtn = getView().findViewById(R.id.grfBtn);
        gbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToGraph();
            }
        });
        goBt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                    Expense ex1 = new Expense();
                    if (spent.getText().toString().trim().isEmpty())
                    {
                        Toast.makeText(getActivity(), "Empty field, try again!", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    ex1.setPrice(Double.parseDouble(spent.getText().toString()));
                String currentTime = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault()).format(new Date());
                    ex1.setTime(currentTime.toString());
                    gotoChoose(ex1);
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

    private void goToGraph() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new barFragment());
        ft.commit();
    }

    private void gotoChoose(Expense ex1 ) {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ChooeseFragment cf = new ChooeseFragment();
        Bundle b = new Bundle();
        b.putParcelable("expense", ex1);
        cf.setArguments(b);
        ft.replace(R.id.frameLayout,cf);
        ft.commit();
    }
    private void gotoprofile() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new ProfileFragment());
        ft.commit();
    }

    private void gotoList() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new InfoFragment());
        ft.commit();
    }
    }