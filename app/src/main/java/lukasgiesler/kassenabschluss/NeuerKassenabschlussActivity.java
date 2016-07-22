package lukasgiesler.kassenabschluss;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

public class NeuerKassenabschlussActivity extends AppCompatActivity {
    Kasse aktuelleKasse;
    //0-5 cents 6-14 euros
    int[] editTextIds = {
            R.id.editText1ct,       R.id.editText2ct,       R.id.editText5ct,
            R.id.editText10ct,      R.id.editText20ct,      R.id.editText50ct,
            R.id.editText1euro,     R.id.editText2euro,     R.id.editText5euro,
            R.id.editText10euro,    R.id.editText20euro,    R.id.editText50euro,
            R.id.editText100euro,   R.id.editText200euro,   R.id.editText500euro,
    };

    // updates total value of kassenabschluss by calculating the value of all input
    public void UpdateTotal(Kassenabschluss kassenabschluss)
    {
        double total = kassenabschluss.GetG1Cent() * 0.01
                + kassenabschluss.GetG2Cent() * 0.02
                + kassenabschluss.GetG5Cent() * 0.05
                + kassenabschluss.GetG10Cent() * 0.1
                + kassenabschluss.GetG20Cent() * 0.2
                + kassenabschluss.GetG50Cent() * 0.5
                + kassenabschluss.GetG1Euro() * 1.0
                + kassenabschluss.GetG2Euro() * 2.0
                + kassenabschluss.GetG5Euro() * 5.0
                + kassenabschluss.GetG10Euro() * 10.0
                + kassenabschluss.GetG20Euro() * 20.0
                + kassenabschluss.GetG50Euro() * 50.0
                + kassenabschluss.GetG100Euro() * 100.0
                + kassenabschluss.GetG200Euro() * 200.0
                + kassenabschluss.GetG500Euro() * 500.0;
        kassenabschluss.SetTotal(total);
        final TextView TxtTotal = (TextView) findViewById(R.id.textViewTotal);
        /* # cuts off 0 at the beginning of a number
         * 0. keep the 0 before the point, if the number is smaller than 1
         * .00 round up to 2 digits behind the point, always show 2 digits */
        DecimalFormat df = new DecimalFormat("#0.00");
        TxtTotal.setText("" + df.format(total) + " €");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neuerkassenabschluss);

        // Uebergebene Kasse vewenden
        aktuelleKasse = (Kasse) getIntent().getSerializableExtra("Kasse");

        // Kassenabschluss anlegen
        final Kassenabschluss neuerKassenabschluss = new Kassenabschluss();

        //iterate over all editTextIds and add a textchangelistener for each
        //will set the changed value in eachs kassenabschluss class and update total value
        for(int i = 0; i < editTextIds.length; i++){
            final EditText editext = (EditText) findViewById(editTextIds[i]);
            final int switchI = i;
            editext.addTextChangedListener(new TextWatcher() {
                @Override
                public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

                @Override
                public void afterTextChanged(Editable editable) {
                    int textAsInt = 0;
                    if(editext.getText().length()!=0){
                        textAsInt = Integer.parseInt(editext.getText().toString());
                    }
                    switch (switchI){
                        case 0:
                            neuerKassenabschluss.SetG1Cent(textAsInt);
                            break;
                        case 1:
                            neuerKassenabschluss.SetG2Cent(textAsInt);
                            break;
                        case 2:
                            neuerKassenabschluss.SetG5Cent(textAsInt);
                            break;
                        case 3:
                            neuerKassenabschluss.SetG10Cent(textAsInt);
                            break;
                        case 4:
                            neuerKassenabschluss.SetG20Cent(textAsInt);
                            break;
                        case 5:
                            neuerKassenabschluss.SetG50Cent(textAsInt);
                            break;
                        case 6:
                            neuerKassenabschluss.SetG1Euro(textAsInt);
                            break;
                        case 7:
                            neuerKassenabschluss.SetG2Euro(textAsInt);
                            break;
                        case 8:
                            neuerKassenabschluss.SetG5Euro(textAsInt);
                            break;
                        case 9:
                            neuerKassenabschluss.SetG10Euro(textAsInt);
                            break;
                        case 10:
                            neuerKassenabschluss.SetG20Euro(textAsInt);
                            break;
                        case 11:
                            neuerKassenabschluss.SetG50Euro(textAsInt);
                            break;
                        case 12:
                            neuerKassenabschluss.SetG100Euro(textAsInt);
                            break;
                        case 13:
                            neuerKassenabschluss.SetG200Euro(textAsInt);
                            break;
                        case 14:
                            neuerKassenabschluss.SetG500Euro(textAsInt);
                            break;
                        default:
                            break;
                    }
                    UpdateTotal(neuerKassenabschluss);
                }
            });
        }

        EditText editext = (EditText) findViewById(R.id.editTextDatum);
        editext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {}

            @Override
            public void afterTextChanged(Editable editable) {
                EditText et = (EditText) findViewById(R.id.editText1ct);
                et.requestFocus();
            }
        });

        // Speichern Button anlegen
        FloatingActionButton fabKassenabschlussSpeichern = (FloatingActionButton) findViewById(R.id.fab_save);

        // Speichern Button On Click Listener
        assert fabKassenabschlussSpeichern != null;
        fabKassenabschlussSpeichern.setOnClickListener(new View.OnClickListener() {
            public void onClick(View arg0) {
                EditText TxtName = (EditText) findViewById(R.id.editTextName);
                if(TxtName.getText().toString().trim().equals("")){
                    Toast.makeText(getApplicationContext(), "Name ist ein Pflichtfeld!", Toast.LENGTH_SHORT).show();
                } else {
                    neuerKassenabschluss.SetKassenabschlussID(aktuelleKasse.GetKassenabschlussList().size()+1);
                    neuerKassenabschluss.SetErstellerName(TxtName.getText().toString());

                    EditText txtDatum = (EditText) findViewById(R.id.editTextDatum);
                    String dateString = txtDatum.getText().toString();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy", Locale.GERMAN);

                    Date date = new Date();
                    try {
                        date  = sdf.parse(dateString);
                    } catch (ParseException e) {
                        e.printStackTrace();
                    }
                    neuerKassenabschluss.SetErstellDatum(date);

                    // Kassenabschluss zur Liste hinzufuegen
                    aktuelleKasse.GetKassenabschlussList().add(neuerKassenabschluss);
                    // Intent anlegen
                    Intent intent = new Intent(getApplicationContext(), KassenabschlusslisteActivity.class);
                    intent.putExtra("Kasse", aktuelleKasse);
                    setResult(1, intent);
                    finish();
                }
            }
        });
        fabKassenabschlussSpeichern.setOnLongClickListener(new View.OnLongClickListener() {
            public boolean onLongClick(View v) {
                Toast.makeText(getApplicationContext(), "Kassenabschluss speichern", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        final Calendar myCalendar = Calendar.getInstance();
        final EditText edittext = (EditText) findViewById(R.id.editTextDatum);

        final DatePickerDialog.OnDateSetListener date = new DatePickerDialog.OnDateSetListener() {

            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear,
                                  int dayOfMonth) {
                myCalendar.set(year, monthOfYear, dayOfMonth);
                String myFormat = "dd/MM/yyyy"; //In which you need put here
                SimpleDateFormat sdf = new SimpleDateFormat(myFormat, Locale.GERMAN);

                edittext.setText(sdf.format(myCalendar.getTime()));
            }

        };

        edittext.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                new DatePickerDialog(NeuerKassenabschlussActivity.this, date, myCalendar
                        .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                        myCalendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });
    }

    @Override
    public void onBackPressed() {
        NeuerKassenabschlussActivity.super.onBackPressed();
        // Uebergebene Kasse vewenden
        setResult(-1);
        finish();
    }
}
