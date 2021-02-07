package com.example.myapplication.Admin;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.myapplication.Login;
import com.example.myapplication.R;
import com.example.myapplication.Utils.Preferences;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class HomeAdmin extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener{

    ImageView logout;

    Preferences preferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_admin);

        preferences = new Preferences(this);

        logout = findViewById(R.id.btnLogout);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                preferences.saveSPBoolean(Preferences.SP_SUDAH_LOGIN, false);
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });

        loadFragment(new LihatMenu());
        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.nav_view);
        navigation.setOnNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        Toast.makeText(this,"Klik tombol logout untuk keluar akun !",Toast.LENGTH_SHORT).show();
    }

    private boolean loadFragment(Fragment fragment) {
        //switching fragment
        if (fragment != null) {
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.fragment_container, fragment)
                    .commit();
            return true;
        }
        return false;
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Fragment fragment = null;

        switch (menuItem.getItemId()) {
            case R.id.navigation_upload:
                fragment = new TambahMenu();
                break;

            case R.id.navigation_look:
                fragment = new LihatMenu();
                break;

            case R.id.navigation_meja:
                fragment = new NomorMeja();
                break;

            case R.id.navigation_kasir:
                fragment = new User();
                break;

            case R.id.navigation_laporan:
                fragment = new Laporan();
                break;
        }

        return loadFragment(fragment);
    }
}