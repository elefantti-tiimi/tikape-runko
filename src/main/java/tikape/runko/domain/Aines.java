package tikape.runko.domain;

import java.util.ArrayList;

public class Aines {
    
    private Integer id;
    private String nimi;
    private Double kaloriKg; //kaloreita per kg
    private Double kaloriKpl; //kaloreita per kpl
    // hinta on ominaisuutena vähän kyseenalainen, koska oikeasti hinnat vaihtelevat paljon
    private Double hintaKg; //kg-hinta
    private Double hintaKpl; //kpl-hinta
    private Ominaisuus maku;
    // Jos jaksaa vääntää, niin Ainekselle voisi syöttäää myös rasva/proteiini/hiilihydraatti/sokeri/kuitu
    // Jos haluatte, niin tehkää vielä erikseen Allergeeni-luokka/taulu, joita voi olla 
    //aineksella n-kappaletta
    private ArrayList<Pirtelo> pirtelot;

    public Aines() {
        this.pirtelot = new ArrayList();
    }
    
    public Aines(Integer id, String nimi) { //, Double kaloriKg, Double kaloriKpl, Double hintaKg, Double hintaKpl, Ominaisuus maku) { perus-konstruktori, lisätään muut toiminnallisuudet myöhemmin)
        this.id = id;
        this.nimi = nimi;
        //this.kaloriKg = kaloriKg;
        //this.kaloriKpl = kaloriKpl;
        //this.hintaKg = hintaKg;
        //this.hintaKpl = hintaKpl;
        //this.maku = maku;
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

    public Double getKaloriKg() {
        return kaloriKg;
    }

    public void setKaloriKg(Double kaloriKg) {
        this.kaloriKg = kaloriKg;
    }

    public Double getKaloriKpl() {
        return kaloriKpl;
    }

    public void setKaloriKpl(Double kaloriKpl) {
        this.kaloriKpl = kaloriKpl;
    }

    public Double getHintaKg() {
        return hintaKg;
    }

    public void setHintaKg(Double hintaKg) {
        this.hintaKg = hintaKg;
    }

    public Double getHintaKpl() {
        return hintaKpl;
    }

    public void setHintaKpl(Double hintaKpl) {
        this.hintaKpl = hintaKpl;
    }

    public Ominaisuus getMaku() {
        return maku;
    }

    public void setMaku(Ominaisuus maku) {
        this.maku = maku;
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
