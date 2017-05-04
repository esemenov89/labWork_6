package code.model.pojo;

/**
 * Created by admin on 22.04.2017.
 */
public class StorageUnit {
    private String author;
    private String title;
    private String publishingHouse;
    private String city;
    private int year;
    private int pagesCount;
    private String isn;
    private String text;

    public String getAuthor() {
        return author;
    }

    public String getTitle() {
        return title;
    }

    public String getPublishingHouse() {
        return publishingHouse;
    }

    public String getCity() {
        return city;
    }

    public int getYear() {
        return year;
    }

    public int getPagesCount() {
        return pagesCount;
    }

    public String getIsn() {
        return isn;
    }

    public String getText() {
        return text;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setPublishingHouse(String publishingHouse) {
        this.publishingHouse = publishingHouse;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public void setPagesCount(int pagesCount) {
        this.pagesCount = pagesCount;
    }

    public void setIsn(String isn) {
        this.isn = isn;
    }

    public void setText(String text) {
        this.text = text;
    }

    public StorageUnit(String author, String title, String publishingHouse, String city, int year, int pagesCount, String isn, String text) {
        this.author = author;
        this.title = title;
        this.publishingHouse = publishingHouse;
        this.city = city;
        this.year = year;
        this.pagesCount = pagesCount;
        this.isn = isn;
        this.text = text;
    }

    @Override
    public String toString() {
        return "StorageUnit{" +
                "author='" + author + '\'' +
                ", title='" + title + '\'' +
                ", publishingHouse='" + publishingHouse + '\'' +
                ", city='" + city + '\'' +
                ", year=" + year +
                ", pagesCount=" + pagesCount +
                ", isn='" + isn + '\'' +
                ", text='" + text + '\'' +
                '}';
    }
}
