import Cards.Card;
import Cards.ManagCard;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by mipan on 26.04.2015.
 */
public class displayCardServlet  extends HttpServlet {

    private static int i = -1;
    private static int j = -1;
    int okValue;
    int failValue;
    int indexBascket;




   @Override
   public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException{
//       response.sendRedirect("/jsp/collection");
       response.sendRedirect("collection");
//       RequestDispatcher rd= context.getRequestDispatcher("/jsp/jsp");
//       rd.forward(request, response);
   }

   @Override
   protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
       if (req.getParameter("backButton")!=null)
       {
           doGet(req, resp);
       }
       else
//       if(req.getParameter("buttonGetCollection")!=null)
       {
           String paragr = req.getParameter("collection");
           req.setAttribute("paragr", paragr);
           HttpSession session = req.getSession(true);

           List<String> list = (List<String>) session.getAttribute("listAtr");
           List<Card> cardsList = (List<Card>) session.getAttribute("rows");

           i++;
           j++;


           if(i==list.size()){
               i=0;
           }
           if(j==cardsList.size()){
               j=0;
           }

           if (req.getParameter("okButton")!=null){
               okValue = cardsList.get(j).getOkValue();
               cardsList.get(j).setOkValue(okValue + 1);
               indexBascket = ManagCard.UpBascket(cardsList.get(j).getBasket());
               cardsList.get(j).setBasket(indexBascket);

           }
           if (req.getParameter("failButton")!=null){
               failValue = cardsList.get(j).getFailValue();
               cardsList.get(j).setFailValue(failValue + 1);
               indexBascket = ManagCard.DownBascket(cardsList.get(j).getBasket());
               cardsList.get(j).setBasket(indexBascket);
           }



           req.setAttribute("bascket", cardsList.get(j).getBasket());
           req.setAttribute("ok", cardsList.get(j).getOkValue());
           req.setAttribute("fail", cardsList.get(j).getFailValue());

           req.setAttribute("card", cardsList.get(j));
           req.setAttribute("ind", i);
           req.setAttribute("cardsize", cardsList.size());
           req.setAttribute("list1", list.get(i));

           req.getSession().getAttribute("param1");
           req.getSession().getAttribute("constText");

           RequestDispatcher rd = req.getRequestDispatcher("card.jsp");
           rd.forward(req, resp);
       }

   }
}
