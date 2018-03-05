package com.letbemagi.magi.domma.Activity;

import android.content.Intent;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.letbemagi.magi.domma.Model.IsLogin;
import com.letbemagi.magi.domma.Model.User;
import com.letbemagi.magi.domma.PrefModel.ListUser;
import com.letbemagi.magi.domma.Preferences.AlertDialogManager;
import com.letbemagi.magi.domma.Preferences.PrefLogin;
import com.letbemagi.magi.domma.Preferences.PrefUser;
import com.letbemagi.magi.domma.Preferences.SessionManagement;
import com.letbemagi.magi.domma.R;
import com.letbemagi.magi.domma.Tab.PagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle mToogle;
    private NavigationView navigationView;

    // Alert Dialog Manager
    AlertDialogManager alert = new AlertDialogManager();

    // Session Manager Class
//    SessionManagement session;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Session class instance
        /*session = new SessionManagement(getApplicationContext());
        session.checkLogin();*/

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        TabLayout tabLayout = findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Home"));
        tabLayout.addTab(tabLayout.newTab().setText("Dompet"));
        tabLayout.addTab(tabLayout.newTab().setText("Category"));
        tabLayout.addTab(tabLayout.newTab().setText("Transaction"));
        tabLayout.addTab(tabLayout.newTab().setText("Budget"));
        tabLayout.addTab(tabLayout.newTab().setText("Wishlist"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

//        session = new SessionManagement(this);
        drawerLayout = findViewById(R.id.drawerLayout);
        mToogle = new ActionBarDrawerToggle(this, drawerLayout,R.string.open,R.string.close);
        navigationView = findViewById(R.id.navigation_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            //method dipanggil saat navigasi menu di klik
            @Override
            public boolean onNavigationItemSelected(MenuItem menuItem) {
                //Memeriksa apakah item tersebut dalam keadaan dicek  atau tidak,
                if(menuItem.isChecked()) menuItem.setChecked(false);
                else menuItem.setChecked(true);
                //Menutup  drawer item klik
                drawerLayout.closeDrawers();
                //Memeriksa untuk melihat item yang akan dilklik dan melalukan aksi
                switch (menuItem.getItemId()){
                    case R.id.chart:
                        Intent intent1 = new Intent(getApplicationContext(),ChartActivity.class);
                        startActivity(intent1);
                        return true;
                    case R.id.report:
                        Intent intent2 = new Intent(getApplicationContext(),ReportActivity.class);
                        startActivity(intent2);
                        return true;
                    case R.id.logout:
                        PrefLogin.save(new IsLogin(false, ""), MainActivity.this);

                        // Starting MainActivity
                        Intent i = new  Intent(getApplicationContext(),LoginActivity.class);
                        i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

                        // Add new Flag to start new Activity
                        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(i);

                        finish();
                        Toast.makeText(getApplicationContext(),"Anda Telah Log Out",Toast.LENGTH_SHORT).show();
                    default:
                        return true;
                }
            }
        });

//        View headerView = getLayoutInflater().inflate(R.layout.navigation_header,navigationView,false);
//        navigationView.addHeaderView(headerView);

        //ImageView headerImage = navigationView.findViewById(R.id.ivUser);

        /*headerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),DetailUserActivity.class);
                startActivity(intent);
            }
        });*/

        drawerLayout.addDrawerListener(mToogle);
        mToogle.syncState();

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        final ViewPager viewPager = findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getSupportFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(mToogle.onOptionsItemSelected(item)){
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
