package XMLConverter;

import Cards.Card;

import java.io.*;
import java.util.List;

import org.jdom.Attribute;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.output.Format;
import org.jdom.output.XMLOutputter;

/**
 * Created by mipan on 30.05.2015.
 */
public class collectionToXML {

    public static void collectionToXML(List<Card> collection, String nameCollection, File file) throws IOException
    {
        Element dictionary = new Element("dictionary");
        dictionary.setAttribute(new Attribute("collection",nameCollection));
        Document doc = new Document(dictionary);
        doc.setRootElement(dictionary);

        for (int ind = 1; ind <= collection.size(); ind++) {

            Element card = new Element("card");
            card.setAttribute(new Attribute("id", ind - 1 +""));
            card.addContent(new Element("front").setText(collection.get(ind - 1).getFront()));
            card.addContent(new Element("back").setText(collection.get(ind - 1).getBack()));
            card.addContent(new Element("basket").setText(collection.get(ind - 1).getBasket() + ""));
            card.addContent(new Element("ok").setText(collection.get(ind - 1).getOkValue() + ""));
            card.addContent(new Element("fail").setText(collection.get(ind - 1).getFailValue() + ""));

            doc.getRootElement().addContent(card);
        }

//        new XMLOutputter().output(doc, System.out);
        XMLOutputter xmlOutput = new XMLOutputter();

        // display nice nice
        xmlOutput.setFormat(Format.getPrettyFormat());

        OutputStreamWriter output = new OutputStreamWriter(new FileOutputStream(file), "UTF-8");
        xmlOutput.output(doc, output);
        output.close();
    }
}
