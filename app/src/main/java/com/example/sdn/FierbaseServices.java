package com.example.sdn;

import android.net.Uri;

import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;

import java.util.ArrayList;

public class FierbaseServices {
     private static FierbaseServices instance;
    private Uri selectedImageURL;
    private User currentUser;
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
    public User getCurrentUser()
    {
        return this.currentUser;
    }

    public void setCurrentUser(User currentUser) {
        this.currentUser = currentUser;
    }

    public boolean updateUser(User user)
    {
        final boolean[] flag = {false};
        // Reference to the collection
        String collectionName = "users";
        String usernameFieldName = "username";
        String usernameValue = user.getUsername();
        String addressFieldName = "address";
        String addressValue = user.getAddress();
        String emailFieldName = "email";
        String emailValue = user.getPhone();
        String photoFieldName = "photo";
        String photoValue = user.getPhoto();


        // Create a query for documents based on a specific field
        Query query = fire.collection(collectionName).
                whereEqualTo(usernameFieldName, usernameValue);

        // Execute the query
        query.get()
                .addOnSuccessListener((QuerySnapshot querySnapshot) -> {
                    for (QueryDocumentSnapshot document : querySnapshot) {
                        // Get a reference to the document
                        DocumentReference documentRef = document.getReference();

                        // Update specific fields of the document
                        documentRef.update(

                                        usernameFieldName, usernameValue,
                                        addressFieldName, addressValue,
                                        emailFieldName, emailValue,
                                        photoFieldName, photoValue

                                )
                                .addOnSuccessListener(aVoid -> {

                                    flag[0] = true;
                                })
                                .addOnFailureListener(e -> {
                                    System.err.println("Error updating document: " + e);
                                });
                    }
                })
                .addOnFailureListener(e -> {
                    System.err.println("Error getting documents: " + e);
                });

        return flag[0];
    }
}
