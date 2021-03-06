package tikape.runko.domain;

//toistaiseksi käytetään vain nimeä ja id:tä, lisää muut muuttujat konstruktoreihin myöhemmin

import java.util.ArrayList;

public class Pirtelo {

    private Integer id;
    private String nimi;
    private String ohje; //vapaa tekstikenttä, pirtelön valmistusohje vapaamuotoisesti kirjoitettuna
            //esim. "Sekoita kaikki ainekset keskenään, työnnä banaani korvaan ja nauti".
    private String kuva;
    
    private ArrayList<Aines> ainekset;

    public Pirtelo(String nimi) {//, String ohje, Double hintaArvio) {
       //this.id = id;
        this.nimi = nimi;
        //this.ohje = ohje;
        //this.hintaArvio = hintaArvio;
        this.ainekset = new ArrayList();
    }
    
    public Pirtelo(Integer id, String nimi) {//, String ohje, Double hintaArvio) {
        this.id = id;
        this.nimi = nimi;
        this.ohje = new String();// tarviiko ohjeen olla tyhjä?
        this.kuva = new String();
        this.ainekset = new ArrayList();
    }

    public ArrayList<Aines> getAinekset() {
        return ainekset;
    }

    public void setAinekset(ArrayList<Aines> ainekset) {
        this.ainekset = ainekset;
    }
    
    public void addAines(Aines aines) {
        ainekset.add(aines);
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNimi() {
        return nimi;
    }

    public void setNimi(String nimi) {
        this.nimi = nimi;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }
    
    public void setKuva(String kuva) {
        this.kuva = kuva;
    }
    
    public String getKuva() {
        return kuva;
    }

    
}
