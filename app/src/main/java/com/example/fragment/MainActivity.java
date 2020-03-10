package com.example.fragment;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {

    private RecyclerView rvHero;
    private ArrayList<heromodel> listHero = new ArrayList<>();

    private void showRecyclerList() {
        rvHero.setLayoutManager(new LinearLayoutManager(this));
        HeroAdapter heroAdapter = new HeroAdapter(this);
        heroAdapter.setHeromodels(listHero);
        rvHero.setAdapter(heroAdapter);

        heroAdapter.setOnItemClickCallback(new HeroAdapter.OnItemClickCallback() {
            @Override
            public void onItemClicked(heromodel hero) {
                Toast.makeText(MainActivity.this, "Memilih " + hero.getNamaHero(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(MainActivity.this, ItemView.class);
                intent.putExtra(ItemView.EXTRA_DATA, (Parcelable) hero);
                startActivity(intent);
            }
        });
    }
    BottomNavigationView bottomNavigationView;
    Fragment selectedFragment = new HomeFragment();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rvHero = findViewById(R.id.hero_list);
        rvHero.setHasFixedSize(true);
        listHero.addAll(HeroData.getListData());

        showRecyclerList();
        setContentView(R.layout.activity_main);
        bottomNavigationView=findViewById(R.id.activitymain_bottomnav);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()){
            case R.id.menu_home:
                selectedFragment = new HomeFragment();
                break;
            case R.id.menu_person:
                selectedFragment = new ProfileFragment();
                break;
        }
        return loadFragment(selectedFragment);
    }

    private boolean loadFragment(Fragment selectedFragment) {
        if (selectedFragment != null){
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activitymain_container,selectedFragment)
                    .commit();
            return true;
        }
        return false;
    }
}
