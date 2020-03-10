package com.example.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class ItemView extends AppCompatActivity {

    public static String EXTRA_DATA = "extra_data";
    private ImageView adivGambarHero;
    private TextView adtvNamaHero, adtvDescHero;
    private Button adbtnShareHero;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.hero_details);

        adivGambarHero   = findViewById(R.id.iv_gambar_hero);
        adtvNamaHero    = findViewById(R.id.tv_nama_hero);
        adtvDescHero    = findViewById(R.id.tv_desc_hero);
        adbtnShareHero  = findViewById(R.id.adbtn_share_hero);

        heromodel hero = getIntent().getParcelableExtra(EXTRA_DATA);
        int image       = hero.getGambarHero();
        String name     = hero.getNamaHero();
        final String desc     = hero.getDescHero();

        if (hero != null) {
            Glide.with(this).load(image).into(adivGambarHero);
            adtvNamaHero.setText(name);
            adtvDescHero.setText(desc);
        }
        adbtnShareHero.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_SEND);
                intent.putExtra(Intent.EXTRA_TEXT, desc);
                intent.setType("text/plain");

                Intent shareIntent = Intent.createChooser(intent, null);
                startActivity(shareIntent);
            }
        });
    }
}