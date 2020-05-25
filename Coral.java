public class Coral extends Organism {

    public Coral() {
        super("coral", "xenia", 0, 0, false, 0, 0);
    }

    public Coral(String species, int age, int aggression, boolean ill, double tempReq, double salinityReq) {
        super("coral", species, age, aggression, ill, tempReq, salinityReq);
    }

}