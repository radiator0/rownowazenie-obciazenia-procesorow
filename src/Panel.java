/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-06-03
 */
import java.awt.*;
import javax.swing.JPanel;

public class Panel extends JPanel {
    private static final long serialVersionUID = -8378771651986102384L;
    private Menadzer m;
    private Graphics2D g2d;

    public Panel(Menadzer m) {
        this.m = m;
        setPreferredSize(new Dimension(1360, 600));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        this.setBackground(Color.gray);
        g2d.setColor(Color.white);
        g2d.drawString("Copyright " + "\u00a9" + " 2017 Krzysztof Pilarczyk. All rights reserved.", 20, 580);

        int y = 50;
        for(Strategia s : m.getStrategie()){
            g2d.setFont(new Font("Garamond", Font.TRUETYPE_FONT, 20));
            g2d.setColor(Color.white);
            g2d.drawString(s.getEtykieta().toUpperCase() , 400, y-10);
            g2d.setFont(new Font("Garamond", Font.TRUETYPE_FONT, 14));

            rysujStatystyki("Ś R E D N I E", "", 0, y-15);
            rysujStatystyki("Obciazenie", String.format("%4d%1s", s.getSrednieObciazenie(), "%"), 0, y);
            rysujStatystyki("Odchylenie", String.format("%4d%1s", s.getSrednieOdchylenie(), "%"), 1, y);
            rysujStatystyki("A K T U A L N E", "", 2, y+7);
            rysujStatystyki("Obciazenie", String.format("%4d%1s", s.getAktualneSrednieObciazenie(), "%"), 3, y+7);
            rysujStatystyki("Odchylenie", String.format("%4d%1s", s.getAktualneSrednieOdchylenie(), "%"), 4, y+7);
            rysujStatystyki("Zapytania", String.format("%8d", s.getIloscZapytan()), 5, y+7);
            rysujStatystyki("Migracje ", String.format("%8d", s.getIloscMigracji()), 6, y+7);

            int x = 20;
            for(Procesor p : s.getProcesory()){
                rysujWykres(x, y, p.getObciazenie());
                x+=40;
            }
            y+= 180;
        }

    }

    private void rysujStatystyki(String klucz, String wartosc, int nr, int y){
        int x = 1225;
        y += nr*16 + 30;
        g2d.drawString(klucz+wartosc, x,y);
    }

    private void rysujWykres(int x, int y, int obciazenie) {

        int liczbaKresek = 20;
        for(int i = 0; i<liczbaKresek; i++) {
            if (100 - obciazenie <= i * (100 / liczbaKresek)) {
                if(obciazenie>=55 && obciazenie<80){
                    g2d.setColor(Color.orange);
                }else if(obciazenie>=80){
                    g2d.setColor(Color.red);
                }else{
                    g2d.setColor(Color.green);
                }
            } else {
                g2d.setColor(Color.darkGray);
            }
            g2d.fillRoundRect(x, y + i * 7, 35, 5, 5, 5);
        }
    }
}
