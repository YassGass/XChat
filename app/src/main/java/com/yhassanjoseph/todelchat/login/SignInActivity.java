package com.yhassanjoseph.todelchat.login;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.yhassanjoseph.todelchat.MainActivity;
import com.yhassanjoseph.todelchat.R;

public class SignInActivity extends AppCompatActivity {

    // variable globale
    private TextInputEditText etMail, etPassWord;
    private String email, passWord;

    //methode init
    private void init(){
        etMail =findViewById(R.id.etMail);
        etPassWord =findViewById(R.id.etPassword);
    }

    //gestion des boutons login
    public void btnLoginClick(View view){
        //recup les donnée du formulaire
        email = etMail.getText().toString();
        passWord = etPassWord.getText().toString().trim();

        //verification des donnée
        if (email.equals("")){
            etMail.setError("entrer votre email");
        }else if (passWord.equals("")){
            etPassWord.setError("entrer votre mot de passe");
        }else{
            FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
            firebaseAuth.signInWithEmailAndPassword(email, passWord)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()){
                                startActivity(new Intent(SignInActivity.this, MainActivity.class));
                                finish();
                            }else {
                                Toast.makeText(SignInActivity.this, "login failed with :", Toast.LENGTH_SHORT)
                                        .show();
                            }
                        }
                    });
        }

        //connexion

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_sign_in);

        init();
    }
}