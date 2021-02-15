package org.avaeriandev.loottable;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class LootTable<E> {

    private BiMap<Integer, E> table;

    public LootTable() {
        this.table = HashBiMap.create();
    }

    private int getTableBound() {
        int max = 0;
        for(int key : table.keySet()) {
            max += key;
        }
        return max;
    }

    public void put(E reward, int weight) {
        table.put(weight, reward);
        sortWeights();
    }

    public void remove(E reward) {
        table.remove(reward);
    }

    public int getWeight(E reward) {
        return table.inverse().get(reward);
    }

    public BiMap<Integer, E> getTable() {
        return table;
    }

    private List<Integer> sortWeights() {

        List<Integer> rawWeights = new ArrayList<>(table.keySet());
        List<Integer> sortedWeights = new ArrayList<>();

        // Iterate until all indexes have been sorted
        while(rawWeights.size() != 0) {

            int maxIndex = 0;
            int maxWeight = 0;

            // Find largest weight/index
            for(int i = 0; i < rawWeights.size(); i++) {
                if(maxWeight < rawWeights.get(i)) {
                    maxWeight = rawWeights.get(i);
                    maxIndex = i;
                }
            }

            sortedWeights.add(maxWeight);
            rawWeights.remove(maxIndex);
        }

        return sortedWeights;
    }

    public E roll() {

        int totalWeight = getTableBound();
        int random = new Random().nextInt(totalWeight);
        List<Integer> weights = sortWeights();

        // Iterate through weights
        for(int weight : weights) {
            if(random <= weight) {
                return table.get(weight);
            }
            random -= weight;
        }
        return null;
    }

}
