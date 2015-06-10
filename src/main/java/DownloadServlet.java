import Cards.Card;
import DataBase.DBData;
import XMLConverter.collectionToXML;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

/**
 * Created by mipan on 01.06.2015.
 */
public class DownloadServlet extends HttpServlet {


    protected void doGet( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBData DBData = new DBData();
        String collection = request.getParameter("collection");
        List<Card> cardsList = DBData.getCardsCollection(collection);

        String pathTMP = request.getSession().getServletContext().getRealPath("/") + "tmp/";

        try {
            File folderTMP = new File(pathTMP);
            if(!folderTMP.isDirectory()) {
                folderTMP.mkdirs();
                folderTMP.deleteOnExit();
            }
        } catch(Exception e)
        {
//            TODO logging
        }

        File XMLfile = new File(pathTMP, "XML_" + collection + "_collection" + ".xml");
        collectionToXML.collectionToXML(cardsList,collection, XMLfile);

        String fileType = "application/xml";

        response.setContentType(fileType);

        response.setHeader("Content-disposition","attachment; filename=XMLcollection.xml");

        OutputStream out = response.getOutputStream();
        FileInputStream in = new FileInputStream(XMLfile);
        byte[] buffer = new byte[4096];
        int length;
        while ((length = in.read(buffer)) > 0){
            out.write(buffer, 0, length);
        }
        in.close();
        out.flush();

        XMLfile.delete();

        RequestDispatcher rd = request.getRequestDispatcher("collection");
        rd.forward(request, response);
    }

    protected void doPost( HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
            doGet(request,response);
    }
}