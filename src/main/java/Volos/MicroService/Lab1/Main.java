package Volos.MicroService.Lab1;

import Volos.MicroService.Lab1.Models.Computer;
import Volos.MicroService.Lab1.Parser.ParserDom;
import Volos.MicroService.Lab1.Parser.ParserJAXB;
import Volos.MicroService.Lab1.Parser.ParserXpath;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) throws JsonProcessingException {

        ObjectMapper objectMapper = new ObjectMapper();

        File inputFile = new File("src/main/java/Volos/MicroService/Lab1/XML/computer.xml");

        ParserDom parserDom = new ParserDom();
        printList(parserDom.parse(inputFile));

        ParserJAXB parserJAXB = new ParserJAXB();
        printList(parserJAXB.parse(inputFile));

        ParserXpath parserXpath = new ParserXpath();
        printList(parserXpath.parse(inputFile));

        List<Computer> computers = parserJAXB.parse(inputFile);
        System.out.println(objectMapper.writeValueAsString(computers));
    }

    private static void printList(List<Computer> list) {
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Computer tmp: list) {
            System.out.println(tmp);
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
