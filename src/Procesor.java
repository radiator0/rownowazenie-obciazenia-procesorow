import java.util.ArrayList;
import java.util.List;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-06-02
 */
public class Procesor {
    private static int ID = 0;
    private int id;
    private int obciazenie = 0 ;
    private List<Proces> procesy = new ArrayList<>();

    public Procesor(){
        id = ID++;
    }

    public Procesor(Procesor p){
        this.id = p.id;
    }

    public void obliczObciazenie(){
        obciazenie = 0;
        for(Proces p : procesy){
            obciazenie+= p.getMoc();
        }
    }

    public void krok(){
        if(procesy.size()>0){
            Proces p = procesy.get(0);
            p.odejmijTakt();
            if(p.getTakty() <= 0) {
                procesy.remove(0);
            }
            obliczObciazenie();
        }
    }

    public Proces odbierzProces(){
        Proces p = procesy.get(0);
        procesy.remove(0);
        return p;
    }

    public int getObciazenie(){
        return obciazenie;
    }

    public void dodajProces(Proces p){
        procesy.add(p);
    }
}
