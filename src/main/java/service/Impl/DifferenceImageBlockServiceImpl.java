package service.Impl;

import model.DifferenceImageBlock;
import service.DifferenceImageBlockService;

import java.util.*;

public class DifferenceImageBlockServiceImpl implements DifferenceImageBlockService {

    protected Integer thole , x, y = 1;

    public DifferenceImageBlockServiceImpl (Integer x, Integer y, Integer thole) {
        this.x = x;
        this.y = y;
        this.thole = thole;
    }

    public LinkedHashMap<Integer, Integer> divideDataToBlocks(Integer size, Integer thole, LinkedList<Integer> registeredDiff) {

        SortedSet<Integer> sortedUniqueX = Utils.findUniqueSortedData(registeredDiff);

        LinkedList<Integer> indexP = Utils.setFlagArray(size, sortedUniqueX);

        LinkedList<Integer> diffP = Utils.diff(indexP);

        LinkedList<Integer> sleepP = Utils.findGreaterThen(diffP, thole);

        LinkedList<Integer> pfp = new LinkedList<>();
        LinkedList<Integer> zfp = new LinkedList<>();

        pfp.add(sortedUniqueX.first());
        pfp.addAll(Utils.icrementElements(sleepP));
        zfp.addAll(sleepP);
        zfp.add(sortedUniqueX.last());

        LinkedHashMap<Integer, Integer> map = Utils.convertArraysToMap(pfp, zfp);

        return map;
    }

    public List<DifferenceImageBlock> findBlocksOfDifferences(LinkedList<Integer> registeredDiffByX, LinkedList<Integer> registeredDiffByY) {

        List<DifferenceImageBlock> diffBlocks = new ArrayList<>();

        LinkedHashMap<Integer, Integer> blocksByX = divideDataToBlocks(x, thole, registeredDiffByX);

        LinkedHashMap<Integer, Integer> blocksByY = divideDataToBlocks(y, thole, registeredDiffByY);

        for (Map.Entry<Integer, Integer> blockByX : blocksByX.entrySet()) {
            int blockX1 = blockByX.getKey();
            int blockX2 = blockByX.getValue();

            LinkedList<Integer> indexesByX = Utils.findValueIndexBetween(registeredDiffByX, blockX1, blockX2);

            for (Map.Entry<Integer, Integer> blockByY : blocksByY.entrySet()) {
                int blockY1 = blockByY.getKey();
                int blockY2 = blockByY.getValue();

                LinkedList<Integer> indexesByY = Utils.findValueIndexBetween(registeredDiffByY, blockY1, blockY2);

                LinkedList<Integer> intersection = Utils.intersect(indexesByX, indexesByY);

                if (!intersection.isEmpty()) {

                    int x1 = registeredDiffByX.get(intersection.getFirst());
                    int x2 = registeredDiffByX.get(intersection.getLast());

                    int y1 = registeredDiffByY.get(intersection.getFirst());
                    int y2 = registeredDiffByY.get(intersection.getLast());

                    diffBlocks.add(new DifferenceImageBlock(x1, x2, y1, y2)) ;
                }
            }
        }
        return diffBlocks;
    }
}
