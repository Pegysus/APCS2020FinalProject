import java.util.*;
public class AquariumDriver {

    public static void main(String[] args) {
        // #17 JScanner
        Scanner input = new Scanner(System.in);

        System.out.print("Size of tank (length, width, height): ");
        double dimensions[] = new double[3];
        dimensions[0] = input.nextInt();
        dimensions[1] = input.nextInt();
        dimensions[2] = input.nextInt();
        double salinity = 1.026;
        double pH = 8.1;
        double lighting = 5543.2;
        double temperature = 78;
        System.out.println("Input the organisms you want in your tank: ");
        int counter = 1;
        boolean check = true;
        ArrayList<Organism> organisms = new ArrayList<Organism>();
        while(check) {
            System.out.println("Organism #" + counter + ": ");
            String placeholder = input.nextLine();
            System.out.print("type: ");
            String type = input.nextLine();
            System.out.print("species: ");
            String species = input.nextLine();
            System.out.print("age: ");
            int age = input.nextInt();
            System.out.print("aggression: ");
            int aggression = input.nextInt();
            System.out.print("is it ill? ");
            boolean ill = input.nextBoolean();
            System.out.print("temperature requirement: ");
            double tempReq = input.nextDouble();
            System.out.print("salinity requirement: ");
            double salinityReq = input.nextDouble();
            System.out.println("Anymore organisms? (Y/N)");
            placeholder = input.nextLine();
            String ans = input.nextLine();
            if(ans.equals("N")) {
                check = false;
            }
            counter++;
            if(type.equals("fish")) organisms.add(new Fish(species, age, aggression, ill, tempReq, salinityReq)); // #23 Polymorphism
            else if(type.equals("coral")) organisms.add(new Coral(species, age, aggression, ill, tempReq, salinityReq));
            else if(type.equals("invert")) organisms.add(new Invert(species, age, aggression, ill, tempReq, salinityReq));
            else System.out.println("Not a sustainable organism");

        }

        // #11 Class Composition
        SaltwaterAquarium aquarium = new SaltwaterAquarium(salinity, temperature, pH, lighting, dimensions, organisms);
        aquarium.cycleTank(10);
        System.out.println(aquarium);
    }

}
