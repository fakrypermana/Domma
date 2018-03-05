package com.letbemagi.magi.domma.Activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letbemagi.magi.domma.Model.IsLogin;
import com.letbemagi.magi.domma.Model.User;
import com.letbemagi.magi.domma.PrefModel.ListUser;
import com.letbemagi.magi.domma.Preferences.AlertDialogManager;
import com.letbemagi.magi.domma.Preferences.PrefLogin;
import com.letbemagi.magi.domma.Preferences.PrefUser;
import com.letbemagi.magi.domma.Preferences.SessionManagement;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

import static com.letbemagi.magi.domma.Preferences.SessionManagement.PREFER_NAME;

public class LoginActivity extends Activity {

    EditText txtUsername, txtPassword;
    Button btnLogin, btnLoginRegist;
    AlertDialogManager alert = new AlertDialogManager();
    List<User> listUser = new ArrayList<>();
//    private SharedPreferences sharedPreferences;
//    SessionManagement session;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

//        session = new SessionManagement(getApplicationContext());
        txtUsername = findViewById(R.id.txtUsername);
        txtPassword = findViewById(R.id.txtPassword);
//        session.checkLogin();

//        Toast.makeText(getApplicationContext(), "User Login Status: " + session.isUserLoggedIn(), Toast.LENGTH_LONG).show();
        btnLogin = findViewById(R.id.btnLogin);
        btnLoginRegist = findViewById(R.id.btnLoginRegist);


//        sharedPreferences = getSharedPreferences(PREFER_NAME, Context.MODE_PRIVATE);
        loadDataUser();
//        session.checkLogin();

        btnLoginRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(),RegistrationActivity.class);
                startActivity(i);
            }
        });


        btnLogin.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View arg0) {
                // Get username, password from EditText
                String username = txtUsername.getText().toString();
                String password = txtPassword.getText().toString();
                //Log.i("Cobacoba", "loadData: "+PrefUser.getJSON(getApplicationContext()));

                // Validate if username, password is filled
                if (username.trim().length() > 0 && password.trim().length() > 0) {
//                    String uName = null;
//                    String uPassword =null;

//                    if (sharedPreferences.contains("Name"))
//                    {
//                        uName = sharedPreferences.getString("Name", "");
//
//                    }
//
//                    if (sharedPreferences.contains("txtPassword"))
//                    {
//                        uPassword = sharedPreferences.getString("txtPassword", "");
//
//                    }

                    boolean found = false;

                    for (User us : listUser) {
                        if ((us.getName().equals(username)) && (us.getPassword().equals(password))) {
                            //set session login
                            PrefLogin.save(new IsLogin(true, username), LoginActivity.this);

                            // Starting MainActivity
                            Intent i = new Intent(getApplicationContext(), MainActivity.class);
                            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                            // Add new Flag to start new Activity
                            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            startActivity(i);
                            found = true;
                            finish();
                        }
                    }

                    if (!found) {
                        Toast.makeText(getApplicationContext(),
                                "Username/Password is incorrect",
                                Toast.LENGTH_LONG).show();
                    }


                    // username / password doesn't match&

                    // Object uName = null;
                    // Object uEmail = null;
//                    if(username.equals(uName) && password.equals(uPassword)){
//
////                        session.createUserLoginSession(uName,
////                                uPassword);
////
////                        // Starting MainActivity
////                        Intent i = new  Intent(getApplicationContext(),MainActivity.class);
////                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
////
////                        // Add new Flag to start new Activity
////                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
////                        startActivity(i);
////
////                        finish();
//
//                    }else{
//
//                        // username / password doesn't match&
//                        Toast.makeText(getApplicationContext(),
//                                "Username/Password is incorrect",
//                                Toast.LENGTH_LONG).show();
//
//                    }
                } else {

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username and password",
                            Toast.LENGTH_LONG).show();

                }

            }
        });
    }

    private void loadDataUser() {
        // jika ada data
        if (PrefUser.load(getApplicationContext()) != null) {
            ListUser listPengguna = PrefUser.load(getApplicationContext());
            Log.i("Cobacoba", "loadDataUser: " + PrefUser.getJSON(getApplicationContext()));
            listUser.addAll(listPengguna.getListUser());
        }
    }
}

