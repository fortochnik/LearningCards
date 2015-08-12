import Cards.Card;
import DataBase.DBData;
import exceptions.XMLAddingException;

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
 * Created by mipan on 12.08.2015.
 */
public class deleteServlet extends HttpServlet {

    String nameCollection;

    DBData DBData = new DBData();


    List<String> dataList = new ArrayList<String>();

    List<Card> cardsList =null;

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        if (cardsList != null){
            cardsList.clear();
        }
        dataList.clear();
        dataList = DBData.getListCollection();
        request.setAttribute("rows", dataList);

        RequestDispatcher rd = request.getRequestDispatcher("delete.jsp");
        rd.forward(request, response);

//        response.sendRedirect("collection");
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {

        List<String> dataList = new ArrayList<String>();
        dataList = DBData.getListCollection();
        request.setAttribute("rows", dataList);

        if (request.getParameter("backButton")!=null){
            response.sendRedirect("collection");
        }
        else
        {
//            HttpSession session = request.getSession(true);
            nameCollection = request.getParameter("collection");
            int idCollection = DBData.getIdCollection(nameCollection);


            try {
                DBData.deleteCollection(idCollection);
            } catch (XMLAddingException e) {
                e.printStackTrace();
            }

//            cardFront = request.getParameter("front");
//            byte[] bytes = cardFront.getBytes(StandardCharsets.ISO_8859_1);
//            cardFront = new String(bytes, StandardCharsets.UTF_8);

//            cardBack = request.getParameter("back");
//            bytes = cardBack.getBytes(StandardCharsets.ISO_8859_1);
//            cardBack = new String(bytes, StandardCharsets.UTF_8);

//          TODO add message to webPage

//            boolean updRow = DBData.setCardToCollection(nameCollection, cardFront, cardBack);

//            doGet(request, response);
            response.sendRedirect("collection");
        }

    }
}
