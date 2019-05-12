package entity;

import java.util.ArrayList;

public class Order {
    private int order_id;
    private String order_time;
    private int total_price;
    private ArrayList<String> books=new ArrayList<String>();

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getTotal_price() {
        return total_price;
    }

    public ArrayList<String> getBooks() {
        return books;
    }

    public String getOrder_time() {
        return order_time;
    }

    public void setOrder_time(String order_time) {
        this.order_time = order_time;
    }

    public void setBooks(ArrayList<String> books) {
        this.books = books;
    }


    public void setTotal_price(int total_price) {
        this.total_price = total_price;
    }
}
