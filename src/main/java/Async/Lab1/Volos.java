package Async.Lab1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Volos {

    public static void main(String[] args) {
        Volos customIntArray = new Volos();

        System.out.println(customIntArray);
        //2
        customIntArray.average();
        //17
        customIntArray.printAllSameNumbers();
        //10
        System.out.println(customIntArray);
        customIntArray.increaseElementsByIndex();
        System.out.println(customIntArray);
        //13
        customIntArray.findFirstPositiveElement();
        //15
        customIntArray.findFirstInputElement();
    }

    private final int[] array = new int[10];

    public Volos() {
        for (int i = 0; i < 10; i++)
            array[i] = -10 + (int) (Math.random() * 20);
    }

    @Override
    public String toString() {
        return "CustomIntArray{" +
                Arrays.toString(array) +
                '}';
    }

    //2
    public void average() {
        // просто нахождение среднего, создали поток и в нем нашли среднее
        IntStream stream = Arrays.stream(array);
        System.out.println("Average: " + stream.average().getAsDouble());
    }

    //17
    public void printAllSameNumbers() {
        // нужно найти все повторяющееся елементы, делаем потом группируем елементы,
        // типо все одинаковые вместе и собираем их в карту где ключ это сам елемент а значение это колличество его повторений
        // и если повторений болльше чем 1 значит выводим
        IntStream stream = Arrays.stream(array);
        stream.boxed()
                .collect(Collectors.groupingBy(s -> s))
                .forEach((k, v) -> {
                    if (v.size() > 1) {
                        System.out.println("Duplicate: " + k);
                    }
                });
    }

    //10
    // просто перебираем весь масив через поток и к каждому елементу добаляем его индекс
    public void increaseElementsByIndex() {
        IntStream.range(0, array.length)
                .forEach(x -> array[x] = x + array[x]);
    }

    //13
    // просто делаем поток и находим первый елемент положительый
    public void findFirstPositiveElement() {
        IntStream stream = Arrays.stream(array);
        System.out.println("First positive element: " + stream
                .filter(number -> number > 0).findFirst().getAsInt());
    }

    //15
    // рандомлю индекс и буду в масиве искать индекс числа с этим индексом,
    // делаю поток перебираю масив и если число совпало вывожу индекс
    public void findFirstInputElement() {
        int indexInput = (int) (Math.random() * 10);
        System.out.println("We are searching index of:" + array[indexInput]);
        IntStream.range(0, array.length)
                .forEach(x -> {
                    if (array[x] == array[indexInput]) {
                        System.out.println("Index: " + x);
                        return;
                    }
                });
    }
}
