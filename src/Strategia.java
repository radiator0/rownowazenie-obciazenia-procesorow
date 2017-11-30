import java.util.List;

/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-06-02
 */
public interface Strategia {
    void dodajProcesor(Procesor p);

    void dodajProces(Proces p);

    void krok();

    void wyswietl();

    List<Procesor> getProcesory();

    void zapiszObciazenia();

    int getSrednieObciazenie();

    String getEtykieta();

    int getAktualneSrednieObciazenie();

    int getSrednieOdchylenie();

    int getAktualneSrednieOdchylenie();

    int getIloscZapytan();

    int getIloscMigracji();
}
