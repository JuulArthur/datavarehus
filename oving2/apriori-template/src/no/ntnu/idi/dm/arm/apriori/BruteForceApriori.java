package no.ntnu.idi.dm.arm.apriori;

import java.util.List;

public class BruteForceApriori<V> extends BaseApriori<V> {

    public BruteForceApriori(List<ItemSet<V>> transactions) {
        super(transactions);
    }

    @Override
    public void apriori(Double minSupport) {
        // get frequent 1-itemsets
        List<ItemSet<V>> generateCandidateSizeK = getAllItemsetsOfSizeOne();
        // The list of the set we have after pruning
        List<ItemSet<V>> currentLevelFrequent;
        System.out.println("Level 1:");
        System.out.println("\tGenerated " + generateCandidateSizeK.size()
                + " candidates.");
        System.out.println("\t\t" + generateCandidateSizeK);
        currentLevelFrequent = pruneInfrequentCandidates(minSupport,generateCandidateSizeK);
        System.out.println("\tKept " + currentLevelFrequent.size()
                + " frequent itemsets");
        for (int i = 2;i<10; i++) {

            // generate candidates for level i
            System.out.println("Level " + i);

            // generate candidates from all possible candidates at the previous step
            generateCandidateSizeK = aprioriGen(generateCandidateSizeK);
            System.out.println("\tGenerated " + generateCandidateSizeK.size()
                    + " candidates.");
            System.out.println("\t\t" + generateCandidateSizeK);

            // prune
            currentLevelFrequent = pruneInfrequentCandidates(minSupport,
                    generateCandidateSizeK);

            System.out.println("\tKept " + currentLevelFrequent.size()
                    + " frequent itemsets");
            if (currentLevelFrequent.size() == 0) {
                System.out
                        .println("We're done here, there's no more frequent itemsets");
                break;
            }
            // store in our list of frequent itemsets
            frequentItemSets.put(i, currentLevelFrequent);

            System.out.println("\t\t" + currentLevelFrequent);
        }

    }

}