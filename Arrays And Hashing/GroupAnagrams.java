class Solution {
    // T: O(m * nlogn), n = strs.length, m = avg. length of strs[i] 
    // S : O(m * n)
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
    // T: O(n * m), n = strs.length, m = avg. length of strs[i] 
    // S : O(n * m), since all strings are stored in map, so `n` string of `m` length 
    public List<List<String>> groupAnagrams(String[] strs) {
        if(strs == null || strs.length == 0){
            return Collections.emptyList();
        }
        Map<String, List<String>> map = new HashMap<>();
        
        for(String str: strs){
            char[] frequencyArr = new char[26];
            for(int i = 0; i < str.length(); i++){
                frequencyArr[str.charAt(i) - 'a']++;
            }
            
            String key = new String(frequencyArr); // cannot use .toString() because char[].toString() calls Object.toString(), which returns a memory address-like format ([C@hashcode])
            List<String> tempList = map.getOrDefault(key, new ArrayList<String>());
            tempList.add(str);
            map.put(key, tempList);
        }
        return new ArrayList<>(map.values());
    }
}
