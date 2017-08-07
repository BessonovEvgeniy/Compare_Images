package service;

import model.DifferenceImageBlock;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;

public interface DifferenceImageBlockService {

    LinkedHashMap<Integer, Integer> divideDataToBlocks(Integer size, Integer thole, LinkedList<Integer> registeredDiff);

    List<DifferenceImageBlock> findBlocksOfDifferences(LinkedList<Integer> registeredDiffByX, LinkedList<Integer> registeredDiffByY);
}

