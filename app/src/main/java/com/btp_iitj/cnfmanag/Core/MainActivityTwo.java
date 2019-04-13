package com.btp_iitj.cnfmanag.Core;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.btp_iitj.cnfmanag.Conference.AddConference;
import com.btp_iitj.cnfmanag.Conference.aboutConferenceFragment;
import com.btp_iitj.cnfmanag.Conference.allConferencesFragment;
import com.btp_iitj.cnfmanag.Domain_Classes.Conference;
import com.btp_iitj.cnfmanag.Domain_Classes.Registration;
import com.btp_iitj.cnfmanag.Domain_Classes.User;
import com.btp_iitj.cnfmanag.MainActivity;
import com.btp_iitj.cnfmanag.Registration.RegistrationFragment;
import com.btp_iitj.cnfmanag.R;
import com.btp_iitj.cnfmanag.Registration.RegistrationStep1Fragment;
import com.btp_iitj.cnfmanag.Registration.RegistrationStep3Fragment;
import com.btp_iitj.cnfmanag.ViewProfileFragment;
import com.google.firebase.auth.FirebaseAuth;

public class MainActivityTwo extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static FragmentManager fragmentManager;
    public static User user = new User();
    public static Registration registration = new Registration();
    public static Conference conf = new Conference();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_two);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.create_new_conf) {
            // Handle the Create new conference button
            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,new AddConference()).addToBackStack("addConference").commit();
        } else if (id == R.id.all_conf) {

            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,new allConferencesFragment()).addToBackStack("allConferencesFragment").commit();
        } else if (id == R.id.editUSerPRo) {
            fragmentManager=getSupportFragmentManager();
            Bundle args= new Bundle();
            RegistrationStep3Fragment ldf=new RegistrationStep3Fragment();
            //String id=value;
            Intent intent=getIntent();
            String value=intent.getStringExtra("username");
            RegistrationStep1Fragment registrationStep1Fragment=new RegistrationStep1Fragment();
            args.putString("username",value);
            registrationStep1Fragment.setArguments(args);
            fragmentManager.beginTransaction().replace(R.id.fragment_container, registrationStep1Fragment).addToBackStack("registrationFragment").commit();

        } else if (id == R.id.viewProfile) {
            fragmentManager=getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container, new ViewProfileFragment()).addToBackStack("viewProfileFragment").commit();

        } else if (id == R.id.Withdraw) {
            ///implement delete user
            ///remove document snapshot

        } else if (id == R.id.about) {
            fragmentManager =getSupportFragmentManager();
            fragmentManager.beginTransaction().replace(R.id.fragment_container,new aboutConferenceFragment()).commit();
            //infor of conference



        }
        else if(id==R.id.logout){
            FirebaseAuth.getInstance().signOut();
            finish();
            startActivity(new Intent(MainActivityTwo.this,MainActivity.class));
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
