package Volos.Async.Lab3;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Создать 2 массива (или коллекции) со случайными числами.
 * В первом массиве — оставить элементы которые больше среднего значения массива, во втором — меньше.
 * Отсортировать массивы и слить в один отсортированный массив те элементы, которые есть в одном массиве и нет в другом.
 */
public class Main {
    static int arraySize1 = 10;
    static int arraySize2 = 10;

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
                .thenApplyAsync(arrList ->
                        new ArrayList<>(arrList.stream().filter(i ->
                                i > (float)arrList.stream().reduce(Integer::sum).get() /
                                        (float)arraySize1).collect(Collectors.toList()))
                ).thenApplyAsync(arrList -> {
                    System.out.println("First list: " + arrList);
                    return arrList;
                });

        CompletableFuture<ArrayList<Integer>> future2 = getFutureList(arraySize2)
                .thenApplyAsync(arrList ->
                        new ArrayList<>(arrList.stream().filter(i ->
                                i < (float)arrList.stream().reduce(Integer::sum).get() /
                                        (float)arraySize2).collect(Collectors.toList()))
                ).thenApplyAsync(arrList -> {
                    System.out.println("Second list: " + arrList);
                    return arrList;
                });

        CompletableFuture<Void> result = future1
                .thenCombineAsync(future2, (a, b) -> {

                    List<Integer> intersection = a.stream()
                            .filter(b::contains)
                            .collect(Collectors.toList());

                    List<Integer> union = Stream.concat(a.stream(), b.stream())
                            .distinct().sorted()
                            .collect(Collectors.toList());

                    return union.stream()
                            .filter(i -> !intersection.contains(i))
                            .collect(Collectors.toList());

                }).thenAcceptAsync(arrList -> {
                    arrList.sort(Integer::compareTo);
                    System.out.println("Final list: " + arrList);
                });

        result.get();

    }
}
