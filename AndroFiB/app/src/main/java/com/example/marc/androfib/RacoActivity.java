package com.example.marc.androfib;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.PopupMenu;
import android.webkit.WebViewClient;
import android.webkit.JavascriptInterface;
import android.content.Context;
import android.widget.Toast;
import android.webkit.DownloadListener;


/**
 * Created by Marc on 14/06/2017.
 */

public class RacoActivity extends AppCompatActivity {
    private String url_aut = "https://raco.fib.upc.edu";
    @Override
    @JavascriptInterface
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);
        //tenemos que conseguir el token, hay que autorizar la app
        WebView myinterface = (WebView) findViewById(R.id.interfWeb);
        myinterface.setWebViewClient(new WebViewClient());
        WebSettings webSettings = myinterface.getSettings();
        webSettings.setJavaScriptEnabled(true); //problema gordo, se tiene que activar java para visitar horario....etc pero puede suponer una inseguridad
        myinterface.loadUrl(url_aut);
        myinterface.setDownloadListener(new DownloadListener() { //no es muy elegante pero funciona para descargar notas.
            @Override
            public void onDownloadStart(String url, String userAgent,
                                        String contentDisposition, String mimetype,
                                        long contentLength) {

                startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
            }
        });


    }

    //MENU NAVEGACION

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.action_bar,menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        MenuItem item = menu.findItem(R.id.action_refresh);
        item.setEnabled(false);
        item.setVisible(false);
        return true;
    }

    public boolean OnClickNav(MenuItem item){
        showMenu(findViewById(item.getItemId()));
        return true;
    }
    public void showMenu(View v){
        PopupMenu popup = new PopupMenu(this,v);
        MenuInflater inflater = popup.getMenuInflater();
        inflater.inflate(R.menu.nav, popup.getMenu());
        popup.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()){
                    case R.id.raco:
                        //no hagas nada, estas en el mapa
                        return true;
                    case R.id.mapa:
                        Intent call = new Intent(RacoActivity.this, MainActivity.class);
                        startActivity(call);
                        return true;
                    case R.id.perfil:
                        call = new Intent(RacoActivity.this, PerfilActivity.class);
                        startActivity(call);
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }
    //FIN MENU


}
