package textanalyzer;

import java.util.Comparator;
//create a class that will sort the pairs word-frequency in descending order of the occurrence
public class SortingTool implements Comparator<PairOfWordsAndOccurrences> {

	@Override
	public int compare(PairOfWordsAndOccurrences one, PairOfWordsAndOccurrences two) {
		// TODO Auto-generated method stub
		return two.occurrence - one.occurrence;
	}

}
