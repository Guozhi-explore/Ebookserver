package Servlet.Servlet;

import entity.Book;
import entity.Order;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.parseInt;

@WebServlet(name = "orderORMServlet",value = "/orderORMServlet")
public class orderORMServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");
        Transaction transaction = null;
        try {
            Session session = HibernateUtil.getSessionFactory().getCurrentSession();
            session.beginTransaction();
            List orderlist=session.createQuery("from Order").list();
            session.getTransaction().commit();
            JSONArray jArray = new JSONArray();
            JSONObject jo = new JSONObject();
            for (int i = 0; i < orderlist.size(); i++) {
                Order theorder = (Order) orderlist.get(i);
                jo.put("time", theorder.getOrder_time());
                jo.put("total_price",theorder.getOrder_time());
                jo.put("books",theorder.getBooks());
                jArray.add(jo);
            }
            String jsonlist=jArray.toString();
            System.out.println(jsonlist);
            out.println(jsonlist);
            out.flush();
            out.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }
}
