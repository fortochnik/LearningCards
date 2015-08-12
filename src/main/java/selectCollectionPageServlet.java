import Cards.Card;
import DataBase.DBData;
import XMLConverter.collectionToXML;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileWriter;
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


//    List<Card> cardsListFirstBasket = null;
//    List<Card> cardsListSecondBasket = null;
//    List<Card> cardsListThirdBasket = null;
//    List<List<Card>> listOfCollections = new ArrayList<List<Card>>();

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws IOException, ServletException
    {
//        dataList.clear();
//        if (cardsListFirstBasket != null) {
//            cardsListFirstBasket.clear();
//        }
//        if (cardsListSecondBasket != null) {
//            cardsListSecondBasket.clear();
//        }
//        if (cardsListThirdBasket != null) {
//            cardsListThirdBasket.clear();
//        }
//        if(listOfCollections != null){
//            listOfCollections.clear();
//        }
        List<String> dataList = new ArrayList<String>();

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

        if(req.getParameter("addButton")!=null) {
            resp.sendRedirect("add");
        }
        else {
            if (req.getParameter("deleteButton") != null) {
                resp.sendRedirect("delete");
            }
            else
            {
                if (req.getParameter("downloadButton") != null) {
                    RequestDispatcher rd = req.getRequestDispatcher("download");
                    rd.forward(req, resp);
                }
                else
                {
                    //                String test = "testConst";
                    //                HttpSession session = req.getSession(true);
                    int idCollection = DBData.getIdCollection(nameCollection);
                    /*
                    set id_collection and zero in Basket into DB.
                    */
                    if (nameCollection != null) {
                        DBData.setDisplayCollection(idCollection);
                    }

                    //                session.setAttribute("nameCollection", nameCollection);


                    RequestDispatcher rd = req.getRequestDispatcher("/card");
                    rd.forward(req, resp);
                }
            }
        }


    }
}