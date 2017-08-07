package service.Impl;

import java.util.*;

public class Utils {

    public static LinkedList<Integer> findNonZero(LinkedList<Integer> list) {
        LinkedList<Integer> indexes = new LinkedList<>();

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > 0) {
                    indexes.add(i);
                }
            }
        }

        return indexes;
    }

    public static LinkedList<Integer> findGreaterThen(LinkedList<Integer> list, Integer limit) {
        LinkedList<Integer> indexes = new LinkedList<>();

        if (!list.isEmpty()) {
            for (int i = 0; i < list.size(); i++) {
                if (list.get(i) > limit) {
                    indexes.add(i);
                }
            }
        }
        return indexes;
    }

    public static LinkedList<Integer> findValueIndexBetween(LinkedList<Integer> list, Integer start, Integer end) {
        LinkedList<Integer> indexes = new LinkedList<>();

        for (int i = 0; i < list.size(); i++) {
            Integer value = list.get(i);
            if (start <= value && value <= end) {
                indexes.add(i);
            }
        }
        return indexes;
    }

    public static LinkedList<Integer> intersect(LinkedList<Integer> array1, LinkedList<Integer> array2) {
        LinkedList<Integer> intersectedArray = new LinkedList<>();

        for (Integer value : array1) {
            if (array2.contains(value)) {
                intersectedArray.add(value);
            }
        }

        return intersectedArray;
    }

    public static List<Integer> icrementElements(List<Integer> list) {
        List<Integer> newList = new LinkedList<>();
        list.addAll(list);
        for (int i = 0; i < list.size(); i++) {
            newList.set(i, newList.get(i) + 1);
        }
        return newList;
    }

    public static LinkedHashMap<Integer, Integer> convertArraysToMap(List<Integer> list1, List<Integer> list2) {

        LinkedHashMap<Integer, Integer> map = new LinkedHashMap<>();
        Iterator<Integer> i1 = list1.iterator();
        Iterator<Integer> i2 = list2.iterator();
        while (i1.hasNext() && i2.hasNext()) {
            map.put(i1.next(), i2.next());
        }
        return map;
    }

    public static SortedSet<Integer> findUniqueSortedData(List<Integer> list){
        SortedSet<Integer> uniqueValues = new TreeSet<>();
        uniqueValues.addAll(list);
        return uniqueValues;
    }

    public static LinkedList<Integer> setFlagArray(Integer arraySize, Set<Integer> indexes) {
        Integer[] array = new Integer[arraySize];
        for (int i = 0; i < arraySize; i++) {
            if (indexes.contains(i + 1)) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
        return new LinkedList<>(Arrays.asList(array));
    }

    public static LinkedList<Integer> diff(LinkedList<Integer> set){

        Integer[] vector = set.toArray(new Integer[set.size()]);
        Integer[] diffVector = new Integer[set.size() - 1];

        for (int i = 0; i < vector.length - 1; i++) {
            diffVector[i] = vector[i + 1] - vector[i];
        }
        return new LinkedList<Integer>(Arrays.asList(diffVector));
    }
}
