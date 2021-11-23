package Voronin.Async.Lab1;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Voronin {

    public static void main(String[] args) {

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
}
