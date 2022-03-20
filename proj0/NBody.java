public class NBody {

    public static double readRadius(String FileName) {
        In in = new In(FileName);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String FileName) {
        In in = new In(FileName);
        int num= in.readInt();
        in.readDouble();
        Planet[] planets = new Planet[num];
        for (int i = 0; i < num; i++) {
            double xxPos = in.readDouble();
            double yyPos = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgFileName = in.readString();
            planets[i] = new Planet(xxPos, yyPos, xxVel, yyVel, mass, imgFileName);
        }
        return planets;
    }

    public static void main(String[] args) {
        //Collecting All Needed Input
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        double radius = readRadius(filename);
        Planet[] planets = readPlanets(filename);

        //Drawing the Background
        StdDraw.setScale(-radius,radius);
        StdDraw.clear();
        StdDraw.picture(0,0,"./images/starfield.jpg");
        StdDraw.show();

        //Drawing All of the Planets
        for (Planet planet : planets) {
            planet.draw();
        }

        //call enableDoubleBuffering
        StdDraw.enableDoubleBuffering();

        //Create a time variable and set it to 0. Set up a loop to loop until this time variable is T
        for (double time = 0; time <= T; time += dt) {
            //Create an xForces array and yForces array
            double[] xForces = new double[planets.length];
            double[] yForces = new double[planets.length];
            /*
                Calculate the net x and y forces for each planet,
                storing these in the xForces and yForces arrays respectively
             */
            for (int i = 0; i < planets.length; i++) {
                xForces[i] = planets[i].calcNetForceExertedByX(planets);
                yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            /*
                Call update on each of the planets.
                This will update each planet’s position, velocity, and acceleration
             */
            for (int i = 0; i < planets.length; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            //Draw the background image
            StdDraw.picture(0,0,"./images/starfield.jpg");

            //Draw all of the planets.
            for (Planet planet : planets) {
                planet.draw();
            }
            StdDraw.show();
            StdDraw.pause(10);
        }

        //Printing the Universe
        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", radius);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel,
                    planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}
