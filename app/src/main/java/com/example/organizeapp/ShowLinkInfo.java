package com.example.organizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;

public class ShowLinkInfo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_link_info);
        float rating = Float.parseFloat(getIntent().getStringExtra("rating"));
        String comment = getIntent().getStringExtra("comment").toString();
        String title = getIntent().getStringExtra("title").toString();
        String link = getIntent().getStringExtra("link").toString();

        TextView hy_link = findViewById(R.id.id_link);
        hy_link.setText("link");
        hy_link.setTextColor(Color.RED);
        hy_link.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(link));
                startActivity(browserIntent);
            }
        });
        TextView comm = findViewById(R.id.id_comment);
        comm.setText(comment);

        TextView ti = findViewById(R.id.id_title);
        ti.setText(title);

        RatingBar rb = findViewById(R.id.id_rating);
        rb.setRating(rating);

    }
}