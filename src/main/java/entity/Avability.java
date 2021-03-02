package entity;

import javax.persistence.*;

@Entity
@Table(name = "avability")
public class Avability {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAvability")
    private int idAvability;

    @Column(name = "avabilityname")
    private String avabilityname;

    @Column(name = "avabilityvalue")
    private int avabilityvalue;

    public Avability() {
    }

    public Avability(int idAvability, String avabilityname, int avabilityvalue) {
        this.idAvability = idAvability;
        this.avabilityname = avabilityname;
        this.avabilityvalue = avabilityvalue;
    }

    public int getIdAvability() {
        return idAvability;
    }

    public void setIdAvability(int idAvability) {
        this.idAvability = idAvability;
    }

    public String getAvabilityname() {
        return avabilityname;
    }

    public void setAvabilityname(String avabilityname) {
        this.avabilityname = avabilityname;
    }

    public int getAvabilityvalue() {
        return avabilityvalue;
    }

    public void setAvabilityvalue(int avabilityvalue) {
        this.avabilityvalue = avabilityvalue;
    }

    @Override
    public String toString() {
        return "Avability{" +
                "idAvability=" + idAvability +
                ", avabilityname='" + avabilityname + '\'' +
                ", avabilityvalue=" + avabilityvalue +
                '}';
    }
}
