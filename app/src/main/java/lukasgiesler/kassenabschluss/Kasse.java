package lukasgiesler.kassenabschluss;

import java.io.Serializable;
import java.util.ArrayList;


public class Kasse implements Serializable {
    private int KassenID;
    private String KassenName;
    private ArrayList<Kassenabschluss> KassenabschlussList = new ArrayList<>();

    public int GetKassenID() {
        return KassenID;
    }

    public void SetKassenID(int newID) {
        KassenID = newID;
    }

    public String GetKassenName() {
        return KassenName;
    }

    public void SetKassenName(String newName) {
        KassenName = newName;
    }

    public ArrayList<Kassenabschluss> GetKassenabschlussList() {
        return KassenabschlussList;
    }

    public void SetKassenabschlussList(ArrayList<Kassenabschluss> newList) {
        KassenabschlussList = newList;
    }

}