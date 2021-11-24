package Voronin.Async.Lab1;

import java.util.Arrays;
import java.util.stream.IntStream;

public class Voronin {

    public static void main(String[] args) {
        Voronin customIntArray = new Voronin();

        System.out.println(customIntArray);
        //3
        customIntArray.minElementAndIndex();
        //8
        customIntArray.maxElementAndIndex();
        //13
        customIntArray.findFirstPositiveElement();
        //15
        customIntArray.findFirstInputElement();
        //7
        customIntArray.sumOfArray();
    }

    private final int[] array = new int[10];

    public Voronin() {
        for (int i = 0; i < 10; i++)
            array[i] = -10 + (int) (Math.random() * 20);
    }

    @Override
    public String toString() {
        return "CustomIntArray{" +
                Arrays.toString(array) +
                '}';
    }

    //3
    public void minElementAndIndex() {
        IntStream stream = Arrays.stream(array);
        int min = stream.min().getAsInt();
        int[] indices = IntStream.range(0, array.length)
                .filter(i -> array[i] == min)
                .toArray();
        System.out.println("Min: " + min);
        System.out.println("Found " + min + " at indices " + Arrays.toString(indices));

    }

    //8
    public void maxElementAndIndex() {
        IntStream stream = Arrays.stream(array);
        int max = stream.max().getAsInt();
        int[] indices = IntStream.range(0, array.length)
                .filter(i -> array[i] == max)
                .toArray();
        System.out.println("Max: " + max);
        System.out.println("Found " + max + " at indices " + Arrays.toString(indices));

    }

    //13
    public void findFirstPositiveElement() {
        IntStream stream = Arrays.stream(array);
        System.out.println("First positive element: " + stream
                .filter(number -> number > 0).findFirst().getAsInt());
    }

    //15
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

    //7
    public void sumOfArray() {
        IntStream stream = Arrays.stream(array);
        System.out.println("Sum: " + stream.sum());
    }
}
