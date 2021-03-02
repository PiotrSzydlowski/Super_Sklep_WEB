package entity;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "sale")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idsale")
    private int idsale;

    @Column(name = "code")
    private String code;

    @Column(name = "datafrom")
    private String datafrom;

    @Column(name = "value")
    private double value;

    @Column(name = "datato")
    private String datato;

    @Column(name = "price")
    private double price;

    public Sale() {
    }

    public Sale(int idsale, String code, String datafrom, double value, String datato, double price) {
        this.idsale = idsale;
        this.code = code;
        this.datafrom = datafrom;
        this.value = value;
        this.datato = datato;
        this.price = price;
    }

    public int getIdsale() {
        return idsale;
    }

    public void setIdsale(int idsale) {
        this.idsale = idsale;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDatafrom() {
        return datafrom;
    }

    public void setDatafrom(String datafrom) {
        this.datafrom = datafrom;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public String getDatato() {
        return datato;
    }

    public void setDatato(String datato) {
        this.datato = datato;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "idsale=" + idsale +
                ", code='" + code + '\'' +
                ", datafrom=" + datafrom +
                ", value=" + value +
                ", datato=" + datato +
                ", price=" + price +
                '}';
    }
}
