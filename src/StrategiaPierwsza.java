import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-06-02
 */
public class StrategiaPierwsza implements Strategia{
    String etykieta = "S t r a t e g i a   p i e r w s z a";
    List<Procesor> procesory = new ArrayList<>();
    static int PROG = 63; // prog obciazenia procesora
    static int LICZBA_PROB = 16; // liczba prob

    List<ArrayList<Integer>> obciazenia = new ArrayList<>();
    int srednieObciazenie = 0;

    int iloscZapytan = 0;
    int iloscMigracji = 0;

    public void dodajProcesor(Procesor p){
        procesory.add(p);
    }


    public void dodajProces(Proces p){
        p = new Proces(p);
        Random r = new Random();
        int id_x = r.nextInt(procesory.size());
        Procesor x = procesory.get(id_x);
        for(int i=0; i<LICZBA_PROB; i++){
            int id; while((id = r.nextInt(procesory.size())) == id_x);
            iloscZapytan++;
            if(procesory.get(id).getObciazenie()<PROG){
                iloscMigracji++;
                procesory.get(id).dodajProces(p);
                return;
            }
        }
        x.dodajProces(p);
    }

    public List<Procesor> getProcesory(){
        return procesory;
    }

    public void krok(){
        for(Procesor p : procesory) p.krok();
        obliczSrednieObciazenie();
    }

    public void wyswietl(){
        for(Procesor p : procesory){
            System.out.print(String.format("%4d", p.getObciazenie()));
        }
        System.out.println();
    }

    public void obliczSrednieObciazenie(){

        if(obciazenia.size()>0) {
            int suma = 0;
            int ilosc = 0;
            for (ArrayList<Integer> list : obciazenia){
                for(Integer i : list){
                    suma += i;
                    ilosc++;
                }
            }
            srednieObciazenie = suma / ilosc;
        }
    }

    public int getAktualneSrednieObciazenie(){
        if(procesory.size()>0) {
            int suma = 0;

            for(Procesor p : procesory) suma+=p.getObciazenie();

            return suma / procesory.size();
        }
        return 0;
    }

    public int getAktualneSrednieOdchylenie(){
        if(procesory.size()>0) {

            int srednia = 0;
            for (Procesor p : procesory) {
                srednia += p.getObciazenie();
            }
            srednia = srednia / procesory.size();

            int suma = 0;
            for (Procesor p : procesory) {
                suma += Math.abs(srednia - p.getObciazenie());
            }

            return suma / procesory.size();
        }
        return 0;
    }

    public int getSrednieObciazenie(){
        return srednieObciazenie;
    }

    public String getEtykieta(){return etykieta; }

    public void zapiszObciazenia(){
        obciazenia.add(new ArrayList<>());
        for(Procesor p : procesory){
            obciazenia.get(obciazenia.size()-1).add((p.getObciazenie()));
        }
    }

    public int getSrednieOdchylenie(){
        if(obciazenia.size()>0) {
            List<Integer> odchylenia = new ArrayList<>();
            for (ArrayList<Integer> list : obciazenia) {
                int srednia = 0;
                for (Integer i : list) {
                    srednia += i;
                }
                srednia = srednia / list.size();

                int suma = 0;
                for (Integer i : list) {
                    suma += Math.abs(srednia - i);
                }
                odchylenia.add(suma / list.size());
            }

            int suma = 0;
            for (Integer i : odchylenia) {
                suma += i;
            }
            return suma / odchylenia.size();
        }
        return 0;
    }

    public int getIloscZapytan(){
        return iloscZapytan;
    }

    public int getIloscMigracji(){
        return iloscMigracji;
    }
}
