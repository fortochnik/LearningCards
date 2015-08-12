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
    int indexBasket;
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

           try
           {
               File file = new File("D:\\testCard.txt");
               FileWriter fileWriter = new FileWriter(file);
               fileWriter.write("Front=" + card.getFront() + ";___ \n ");
               fileWriter.write("IdCardInDB=" + card.getIdCardInDB() + ";___ \n");
               fileWriter.write("Basket=" + card.getBasket() + "; ");
               fileWriter.close();
           }
           catch (Exception e1) {	}
           if (req.getParameter("okButton") != null) {

               okValue = card.getOkValue();
               card.setOkValue(okValue + 1);
               indexBasket = ManagCard.UpBasket(card.getBasket());
               card.setBasket(indexBasket);

               ManagCard.setBasketAndStatistic(card.getIdCardInDB(), indexBasket, card.getOkValue(), true);

           }
           if (req.getParameter("failButton") != null) {
               failValue = card.getFailValue();
               card.setFailValue(failValue + 1);
               indexBasket = ManagCard.DownBasket(card.getBasket());
               card.setBasket(indexBasket);

               ManagCard.setBasketAndStatistic(card.getIdCardInDB(), card.getBasket(), card.getFailValue(), false);
           }

           RequestDispatcher rd = req.getRequestDispatcher("card.jsp");
           rd.forward(req, resp);
       }
   }
}
