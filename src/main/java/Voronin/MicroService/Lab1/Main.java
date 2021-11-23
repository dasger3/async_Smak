package Voronin.MicroService.Lab1;

import Voronin.MicroService.Lab1.Models.Gun;
import Voronin.MicroService.Lab1.Parser.ParserDom;

import java.io.File;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        File inputFile = new File("src/main/java/Voronin/MicroService/Lab1/XML/gun.xml");

        ParserDom parserDom = new ParserDom();
        printList(parserDom.parse(inputFile));
    }

    private static void printList(List<Gun> list) {
        System.out.println("--------------------------------------------------------------------------------------------");
        for (Gun tmp: list) {
            System.out.println(tmp);
        }
        System.out.println("--------------------------------------------------------------------------------------------");
    }
}
