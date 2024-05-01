package com.example.sdn.fragmnts;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.fragment.app.ListFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.Toast;

import com.example.sdn.R;
import com.example.sdn.data.Expense;
import com.example.sdn.data.Expense2;
import com.example.sdn.data.FierbaseServices;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.firestore.DocumentReference;

import java.lang.reflect.Type;
import java.sql.Time;
import java.time.LocalTime;
import java.util.UUID;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ChooesesFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ChooesesFragment extends Fragment {
    private ImageButton h,c ,c2 , c3 ,f , p1 ;
    private FloatingActionButton back;
    FierbaseServices fbs;
    private Expense ex1 ;
    private Expense2 expense2;

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
        Bundle ex = getArguments();
        if (ex != null) {
            ex1 = ex.getParcelable("expense");}
        h = getView().findViewById(R.id.House);
        h.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex1.setType("House");
                gotoList();
            }
        });
        c = getView().findViewById(R.id.Car);
        c.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex1.setType("Car");
                gotoList();
            }
        });
        c2 = getView().findViewById(R.id.Communications);
        c2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex1.setType("Communiations");
                gotoList();
            }
        });
        c3 = getView().findViewById(R.id.Clothes);
        c3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex1.setType("Clothes");
                gotoList();
            }
        });
        f = getView().findViewById(R.id.Fixing);
        f.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex1.setType("Fixing");
                gotoList();
            }
        });
        p1 = getView().findViewById(R.id.Phone);
        p1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ex1.setType("Phone");
                addToFirestore();
                gotoList();
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

    private void addToFirestore() {

            String price1,type , time;
//get data from screen

        price1=ex1.getPrice().toString();
        type=ex1.getType().toString();
        time=ex1.getTime().toString();

        expense2 = new Expense2(price1, type, time);

        fbs.getFire().collection("expense").add(expense2)
                    .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                        @Override
                        public void onSuccess(DocumentReference documentReference) {
                            Toast.makeText(getActivity(), "ADD Expense is Succesed ", Toast.LENGTH_SHORT).show();
                            Log.e("addToFirestore() - add to collection: ", "Successful!");
                            gotoList();
                        }
                    }).addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.e("addToFirestore() - add to collection: ", e.getMessage());
                        }
                    });


    }

    private void gotoList() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new ListFragment());
        ft.commit();
    }
    private void gotoBudget() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new BudgetTrackingFragment());
        ft.commit();
    }
}