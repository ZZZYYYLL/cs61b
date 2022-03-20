public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;
    public static double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img) {
        xxPos = xP;
        yyPos = yP;
        xxVel = xV;
        yyVel = yV;
        mass = m;
        imgFileName = img;
    }

    public Planet(Planet p) {
        xxPos = p.xxPos;
        yyPos = p.yyPos;
        xxVel = p.xxVel;
        yyVel = p.yyVel;
        mass = p.mass;
        imgFileName = p.imgFileName;
    }

    public double calcDistance(Planet p) {
        double x = this.xxPos - p.xxPos;
        double y = this.yyPos - p.yyPos;
        double distance = Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
        return distance;
    }

    public double calcForceExertedBy(Planet p) {
        double force = (G * this.mass * p.mass) / (calcDistance(p) * calcDistance(p));
        return force;
    }

    public double calcForceExertedByX(Planet p) {
        double dx = p.xxPos - this.xxPos;
        return calcForceExertedBy(p) * (dx / calcDistance(p));
    }

    public double calcForceExertedByY(Planet p) {
        double dy = p.yyPos - this.yyPos;
        return calcForceExertedBy(p) * (dy / calcDistance(p));
    }

    public double calcNetForceExertedByX(Planet[] planets) {
        double FxNet = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                FxNet = calcForceExertedByX(p);
            }
        }
        return FxNet;
    }

    public double calcNetForceExertedByY(Planet[] planets) {
        double FyNet = 0;
        for (Planet p : planets) {
            if (!this.equals(p)) {
                FyNet = calcForceExertedByY(p);
            }
        }
        return FyNet;
    }

    public void update(double dt, double fx, double fy) {
        double ax = fx / mass;
        double ay = fy / mass;
        double xxNewVel = xxVel + ax * dt;
        double yyNewVel = yyVel + ay * dt;
        double xxNewPos = xxPos + xxNewVel * dt;
        double yyNewPos = yyPos + yyNewVel * dt;
        xxVel = xxNewVel;
        yyVel = yyNewVel;
        xxPos = xxNewPos;
        yyPos = yyNewPos;
    }

    public void draw() {
        StdDraw.picture(xxPos, yyPos, "./images" + imgFileName);
    }

}
