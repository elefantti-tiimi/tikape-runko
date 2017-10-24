package tikape.runko.domain;

public class AinesPirtelo {
    
    // kertoo ainesosien määrän
    private Double maara;
    // kertoo minkä tyyppinen ainesosa kyseessä, esim. kpl, gramma tai suhde. Tämän voi tehdä myös enumina 
    private String tyyppi;
    // kertoo miten ainesosaa pirtelössä käytetään, esim. soseutettuna, katolta heitettynä
    // näkyy sivulla ohjeena ja on vapaa teksti
    private String kuvaus;
    //esimerkkituloste: "5 (maara) kpl(tyyppi) [banaani] saappaan alla poljettuna(ohje)"
    private Aines aines;
    private Pirtelo pirtelo;

    public AinesPirtelo(Pirtelo pirtelo, Aines aines, Double maara, String tyyppi) {//, int tyyppi, String ohje) {
        this.pirtelo = pirtelo;
        this.aines = aines;
        this.maara = maara;
        this.tyyppi = tyyppi;
        //this.ohje = ohje;
    }

    public Double getMaara() {
        return maara;
    }

    public void setMaara(Double maara) {
        this.maara = maara;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getKuvaus() {
        return kuvaus;
    }

    public void setKuvaus(String kuvaus) {
        this.kuvaus = kuvaus;
    }
    
    public Aines getAines() {
        return aines;
    }
    
    public Pirtelo getPirtelo() {
        return pirtelo;
    }
    
    
}
