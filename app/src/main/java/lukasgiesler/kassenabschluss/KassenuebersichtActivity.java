package lukasgiesler.kassenabschluss;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.Toast;

import java.util.ArrayList;


public class KassenuebersichtActivity extends AppCompatActivity {
    private int my_id;
    private Kassenliste kassenliste;
    private ArrayList<Kasse> kassen_arraylist;
    private Kasse selectedKasse;
    private Button selectedBtnKasse;

    private void setMyId(){
        if(kassen_arraylist.isEmpty()){
            my_id = 0;
        }else{
            my_id = kassen_arraylist.get(kassen_arraylist.size()-1).GetKassenID();
        }
    }

    private void addKassenButton(Kasse addKasse){
        final Kasse kasse = addKasse;
        // Button erstellen
        final Button btnKasse = new Button(this);
        btnKasse.setText(kasse.GetKassenName());

        registerForContextMenu(btnKasse.getRootView());

        btnKasse.setOnClickListener(new View.OnClickListener() {

            public void onClick(View arg0) {
                // Intent erstellen
                Intent intent = new Intent(getApplicationContext(), KassenabschlusslisteActivity.class);
                intent.putExtra("Kasse", kasse);
                // Activity starten
                startActivityForResult(intent, 0);
            }
        });

        btnKasse.setOnLongClickListener(new View.OnLongClickListener(){

            @Override
            public boolean onLongClick(View v) {
                //globale Variablen setzen um sie in onCreateContextMenu zu benutzen
                selectedBtnKasse = btnKasse;
                selectedKasse = kasse;
                openContextMenu(v);
                return true;
            }
        });

        // Zum Layout hinzufuegen
        LinearLayout ll = (LinearLayout)findViewById(R.id.linearKassen);
        LayoutParams lp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
        ll.addView(btnKasse, lp);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 0){
            if(resultCode == RESULT_OK){
                Kasse kasse = (Kasse) getIntent().getSerializableExtra("Kasse");
                int idx = kassen_arraylist.indexOf(kasse);
                kassen_arraylist.set(idx, kasse);
            }
        }
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassenuebersicht);

        kassenliste = Kassenliste.readFromFile(getApplicationContext());
        kassen_arraylist = kassenliste.getKassenArrayList();

        setMyId();

        for (Kasse k: kassen_arraylist) {
            addKassenButton(k);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu this adds items to the action bar if it is present
        getMenuInflater().inflate(R.menu.menu_kassenuebersicht, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle presses on the action bar items
        switch(item.getItemId()){
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
            case R.id.action_neuekasse:
                // Neue Kasse erstellen
                final Kasse newKasse = new Kasse();
                newKasse.SetKassenID(++my_id);
                newKasse.SetKassenName("Kasse " + newKasse.GetKassenID());
                // Kasse zur Liste hinzufuegen
                kassen_arraylist.add(newKasse);
                addKassenButton(newKasse);

                //scroll at the end of scroll view
                ScrollView sv = (ScrollView) findViewById(R.id.scrollViewUebersicht);
                sv.fullScroll(View.FOCUS_DOWN);
        }
        return true;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {
            case R.id.context_menu_delete:
                //TODO Lösung ohne globale variablen für kasse und button
                kassen_arraylist.remove(selectedKasse);
                LinearLayout ll = (LinearLayout)findViewById(R.id.linearKassen);
                ll.removeView(selectedBtnKasse);
                setMyId();
                return true;
            default:
                return super.onContextItemSelected(item);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current game state
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
        kassenliste.saveToFile(getApplicationContext());
    }

    @Override
    public void onStop() {
        super.onStop();
        // Save the user's current game state
        // Always call the superclass so it can save the view hierarchy state
        kassenliste.saveToFile(getApplicationContext());
    }
}