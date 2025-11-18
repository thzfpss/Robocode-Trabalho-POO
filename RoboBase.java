package seuGrupo;
import robocode.*;
import java.awt.Color;
import robocode.util.Utils;

public abstract class RoboBase extends AdvancedRobot {
    
    public String nomeDoParceiro = "";

    public void run() {
        setBodyColor(new Color(0, 0, 0)); 
        setGunColor(new Color(0, 255, 0)); 
        setRadarColor(Color.WHITE);
        setBulletColor(Color.YELLOW);
        

        setAdjustGunForRobotTurn(true);
        setAdjustRadarForGunTurn(true);
        setAdjustRadarForRobotTurn(true);
        
        loopPrincipal(); 
    }

    public abstract void loopPrincipal();


    public void atirarComSegurança(double distancia, String nomeAlvo) {
        if (nomeAlvo.contains(nomeDoParceiro)) return; 

        double forca = (distancia > 200) ? 1 : 3;
        
      
        if (getGunHeat() == 0) {
            fire(forca);
        }
    }
    

    public void travarRadar(ScannedRobotEvent e) {
        double anguloRadar = getHeadingRadians() + e.getBearingRadians() - getRadarHeadingRadians();
        setTurnRadarRightRadians(Utils.normalRelativeAngle(anguloRadar));
    }
}