package Oving5;
import javax.persistence.*;
@Entity

public class konto {
    @Id
    @Column(name="kontonr")
    private int kontoNr;


    //-------Oppg. 4---------
    @Version
    @Column(name="lock_col")
    private int lock=0;


    private double saldo;
    private String navn;

    public konto(){}

    public konto(int kontoNr,double saldo, String navn){
        this.kontoNr=kontoNr;
        this.navn=navn;
        this.saldo=saldo;
    }

    public long getKontoNr() {
        return kontoNr;
    }
    public void setLock(int lock){
        this.lock=lock;
    }

    public double getSaldo() {
        return saldo;
    }
    public void setNavn(String navn){
        this.navn=navn;
    }
    public void setSaldo(double belop){
        this.saldo=belop;
    }

    public int getlock(){
        return lock;
    }


    public String getNavn() {
        return navn;
    }
    public void trekk(double belop){
        saldo-= belop;
    }
    public void leggIn(double belop){
        saldo+=belop;
    }

    @Override
    public String toString(){
        return "KontoNr: "+getKontoNr()+
                "\nSaldo: "+getSaldo()+
                "\nNavn: "+getNavn();
    }

}
