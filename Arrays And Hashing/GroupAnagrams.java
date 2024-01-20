class Solution {
    // T: O(m * nlogn) m = strs.length, n = max length of strs[i] 
    // S : O(m)
    public List<List<String>> groupAnagrams(String[] strs) {
        
        HashMap<String, List<String>> map = new HashMap<>();
        for(String s : strs){
            //sorting the individual string from strs array to get unique key
            char[] arr = s.toCharArray();
            Arrays.sort(arr); 
            String keyStr = new String(arr);

            //For each key, create a new arraylist if it is absent and add string corresponding to key
            map.putIfAbsent(keyStr, new ArrayList<>());
            map.get(keyStr).add(s);
        }
        // map.values() returns a collection, so we use constructor of ArrayList class in Java to create AL with elements in the collection
        return new ArrayList<>(map.values());
    }
}