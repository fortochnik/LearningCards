package XMLConverter;/**
 * Created by mipan on 28.06.2015.
 */

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;

import Cards.Card;
import DataBase.DBData;
import exceptions.XMLAddingException;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;

import java.io.*;

public class XMLToCollection {
    static DataBase.DBData DBData = new DBData();

    public static boolean XMLToCollection(String fullFileName) throws XMLAddingException {
        int idCollection = -1;
        try {
            File fXmlFile = new File(fullFileName);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(fXmlFile);
            doc.getDocumentElement().normalize();

            NodeList listNameCollection = doc.getElementsByTagName("dictionary");
            Node NodeNameCollection = listNameCollection.item(0);
            Element elementNameCollection = (Element) NodeNameCollection;
            String nameCollection = elementNameCollection.getAttribute("collection");

            if (!nameCollection.equals(""))
            {
                DBData.createCollection(nameCollection);

            }
            else
            {
                throw new XMLAddingException();
            }

            idCollection = DBData.getIdCollection(nameCollection);

            NodeList nList = doc.getElementsByTagName("card");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);

                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;

                    String front = eElement.getElementsByTagName("front").item(0).getTextContent();
                    String back = eElement.getElementsByTagName("back").item(0).getTextContent();
                    String bascket = eElement.getElementsByTagName("basket").item(0).getTextContent();
                    String okValue = eElement.getElementsByTagName("ok").item(0).getTextContent();
                    String failValue = eElement.getElementsByTagName("fail").item(0).getTextContent();
                    Card card = new Card(front, back, Integer.parseInt(bascket), Integer.parseInt(okValue), Integer.parseInt(failValue), idCollection );
                    DBData.setCardToCollection(card);
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
            DBData.deleteCollection(idCollection);
            throw new XMLAddingException();
//            return false;
        }
        return true;
    }
}
