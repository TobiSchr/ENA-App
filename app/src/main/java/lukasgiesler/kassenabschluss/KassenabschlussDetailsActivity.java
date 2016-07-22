package lukasgiesler.kassenabschluss;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class KassenabschlussDetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kassenabschlussdetails);

        // Uebergebener Kassenabschluss vewenden
        final Kassenabschluss aktuellerKassenabschluss = (Kassenabschluss) getIntent().getSerializableExtra("Kassenabschluss");

        setTitle("Kassenabschluss #"+aktuellerKassenabschluss.GetKassenabschlussID());

        TextView TxtName = (TextView) findViewById(R.id.textViewName);
        TxtName.setText(aktuellerKassenabschluss.GetErstellerName());

        TextView TxtErstellDatum = (TextView) findViewById(R.id.textViewDatum);
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);
        TxtErstellDatum.setText(sdf.format(aktuellerKassenabschluss.GetErstellDatum()));

        TextView Txt1ct = (TextView) findViewById(R.id.textView1ct);
        Txt1ct.setText(String.valueOf(aktuellerKassenabschluss.GetG1Cent()));

        TextView Txt2ct = (TextView) findViewById(R.id.textView2ct);
        Txt2ct.setText("" + aktuellerKassenabschluss.GetG2Cent());

        TextView Txt5ct = (TextView) findViewById(R.id.textView5ct);
        Txt5ct.setText("" + aktuellerKassenabschluss.GetG5Cent());

        TextView Txt10ct = (TextView) findViewById(R.id.textView10ct);
        Txt10ct.setText("" + aktuellerKassenabschluss.GetG10Cent());

        TextView Txt20ct = (TextView) findViewById(R.id.textView20ct);
        Txt20ct.setText("" + aktuellerKassenabschluss.GetG20Cent());

        TextView Txt50ct = (TextView) findViewById(R.id.textView50ct);
        Txt50ct.setText("" + aktuellerKassenabschluss.GetG50Cent());

        TextView Txt1euro = (TextView) findViewById(R.id.textView1euro);
        Txt1euro.setText("" + aktuellerKassenabschluss.GetG1Euro());

        TextView Txt2euro = (TextView) findViewById(R.id.textView2euro);
        Txt2euro.setText("" + aktuellerKassenabschluss.GetG2Euro());

        TextView Txt5euro = (TextView) findViewById(R.id.textView5euro);
        Txt5euro.setText("" + aktuellerKassenabschluss.GetG5Euro());

        TextView Txt10euro = (TextView) findViewById(R.id.textView10euro);
        Txt10euro.setText("" + aktuellerKassenabschluss.GetG10Euro());

        TextView Txt20euro = (TextView) findViewById(R.id.textView20euro);
        Txt20euro.setText("" + aktuellerKassenabschluss.GetG20Euro());

        TextView Txt50euro = (TextView) findViewById(R.id.textView50euro);
        Txt50euro.setText("" + aktuellerKassenabschluss.GetG50Euro());

        TextView Txt100euro = (TextView) findViewById(R.id.textView100euro);
        Txt100euro.setText("" + aktuellerKassenabschluss.GetG100Euro());

        TextView Txt200euro = (TextView) findViewById(R.id.textView200euro);
        Txt200euro.setText("" + aktuellerKassenabschluss.GetG200Euro());

        TextView Txt500euro = (TextView) findViewById(R.id.textView500euro);
        Txt500euro.setText("" + aktuellerKassenabschluss.GetG500Euro());

        TextView TxtTotal = (TextView) findViewById(R.id.textViewTotal);
        DecimalFormat df = new DecimalFormat("#0.00");
        TxtTotal.setText("" + df.format(aktuellerKassenabschluss.GetTotal()) + " €");
    }
}
