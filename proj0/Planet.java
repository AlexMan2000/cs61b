/**
 * Created by AlexMan
 */
public class Planet {
    public double xxPos;
    public double yyPos;
    public double xxVel;
    public double yyVel;
    public double mass;
    public String imgFileName;

    // Gravitational Constant, use
    public static final double G = 6.67e-11;

    public Planet(double xP, double yP, double xV,
                  double yV, double m, String img){
        this.xxPos = xP;
        this.yyPos = yP;
        this.xxVel = xV;
        this.yyVel = yV;
        this.mass = m;
        this.imgFileName = img;
    }

    public Planet(Planet p){
        this(p.xxPos, p.yyPos, p.xxVel, p.yyVel, p.mass, p.imgFileName);
    }

    /**
     * Calculate the distance between two planets.
     * @param other
     * @return
     */
    public double calcDistance(Planet other){
        return Math.pow(Math.pow(this.xxPos - other.xxPos, 2)
                + Math.pow( this.yyPos - other.yyPos, 2) , 0.5);
    }

    /**
     * Calculate the force exerted by other planet
     * @param other
     * @return
     */
    public double calcForceExertedBy(Planet other){
        double numerator = G * this.mass * other.mass;
        double denominator = Math.pow(calcDistance(other), 2);
//        return numerator * Math.pow(denominator, -1);
        return numerator / denominator;
    }


    /**
     * Compute the total force exerted in the X directions by the given planet
     * @param other
     * @return
     */
    public double calcForceExertedByX(Planet other){
        double totalForce = calcForceExertedBy(other);
        return totalForce * (other.xxPos - this.xxPos) / calcDistance(other);
    }


    /**
     * Compute the total force exerted in the Y directions by the given planet
     * @param other
     * @return
     */
    public double calcForceExertedByY(Planet other){
        double totalForce = calcForceExertedBy(other);
        return totalForce * (other.yyPos - this.yyPos) / calcDistance(other);
    }


    /**
     * Calculate the net X force exerted by all planets in that array upon the current Planet
     * @param allPlanets an array of Planets
     * @return
     */
    public double calcNetForceExertedByX(Planet[] allPlanets){
        double netForceX = 0.0;
        for(Planet planet: allPlanets){
            if(!planet.equals(this)){
                netForceX += calcForceExertedByX(planet);
            }
        }
        return netForceX;
    }


    /**
     * Calculate the net Y force exerted by all planets in that array upon the current Planet
     * @param allPlanets an array of Planets
     * @return
     */
    public double calcNetForceExertedByY(Planet[] allPlanets){
        double netForceY = 0.0;
        for(Planet planet: allPlanets){
            if(!planet.equals(this)){
                netForceY += calcForceExertedByY(planet);
            }
        }
        return netForceY;
    }

    /**
     * Calculate how much the forces exerted on the planet will cause that planet to accelerate,
     * and the resulting change in the planet’s velocity and position in a small period of time dt
     * @param dt a fixed amount time during which the force X and force Y are exerted
     * @param fX force in the X direction
     * @param fY force in the Y direction
     */
    public void update(double dt, double fX, double fY){
        double aNetX = fX / this.mass;
        double aNetY = fY / this.mass;
        xxVel += dt * aNetX;
        yyVel += dt * aNetY;
        xxPos += dt * xxVel;
        yyPos += dt * yyVel;
    }

    /**
     * Uses the StdDraw API mentioned above to
     * draw the Planet’s image at the Planet’s position
     */
    public void draw(){
        StdDraw.picture(xxPos, yyPos, "images/"+imgFileName);
    }
}
