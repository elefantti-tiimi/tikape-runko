package tikape.runko.domain;

public enum Arvosana {
    Paska(0),
    Paha(1),
    Huono(2),
    OK(3),
    Hyva(4),
    Loistava(5);

    private final int arvo;

    Arvosana(final int newValue) {
            arvo = newValue;
        }

    public int getArvo() {
        return arvo; 
    }
}