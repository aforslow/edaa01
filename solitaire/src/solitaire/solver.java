import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ratings{
	private Map<String, List<Integer>> ratings;

/** Skapar ett objekt för hantering av betygssättning. */
public Ratings() {
	ratings = new HashMap<String, List<Integer>>();
}

/** Lägger till betyget rating för name. */
public void addRate(String name, int rating) {
	List<Integer> list = ratings.get(name);
	if (list == null){
		list = new ArrayList<Integer>();
		ratings.put(name, list);
	}
		list.add(rating);
}

/** Returnerar medelbetyget för namnet name.
* Om namnet saknar betyg ska 0 returneras. */
public double averageRating(String name) {
	LinkedList<Integer> list = ratings.get(name);
	if (list != null){
		int sum = 0;
		for (Integer e: list){
			sum += e;
		}
		average = ((double) sum)/list.size();
		return average;
	} else{
		return 0;
	}
}

/** Returnerar en map med alla namn och deras respektive medelbetyg. */
public Map<String, Double> allAverageRatings() {
	Set<String> ks = ratings.keySet();
	Map<String, Double> allAv = new HashMap<String, Double>();
	for (String key : ks){
		double val = averageRating(key);
		allAv.put(key, val);
	}
	return allAv;
}
}