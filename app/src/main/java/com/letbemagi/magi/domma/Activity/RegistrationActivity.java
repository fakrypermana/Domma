package com.letbemagi.magi.domma.Activity;

import android.app.ActionBar;
import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.letbemagi.magi.domma.Model.User;
import com.letbemagi.magi.domma.PrefModel.ListUser;
import com.letbemagi.magi.domma.Preferences.PrefLogin;
import com.letbemagi.magi.domma.Preferences.PrefUser;
import com.letbemagi.magi.domma.Preferences.SessionManagement;
import com.letbemagi.magi.domma.R;

import java.util.ArrayList;
import java.util.List;

import okhttp3.internal.cache.DiskLruCache;

/**
 * Created by magi on 05/12/2017.
 */

public class RegistrationActivity extends Activity {

    //    SharedPreferences sharedPreferences;
    EditText edtRegistName, edtRegistEmail, edtRegistPass;
    Button btnAddUser;
    List<User> listUser = new ArrayList<>();
    Button btnLoginRegist;
//    SharedPreferences.Editor editor;
//    SessionManagement session;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

//        session = new SessionManagement(this);

        edtRegistName = findViewById(R.id.edtRegistUsername);
        edtRegistEmail = findViewById(R.id.edtRegistEmail);
        edtRegistPass = findViewById(R.id.edtRegistPass);
        btnAddUser = findViewById(R.id.btnRegist);
        btnLoginRegist = findViewById(R.id.btnRegistLogin);

//        sharedPreferences = getApplicationContext().getSharedPreferences("Reg", 0);
//        // get editor to edit in file
//        editor = sharedPreferences.edit();

//        session.checkLogin();
        if (checkLogin()) {
            // Starting MainActivity
            Intent i = new Intent(getApplicationContext(), MainActivity.class);
            i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            // Add new Flag to start new Activity
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(i);

            finish();
        }

//        loadDataUser();
//        for (User user: listUser) {
//            if(user != null){
//                Intent i = new Intent(getApplicationContext(),LoginActivity.class);
//                startActivity(i);
//            }
//        }

        btnLoginRegist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(i);
            }
        });

        btnAddUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                User user = new User(edtRegistName.getText().toString(), edtRegistEmail.getText().toString(),
                        edtRegistPass.getText().toString());
                listUser.add(user);

                String username = edtRegistName.getText().toString();
                String password = edtRegistPass.getText().toString();
                String email = edtRegistEmail.getText().toString();
                //Log.i("Cobacoba", "loadData: "+PrefUser.getJSON(getApplicationContext()));

                // Validate if username, password is filled
                if (username.trim().length() > 0 && password.trim().length() > 0 && email.trim().length() > 0) {
                    // jika ada data
                    if (PrefUser.load(getApplicationContext()) != null) {
                        ListUser listUse = PrefUser.load(getApplicationContext());

                        List<User> list = listUse.getListUser();
                        list.add(user);
                        listUse.setListUser(list);

                        PrefUser.save(listUse, getApplicationContext());
                    }
                    // kalau data masih kosong
                    else {
                        //Log.i("test", "onClick: data kosong");
                        List<User> list = new ArrayList<>();
                        list.add(user);

                        PrefUser.save(new ListUser(list), getApplicationContext());
                    }
                    // after saving the value open next activity
                    Intent ob = new Intent(RegistrationActivity.this, LoginActivity.class);
                    startActivity(ob);
                }  else {

                    // user didn't entered username or password
                    Toast.makeText(getApplicationContext(),
                            "Please enter username and password",
                            Toast.LENGTH_LONG).show();

                }
            }
        });
    }


    private boolean checkLogin() {
        // jika ada data
        if (PrefLogin.load(getApplicationContext()) != null) {
            //Log.i("Cobacoba", "loadData: "+PrefDompet.getJSON(getActivity()));
            return PrefLogin.load(getApplicationContext()).isLogin();
        }
        return false;
    }

    /*private void loadDataUser() {
        // jika ada data
        if (PrefUser.load(getApplicationContext()) != null) {
            ListUser listPengguna = PrefUser.load(getApplicationContext());
            Log.i("Cobacoba", "loadDataUser: " + PrefUser.getJSON(getApplicationContext()));
            listUser.addAll(listPengguna.getListUser());
        }
    }*/
}