package it.gabriele.iovino.skillspringapi.models;


import jakarta.persistence.*;

@Entity
@Table(name = "courses")
@SecondaryTable(name="categories")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "img_url")
    private String imgUrl;


    @Column(name = "ratings_count")
    private int ratingsCount;

    @Column(name = "ratings_total")
    private float ratingsTotal;


    @Column(name = "price")
    private float price;

    @OneToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    private Category category;



    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getRatingsCount() {
        return ratingsCount;
    }

    public void setRatingsCount(int ratingsCount) {
        this.ratingsCount = ratingsCount;
    }

    public float getRatingsTotal() {
        return ratingsTotal;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public void setRatingsTotal(float ratingsTotal) {
        this.ratingsTotal = ratingsTotal;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }
}
