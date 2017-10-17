package tikape.runko.domain;

public class Pirtelo {

    private Integer id;
    private String nimi;
    private String ohje; //vapaa tekstikenttä, pirtelön valmistusohje vapaamuotoisesti kirjoitettuna
            //esim. "Sekoita kaikki ainekset keskenään, työnnä banaani korvaan ja nauti".
    private Double hintaArvio; // Hakua varten, koska ainesten perusteella hakiessa hinta vaihtelee määristä riippuen       

    public Pirtelo(String nimi) {//, String ohje, Double hintaArvio) {
       //this.id = id;
        this.nimi = nimi;
        //this.ohje = ohje;
        //this.hintaArvio = hintaArvio;
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

    
}
