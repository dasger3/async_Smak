package Voronin.MicroService.Lab1;

import Voronin.MicroService.Lab1.Models.Gun;
import Voronin.MicroService.Lab1.Parser.ParserDom;
import Voronin.MicroService.Lab1.Parser.ParserJAXB;
import Voronin.MicroService.Lab1.Parser.ParserXpath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        File inputFile = new File("src/main/java/Voronin/MicroService/Lab1/XML/gun.xml");

        ParserDom parserDom = new ParserDom();
        printList(parserDom.parse(inputFile));

        ParserJAXB parserJAXB = new ParserJAXB();
        printList(parserJAXB.parse(inputFile));

        ParserXpath parserXpath = new ParserXpath();
        printList(parserXpath.parse(inputFile));

        List<Gun> guns = parserJAXB.parse(inputFile);
        System.out.println(objectMapper.writeValueAsString(guns));
    }

    private static void printList(List<Gun> list) {
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Gun tmp: list) {
            System.out.println(tmp);
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
