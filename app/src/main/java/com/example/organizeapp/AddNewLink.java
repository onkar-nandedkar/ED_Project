package com.example.organizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;

public class AddNewLink extends AppCompatActivity {

    EditText title , comment , link;
    RatingBar ratingBar;
    Button add;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_link);
        title = findViewById(R.id.titleEB);
        comment = findViewById(R.id.CommentsEB);
        link = findViewById(R.id.LinkEB);
        ratingBar = findViewById(R.id.ratingBar);
        add = findViewById(R.id.button);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String t , c , l , r;

                t = title.getText().toString();
                c = comment.getText().toString();
                l = link.getText().toString();
                r = Float.toString(ratingBar.getRating());

                Intent intent = new Intent(AddNewLink.this , ResourceManager.class);
                intent.putExtra("title" , t);
                intent.putExtra("comment" , c);
                intent.putExtra("link" , l);
                intent.putExtra("rating" , r);

                setResult(RESULT_OK , intent);
                finish();
            }
        });
    }
}