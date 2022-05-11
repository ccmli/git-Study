public class NBody {
    public static double readRadius(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();
        return radius;
    }

    public static Planet[] readPlanets(String filename) {
        In in = new In(filename);
        int num = in.readInt();
        double radius = in.readDouble();

        Planet[] planets = new Planet[num];

        for (int i = 0; i < num; i++){
            double x = in.readDouble();
            double y = in.readDouble();
            double xxVel = in.readDouble();
            double yyVel = in.readDouble();
            double mass = in.readDouble();
            String imgName = in.readString();

            Planet a = new Planet(x,y,xxVel,yyVel,mass,imgName);
            planets[i] = a;
        }
        return planets;
    }

    public static void main(String[] args) {
        double T = Double.parseDouble(args[0]);
        double dt = Double.parseDouble(args[1]);
        String filename = args[2];
        Planet[] planets = NBody.readPlanets(filename);
        double r = NBody.readRadius(filename);


        // set scale
        StdDraw.setScale(-r, r);
        double time = 0;
        int planetsLength = planets.length; //查詢planets長度方法


        while (time <= T) {
            double[] xForces = new double[planetsLength];
            double[] yForces = new double[planetsLength];

            for (int i = 0; i < planetsLength; i++) {
                    xForces[i] = planets[i].calcNetForceExertedByX(planets);
                    yForces[i] = planets[i].calcNetForceExertedByY(planets);
            }

            for (int i = 0; i < planetsLength; i++) {
                planets[i].update(dt, xForces[i], yForces[i]);
            }

            StdDraw.picture(0, 0, "images/starfield.jpg");

            for (Planet i : planets) {
                i.draw();
            }

            StdDraw.show();
            StdDraw.pause(10);
            time += dt;
        }

        StdOut.printf("%d\n", planets.length);
        StdOut.printf("%.2e\n", r);
        for (int i = 0; i < planets.length; i++) {
            StdOut.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n",
                    planets[i].xxPos, planets[i].yyPos, planets[i].xxVel, planets[i].yyVel, planets[i].mass, planets[i].imgFileName);
        }
    }
}