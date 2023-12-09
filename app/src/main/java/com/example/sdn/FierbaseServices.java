package com.example.sdn;

import android.net.Uri;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class FierbaseServices {
     private static FierbaseServices instance;
    private Uri selectedImageURL;
     private FirebaseAuth auth;
     private FirebaseFirestore fire;
     private FirebaseStorage storage;
    public Uri getSelectedImageURL() {
        return selectedImageURL;
    }

    public void setSelectedImageURL(Uri selectedImageURL) {
        this.selectedImageURL = selectedImageURL;
    }
     public FierbaseServices(){
        auth = FirebaseAuth.getInstance();
        fire = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();
    }

    public FirebaseAuth getAuth() {
        return auth;
    }

    public FirebaseFirestore getFire() {
        return fire;
    }

    public FirebaseStorage getStorage() {
        return storage;
    }


     public static FierbaseServices getInstance(){
         if(instance == null){
             instance = new FierbaseServices();
         }
         return instance;
     }
}
