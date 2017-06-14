package com.example.marc.androfib;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.PopupMenu;

/**
 * Created by Marc on 14/06/2017.
 */

public class AvisosActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_avisos);


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
                    case R.id.avisos:
                        //no hagas nada, estas en el mapa
                        return true;
                    case R.id.mapa:
                        Intent call = new Intent(AvisosActivity.this, MainActivity.class);
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
