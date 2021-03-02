package entity;

import lombok.Data;

import javax.persistence.*;
@Data
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    @Column(name = "productid")
    private int productid;

    @Column(name = "producttitle")
    private String producttitle;

    @Column(name = "productdescrption")
    private String productdescrption;

    @Column(name = "productphoto")
    private String productphoto;

    @Column(name = "productprice")
    private double productprice;

    @Column(name = "productdiscount")
    private double productdiscount;

    @Column(name = "productquantity")
    private int productquantity;

    @ManyToOne
    private Category category;

    public Product() {
    }

    public Product(String producttitle, String productdescrption, String productphoto, double productprice,
                   double productdiscount, int productquantity, Category category) {
        this.producttitle = producttitle;
        this.productdescrption = productdescrption;
        this.productphoto = productphoto;
        this.productprice = productprice;
        this.productdiscount = productdiscount;
        this.productquantity = productquantity;
        this.category = category;
    }

    public int getProductid() {
        return productid;
    }

    public void setProductid(int productid) {
        this.productid = productid;
    }

    public String getProducttitle() {
        return producttitle;
    }

    public void setProducttitle(String producttitle) {
        this.producttitle = producttitle;
    }

    public String getProductdescrption() {
        return productdescrption;
    }

    public void setProductdescrption(String productdescrption) {
        this.productdescrption = productdescrption;
    }

    public String getProductphoto() {
        return productphoto;
    }

    public void setProductphoto(String productphoto) {
        this.productphoto = productphoto;
    }

    public double getProductprice() {
        return productprice;
    }

    public void setProductprice(double productprice) {
        this.productprice = productprice;
    }

    public double getProductdiscount() {
        return productdiscount;
    }

    public void setProductdiscount(double productdiscount) {
        this.productdiscount = productdiscount;
    }

    public int getProductquantity() {
        return productquantity;
    }

    public void setProductquantity(int productquantity) {
        this.productquantity = productquantity;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "productid=" + productid +
                ", producttitle='" + producttitle + '\'' +
                ", productdescrption='" + productdescrption + '\'' +
                ", productphoto='" + productphoto + '\'' +
                ", productprice=" + productprice +
                ", productdiscount=" + productdiscount +
                ", productquantity=" + productquantity +
                '}';
    }

    public double getPriceAfterDiscount(){
        double priceAfterDiscount = ((this.getProductdiscount()/100) * this.getProductprice());
        return this.getProductprice() - priceAfterDiscount;
    }
}
