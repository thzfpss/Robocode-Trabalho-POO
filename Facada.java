package seuGrupo;
import robocode.*;
import robocode.util.Utils;
import java.awt.Color;

public class Facada extends RoboBase {
    
    public void loopPrincipal() {
        nomeDoParceiro = "Locking"; 
        setBodyColor(Color.RED); 
        
      
        setTurnRadarRight(Double.POSITIVE_INFINITY);
        execute();
        
        while(true) {
         
            if (getRadarTurnRemaining() == 0) {
                setTurnRadarRight(Double.POSITIVE_INFINITY);
            }
            execute();
        }
    }

    public void onScannedRobot(ScannedRobotEvent e) {
        if (e.getName().contains(nomeDoParceiro)) return;

       
        double anguloAbsoluto = getHeadingRadians() + e.getBearingRadians();
        double velocidadeInimigo = e.getVelocity();
        double headingInimigo = e.getHeadingRadians();
        double latencia = e.getDistance() / 14; 

        double futuroX = Math.sin(headingInimigo) * velocidadeInimigo * latencia;
        double futuroY = Math.cos(headingInimigo) * velocidadeInimigo * latencia;
        
        double anguloTiro = Utils.normalRelativeAngle(anguloAbsoluto - getGunHeadingRadians() + (futuroX / e.getDistance()));

        setTurnGunRightRadians(anguloTiro);
        atirarComSegurança(e.getDistance(), e.getName());


        setTurnRightRadians(Utils.normalRelativeAngle(e.getBearingRadians() + Math.PI/2));
        
 
        if (e.getDistance() > 150) {
            setAhead(100);
        } else {
            setBack(100);
        }
        

        travarRadar(e);
    }
    
    public void onHitWall(HitWallEvent e) {
    
        setBack(100);
        setTurnRight(90);
    }
}