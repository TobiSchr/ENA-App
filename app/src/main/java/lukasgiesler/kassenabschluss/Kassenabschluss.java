package lukasgiesler.kassenabschluss;

import java.io.Serializable;
import java.util.Date;

public class Kassenabschluss implements Serializable {
    private int KassenabschlussID;
    private String ErstellerName;
    private Date ErstellDatum;
    private int G1Cent;
    private int G2Cent;
    private int G5Cent;
    private int G10Cent;
    private int G20Cent;
    private int G50Cent;
    private int G1Euro;
    private int G2Euro;
    private int G5Euro;
    private int G10Euro;
    private int G20Euro;
    private int G50Euro;
    private int G100Euro;
    private int G200Euro;
    private int G500Euro;
    private double Total;
    public int GetKassenabschlussID()
    {
        return KassenabschlussID;
    }
    public void SetKassenabschlussID(int newID)
    {
        KassenabschlussID = newID;
    }
    public String GetErstellerName()
    {
        return ErstellerName;
    }
    public void SetErstellerName(String newName)
    {
        ErstellerName = newName;
    }
    public Date GetErstellDatum()
    {
        return ErstellDatum;
    }
    public void SetErstellDatum(Date newDate)
    {
        ErstellDatum = newDate;
    }
    public int GetG1Cent()
    {
        return G1Cent;
    }
    public void SetG1Cent(int newGeld)
    {
        G1Cent = newGeld;
    }
    public int GetG2Cent()
    {
        return G2Cent;
    }
    public void SetG2Cent(int newGeld)
    {
        G2Cent = newGeld;
    }
    public int GetG5Cent()
    {
        return G5Cent;
    }
    public void SetG5Cent(int newGeld)
    {
        G5Cent = newGeld;
    }
    public int GetG10Cent()
    {
        return G10Cent;
    }
    public void SetG10Cent(int newGeld)
    {
        G10Cent = newGeld;
    }
    public int GetG20Cent()
    {
        return G20Cent;
    }
    public void SetG20Cent(int newGeld)
    {
        G20Cent = newGeld;
    }
    public int GetG50Cent()
    {
        return G50Cent;
    }
    public void SetG50Cent(int newGeld)
    {
        G50Cent = newGeld;
    }
    public int GetG1Euro()
    {
        return G1Euro;
    }
    public void SetG1Euro(int newGeld)
    {
        G1Euro = newGeld;
    }
    public int GetG2Euro()
    {
        return G2Euro;
    }
    public void SetG2Euro(int newGeld)
    {
        G2Euro = newGeld;
    }
    public int GetG5Euro()
    {
        return G5Euro;
    }
    public void SetG5Euro(int newGeld)
    {
        G5Euro = newGeld;
    }
    public int GetG10Euro()
    {
        return G10Euro;
    }
    public void SetG10Euro(int newGeld)
    {
        G10Euro = newGeld;
    }
    public int GetG20Euro()
    {
        return G20Euro;
    }
    public void SetG20Euro(int newGeld)
    {
        G20Euro = newGeld;
    }
    public int GetG50Euro()
    {
        return G50Euro;
    }
    public void SetG50Euro(int newGeld)
    {
        G50Euro = newGeld;
    }
    public int GetG100Euro()
    {
        return G100Euro;
    }
    public void SetG100Euro(int newGeld)
    {
        G100Euro = newGeld;
    }
    public int GetG200Euro()
    {
        return G200Euro;
    }
    public void SetG200Euro(int newGeld)
    {
        G200Euro = newGeld;
    }
    public int GetG500Euro()
    {
        return G500Euro;
    }
    public void SetG500Euro(int newGeld)
    {
        G500Euro = newGeld;
    }
    public double GetTotal()
    {
        return Total;
    }
    public void SetTotal(double newGeld)
    {
        Total = newGeld;
    }
}
