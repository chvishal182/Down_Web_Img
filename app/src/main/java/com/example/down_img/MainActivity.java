package com.example.down_img;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    ImageView img;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        img = findViewById(R.id.ivShow);
    }

    public void onDownload(View view) {
        downloadImg photo = new downloadImg();
        Bitmap myPhoto;
        try {
            myPhoto = photo.execute("https://static.wikia.nocookie.net/kimetsu-no-yaiba/images/5/5d/Character_Slide.png/revision/latest/scale-to-width-down/670?cb=20201019160837").get();
            img.setImageBitmap(myPhoto);
        }catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public  class downloadImg extends AsyncTask<String,Void, Bitmap>
    {

        @Override
        protected Bitmap doInBackground(String... urls) {

            URL url;
            Bitmap myImg;
            HttpURLConnection httpURLConnection;
            try{
                url = new URL(urls[0]);
                httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.connect();//connect to the connection
                InputStream inputStream = httpURLConnection.getInputStream();
                myImg = BitmapFactory.decodeStream(inputStream);
                return myImg;


            }catch (Exception e)
            {
                    e.printStackTrace();
                    return null;
            }

        }
    }
}