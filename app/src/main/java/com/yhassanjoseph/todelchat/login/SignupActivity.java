package com.yhassanjoseph.todelchat.login;

import static com.yhassanjoseph.todelchat.commons.Constants.*;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.storage.StorageReference;
import com.yhassanjoseph.todelchat.MainActivity;
import com.yhassanjoseph.todelchat.R;

import java.util.regex.Pattern;

import io.grpc.Context;

public class SignupActivity extends AppCompatActivity {
    //var globale
    private TextInputEditText etName, etEmail, etPassWord, etComfirmPassWord;
    private String name, email, passWord, comfirmPassWord;

    //var FirebaseUser
    private FirebaseUser fuser;

    //référence à la collection Users
    private CollectionReference userCollectionReference;

    //ajouter de la référence vers le dossier du storage
    private StorageReference fileReference;

    //ajout de traitement d'image
    private Uri localFileUri; //l'image local
    private Uri serverFile; //url du fichier stocker dans le storage
    private String urlStorageAvatar; //url dans le storage en string pour la db

    //localisation du wiget container de l'image
    private ImageView idAddAvatar;
    private String userID;

    //methode init
    private void init() {
        etName = findViewById(R.id.etName);
        etEmail = findViewById(R.id.etMail);
        etPassWord = findViewById(R.id.etPasswordSignup);
        etComfirmPassWord = findViewById(R.id.etComfirmPassWord);
    }

    //ajout a la methode init avec firebase
    private void initFB() {
        //init firestore
        userCollectionReference = FIREBASE_INSTANCE.collection(USERS);

        //init storage
        fileReference = STORAGE_INSTANCE.getReference();
        fuser = CURRENT_USER;
    }


    //gestion des boutons signup
    public void btnSignupClick(View view) {
        name = etName.getText().toString().trim();
        email = etEmail.getText().toString().trim();
        passWord = etPassWord.getText().toString().trim();
        comfirmPassWord = etComfirmPassWord.getText().toString().trim();

        //les verification de base

        //si les cases sont vides
        if (name.equals("")) {
            etName.setError("entrer votre nom");
        } else if (email.equals("")) {
            etEmail.setError("entrer votre email");
        } else if (passWord.equals("")) {
            etPassWord.setError("entrer votre mot de passe");
        } else if (comfirmPassWord.equals("")) {
            etComfirmPassWord.setError("comfirmer votre mot de passe");
        }
        // verfication des patterns
        else if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            etEmail.setError("entrer votre email");
            //verification du mot passe
        } else if (!passWord.equals(comfirmPassWord)) {
            etComfirmPassWord.setError("entrer votre email");
        } else {
            //connexion a la base et enregistrement des données
            FIREBASE_AUTH.createUserWithEmailAndPassword(email, passWord)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
                                startActivity(new Intent(SignupActivity.this, MainActivity.class));
                            } else {
                                Toast.makeText(SignupActivity.this, getString(R.string.failed_signup,
                                        task.getException())
                                        , Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    });
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_signup);

        init();
        initFB();
    }
}