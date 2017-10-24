package tikape.runko.domain;

import java.util.ArrayList;

public class Aines {
    
    private Integer id;
    private String nimi;
    private ArrayList<Pirtelo> pirtelot;

    public Aines() {
        this.pirtelot = new ArrayList();
    }
    
    public Aines(Integer id, String nimi) { //, Double kaloriKg, Double kaloriKpl, Double hintaKg, Double hintaKpl, Ominaisuus maku) { perus-konstruktori, lisätään muut toiminnallisuudet myöhemmin)
        this.id = id;
        this.nimi = nimi;
        this.pirtelot = new ArrayList();

    }
    
    public Aines(String nimi) {
        this.nimi = nimi;
        this.pirtelot = new ArrayList();

    }

    public void setPirtelot(ArrayList<Pirtelo> pirtelot) {
        this.pirtelot = pirtelot;
    }

    public ArrayList<Pirtelo> getPirtelot() {
        return pirtelot;
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

}
