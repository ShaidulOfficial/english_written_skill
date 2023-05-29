package com.shaidulsoftstudio.englishwritten;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


import com.shaidulsoftstudio.englishwritten.databinding.ActivityMainBinding;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {
    ActivityMainBinding activityMainBinding;
    private static final int TIME_INTERVAL = 2000;
    private long backPressed;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        toolbar = findViewById(R.id.main_toolbar);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                MainActivity.this,
                activityMainBinding.drawerLayout,
                toolbar, R.string.nav_draw_open,
                R.string.nav_draw_close);


        activityMainBinding.drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        activityMainBinding.navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                switch (id) {
                    case R.id.dev_about:
                     startActivity(new Intent(MainActivity.this,DeveloperActivity.class));
                        break;

                    case R.id.homemenu:
                        break;
                    case R.id.favmenu:
                        startActivity(new Intent(MainActivity.this,FavouriteActivity.class));
                        break;

                    case R.id.text_para_menu:
                        startActivity(new Intent(MainActivity.this, ParagraphnameActivity.class));
                        break;
                    case R.id.inst_para_menu:
                        startActivity(new Intent(MainActivity.this, InparaActivity.class));
                        break;
                }
                activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);
                return true;
            }
        });

        activityMainBinding.paragraphid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, ParagraphnameActivity.class));
            }
        });
        activityMainBinding.inparagraphid.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, InparaActivity.class));
            }
        });

    }

    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onBackPressed() {
        if (activityMainBinding.drawerLayout.isDrawerOpen(GravityCompat.START)) {
            activityMainBinding.drawerLayout.closeDrawer(GravityCompat.START);

        } else {
            if (backPressed + TIME_INTERVAL > System.currentTimeMillis()) {

                super.onBackPressed();
                return;
            } else {
                Toast.makeText(getBaseContext(), "Press again to exit app", Toast.LENGTH_SHORT).show();
            }
            backPressed = System.currentTimeMillis();
        }

    }
}