package com.example.sdn;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public class FierbaseServices {
     private static FierbaseServices instance;
     private FirebaseAuth auth;
     private FirebaseFirestore fire;
     private FirebaseStorage storage;
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
