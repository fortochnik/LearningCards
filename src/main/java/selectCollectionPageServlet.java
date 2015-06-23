import Cards.Card;
import DataBase.DBData;
import XMLConverter.collectionToXML;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mipan on 19.04.2015.
 */
public class selectCollectionPageServlet extends HttpServlet {
    DBData DBData = new DBData();

    List<String> dataList = new ArrayList<String>();

    List<Card> cardsList =null;

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
        if (cardsList != null){
            cardsList.clear();
        }
        dataList.clear();
        dataList = DBData.getListCollection();
        request.setAttribute("rows", dataList);
        RequestDispatcher rd = request.getRequestDispatcher("collection.jsp");
        rd.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException
    {
        String nameCollection = req.getParameter("collection");
        cardsList = DBData.getCardsCollection(nameCollection);
//        if (req.getParameter("download")!=null){
//            collectionToXML.collectionToXML(cardsList, nameCollection);
//            doGet(req, resp);
//        }
        if(req.getParameter("addButton")!=null) {
            resp.sendRedirect("add");
//            resp.sendRedirect("addServlet");
        }
        else {

            if (req.getParameter("downloadButton") != null) {
                RequestDispatcher rd = req.getRequestDispatcher("download");
                rd.forward(req, resp);
            } else {
                String test = "testConst";
                HttpSession session = req.getSession(true);
                session.setAttribute("rows", cardsList);
                session.setAttribute("nameCollection", nameCollection);
                session.setAttribute("constText", test);
                //        session.setAttribute("fromList", cardsList.get(0).getBack());
        /*TEST*/
                List<String> list = new ArrayList<String>();
                list.add("stringOne");
                list.add("stringTwo");

                //        HttpSession session = req.getSession(true);
                session.setAttribute("listAtr", list);
        /*TEST*/

                RequestDispatcher rd = req.getRequestDispatcher("/card");
                rd.forward(req, resp);
            }
        }


    }
}