package Galushko.MicroService.Lab1;

import Galushko.MicroService.Lab1.Models.Knife;
import Galushko.MicroService.Lab1.Parser.ParserDom;
import Galushko.MicroService.Lab1.Parser.ParserJAXB;
import Galushko.MicroService.Lab1.Parser.ParserXpath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        File inputFile = new File("src/main/java/Galushko/MicroService/Lab1/XML/knife.xml");

        ParserDom parserDom = new ParserDom();
        printList(parserDom.parse(inputFile));

        ParserJAXB parserJAXB = new ParserJAXB();
        printList(parserJAXB.parse(inputFile));

        ParserXpath parserXpath = new ParserXpath();
        printList(parserXpath.parse(inputFile));

        List<Knife> knives = parserJAXB.parse(inputFile);
        System.out.println(objectMapper.writeValueAsString(knives));
    }

    private static void printList(List<Knife> list) {
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Knife tmp: list) {
            System.out.println(tmp);
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
