package tikape.runko.domain;

public class AinesPirtelo {
    
    // kertoo ainesosien määrän
    private int maara;
    // kertoo minkä tyyppinen ainesosa kyseessä, esim. kpl, gramma tai suhde. Tämän voi tehdä myös enumina 
    private int tyyppi;
    // kertoo miten ainesosaa pirtelössä käytetään, esim. soseutettuna, katolta heitettynä
    // näkyy sivulla ohjeena ja on vapaa teksti
    private String ohje;
    //esimerkkituloste: "5 (maara) kpl(tyyppi) [banaani] saappaan alla poljettuna(ohje)"

    public AinesPirtelo(int maara, int tyyppi, String ohje) {
        this.maara = maara;
        this.tyyppi = tyyppi;
        this.ohje = ohje;
    }

    public int getMaara() {
        return maara;
    }

    public void setMaara(int maara) {
        this.maara = maara;
    }

    public int getTyyppi() {
        return tyyppi;
    }

    public void setTyyppi(int tyyppi) {
        this.tyyppi = tyyppi;
    }

    public String getOhje() {
        return ohje;
    }

    public void setOhje(String ohje) {
        this.ohje = ohje;
    }
    
    
}
