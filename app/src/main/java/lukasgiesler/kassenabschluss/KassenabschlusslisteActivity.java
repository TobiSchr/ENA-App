package lukasgiesler.kassenabschluss;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class KassenabschlusslisteActivity extends AppCompatActivity {

    Kasse aktuelleKasse;

    private void updateAbschluesse(){
        LinearLayout ll = (LinearLayout) findViewById(R.id.linearLayoutKassenabschlussliste);
        ll.removeAllViews();
        double GesamtWert = 0.0;
        // Ueber alle Kassenabschluesse iterieren
        for (final Kassenabschluss i : aktuelleKasse.GetKassenabschlussList()) {
            // Gesamt Einnahmen dieses Kassenabschlusses zur Gesamtsumme addieren
            GesamtWert = GesamtWert + i.GetTotal();
            // Button erstellen
            Button btnKassenabschluss = new Button(this);

            String myFormat = "dd/MM/yyyy";
            SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMAN);

            DecimalFormat df = new DecimalFormat("#0.00");

            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("#");
            stringBuilder.append(i.GetKassenabschlussID());
            stringBuilder.append("  ");
            stringBuilder.append(i.GetErstellerName());
            stringBuilder.append("\n");
            stringBuilder.append(sdf.format(i.GetErstellDatum().getTime()));
            stringBuilder.append("  ");
            stringBuilder.append(df.format(i.GetTotal()));
            String text = stringBuilder.toString();

            btnKassenabschluss.setGravity(Gravity.LEFT);
            btnKassenabschluss.setText(text);
            btnKassenabschluss.setOnClickListener(new View.OnClickListener() {

                public void onClick(View arg0) {
                    // Intent erstellen
                    Intent intent = new Intent(getApplicationContext(), KassenabschlussDetailsActivity.class);
                    intent.putExtra("Kassenabschluss", i);
                    // Activity starten
                    startActivity(intent);
                }
            });
            // Zum Layout hinzufuegen
            LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            ll.addView(btnKassenabschluss, lp);
        }
        // GesamtWert Text anpassen
        TextView tvGesamtEinnahmen = (TextView) findViewById(R.id.gesamtEinnahmenTextView);
        DecimalFormat df = new DecimalFormat("#0.00");
        tvGesamtEinnahmen.setText("" + df.format(GesamtWert) + " €");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassenabschlussliste);

        // Uebergebene Kasse vewenden
        aktuelleKasse = (Kasse) getIntent().getSerializableExtra("Kasse");

        setTitle(""+aktuelleKasse.GetKassenName());

        if(aktuelleKasse != null) {
            updateAbschluesse();
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab_add);

        fab.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                // Intent anlegen
                Intent intent = new Intent(getApplicationContext(), NeuerKassenabschlussActivity.class);
                intent.putExtra("Kasse", aktuelleKasse);
                // Activity starten
                startActivityForResult(intent, 1);

            }
        });
        fab.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Neuen Kassenabschlussliste anlegen", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == 1){
            if(resultCode == 1){
                aktuelleKasse = (Kasse) data.getSerializableExtra("Kasse");
                updateAbschluesse();
            }
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //Intent data = new Intent();
        final Kasse aktuelleKasse = (Kasse) getIntent().getSerializableExtra("Kasse");
        // Intent anlegen
        Intent data = new Intent(getApplicationContext(), NeuerKassenabschlussActivity.class);
        data.putExtra("Kasse", aktuelleKasse);
        setResult(0, data);
        finish();
    }
}
