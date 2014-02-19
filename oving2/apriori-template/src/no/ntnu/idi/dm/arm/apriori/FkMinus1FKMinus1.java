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
		int allGeneratedCandidatesCounter = 0;
		Set<ItemSet<V>> frequentCandidateSet = new HashSet<ItemSet<V>>();
        ItemSet<V> frequentCandidateOne;
        ItemSet<V> frequentCandidateTwo;
        ItemSet<V> newCombination;
		for (int i = 0;i<frequentCandidatesKMinus1.size()-1;i++ ){
            frequentCandidateOne = frequentCandidatesKMinus1.get(i);
            for (int j = 0; j<frequentCandidatesKMinus1.size();j++){
                frequentCandidateTwo = frequentCandidatesKMinus1.get(j);
                Iterator<V> iteratorOne = frequentCandidateOne.getItems().iterator();
                Iterator<V> iteratorTwo = frequentCandidateTwo.getItems().iterator();
                int counter = 0;
                while (iteratorOne.hasNext()){
                    if(iteratorOne.next()!=iteratorTwo.next()){
                        break;
                    }
                    counter++;
                }
                if(counter==frequentCandidateOne.size()-1){
                    newCombination = frequentCandidateOne.union(frequentCandidateTwo);
                    frequentCandidateSet.add(newCombination);
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
