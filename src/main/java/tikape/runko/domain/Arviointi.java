package tikape.runko.domain;

public class Arviointi {

    private Integer pirteloId; //mikä pirtelö kyseessä. Tämä pitää 
    private String palaute; //sanallinen pirtelöstä kirjoitettu palaute
    private Integer luokitus; // esimerkiksi 0-5 tähteä
    private String vastaus; //Halutaanko palautteeseen vastata kommentilla?

    public Arviointi(Integer pirteloId, String palaute, Integer luokitus, String vastaus) {
        this.pirteloId = pirteloId;
        this.palaute = palaute;
        this.luokitus = luokitus;
        this.vastaus = vastaus;
    }

    public Integer getPirtelo() {
        return pirteloId;
    }

    public void setPirtelo(Integer pirteloId) {
        this.pirteloId = pirteloId;
    }

    public String getPalaute() {
        return palaute;
    }

    public void setPalaute(String palaute) {
        this.palaute = palaute;
    }

    public Integer getLuokitus() {
        return luokitus;
    }

    public void setLuokitus(Integer luokitus) {
        this.luokitus = luokitus;
    }

    public String getVastaus() {
        return vastaus;
    }

    public void setVastaus(String vastaus) {
        this.vastaus = vastaus;
    }
 
    
             
}
