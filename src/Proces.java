/**
 * Author: Krzysztof Pilarczyk
 * Date: 2017-06-02
 */
public class Proces {
    private static int ID = 0;
    private int id;
    private int moc;
    private int takty;

    public Proces(int moc, int takty){
        id = ID++;
        this.moc = moc;
        this.takty = takty;
    }

    public Proces(Proces p){
        this.id = p.id;
        this.moc = p.moc;
        this.takty = p.takty;
    }

    public int getMoc() {
        return moc;
    }

    public void odejmijTakt(){
        takty--;
    }

    public int getTakty() {
        return takty;
    }
}
