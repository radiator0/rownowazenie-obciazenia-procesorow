import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-06-02
 */
public class Menadzer {
    private List<Strategia> strategie = new ArrayList<>();
    int liczbaKrokow = 0;

    public Menadzer(){
        strategie.add(new StrategiaPierwsza());
        strategie.add(new StrategiaDruga());
        strategie.add(new StrategiaTrzecia());
        generujProcesory(30);
    }

    public void generujProcesory(int ilosc){
        for(int i=0; i<ilosc; i++){
            Procesor p = new Procesor();
            for(Strategia s : strategie){
                s.dodajProcesor(new Procesor(p));
            }
        }
    }

    public Proces generujProces(){
        Random r = new Random();

        // losowanie mocy
        int moc = r.nextInt(3)+1;
        // losowanie liczby taktow
        int takty = r.nextInt(31)+1;

        return new Proces(moc,takty);
    }



    public void krok(){
        Random r = new Random();

        // losowanie czy dodac
        int x = (liczbaKrokow/32) % 100;
        if(100-x>r.nextInt(100) || liczbaKrokow<300) {

            // losowanie ile procesow dodac
            int ilosc = r.nextInt(7);
            for (int i = 0; i < ilosc; i++) {
                Proces p = generujProces();
                for (Strategia s : strategie) {
                    s.dodajProces(p);
                }
            }
        }

        for(Strategia s : strategie){
            s.krok();
        }

        if(liczbaKrokow%25==0){
            for(Strategia s : strategie){
                s.zapiszObciazenia();
            }
        }

        liczbaKrokow++;
        System.out.println(liczbaKrokow);
    }

    public List<Strategia> getStrategie() { return strategie; }

}
