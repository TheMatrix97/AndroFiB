package com.example.marc.androfib;

import android.app.ActionBar;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Menu;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

import java.lang.Integer;
import java.net.HttpURLConnection;
import android.widget.LinearLayout;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private String urlA5 = "https://api.fib.upc.edu/v2/laboratoris/imatges/A5.png";
    private String urlC6 = "https://api.fib.upc.edu/v2/laboratoris/imatges/C6.png";
    private String urlB5 = "https://api.fib.upc.edu/v2/laboratoris/imatges/B5.png";
    private String client_id = "TPQg4vM81egJ3BimByz3jmnjRSXTX5yzTv1uB1q0";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        work();


    }
    private void work(){
        URL A5 = crear_URL(urlA5);
        URL C6 = crear_URL(urlC6);
        URL B5 = crear_URL(urlB5);
        System.out.println("WORKED " + A5.getHost());
        new DownloadFile().execute(A5,B5,C6);
    }
    //MENU --->
    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.action_refresh:
                work();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }

    }



    private void add_child(final Bitmap[] bitmapArray){
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                ImageView imgA5 = (ImageView) findViewById(R.id.A5);
                ImageView imgB5 = (ImageView) findViewById(R.id.B5);
                ImageView imgC6 = (ImageView) findViewById(R.id.C6);
                imgA5.setImageBitmap(bitmapArray[0]);
                imgB5.setImageBitmap(bitmapArray[1]);
                imgC6.setImageBitmap(bitmapArray[2]);
                System.out.println("funciona!");
            }
        });

    }
    private class DownloadFile extends AsyncTask<URL,Integer,Boolean>{
        protected Boolean doInBackground(URL ... urls){
            int count = urls.length;
            Bitmap[] bitmapArray = new Bitmap[3];
            for(int i = 0; i < count; i++) {
                Bitmap loaded_img = null;
                System.out.println("doing: " + i);
                try {
                    HttpURLConnection conn = (HttpURLConnection) urls[i].openConnection();
                    conn.connect();
                    System.out.println("TEST: " + urls[i].getHost());
                    loaded_img = BitmapFactory.decodeStream(conn.getInputStream());
                } catch (IOException e) {
                    System.out.println("ERROR EN DESCARGA");
                    Toast.makeText(getApplicationContext(), "ERROR al descargar IMG " + e.getMessage(), Toast.LENGTH_LONG).show();
                    e.printStackTrace();

                }
                bitmapArray[i] = loaded_img;
                System.out.println("DESCARGADO!");
            }
            add_child(bitmapArray);
            return true;
        }
        protected void onProgressUpdate(Integer... progress) {
        }
        protected void onPostExecute(Boolean res){
            if(res) System.out.println("succes!");
            else System.out.println("error");
        }
    }

    private URL crear_URL(String url){
        URL t = null;
        String aux = "?client_id=" + client_id;
        try{
            t = new URL(url + aux);

        }catch(MalformedURLException e){
            System.out.println("ERROR EN ULR MAKER");
            Toast.makeText(getApplicationContext(), "ERROR al formar URL " + e.getMessage(), Toast.LENGTH_LONG).show();
            e.printStackTrace();
        }
        return t;
    }
}
