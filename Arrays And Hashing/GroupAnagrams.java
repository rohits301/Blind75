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

class Solution {
    // OPTIMAL
    // T: O(m * n * 26), m = strs.length, n = max length of strs[i] 
    // S : O(m)
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0) return Collections.emptyList();
        Map<String, List<String>> map = new HashMap<>();
        for(String s: strs){
            //char type 0~127 is enough for constraint 0 <= strs[i].length <= 100
            //char array to String is really fast, thanks @legendaryengineer
            //You should use other data type when length of string is longer.
            //E.g. Use byte (-128 to 127), short (-32,768 to 32,767),
            //int. -2,147,483,648 to 2,147,483,647
            char[] frequencyArr = new char[26];
            for(int i = 0;i<s.length();i++){
                frequencyArr[s.charAt(i) - 'a']++;
            }
            //6 ms use char(0~127) array and new String(frequencyArr) method.
            //17ms when use byte (-128 to 127) array and Arrays.toString(frequencyArr) method
            //29ms when use int(-2,147,483,648 to 2,147,483,647) and Arrays.toString(frequencyArr) method
            String key = new String(frequencyArr);
            List<String> tempList = map.getOrDefault(key, new ArrayList<String>());
            tempList.add(s);
            map.put(key,tempList);
        }
        return new ArrayList<>(map.values());
    }
}