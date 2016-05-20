package asda;
import robocode.*;
import java.util.*;
import java.awt.Color;
import robocode.util.Utils;

// API help : http://robocode.sourceforge.net/docs/robocode/robocode/Robot.html

/**
 * AlphaBotCorndog - a robot by (your name here)
 */
public class AlphaBotCorndog extends AdvancedRobot {
    /**
     */
    public int oppEnergy = 100; //this is the starting energy
    public void run() {
        
        setColors(Color.red,Color.black,Color.white); // body,gun,radar
        
        while(true) {
            randMove();
            
            scan();
            turnRadarRight(360);
        }
    }

    /**
     * onScannedRobot: What to do when you see another robot
     */
    
    public void onScannedRobot(ScannedRobotEvent e) {
        if (getGunHeat()>0){
            randMove();
            }
        double absBearing = getHeadingRadians() + e.getBearingRadians();
        double radarTurn = absBearing - getRadarHeadingRadians();
        setTurnRadarRightRadians(2 * Utils.normalRelativeAngle(radarTurn));
        setTurnGunRight(getHeading() - getGunHeading() + e.getBearing());
        double radarLowerBound = getRadarHeadingRadians() - (Math.PI / 6);
        double radarUpperBound = getRadarHeadingRadians() + (Math.PI / 6);
        if (getGunHeadingRadians() < radarUpperBound && getGunHeadingRadians() > radarLowerBound) {
            setFire(Math.min(400 / e.getDistance(), 3));
        }   
        
    }


    public void onHitByBullet(HitByBulletEvent e) {
        back(10);
    }
    
    /**
     * onHitWall: What to do when you hit a wall
     */
    
    public void onHitWall(HitWallEvent e) {
        back(100);
        turnRight(90);
    }   
    
    public void randMove(){
               Random r = new Random();
        int rBearing = r.nextInt(90);
        Random t = new Random();
        int rDistance = t.nextInt(100);
        Random s = new Random();
        int bool = s.nextInt(1);
        if (bool==0){
        rBearing *= -1;
        }
        setTurnRight(rBearing);
        setAhead(rDistance);
        execute();
    }
}