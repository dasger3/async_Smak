package Voronin.MicroService.Lab1.Parser;

import Voronin.MicroService.Lab1.Models.Enums.Handy;
import Voronin.MicroService.Lab1.Models.Enums.Range;
import Voronin.MicroService.Lab1.Models.Gun;
import Voronin.MicroService.Lab1.Models.TTC;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedList;
import java.util.List;

public class ParserDom {

    public List<Gun> parse(File file) {
        try {
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(file);
            doc.getDocumentElement().normalize();
            /*System.out.println("Root element : " + doc.getDocumentElement().getNodeName());
            System.out.println("----------------------------");*/

            NodeList nList = doc.getDocumentElement().getElementsByTagName("gun");

            List<Gun> guns = new LinkedList<>();

            for (int temp = 0; temp < nList.getLength(); temp++) {

                Node node = nList.item(temp);

                if (node.getNodeType() == Node.ELEMENT_NODE) {

                    Element element = (Element) node;
                    Gun gun = new Gun();

                    gun.setId(Integer.parseInt(element.getAttribute("id")));
                    gun.setModel(getValue(element, "model"));
                    gun.setOrigin(getValue(element, "origin"));
                    gun.setMaterial(getValue(element, "material"));
                    gun.setHandy(Handy.valueOf(getValue(element, "handy")));

                    Node TTC = element.getElementsByTagName("TTC").item(0);

                    if (node.getNodeType() == Node.ELEMENT_NODE) {

                        Element elementTTC = (Element) TTC;
                        Voronin.MicroService.Lab1.Models.TTC ttc = new TTC();

                        ttc.setRange(Range.valueOf(getValue(elementTTC, "range")));
                        ttc.setHasClip(Boolean.parseBoolean(getValue(elementTTC, "hasClip")));
                        ttc.setHasOptics(Boolean.parseBoolean(getValue(elementTTC, "hasOptics")));
                        ttc.setSightingRange(Double.parseDouble(getValue(elementTTC, "sightingRange")));

                        gun.setTTC(ttc);
                    }

                    guns.add(gun);
                }
            }

            return guns;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getValue(Element element, String nameOfValue) {
        return element.getElementsByTagName(nameOfValue).item(0).getTextContent();
    }
}
