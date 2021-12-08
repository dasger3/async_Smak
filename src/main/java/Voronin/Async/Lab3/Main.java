package Voronin.Async.Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Создать 3 массива (или коллекции) со случайными числами.
 * В первом массиве — элементы умножить на 5. Во втором — оставить только четные.
 * В третьем — оставить элементы в диапазоне от 0.4 до 0.6 максимального значения.
 * Отсортировать массивы и слить в один массив элементы отсортированный массив в котором есть элементы которые входят во все массивы.
 */
public class Main {
    static int arraySize1 = 10;
    static int arraySize2 = 10;
    static int arraySize3 = 10;

    public static CompletableFuture<ArrayList<Integer>> getFutureList(int arraySize) {
        return CompletableFuture.supplyAsync(() -> {
            int amp = 15;
            ArrayList<Integer> array = new ArrayList<>();
            for (int i = 0; i < arraySize; i++) {
                array.add((int) ((Math.random() * amp) - 5));
            }
            return array;
        });
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        CompletableFuture<ArrayList<Integer>> future1 = getFutureList(arraySize1)
                .thenApplyAsync(arrList -> {
                            arrList.forEach(i -> i *= 5);
                            return arrList;
                        }
                ).thenApplyAsync(arrList -> {
                    System.out.println("First list: " + arrList);
                    return arrList;
                });

        CompletableFuture<ArrayList<Integer>> future2 = getFutureList(arraySize2)
                .thenApplyAsync(arrList ->
                        new ArrayList<>(arrList.stream().filter(i ->
                                i % 2 == 0).collect(Collectors.toList()))
                ).thenApplyAsync(arrList -> {
                    System.out.println("Second list: " + arrList);
                    return arrList;
                });

        CompletableFuture<ArrayList<Integer>> future3 = getFutureList(arraySize3)
                .thenApplyAsync(arrList ->
                        new ArrayList<>(arrList.stream().filter(i ->
                                        i > 0.4 * (float) arrList.stream().reduce(Integer::max).get() &&
                                                i > 0.6 * (float) arrList.stream().reduce(Integer::max).get())
                                .collect(Collectors.toList()))
                ).thenApplyAsync(arrList -> {
                    System.out.println("Third list: " + arrList);
                    return arrList;
                });

        CompletableFuture<Void> result = future1
                .thenCombineAsync(future2, (a, b) -> a.stream()
                        .filter(b::contains)
                        .collect(Collectors.toList()))
                .thenCombineAsync(future3, (a, b) -> a.stream()
                        .filter(b::contains)
                        .collect(Collectors.toList()))
                .thenAcceptAsync(arrList -> {
                    arrList.sort(Integer::compareTo);
                    System.out.println("Final list: " + arrList);
                });


        result.get();

    }
}
