package com.yhassanjoseph.todelchat.commons;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;

public interface Constants {
    //constentes firebase
    //auth
    FirebaseAuth FIREBASE_AUTH = FirebaseAuth.getInstance();

    //curent user
    FirebaseUser CURRENT_USER = FIREBASE_AUTH.getCurrentUser();

    //firestore
    FirebaseFirestore FIREBASE_INSTANCE = FirebaseFirestore.getInstance();

    //storage
    FirebaseStorage STORAGE_INSTANCE = FirebaseStorage.getInstance();

    //collection firebase
    // collections Users
    String USERS = "Users";
    String NAME = "name";
    String EMAIL = "email";
    String ONLINE = "online";
    String AVATAR = "avater";

    //les dossier du storage
    //dossier avatar User
    String AVATAR_FOLDER = "avatar_user";
}
