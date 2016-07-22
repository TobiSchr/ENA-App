package lukasgiesler.kassenabschluss;

import android.content.Context;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by TobiX on 04.06.2016.
 */
public class Kassenliste implements Serializable {
    private ArrayList<Kasse> KassenArrayList = new ArrayList<>();

    public ArrayList<Kasse> getKassenArrayList() {
        return KassenArrayList;
    }
    public void setKassenArrayList(ArrayList<Kasse> kassenArrayList) {
        KassenArrayList = kassenArrayList;
    }

    // Constant with a file name
    public static String fileName = "kassenliste.ser";

    // Serializes an object and saves it to a file
    public void saveToFile(Context context) {
        try {
            FileOutputStream fileOutputStream = context.openFileOutput(fileName, Context.MODE_PRIVATE);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(this);
            objectOutputStream.close();
            fileOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    // Creates an object by reading it from a file
    public static Kassenliste readFromFile(Context context) {
        Kassenliste kassenliste = new Kassenliste();
        try {
            FileInputStream fileInputStream = context.openFileInput(fileName);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            kassenliste = (Kassenliste) objectInputStream.readObject();
            objectInputStream.close();
            fileInputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return kassenliste;
    }
}
