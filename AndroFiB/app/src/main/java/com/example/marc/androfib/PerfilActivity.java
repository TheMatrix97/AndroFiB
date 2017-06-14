package com.example.marc.androfib;

import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by Roger on 14/06/2017.
 */

public class PerfilActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);
        cargar_datos();




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
                Intent call;
                switch (item.getItemId()){
                    case R.id.raco:
                        call = new Intent(PerfilActivity.this, RacoActivity.class);
                        startActivity(call);
                        return true;
                    case R.id.mapa:
                        call = new Intent(PerfilActivity.this, MainActivity.class);
                        startActivity(call);
                        return true;
                }
                return false;
            }
        });
        popup.show();
    }
    //FIN MENU
    private void cargar_datos(){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(PerfilActivity.this);
        SharedPreferences.Editor myEditor = myPreferences.edit();
        String name = myPreferences.getString("nom", "unknown");
        String cognom = myPreferences.getString("cognom", "unknown");
        TextView nomView = (TextView) findViewById(R.id.nom);
        TextView cognomView = (TextView) findViewById(R.id.cognom);
        if(name.equals("unknown") || cognom.equals("unknown")){
            //obtener datos
            myEditor.putString("nom", "Marc");
            myEditor.putString("cognom", "Catrisse Pérez");
            myEditor.apply();


        }
        nomView.setText(nomView.getText() + name);
        cognomView.setText(cognomView.getText() + cognom);


    }


}
