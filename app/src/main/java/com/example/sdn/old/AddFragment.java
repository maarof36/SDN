package com.example.sdn.old;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.sdn.fragmnts.data.FirebaseServices;
import com.example.sdn.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link AddFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class AddFragment extends Fragment {
    private EditText et11 ,et22 ,et33 ,et44;
    private Button btD;
    private FirebaseServices fbs;

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public AddFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment AddFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static AddFragment newInstance(String param1, String param2) {
        AddFragment fragment = new AddFragment();
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
        return inflater.inflate(R.layout.fragment_add, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        connectComponents();

    }

    private void connectComponents() {
        fbs = FirebaseServices.getInstance();
        et11 = getView().findViewById(R.id.et1);
        et22 = getView().findViewById(R.id.et2);
        et33 = getView().findViewById(R.id.et3);
        et44 = getView().findViewById(R.id.et4);
        btD = getView().findViewById(R.id.btDone);

        btD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //get data from screen

                String etO = et11.getText().toString();
                String etS = et22.getText().toString();
                String etT = et33.getText().toString();
                String etF = et44.getText().toString();
                //data check
                if (etO.trim().isEmpty()||etS.trim().isEmpty()||etT.trim().isEmpty()||etF.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_LONG).show();
                    return;
                }
                //Add data to fireStore if everything is okay

                Creture creture = new Creture(etO,etS,etT,etF);

                fbs.getFire().collection("creture").add(creture).addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        //massege
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }

                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //massege
                        Toast.makeText(getActivity(), "", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });
    }
}
