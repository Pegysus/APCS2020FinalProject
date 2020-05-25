import java.util.*;
public class FishTank {

    protected double temperature, pH, volume, lighting; // #3 double
    protected int numberOfFish, bacteriaColony; // #3 int
    protected double[] dimensions;
    protected ArrayList<Organism> organisms; // #13 ArrayList

    public FishTank() {
        temperature = 78.0;
        pH = 7.0;
        volume = 0.0;
        lighting = 0.0;

        numberOfFish = 0;
        bacteriaColony = 0;

        dimensions = new double[3];
        for(int i = 0; i < 4; i++) dimensions[i] = 0;

        organisms = new ArrayList<>();

    }

    public FishTank(double temp, double pH, double lighting, double[] dimensions, ArrayList<Organism> organisms, int numberOfFish, int bacteriaColony) {

        temperature = temp;
        this.pH = pH;
        this.volume = volume;
        this.lighting = lighting;

        this.dimensions = new double[3];
        for(int i = 0; i < dimensions.length; i++)
            this.dimensions[i] = dimensions[i];
        volume = calcVolume();

        this.organisms = new ArrayList<Organism>();
        this.organisms = organisms;

        this.numberOfFish = numberOfFish;
        this.bacteriaColony = bacteriaColony;

    }


    // getters
    public double getTemperature() { return temperature; }
    public double getpH() { return pH; }
    public double getSize() { return volume; }
    public double getLighting() { return lighting; }
    public double[] getDimensions() { return dimensions; }
    public ArrayList<Organism> getLivestock() { return organisms; }
    public int getNumberOfFish() { return numberOfFish; }
    public int getBacteriaColony() { return bacteriaColony; }


    // setters
    public void addOrganism(Organism organism) {
        organisms.add(organism);
        if(organism.getType().equals("fish"))
            numberOfFish++;
    }
    public void removeOrganism(Organism organism) {
        if(organism.getType().equals("fish"))
            numberOfFish--;
        organisms.remove(organisms.indexOf(organism));
    }
    public void setBacteriaColony(int change) { bacteriaColony = change; }
    public void changeTemp(int newTemp) { temperature = newTemp; }
    public void setpH(double newpH) { pH = newpH; }


    // other methods
    public double calcVolume() {
        double vol = dimensions[0]*dimensions[1]*dimensions[2]*0.004329;
        return vol;
    }
    public boolean getCompatibility() {
        if(numberOfFish > (int) volume) // #8 casting
            return false;
        return true;
    }

}