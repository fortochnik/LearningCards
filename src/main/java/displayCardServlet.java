import Cards.Card;
import Cards.ManagCard;
import DataBase.DBData;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Created by mipan on 26.04.2015.
 */
public class displayCardServlet  extends HttpServlet {
    int okValue;
    int failValue;

    DBData DBData = new DBData();
    ManagCard ManagCard = new ManagCard();


   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{

       response.sendRedirect("collection");
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

       if (req.getParameter("backButton")!=null)
       {
           doGet(req, resp);

       }
       else{

           Card card = DBData.getCardForDisplay();
           req.setAttribute("card", card);

           if(req.getParameter("collection") != null){
               DBData.setIdCardInDB(card.getIdCardInDB());
           }

           int idCardinDB;
           if (req.getParameter("okButton") != null) {
               int newBasket;
               idCardinDB = DBData.getIdCardInDB();
               Card updCard = DBData.getCardByID(idCardinDB);

               int OldBasket = updCard.getBasket();
               int okValue = updCard.getOkValue() + 1;

               newBasket = ManagCard.UpBasket(updCard.getBasket());

               ManagCard.setBasketAndStatistic(idCardinDB, newBasket, okValue, true);

           }
           if (req.getParameter("failButton") != null) {
               int newBasket;

               idCardinDB = DBData.getIdCardInDB();
               Card updCard = DBData.getCardByID(idCardinDB);

               int OldBasket = updCard.getBasket();
               int failValue = updCard.getFailValue() + 1;

               updCard.setFailValue(failValue + 1);
               newBasket = ManagCard.DownBasket(updCard.getBasket());
               ManagCard.setBasketAndStatistic(idCardinDB, newBasket, failValue, false);
           }
           DBData.setIdCardInDB(card.getIdCardInDB());

           RequestDispatcher rd = req.getRequestDispatcher("card.jsp");
           rd.forward(req, resp);
       }
   }
}
