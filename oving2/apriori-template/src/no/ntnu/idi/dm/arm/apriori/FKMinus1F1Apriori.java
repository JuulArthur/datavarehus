package no.ntnu.idi.dm.arm.apriori;

import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class FKMinus1F1Apriori<V> extends BaseApriori<V> {

	public FKMinus1F1Apriori(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public List<ItemSet<V>> aprioriGen(
			List<ItemSet<V>> frequentCandidatesKMinus1) {
		Collections.sort(frequentCandidatesKMinus1);
        // Keep count of how many new combinations we have generated
		int allGeneratedCandidatesCounter = 0;
        // The list of all new combinations created
		Set<ItemSet<V>> frequentCandidateSet = new HashSet<ItemSet<V>>();
		
		for (ItemSet<V>frequentCandidate : frequentCandidatesKMinus1 ){
            for (ItemSet<V> oneItem : frequent1Itemsets) {
                // Checks that the item is not in the set already
                if(frequentCandidate.difference(oneItem).size() == frequentCandidate.size()){
                    // Create the new combination and add it to the set
                    ItemSet<V> newCombination = frequentCandidate.union(oneItem);
                    frequentCandidateSet.add(newCombination);
                    // Calculate support and confidence
                    getAndCacheSupportForItemset(newCombination);
                    allGeneratedCandidatesCounter++;
                }
            }
        }
        System.out.println(allGeneratedCandidatesCounter
                + " total, unique itemsets: " + frequentCandidateSet.size());
        return new LinkedList<ItemSet<V>>(frequentCandidateSet);
	}
}
