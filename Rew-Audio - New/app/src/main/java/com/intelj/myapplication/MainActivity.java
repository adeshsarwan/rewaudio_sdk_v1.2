package com.intelj.myapplication;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.intelj.myapplication.databinding.ActivityMainBinding;
import com.y_ral.mRaid.MraidAds;

import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewStub;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    ViewStub ads;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ads = findViewById(R.id.ads);
        MraidAds mraidAds = new MraidAds(this, ads,"external");
        mraidAds.loadGAds();


    }


}