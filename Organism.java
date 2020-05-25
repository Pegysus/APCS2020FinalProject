public class Organism {

    private String type;
    private String species;
    private int age;
    private int aggression;
    private boolean ill;
    private double tempReq;
    private double salinityReq;


    // constructors
    public Organism() {
        type = "Fish";
        species = "clownfish";
        age = 0;
        aggression = 4;
        ill = false;
        tempReq = 78;
        salinityReq = 1.026;
    }
    public Organism(String type, String species, int age, int aggression, boolean ill, double tempReq, double salinityReq) {
        this.type = type;
        this.species = species;
        this.age = age;
        this.aggression = aggression;
        this.ill = ill;
        this.tempReq = tempReq;
        this.salinityReq = salinityReq;
    }


    // getters
    public boolean isIll() { return ill; }
    public String getType() { return type; }
    public String getSpecies() { return species; }
    public int getAggression() { return aggression; }


    // setters
    public void turnIll() { ill = true; }
    public void cure() { ill = false; }
    public void passTime(int time) { age += time; }


    // other methods
    public String toString() {
        String output = type + ": " + species;
        return output;
    }


}