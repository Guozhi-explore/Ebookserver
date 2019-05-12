package entity;

public class Book {
    private int book_id;
    private String name;
    private String author;
    private String isbn;
    private int amount;
    private int price;
    private int sales;
    private String img_src;
    private String abstrac;

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getAuthor() {
        return author;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public String getIsbn() {
        return isbn;
    }

    public int getPrice() {
        return price;
    }

    public int getSales() {
        return sales;
    }

    public String getAbstrac() {
        return abstrac;
    }

    public String getImg_src() {
        return img_src;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public void setImg_src(String img_src) {
        this.img_src = img_src;
    }

    public void setAbstrac(String abstrac) {
        this.abstrac = abstrac;
    }
}
