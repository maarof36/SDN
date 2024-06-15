package com.example.sdn.Main;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.example.sdn.R;
import com.example.sdn.data.FirebaseServices;
import com.example.sdn.UserManagement.LoginFragment;
import com.example.sdn.data.User1;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

public class MainActivity extends AppCompatActivity {

    FirebaseServices fbs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbs = FirebaseServices.getInstance();
       // User1 userData = getUserData();

        if (fbs.getAuth().getCurrentUser() != null) gotoTrackingFragment();
        else gotoLoginFragment();


    }

    private void gotoLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new LoginFragment());
        ft.commit();
    }

    private void gotoTrackingFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout, new BudgetTrackingFragment());
        ft.commit();
    }

    public User1 getUserData() {
        final User1[] currentUser = {null};
        try {
            fbs.getFire().collection("users")
                    .get()
                    .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                        @Override
                        public void onComplete(@NonNull Task<QuerySnapshot> task) {
                            if (task.isSuccessful()) {
                                for (QueryDocumentSnapshot document : task.getResult()) {

                                    User1 user = document.toObject(User1.class);
                                    if (fbs.getAuth().getCurrentUser() != null && (fbs.getAuth().getCurrentUser().getEmail().equals(user.getUsername()))) {
                                        //if (fbs.getAuth().getCurrentUser().getEmail().equals(user.getUsername())) {
                                        currentUser[0] = document.toObject(User1.class);
                                        fbs.setCurrentUser(currentUser[0]);
                                    }
                                }
                            } else {
                                Log.e("AllRestActivity: readData()", "Error getting documents.", task.getException());
                            }
                        }
                    });
        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), "error reading!" + e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        return currentUser[0];
    }
}
