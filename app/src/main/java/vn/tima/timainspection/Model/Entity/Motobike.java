package vn.tima.timainspection.Model.Entity;

/**
 * Created by Hoang do on 1/5/2017.
 */

public class Motobike {
    private int Id;
    private int IdBrand;
    private int IdType;
    private int ProductReviewId;
    private String TypeName;
    private String ProductName;
    private String FullName;
    private String Year;
    private String CountYear;
    private String PercentReduction;
    private String ContentReview;
    private long Price;
    private long TotalPrice;

    public int getProductReviewId() {
        return ProductReviewId;
    }

    public void setProductReviewId(int productReviewId) {
        ProductReviewId = productReviewId;
    }

    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public int getIdBrand() {
        return IdBrand;
    }

    public void setIdBrand(int idBrand) {
        IdBrand = idBrand;
    }

    public int getIdType() {
        return IdType;
    }

    public void setIdType(int idType) {
        IdType = idType;
    }

    public String getTypeName() {
        return TypeName;
    }

    public void setTypeName(String typeName) {
        TypeName = typeName;
    }

    public String getProductName() {
        return ProductName;
    }

    public void setProductName(String productName) {
        ProductName = productName;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getCountYear() {
        return CountYear;
    }

    public void setCountYear(String countYear) {
        CountYear = countYear;
    }

    public String getPercentReduction() {
        return PercentReduction;
    }

    public void setPercentReduction(String percentReduction) {
        PercentReduction = percentReduction;
    }

    public String getContentReview() {
        return ContentReview;
    }

    public void setContentReview(String contentReview) {
        ContentReview = contentReview;
    }

    public long getPrice() {
        return Price;
    }

    public void setPrice(long price) {
        Price = price;
    }

    public long getTotalPrice() {
        return TotalPrice;
    }

    public void setTotalPrice(long totalPrice) {
        TotalPrice = totalPrice;
    }
}
