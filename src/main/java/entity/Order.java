package entity;

import javax.persistence.*;

@Entity(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "orderid")
    private int orderid;

    @Column(name = "userid")
    private int userid;

    @Column(name = "productid")
    private int productid;

    @Column(name = "totalprice")
    private double totalprice;

    @Column(name = "orderstatus")
    private String orderstatus;

    @Column(name = "ordernumber")
    private int ordernumber;

    @Column(name = "quantity")
    private int quantity;

    @Column(name = "coupon")
    private int coupon;


    public Order() {
    }

    public Order(int userid, int productid, double totalprice, String orderstatus, int ordernumber, int quantity, int coupon) {
        this.userid = userid;
        this.productid = productid;
        this.totalprice = totalprice;
        this.orderstatus = orderstatus;
        this.ordernumber = ordernumber;
        this.quantity = quantity;
        this.coupon = coupon;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public double getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(double totalprice) {
        this.totalprice = totalprice;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public int getOrdernumber() {
        return ordernumber;
    }

    public void setOrdernumber(int ordernumber) {
        this.ordernumber = ordernumber;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCoupon() {
        return coupon;
    }

    public void setCoupon(int coupon) {
        this.coupon = coupon;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderid=" + orderid +
                ", userid=" + userid +
                ", productid=" + productid +
                ", totalprice='" + totalprice + '\'' +
                ", orderstatus='" + orderstatus + '\'' +
                ", ordernumber=" + ordernumber +
                ", quantity=" + quantity +
                '}';
    }
}
