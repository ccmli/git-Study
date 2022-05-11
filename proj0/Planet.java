public class Planet {
    // Non-Static Variable
    public double xxPos; // x-position
    public double yyPos; // y-position
    public double xxVel; // velocity in x-direction
    public double yyVel; // velocity in y-direction
    public double mass;
    public String imgFileName;

    // Constructor
    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    // Constructor(copy an identical Planet)
    public Planet(Planet p) {
        this(p.xxPos,p.yyPos,p.xxVel, p.yyVel,p.mass,p.imgFileName);
    }

    public double calcDistance(Planet p) {
        double dx = p.xxPos - this.xxPos;
        double dy = p.yyPos - this.yyPos;

        double dxPower2 = dx * dx;
        double dyPower2 = dy * dy;
        double rPower2 = dxPower2+dyPower2;
        double r = Math.sqrt(rPower2);

        return r;
    }

    // method planetA.calcForceExertedByX(planetB) return F1x(double)
    public double calcForceExertedBy(Planet p) {
        double G = 6.67e-11;
        double R = this.calcDistance(p);
        double TotalForce = (G * this.mass * p.mass / (R * R));
        return TotalForce;
    }

    public double calcForceExertedByX(Planet p) {
        double F = this.calcForceExertedBy(p);
        double R = this.calcDistance(p);
        double Fx = F * (p.xxPos - this.xxPos) / R;
        return Fx;
    }

    public double calcForceExertedByY(Planet p) {
        double F = this.calcForceExertedBy(p);
        double R = this.calcDistance(p);
        double Fy = F * (p.yyPos - this.yyPos) / R;
        return Fy;
    }

    public double calcNetForceExertedByX(Planet[] allPlanets) {
        double netFx = 0;
        for (Planet i : allPlanets) {
            if (!this.equals(i)){
                netFx += this.calcForceExertedByX(i);
            }
        }
        return netFx;
    }

    public double calcNetForceExertedByY(Planet[] allPlanets) {
        double netFy = 0;
        for (Planet i : allPlanets) {
            if (!this.equals(i)){
                netFy += this.calcForceExertedByY(i);
            }
        }
        return netFy;
    }

    public void update(double dt, double fx, double fy) {
        double Ax = fx / this.mass;
        double Ay = fy / this.mass;
        this.xxVel += dt * Ax;
        this.yyVel += dt * Ay;
        this.xxPos += dt * this.xxVel;
        this.yyPos += dt * this.yyVel;
    }

    public void draw() {
        StdDraw.picture(this.xxPos, this.yyVel, "images/" + this.imgFileName);
    }
}