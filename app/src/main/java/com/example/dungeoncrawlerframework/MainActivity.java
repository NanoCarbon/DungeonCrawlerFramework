package com.example.dungeoncrawlerframework;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.dungeoncrawlerframework.ui.main.SectionsPagerAdapter;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter(this, getSupportFragmentManager());
        ViewPager viewPager = findViewById(R.id.view_pager);
        viewPager.setAdapter(sectionsPagerAdapter);
        TabLayout tabs = findViewById(R.id.tabs);
        tabs.setupWithViewPager(viewPager);
        Button enterLabryinthButton = findViewById(R.id.enterLabryinthButton);
        enterLabryinthButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                startSelectPlayerActivity();
            }
        });
    }
    public void startSelectPlayerActivity(){
        //todo:[Medium] should display the hero's latest save data upon start of this activity
        Intent intent = new Intent(this,SelectPlayer.class);
        startActivity(intent);
    }
}