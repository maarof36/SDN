package com.example.sdn.fragments;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.sdn.Main.BudgetTrackingFragment;
import com.example.sdn.R;
import com.example.sdn.data.Utils;
import com.example.sdn.data.FirebaseServices;
import com.example.sdn.data.User1;
import com.example.sdn.UserManagement.LoginFragment;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProfileFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProfileFragment extends Fragment {
   private FloatingActionButton fbtC , Back;
   private ImageView ProfilePic;
    //EditText etName,etEmail, etAddress;
   private Button btnUpdate, LogOutbtn;
   private Utils utils;
    private static final int GALLERY_REQUEST_CODE = 123;
    private FirebaseServices fbs;
    private EditText UserName, Email,Addres;
    private boolean flagAlreadyFilled = false;



    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ProfileFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProfileFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProfileFragment newInstance(String param1, String param2) {
        ProfileFragment fragment = new ProfileFragment();
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
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

    @Override
    public void onStart() {
        super.onStart();
        init();

        //fillUserData();
        //flagAlreadyFilled = true;

    }



    private void init() {
        fbs = FirebaseServices.getInstance();
        btnUpdate =getView().findViewById(R.id.Upbutton);
        LogOutbtn =getView().findViewById(R.id.logoutBtn);
        UserName=getView().findViewById(R.id.name);
        Email=getView().findViewById(R.id.email);
        Addres=getView().findViewById(R.id.address);

        if(fbs.getCurrentUser()!=null){
            UserName.setText(fbs.getAuth().getCurrentUser().getEmail());
            Email.setText(fbs.getCurrentUser().getEmail());
            Addres.setText(fbs.getCurrentUser().getAddress());
        }

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //Data validation
                String username = UserName.getText().toString();
                String address = Addres.getText().toString();
                if (username.trim().isEmpty() || address.trim().isEmpty()) {
                    Toast.makeText(getActivity(), "some fields are empty", Toast.LENGTH_SHORT).show();
                    return;
                }

                User1 current = fbs.getCurrentUser();
                if (current != null)
                {
                    if (!current.getUsername().equals(username)  ||
                            !current.getAddress().equals(address)||
                            !current.getPhoto().equals(fbs.getSelectedImageURL().toString()))
                    {
                        if (fbs.getSelectedImageURL() != null)
                            current = new User1(username,fbs.getAuth().getCurrentUser().getEmail().toString(),address, fbs.getSelectedImageURL().toString(), current.getExpenses());
                        else
                            current = new User1(username, fbs.getAuth().getCurrentUser().getEmail(), address, "", current.getExpenses());

                        fbs.updateUser(current);
                        utils.showMessageDialog(getActivity(), "Data updated succesfully!");
                        fbs.reloadInstance();
                    }
                    else
                    {
                        utils.showMessageDialog(getActivity(), "No changes!Try again");
                    }
                }
            }
        });
        LogOutbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(getContext()).setMessage("Are you sure want to sign out?")
                        .setCancelable(false)
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                fbs.getAuth().signOut();
                                getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.frameLayout,new LoginFragment()).commit();
                            }
                        }).setNegativeButton("No",null)
                        .show();
            }
        });
        Back = getView().findViewById(R.id.btBtoBudget);
        Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                gotoBudget();
            }
        });
        ProfilePic = getView().findViewById(R.id.imageView5);
        fbtC =getView().findViewById(R.id.fbtCamera);

        fbtC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });
        utils = Utils.getInstance();
        fillUserData();
    }
    private void fillUserData() {
        if (flagAlreadyFilled)
            return;
        User1 current = fbs.getCurrentUser();
        if (current != null)
        {
            UserName.setText(current.getUsername());
            Email.setText(current.getEmail());
            Addres.setText(current.getAddress());
            if (current.getPhoto() != null && !current.getPhoto().isEmpty()) {
                Picasso.get().load(current.getPhoto()).into(ProfilePic);
                fbs.setSelectedImageURL(Uri.parse(current.getPhoto()));
            }
        }
    }

    private void openGallery() {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, GALLERY_REQUEST_CODE);
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == GALLERY_REQUEST_CODE && resultCode == getActivity().RESULT_OK && data != null) {
            Uri selectedImageUri = data.getData();
            ProfilePic.setImageURI(selectedImageUri);
            utils.uploadImage(getActivity(), selectedImageUri);
        }
    }
    private void gotoLogin() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new LoginFragment());
        ft.commit();
    }
    private void gotoBudget() {
        FragmentTransaction ft=getActivity().getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new BudgetTrackingFragment());
        ft.commit();
    }
}