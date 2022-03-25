package com.example.myapplication;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.jakewharton.rxbinding2.view.RxView;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionButton;
import com.oguzdev.circularfloatingactionmenu.library.FloatingActionMenu;
import com.oguzdev.circularfloatingactionmenu.library.SubActionButton;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import java.util.concurrent.TimeUnit;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("CheckResult")
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        BottomNavigationView navView = findViewById(R.id.nav_view);
        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
                R.id.navigation_home, R.id.navigation_notifications)
                .build();
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
        NavigationUI.setupWithNavController(navView, navController);

        ImageView icon = new ImageView(this);
        icon.setImageDrawable(getResources().getDrawable(R.drawable.ic_add_black, null));
        final FloatingActionButton fabButton = new FloatingActionButton.Builder(this)
                .setContentView(icon)
                .setPosition(FloatingActionButton.POSITION_BOTTOM_CENTER)
                .setLayoutParams(new FloatingActionButton.LayoutParams(250,250))
                .build();
        SubActionButton.Builder rLSubBuilder = new SubActionButton.Builder(this);

        ImageView imageViewAddAccount = new ImageView(this);
        ImageView imageViewPwdCreator = new ImageView(this);
        ImageView imageViewPwdTest = new ImageView(this);
        imageViewAddAccount.setImageDrawable(getResources().getDrawable(R.drawable.ic_addact, null));
        imageViewPwdCreator.setImageDrawable(getResources().getDrawable(R.drawable.ic_psd_creator, null));
        imageViewPwdTest.setImageDrawable(getResources().getDrawable(R.drawable.ic_psd_test,null));
        SubActionButton buttonAddAccount = rLSubBuilder.setContentView(imageViewAddAccount)
                .setLayoutParams(new FloatingActionButton.LayoutParams(180,180))
                .build();
        SubActionButton buttonPwdCreator = rLSubBuilder.setContentView(imageViewPwdCreator)
                .setLayoutParams(new FloatingActionButton.LayoutParams(180,180))
                .build();
        SubActionButton buttonPwdTest = rLSubBuilder.setContentView(imageViewPwdTest)
                .setLayoutParams(new FloatingActionButton.LayoutParams(180,180))
                .build();
        final FloatingActionMenu buttonToolMenu = new FloatingActionMenu.Builder(this)
                .addSubActionView(buttonPwdCreator)
                .addSubActionView(buttonAddAccount)
                .addSubActionView(buttonPwdTest)
                .setStartAngle(-45)
                .setEndAngle(-135)
                .attachTo(fabButton)
                .build();
        buttonToolMenu.setStateChangeListener(new FloatingActionMenu.MenuStateChangeListener() {
            @Override
            public void onMenuOpened(FloatingActionMenu floatingActionMenu) {
                icon.setRotation(0);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 45);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(icon, pvhR);
                animation.start();
            }

            @Override
            public void onMenuClosed(FloatingActionMenu floatingActionMenu) {
                icon.setRotation(45);
                PropertyValuesHolder pvhR = PropertyValuesHolder.ofFloat(View.ROTATION, 0);
                ObjectAnimator animation = ObjectAnimator.ofPropertyValuesHolder(icon, pvhR);
                animation.start();
            }
        });
        RxView.clicks(buttonAddAccount)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    buttonToolMenu.close(true);
                    Intent intent = new Intent(MainActivity.this, AddActActivity.class);
                    startActivity(intent);
                });
        RxView.clicks(buttonPwdCreator)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    buttonToolMenu.close(true);
                    Intent intent = new Intent(MainActivity.this, PsdCreatorActivity.class);
                    startActivity(intent);
                });
        RxView.clicks(buttonPwdTest)
                .throttleFirst(1, TimeUnit.SECONDS)
                .subscribe(o -> {
                    buttonToolMenu.close(true);
                    Intent intent = new Intent(MainActivity.this, PsdTestActivity.class);
                    startActivity(intent);
                });
    }
}