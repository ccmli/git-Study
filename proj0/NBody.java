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

}