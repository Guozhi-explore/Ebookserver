package Servlet.Servlet;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import entity.Book;


@WebServlet(name = "bookORMServlet",value="/bookORMServlet")
public class bookORMServlet extends HttpServlet {
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
            List booklist=session.createQuery("from Book").list();
            session.getTransaction().commit();
            JSONArray jArray = new JSONArray();
            JSONObject jo = new JSONObject();
            for (int i = 0; i < booklist.size(); i++) {
                Book thebook = (Book) booklist.get(i);
                jo.put("book_id", thebook.getBook_id());
                jo.put("name",thebook.getName());
                jo.put("author","hibernate");
                jo.put("price",thebook.getPrice());
                jo.put("amount", thebook.getAmount());
                jo.put("isbn", thebook.getIsbn());
                jo.put("sales",thebook.getSales());
                jo.put("abstract",thebook.getAbstrac());
                jo.put("img_src",thebook.getImg_src());
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
