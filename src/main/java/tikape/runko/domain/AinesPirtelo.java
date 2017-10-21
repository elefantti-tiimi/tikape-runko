package tikape.runko.domain;

public class AinesPirtelo {
    
    // kertoo ainesosien määrän
    private Integer maara;
    // kertoo minkä tyyppinen ainesosa kyseessä, esim. kpl, gramma tai suhde. Tämän voi tehdä myös enumina 
    private String tyyppi;
    // kertoo miten ainesosaa pirtelössä käytetään, esim. soseutettuna, katolta heitettynä
    // näkyy sivulla ohjeena ja on vapaa teksti
    private String ohje;
    //esimerkkituloste: "5 (maara) kpl(tyyppi) [banaani] saappaan alla poljettuna(ohje)"
    private Aines aines;
    private Pirtelo pirtelo;

    public AinesPirtelo(Pirtelo pirtelo, Aines aines, Integer maara, String tyyppi) {//, int tyyppi, String ohje) {
        this.pirtelo = pirtelo;
        this.aines = aines;
        this.maara = maara;
        this.tyyppi = tyyppi;
        //this.ohje = ohje;
    }

    public Integer getMaara() {
        return maara;
    }

    public void setMaara(Integer maara) {
        this.maara = maara;
    }

    public String getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(String tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }
    
    public Aines getAines() {
        return aines;
    }
    
    public Pirtelo getPirtelo() {
        return pirtelo;
    }
    
    
}
