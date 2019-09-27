package cecs429.index;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/*import java.util.Collections;
import java.util.List;*/

/**
	 * Implements an Index using a HashMap. Requires knowing the full corpus vocabulary and number of documents
	 * prior to construction.
	 */
public class InvertedIndex implements Index {
	//  	private static final List results = null;
	//private final boolean[][] mMatrix;
		private final List<String> mVocabulary;
		private int mCorpusSize;
	//	ArrayList arr;
		/*Creating a constructor of InvertedIndex*/
		public InvertedIndex(Collection<String> vocabulary, int corpuseSize) {
		//		mMatrix = new boolean[vocabulary.size()][corpuseSize];
		//		arr = new ArrayList<integer>();
				mVocabulary = new ArrayList<String>();
				mVocabulary.addAll(vocabulary);
				mCorpusSize = corpuseSize;
				
				Collections.sort(mVocabulary);
			}
		
		/*Creating a Hashmap for List<posting>*/
		private HashMap<String, List<Posting>> map=new HashMap<>();
				
		
		public void addTerm(String term,int documentId) {
			/*Creating a List of Posting*/
			List<Posting> myList = new ArrayList<Posting>();	
			Posting P = new Posting(documentId);
			
			if(!map.containsKey(term)){
				myList.add(P);
				map.put(term, myList);				
			}
			else {
				List<Posting> results = new ArrayList<>(); 
				results=map.get(term);
				
				if(results.get(results.size()-1).getDocumentId()!=documentId) {
					results.add(P);
				}
				map.put(null, results);
		}
			
		}
		
		/*Creating list<posting> for distinct documents id*/
		@Override
		public List<Posting> getPostings(String term) {
			return map.get(term);
		}
		
		public List<String> getVocabulary(){
			Set<String> keys =map.keySet();
			List<String> listKey=new ArrayList<String>();
			for(String key:keys)
			{
				listKey.add(key);
			}
			
			return listKey;
		}

		/*public HashMap<String, List<Posting>> getMap() {
			return map;
		}

		public void setMap(HashMap<String, List<Posting>> map) {
			this.map = map;
		}*/
}
