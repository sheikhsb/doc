package com.example.doc;

import android.app.Activity;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.drawerlayout.widget.DrawerLayout;

import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.net.URL;

import kotlin.reflect.KVisibility;

public class DownloadImageTask extends AsyncTask<String, Void, Drawable> {
    private View view;
    private WeakReference<Activity> activityRef;
    static  int count = 0;

    public DownloadImageTask(View view, Activity activity) {
        this.view = view;
        count++;
        activityRef = new WeakReference<>(activity);

    }

    @Override
    protected Drawable doInBackground(String... urls) {
        String imageUrl = urls[0];
        try {
            URL url = new URL(imageUrl);
            InputStream inputStream = (InputStream) url.getContent();
            return Drawable.createFromStream(inputStream, "src name");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(Drawable drawable) {
        if (drawable != null) {
            if (view instanceof TextView) {
                ((TextView) view).setCompoundDrawablesWithIntrinsicBounds(null, drawable, null, null);
            } else if (view instanceof ImageView) {
                ((ImageView) view).setImageDrawable(drawable);
            }
        }
        count--;
        if(count==6)
        {
            Activity activity = activityRef.get();
            if (activity != null) {
                View mainLayout = activity.findViewById(R.id.mainLayout);
                View loader = activity.findViewById(R.id.loader);
                TextView loadingTextView = activity.findViewById(R.id.loadingTextView);
                if (mainLayout != null) {
                    loadingTextView.clearAnimation();
                    loader.setVisibility(View.GONE);
                    mainLayout.setVisibility(View.VISIBLE);
                }
            }
        }
    }
}
