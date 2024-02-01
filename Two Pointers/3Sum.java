class Solution {
    // refer STRIVER's VIDEO
    // BRUTE FORCE
    // T: O(n^3), S: O(no. of unique triplets)
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        Set<List<Integer>> set = new HashSet();

        int n = nums.length;
        for (int i = 0; i < n - 2; i++) {
            for (int j = i + 1; j < n - 1; j++) {
                for (int k = j + 1; k < n; k++) {

                    if (nums[i] + nums[j] + nums[k] == 0) {
                        List<Integer> temp = new ArrayList<>();
                        Collections.addAll(temp, nums[i], nums[j], nums[k]);
                        Collections.sort(temp);
                        set.add(temp);
                    }
                }
            }
        }

        for (List<Integer> list : set) {
            ans.add(list);
        }
        return ans;
    }
}

class Solution {
    // BETTER
    // T: O(n^2) 
    // S: O(n + no. of unique ele * 2); n=for hashset, no. of unique ele - first is for set, second is for the ans list
    // using set, an external DS to store the answer 
    public List<List<Integer>> threeSum(int[] nums) {
        int n = nums.length;
        Set<List<Integer>> set = new HashSet<>();

        for (int i = 0; i < n - 1; i++) {
            Set<Integer> hashset = new HashSet<>();
            for (int j = i + 1; j < n; j++) {

                int third = -(nums[i] + nums[j]);
                if (hashset.contains(third)) {
                    List<Integer> temp = new ArrayList<>();
                    Collections.addAll(temp, nums[i], nums[j], third);
                    Collections.sort(temp);
                    set.add(temp);
                }
                hashset.add(nums[j]);
            }
        }
        return new ArrayList<>(set); // the answer list
    }
}

class Solution {
    // OPTIMAL
    // T: O(n^2 + nlogn), S: O(no. of triplets) 
    // STRIVER video solution for Brute, Better and Optimal
    public List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        int n = nums.length;

        // 1. Sort the array
        Arrays.sort(nums);
        for (int i = 0; i < n; i++) {
            // to avoid duplicate i
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // 2. two-pointer on i and j 
            // for a given i, find j and k such that all sum to zero
            int j = i + 1, k = n - 1;
            while (j < k) {
                int sum = nums[i]+nums[j]+nums[k];
                if(sum < 0){
                    j++;
                } else if(sum >0){
                    k--;
                } else {
                    // sum == 0, found the triplet
                    List<Integer> list = new ArrayList<>();
                    Collections.addAll(list, nums[i],nums[j],nums[k]);
                    ans.add(list);
                    j++;
                    k--;

                    // to avoid duplicate j and duplicate k
                    while(j<k && nums[j] == nums[j-1]) j++;
                    while(j<k && nums[k] == nums[k+1]) k--;
                }
            }
        }
        return ans;
    }
}