package com.example.sdn;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.example.sdn.fragmnts.data.FirebaseServices;
import com.example.sdn.fragmnts.BudgetTrackingFragment;
import com.example.sdn.usermngmnt.LoginFragment;

public class MainActivity extends AppCompatActivity {

    FirebaseServices fbs;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        fbs = FirebaseServices.getInstance();

        if(fbs.getAuth().getCurrentUser()!=null) gotoTrackingFragment();
        else gotoLoginFragment();

    }
    private void gotoLoginFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new LoginFragment());
        ft.commit();
    }

      private void gotoTrackingFragment() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        ft.replace(R.id.frameLayout,new BudgetTrackingFragment());
        ft.commit();
    }
}
