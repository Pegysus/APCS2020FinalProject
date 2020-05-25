public class Fish extends Organism {

    public Fish() {
        super("fish", "clownfish", 0, 0, false, 0, 0);
    }

    public Fish(String species, int age, int aggression, boolean ill, double tempReq, double salinityReq) {
        super("fish", species, age, aggression, ill, tempReq, salinityReq);
    }

}