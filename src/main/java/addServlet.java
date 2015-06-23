import Cards.Card;
import DataBase.DBData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mipan on 17.06.2015.
 */
public class addServlet extends HttpServlet {
    String nameCollection;
    String cardFront;
    String cardBack;

    DataBase.DBData DBData = new DBData();

    List<String> dataList = new ArrayList<String>();

    List<Card> cardsList =null;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

        if (cardsList != null){
            cardsList.clear();
        }
        dataList.clear();
        dataList = DBData.getListCollection();
        request.setAttribute("rows", dataList);

        RequestDispatcher rd = request.getRequestDispatcher("add.jsp");
        rd.forward(request, response);

    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        if (request.getParameter("backButton")!=null){
            response.sendRedirect("collection");
        }
        else
        {
            HttpSession session = request.getSession(true);
            nameCollection = request.getParameter("collection");
            cardFront = request.getParameter("front");
            byte[] bytes = cardFront.getBytes(StandardCharsets.ISO_8859_1);
            cardFront = new String(bytes, StandardCharsets.UTF_8);

            cardBack = request.getParameter("back");
            bytes = cardBack.getBytes(StandardCharsets.ISO_8859_1);
            cardBack = new String(bytes, StandardCharsets.UTF_8);

//          TODO add message to webPage

            boolean updRow = DBData.setCardToCollection(nameCollection, cardFront, cardBack);

            doGet(request, response);
        }

    }

}
