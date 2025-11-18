package seuGrupo;
import robocode.*;
import robocode.util.Utils;
import java.awt.Color;
import java.awt.geom.Point2D;

public class Locking extends RoboBase {
    
    private int direcao = 1; 

    public void loopPrincipal() {
        nomeDoParceiro = "Facada";
        setBodyColor(Color.BLUE); 
        
        setTurnRadarRight(Double.POSITIVE_INFINITY);
        
        while(true) {
        
            if (getRadarTurnRemaining() == 0) {
                setTurnRadarRight(Double.POSITIVE_INFINITY);
            }
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getName().contains(nomeDoParceiro)) return;

        // 1. MIRA (Simples e Rápida)
        double mira = getHeadingRadians() + e.getBearingRadians() - getGunHeadingRadians();
        setTurnGunRightRadians(Utils.normalRelativeAngle(mira));
        atirarComSegurança(e.getDistance(), e.getName());


        if (e.getDistance() < 250) {

            double anguloFuga = e.getBearing() + 180;
            
       
            if (anguloFuga > 180) anguloFuga -= 360;
            
            setTurnRight(anguloFuga);
            setAhead(150 * direcao);
        } else {
     
            setTurnRight(e.getBearing() + 90);
            setAhead(100 * direcao);
        }
        
  
        double anguloRadar = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(Utils.normalRelativeAngle(anguloRadar));
    }
    
   
    public void onHitWall(HitWallEvent e) {
        direcao = -direcao; 
        setAhead(150 * direcao);
    }
    

    public void onHitRobot(HitRobotEvent e) {
        if (e.getName().contains(nomeDoParceiro)) return;
        

        double mira = getHeadingRadians() + e.getBearingRadians() - getGunHeadingRadians();
        setTurnGunRightRadians(Utils.normalRelativeAngle(mira));
        fire(3);
        

        direcao = -direcao;
        setBack(100 * direcao);
    }
}