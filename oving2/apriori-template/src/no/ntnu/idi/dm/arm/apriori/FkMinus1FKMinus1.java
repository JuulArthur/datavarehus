package no.ntnu.idi.dm.arm.apriori;

import java.util.*;

public class FkMinus1FKMinus1<V> extends BaseApriori<V> {

	public FkMinus1FKMinus1(List<ItemSet<V>> transactions) {
		super(transactions);
	}

	@Override
	public List<ItemSet<V>> aprioriGen(
			List<ItemSet<V>> frequentCandidatesKMinus1) {

		Collections.sort(frequentCandidatesKMinus1);
        // Keep track of how many combinations we have created
		int allGeneratedCandidatesCounter = 0;
        // The set to put all the new combinations
		Set<ItemSet<V>> frequentCandidateSet = new HashSet<ItemSet<V>>();
        // The first candidate to combine to another
        ItemSet<V> frequentCandidateOne;
        // The second candidate to combine to the first
        ItemSet<V> frequentCandidateTwo;
        // The new combination created
        ItemSet<V> newCombination;
		for (int i = 0;i<frequentCandidatesKMinus1.size()-1;i++ ){
            frequentCandidateOne = frequentCandidatesKMinus1.get(i);
            for (int j = 0; j<frequentCandidatesKMinus1.size();j++){
                frequentCandidateTwo = frequentCandidatesKMinus1.get(j);
                // Get the iterators, so we can check if the elements in the different sets are equal
                Iterator<V> iteratorOne = frequentCandidateOne.getItems().iterator();
                Iterator<V> iteratorTwo = frequentCandidateTwo.getItems().iterator();
                // To keep track of how many of the elements are equal
                int counter = 0;
                // Add to the counter how many elements that are equal
                while (iteratorOne.hasNext()){
                    if(iteratorOne.next()!=iteratorTwo.next()){
                        break;
                    }
                    counter++;
                }
                // If all elements are equal except from the last one we want to combine these two sets
                if(counter==frequentCandidateOne.size()-1){
                    newCombination = frequentCandidateOne.union(frequentCandidateTwo);
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
