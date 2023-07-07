package com.example.doc;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.drawable.DrawableCompat;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

public class home extends AppCompatActivity {

    NavigationView nav;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    LinearLayout linearLayoutDoctorList;
    LinearLayout linearLayoutLatestNews;
    TextView loadingTextView ;

   protected void startLoadingAnimation(TextView textView) {
        Animation anim = new AlphaAnimation(0.0f, 1.0f);
        anim.setDuration(500);
        anim.setRepeatMode(Animation.REVERSE);
        anim.setRepeatCount(Animation.INFINITE);

        textView.startAnimation(anim);
    }

    protected void stopLoadingAnimation() {
        loadingTextView.clearAnimation();
    }


    private void updateLoadingText(final TextView textView) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int count = 0;

            @Override
            public void run() {
                StringBuilder dots = new StringBuilder();
                for (int i = 0; i < count; i++) {
                    dots.append(".");
                }
                textView.setText(dots);

                count++;
                if (count > 4) {
                    count = 0;
                }

                handler.postDelayed(this, 500); // Update every 500 milliseconds
            }
        };

        handler.post(runnable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        loadingTextView = findViewById(R.id.loadingTextView);
        startLoadingAnimation(loadingTextView);
        updateLoadingText(loadingTextView);


        TextView home = findViewById(R.id.home);

        home.setTextColor(getResources().getColor(R.color.primary_dark));

        Drawable[] drawables = home.getCompoundDrawables();
        Drawable topDrawable = drawables[1]; // Index 1 represents the top drawable
        topDrawable.setColorFilter(new PorterDuffColorFilter(getResources().getColor(R.color.primary_dark), PorterDuff.Mode.SRC_IN));
        home.setCompoundDrawablesWithIntrinsicBounds(drawables[0], topDrawable, drawables[2], drawables[3]);



        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        nav = findViewById(R.id.nav);
        drawerLayout = findViewById(R.id.drawer);

        toggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.open, R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        nav.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int n = item.getItemId();
                if(n == findViewById(R.id.myProfile).getId())
                {
                    Intent i = new Intent(home.this,profile.class);
                    startActivity(i);
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(n == findViewById(R.id.recentDoctors).getId())
                {
                    Toast.makeText(home.this, "Setting", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(n == findViewById(R.id.wallet).getId())
                {
                    Toast.makeText(home.this, "Help", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }
                else if(n == findViewById(R.id.logout).getId())
                {
                    Toast.makeText(home.this, "Help", Toast.LENGTH_SHORT).show();
                    drawerLayout.closeDrawer(GravityCompat.START);
                }

                return true;
            }
        });

        linearLayoutDoctorList = findViewById(R.id.doctorList);

        String[] textArray = {"Text 1", "Text 2", "Text 3", "Text 4", "Text 5", "Text 6", "Text 7", "Text 8"};

        for (String text : textArray) {
            TextView textView = new TextView(this);
            textView.setText(text);

            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );

            layoutParams.setMargins(0, 0, 80, 0);
            textView.setLayoutParams(layoutParams);
            textView.setTextColor(getResources().getColor(R.color.black));


            DownloadImageTask task = new DownloadImageTask(textView, home.this);
            String imageUrl = "https://www.photoenlarger.com/pics/logo-fb-en.png";
            task.execute(imageUrl);

            linearLayoutDoctorList.addView(textView);
        }

        linearLayoutLatestNews = findViewById(R.id.latestNews);

        String[] ImageArray = {"Text 1", "Text 2", "Text 3", "Text 4", "Text 5", "Text 6", "Text 7", "Text 8"};

        for (String text : ImageArray) {
            ImageView imageView = new ImageView(this);

            int desiredWidthInPixels = 350;
            int desiredHeightInPixels = 350;


            LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
                    desiredWidthInPixels,
                    desiredHeightInPixels
            );

            layoutParams.setMargins(0, 0, 80, 0);
            imageView.setLayoutParams(layoutParams);

            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Perform your desired action when the ImageView is clicked
                    Toast.makeText(getApplicationContext(), "ImageView clicked", Toast.LENGTH_SHORT).show();
                }
            });

            DownloadImageTask task = new DownloadImageTask(imageView, home.this);
            String imageUrl = "https://www.freecodecamp.org/news/content/images/2022/09/jonatan-pie-3l3RwQdHRHg-unsplash.jpg";
            task.execute(imageUrl);

            linearLayoutLatestNews.addView(imageView);

        }
    }
}