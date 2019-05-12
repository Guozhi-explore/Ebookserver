package Servlet.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;

import static java.lang.Integer.parseInt;

@WebServlet(name = "createOrderServlet",value = "/createOrderServlet")
public class createOrderServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            Connection connect = DBConfig.getConnection();
            int user_id= parseInt(request.getParameter("user_id"));
            String sql1="insert into orders values(0,?,now())";             //在order表中添加，并得到order_id
            PreparedStatement preparedStatement = connect.prepareStatement(sql1,Statement.RETURN_GENERATED_KEYS);
            preparedStatement.setInt(1,user_id);
            preparedStatement.execute();

            ResultSet rs = preparedStatement.getGeneratedKeys();
            if (rs.next()) {
                int order_id = rs.getInt(1);
                String sql2 = "select number,book_id\n" +
                        "from cart natural join book\n" +
                        "where user_id=?;";                                      //得到购物车里的书籍id与数量
                preparedStatement = connect.prepareStatement(sql2);
                preparedStatement.setInt(1, user_id);
                ResultSet resultset = preparedStatement.executeQuery();
                boolean successFlag = true;
                while (resultset.next()) {
                    int book_id = resultset.getInt("book_id");
                    int number = resultset.getInt("number");


                    String sql7="select * from book where book_id=? and amount>=?";
                    preparedStatement = connect.prepareStatement(sql7);
                    preparedStatement.setInt(1, book_id);
                    preparedStatement.setInt(2, number);
                    ResultSet resultSet = preparedStatement.executeQuery();
                    if (resultSet.next())                                               //购买数量小于库存数量可以购买
                    {
                        String sql5 = "update book\n" +                                     //把书的总量减去用户购买的数量
                                "set amount=amount-?\n" +
                                "where book_id=? and amount>=?;";
                        preparedStatement = connect.prepareStatement(sql5);
                        preparedStatement.setInt(1, number);
                        preparedStatement.setInt(2, book_id);
                        preparedStatement.setInt(3, number);
                        preparedStatement.execute();

                        String sql3 = "insert into order_item values(?,?,?)";             //将得到的数据逐一添加入order_item中
                        preparedStatement = connect.prepareStatement(sql3);
                        preparedStatement.setInt(1, order_id);
                        preparedStatement.setInt(2, book_id);
                        preparedStatement.setInt(3, number);
                        preparedStatement.execute();

                        String sql6 = "update book\n" +                                     //把书的销量加上用户购买的数量
                                "set sales=sales+?\n" +
                                "where book_id=?;";
                        preparedStatement = connect.prepareStatement(sql6);
                        preparedStatement.setInt(1, number);
                        preparedStatement.setInt(2, book_id);
                        preparedStatement.execute();

                        String sql4 = "delete from cart where user_id=? and book_id=?";                 //将该用户购物车清空
                        preparedStatement = connect.prepareStatement(sql4);
                        preparedStatement.setInt(1, user_id);
                        preparedStatement.setInt(2, book_id);
                        preparedStatement.execute();
                    } else {
                        successFlag = false;
                    }
                }
                if (successFlag == true) {
                    out.println("success");
                }
            }
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
