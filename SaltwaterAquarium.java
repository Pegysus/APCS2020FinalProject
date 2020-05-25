import java.util.*;
public class SaltwaterAquarium extends FishTank implements Ecosystem { // #22 Inheritance

    public static final double[] defaultDimensions = {0, 0, 0};
    public static final ArrayList<Organism> defaultOrganisms = new ArrayList<>();

    // #15 clear and descriptive comments
    // #16 variable names (instance variables)
    // #9 Class Design "Big 3" variables
    // #9 Class Design "The Brain" private
    private double salinity;
    private double[] waste; // #12 array
    private boolean cycled; // #3 boolean
    private ArrayList<Double> happiness; // #13 Wrapper class


    // #9 Class Design "Big 3" constructors
    // #9 Class Design "The Brain" public
    public SaltwaterAquarium() {

        super(78, 8.1, 0.0, defaultDimensions, defaultOrganisms, 0, 2);

        salinity = 1.026;
        double tempPH = calcpH();
        setpH(tempPH);

        waste = new double[3];
        waste[0] = 0; // ammonia
        waste[1] = 0; // nitrite
        waste[2] = 0; // nitrate
        happiness = new ArrayList<>();

    }

    public SaltwaterAquarium(double salinity, double temperature, double pH, double lighting, double[] dimensions, ArrayList<Organism> organisms) {

        super(temperature, pH, lighting, dimensions, organisms, 0, 2);

        this.salinity = salinity;
        double tempPH = calcpH();
        setpH(tempPH);

        waste = new double[3];
        waste[0] = 0; // ammonia
        waste[1] = 0; // nitrite
        waste[2] = 0; // nitrate

        happiness = new ArrayList<>();

        for(Organism organism : organisms) {
            if(organism.isIll()) {
                happiness.add(0.0);
            } else {
                double randomHappy = Math.random()*1/4 + 0.75;
                happiness.add(randomHappy);
            }
        }

    }


    // #9 Class Design "Dynamic Duo" getters + setters
    // #10 methods: getters
    public double[] getWasteLevel() { return waste; }
    public double getSalinity() { return salinity; }
    public boolean ifCycled() { return cycled; }


    // #10 methods: setters
    public void changeSalinity(int newSalinity) { salinity = newSalinity; }
    public void cycleTank(double time) {
        if(time > 8) { // #1 - relational operator
            cycled = true;
        } else {
            cycled = false;
        }
    }
    public void waterChange() { waste[2] = 0; }


    // #10 methods: other methods
    public double calcpH() {
        double ph = 0.0;
        // #2 - if-then-else
        if(salinity > 1.023 && salinity < 1.027) { // #1 - relational operator + logical operator
            ph = 8.1;
        } else if(salinity < 1.023 && salinity > 0) { // #1 - relational operator + logical operator
            ph = 7.5;
        } else {
            ph = 8.7;
        }
        return ph;
    }
    public int maxAggression() {
        int maxAggression = 0;
        ArrayList<Organism> org = getLivestock();
        for(Organism organism : org) { // #14 for-each loop to find maximum of ArrayList
            maxAggression = Math.max(maxAggression, organism.getAggression());
        }
        return maxAggression;
    }
    public boolean getCompatibility() {
        if(numberOfFish > (int) volume) { // #8 casting
            return false;
        } else {
            int totalAggression = 0;
            for(Organism organism : organisms) { // #4 for-each loop
                if(organism.getType().equals("fish")) {
                    totalAggression += organism.getAggression();
                }
            }
            if(totalAggression > (int) volume) {
                return false;
            }
        }
        return true;
    }


    // #10 overloaded method
    public void changeWaste(Organism organism) {
        String type = organism.getType();
        switch(type) { // #5 switch statement
            case "fish":
                waste[0] += 0.8/calcVolume();
            case "coral":
                waste[0] += 0.1/calcVolume();
            case "invert":
                waste[0] += 0.2/calcVolume();
            default:
                waste[0] += 0.1/calcVolume();
        }
    }
    public void changeWaste(double[] addedWaste) {
        int i = 0;
        while(i < 3) { // #4 while loop
            waste[i] += addedWaste[i];
            i++;
        }
    }
    public void convertWaste() {
        // #2 if-then-else + throw error
        if(ifCycled()) {
            int i = 0;
            do { // #4 do-while loop
                waste[i+1] += waste[i];
                waste[i] = 0;
                i++;
            } while(i < 2);
        } else {
            throw new IllegalArgumentException("Tank has not been cycled yet");
        }
    }


    // #10 overloaded method
    public void cycle(int time) {
        for(int i = 0; i < time; i++) { // #4 for loop
            int exponential = (int)(Math.random()*2+2); // #6 Math.random
            setBacteriaColony((int)(Math.pow(bacteriaColony, exponential))); // #6 Math.pow
        }
        if(getBacteriaColony() > 48) {
            cycled = true;
        } else {
            cycled = false;
        }
    }
    public void cycle(double addedBacteria) {
        setBacteriaColony(getBacteriaColony() + (int)(addedBacteria));
        if(getBacteriaColony() > 48) {
            cycled = true;
        } else {
            cycled = false;
        }
    }


    public void swap(int a, int b) {
        double temp = happiness.get(a);
        happiness.set(a, happiness.get(b));
        happiness.set(b, temp);
    }
    public int partition(int l, int h) {
        double piv = happiness.get(h);
        int i = l-1;
        for(int j = l; j < h; j++) {
            if(happiness.get(j) <= piv) {
                i++;
                swap(i, j);
            }
        }

        swap(i+1, h);
        return i+1;
    }
    public void sortHappiness(int l, int h) {
        if(l < h) {
            int ind = partition(l, h);

            sortHappiness(l, ind-1);
            sortHappiness(ind+1, h);
        }
    }
    public double leastHappy() {
        sortHappiness(0, happiness.size()-1);
        return happiness.get(0);
    }
    public double mostHappy() {
        sortHappiness(0, happiness.size()-1);
        return happiness.get(happiness.size()-1);
    }


    public String tangs() {
        // #18 Nested for loop
        String output = "Tangs in tank: ";
        for(Organism organism : organisms) { // #4 for each loop
            if(organism.getType().equals("fish")) { // #7 String method .equals
                for(int i = 0; i < organism.getSpecies().length(); i++) {
                    String species = organism.getSpecies();
                    if(species.charAt(i) == 't') { // #7 String method .charAt
                        if(species.substring(i, i+5).equals("tang")) { // #7 String method .substring
                            output += species + ", ";
                        }
                    }
                }
            }
        }
        return output;
    }


    // #9 Class Design "Big 3" toString()
    public String toString() {
        String output = new String();
        convertWaste();
        output += "Your fish tank is thriving!\n";
        output += "+---------------------------------+\n";
        output += "|        __                       |\n";
        output += "|     |\\/ .\\               __     |\n";
        output += "|     |/\\__/              /. \\/|  |\n";
        output += "|               _______   \\__/\\|  |\n";
        output += "|               |      \\__        |\n";
        output += "|     ___       |         |       |\n";
        output += "|    /   |      |         |       |\n";
        output += "|  _/    |      /         |       |\n";
        output += "| /      |     /          \\       |\n";
        output += "+---------------------------------+\n";
        output += "Current waste level: " + waste[0] + ", " + waste[1] + ", " + waste[2] + "\n";
        output += tangs() + "\n";
        output += "pH, salinity, and temp: " + pH + ", " + salinity + ", " + temperature + "\n";
        output += "lighting and size of tank: " + lighting + ", " + getSize();
        for(Organism organism : organisms) {
            changeWaste(organism);
        }
        return output;
    }

}
