public class Invert extends Organism {

    public Invert() {
        super("invert", "tuxedo urchin", 0, 0, false, 0, 0);
    }

    public Invert(String species, int age, int aggression, boolean ill, double tempReq, double salinityReq) {
        super("invert", species, age, aggression, ill, tempReq, salinityReq);
    }

}