class Solution {
    // submit on NEETCODE
    // code - NEETCODE
    // STRIVER for explaination
    // T: O(V+E+N); N = SUM OF ALL LENGTH OF STRINGS
    // S: O(V+E)
    public String foreignDictionary(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // initialize
        // since here we don't know, V and K
        for(String w: words){
        for(char ch : w.toCharArray()){
            adj.putIfAbsent(ch, new HashSet<>());
            indegree.putIfAbsent(ch, 0);
        }
        }

        //iterate for each word and populate in graph (adj)
        for(int i=0; i<words.length-1; i++){
            String s1 = words[i];
            String s2 = words[i+1];
            int len = Math.min(s1.length(), s2.length());

            if(s1.length() > s2.length()
               && s1.substring(0, len).equals(s2.substring(0,len))){
                return ""; //invalid, cannot differentiate b/w words in alien dict.
            }

            for(int j=0; j<len; j++){
                if(s1.charAt(j) != s2.charAt(j)){
                    if(!adj.get(s1.charAt(j)).contains(s2.charAt(j))){
                        // add edge
                        adj.get(s1.charAt(j)).add(s2.charAt(j));
                        // increase indegree of s2 as incoming edge there
                        indegree.put(s2.charAt(j), indegree.get(s2.charAt(j))+1);
                    }
                    break;
                }
            }
        }   

        // TOPO SORT
        // KAHN ALGO
        Queue<Character> q = new LinkedList<>();
        for(char ch : indegree.keySet()){
            if(indegree.get(ch) == 0){
                q.offer(ch);
            }
        }

        StringBuilder res = new StringBuilder();
        while(!q.isEmpty()){
            char ch = q.poll();
            res.append(ch);

            for(char nbr: adj.get(ch)){
                indegree.put(nbr, indegree.get(nbr)-1);

                if(indegree.get(nbr) == 0){
                    q.offer(nbr);
                }
            }
        }

        if(res.length() != indegree.size()){
            return "";
        }

        return res.toString();
    }
}
