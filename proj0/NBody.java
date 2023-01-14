/**
 * Created by AlexMan
 */
public class NBody {

    public static double readRadius(String fileName){
        In in = new In(fileName);
        int numberOfPlanets = in.readInt();
        return in.readDouble();
    }

    public static Planet[] readPlanets(String fileName){
        In in = new In(fileName);
        int numberOfPlanets = in.readInt();
        Planet[] allPlanets = new Planet[numberOfPlanets];
        double radius = in.readDouble();
        int count = 0;
        while(!in.isEmpty()){
            if(count == numberOfPlanets){
                break;
            }
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgPath = in.readString();
            allPlanets[count] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgPath);
            count ++;
        }
        return allPlanets;
    }

    public static void main(String[] args) {
        /* Collecting All Needed Input */
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] allPlanets = readPlanets(filename);
        int planetsNumber = allPlanets.length;

        /* Drawing the Background */
        double x = 0.0;
        double y = 0.0;
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(x, y, "images/starfield.jpg");

        /* Drawing All of the Planets */
        for(Planet planet: allPlanets){
            planet.draw();
        }
        StdDraw.pause(1000);

        /* Creating Animation */
        StdDraw.enableDoubleBuffering();
        double timestamp = 0;
        while(timestamp < T){
            double[] xForces = new double[planetsNumber];
            double[] yForces = new double[planetsNumber];
            for(int i = 0; i < planetsNumber; i++){
                double netForceX = allPlanets[i].calcNetForceExertedByX(allPlanets);
                double netForceY = allPlanets[i].calcNetForceExertedByY(allPlanets);
                xForces[i] = netForceX;
                yForces[i] = netForceY;
            }
            for(int i = 0; i < planetsNumber; i++){
                allPlanets[i].update(dt, xForces[i], yForces[i]);
            }
            StdDraw.picture(x, y, "images/starfield.jpg");
            for(Planet planet: allPlanets){
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
            timestamp += dt;
        }

        /* Print the final result */
        StdOut.printf("%d\n", planetsNumber);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planetsNumber; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    allPlanets[i].xxPos, allPlanets[i].yyPos, allPlanets[i].xxVel,
                    allPlanets[i].yyVel, allPlanets[i].mass, allPlanets[i].imgFileName);
        }

    }
}
