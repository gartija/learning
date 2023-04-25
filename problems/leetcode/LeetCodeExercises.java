package problems.leetcode;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

public class LeetCodeExercises {
    public static void main(String[] args) {
        int [] arr1 = {2,7,11,15};
        int [] arr2 = {-3,4,3,90};
        char [][] arr3 = {{'.','.','5','.','.','.','.','.','.'},{'1','.','.','2','.','.','.','.','.'},{'.','.','6','.','.','3','.','.','.'},{'8','.','.','.','.','.','.','.','.'},{'3','.','1','5','2','.','.','.','.'},{'.','.','.','.','.','.','.','4','.'},{'.','.','6','.','.','.','.','.','.'},{'.','.','.','.','.','.','.','9','.'},{'.','.','.','.','.','.','.','.','.'}};
        char []arr4 = {'h','e','l','l','o'};
        LeetCodeExercises s = new LeetCodeExercises();
        int n = s.reverse(-123);
        //s.reverseString(arr4);
        //int []answer = s.twoSum(arr2,0);
        //boolean b = s.isValidSudoku(arr3);
        //System.out.println(b);
        //int a = s.singleNumber(arr);
        //s.rotate(arr, 3);
        //System.out.println(Arrays.toString(arr));
        //s.firstUniqChar("dabbcb");
        //s.isAnagram("anagram","nagaram");
        //s.isPalindrome("A man, a plan, a canal: Panama");
        //s.myAtoi("  -0004193 with words");
        //String []arr5 = {"ab","a"};
        // s.longestCommonPrefix(arr5);
    }

    public void rotate(int[] nums, int k) {
        
        if(k>0) {
            k = k>nums.length?k%nums.length:k;
            int []temp = new int[k];
            int t;
            for(int i=0;i<k;i++) {
                temp[i] = nums[nums.length-k+i];
            }
            for(int i=0;i<nums.length;i++) {
                t = nums[i];
                nums[i] = temp[i%k];
                temp[i%k] = t;
            }
        }
    }

    public boolean containsDuplicate(int[] nums) {
        if(nums.length>1) {
            Set<Integer> set = new HashSet<>();
            for(int i = 0; i<nums.length;i++) {
                if(!set.add(nums[i])) {
                    return true;
                }
            }
        }
        return false;

        /*
        This one works but performance is poor
        if(nums.length>1) {
            //int []numsClone = nums.clone();            
            return Arrays.stream(nums).distinct().count()==nums.length;
        }
        else {
            return false;
        }*/
    }

    public int singleNumber(int[] nums) {
        if(nums.length==1) {
            return nums[0];
        }
        else {
            Set <Integer>uniques = new HashSet<>();
            for(int i=0; i<nums.length;i++) {
                if(uniques.contains(nums[i])) {
                    uniques.add(nums[i]);
                }
                else {
                    uniques.remove(nums[i]);
                }
            }
            return uniques.iterator().next();
        }
    }

    public int[] intersect(int[] nums1, int[] nums2) {
        int retSize = nums1.length>nums2.length?nums2.length:nums1.length;
        int []ret = new int[retSize];
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        int j=0, i = 0,k=0;
        while(i<nums1.length && k<nums2.length) {
            while(i<nums1.length && k<nums2.length && nums2[k]<nums1[i]) {
                k++;
            }
            if(k<nums2.length && i<nums1.length && nums2[k]==nums1[i]) {
                ret[j]=nums1[i];
                j++;
                k++;
                i++;
            }
            else {
                while(i<nums1.length && k<nums2.length && nums2[k]>nums1[i]) {
                    i++;
                }
            }
        }
        return Arrays.copyOf(ret, j);
    }

    //Both solutions are poorly performant. A performant solution I found is using the same array and finding in advance if it needs an additional position.
    public int[] plusOne(int[] digits) {
        StringBuilder b = new StringBuilder(); {
            for(int d : digits) {
                b.append(d);
            }
        }
        BigInteger ret = new BigInteger(b.toString()).add(new BigInteger("1"));
        String s= ret.toString(10);
        int []retN = new int[s.length()];
        int i = 0;
        for(char c : s.toCharArray()) {
            retN[i] = Integer.parseInt(c+"");
            i++;
        }
        return retN;
        //ret;
        
        /*LinkedList<Integer> ret = new LinkedList<>();
        boolean done = false; 
        for(int i=digits.length-1;i>=0;i--) {
            if(done) {
                ret.addFirst(digits[i]);
            }
            else {
                if(digits[i]==9) {
                    ret.addFirst(0);
                }
                else {
                    ret.addFirst(digits[i]+1);
                    done=true;
                }
            }
        }
        if(!done) {
            ret.addFirst(1);
        }
        return ret.stream().mapToInt(Integer::intValue).toArray();*/
        //return null;
    }

    public void moveZeroes(int[] nums) {
        int curNum = nums[0],lastNonO = curNum!=0?1:0;
        for(int i=1;nums.length>1 && i<nums.length;i++)  {
            if(nums[i]!=0) {
                if(curNum == 0) {
                    nums[lastNonO] = nums[i];
                    nums[i] = 0;
                }
                lastNonO++;
            }
            curNum = nums[i];
        }
    }

    public int[] twoSum(int[] nums, int target) {
        int ret[] = new int[2];
        if(nums.length==2) {
            ret[0]=0;
            ret[1]=1;
            return ret; 
        }
        else {
            var i=0; 
            var done = false;
            Map.Entry<Integer,Integer> head,tail;
            TreeMap<Integer,Integer> finderMap = new TreeMap<>();        
            for(var n : nums) {
                if(finderMap.containsKey(n) && n*2==target) {
                    ret[0]=finderMap.get(n);
                    ret[1]=i;
                    return ret;
                }
                else {
                    finderMap.put(n, i);
                    ++i;
                }
            }
            head = finderMap.pollFirstEntry();
            while(!done) {
                tail = finderMap.pollLastEntry();
                if(tail.getKey()+head.getKey()<=target) {                    
                    while(tail.getKey()+head.getKey()<target) {
                        head = finderMap.pollFirstEntry();
                    }
                    if(tail.getKey()+head.getKey() == target) {
                        ret[0] = head.getValue();
                        ret[1] = tail.getValue();
                        done = true; 
                    }
                }
            }
        }
        return ret; 
    }

    public boolean isValidSudoku(char[][] board) {
        Set<?> []rows = new HashSet<?>[9];
        Set<?> []cols = new HashSet<?>[9];
        Set<?> [][]quads = new HashSet<?>[3][3];
        int quadRow,quadCol;
        for(int i = 0 ; i<9; i++) {
            quadRow = i/3;
            for(int j=0;j<9;j++) {
                quadCol = j/3;
                if(board[i][j]!='.') {
                    if(rows[i]!=null && rows[i].contains(board[i][j])) {
                        return false;
                    }
                    else if(rows[i]==null) {
                        rows[i] = new HashSet<Character>();   
                    }                    
                    ((HashSet<Character>)rows[i]).add(board[i][j]);
                    if(cols[j]!=null && cols[j].contains(board[i][j])) {
                        return false;
                    }
                    else if(cols[j]==null) {
                        cols[j] = new HashSet<Character>();
                    }
                    ((HashSet<Character>)cols[j]).add(board[i][j]);
                    if(quads[quadRow][quadCol]!=null && quads[quadRow][quadCol].contains(board[i][j])) {
                        return false;
                    }
                    else if(quads[quadRow][quadCol]==null) {
                        quads[quadRow][quadCol] = new HashSet<Character>();
                    }
                    ((HashSet<Character>)quads[quadRow][quadCol]).add(board[i][j]);
                }
            }
        }
        return true;
    }

    public void rotate(int[][] matrix) {        
        int pointO = 0, bufferOne, bufferTwo;
        for(int lengthPrima = matrix.length-1;lengthPrima>0;lengthPrima-=2,pointO++) {
            for(int i = 0;i<lengthPrima;i++) {
                bufferOne = matrix[pointO][pointO+i];
                bufferTwo = matrix[pointO+i][pointO+lengthPrima];
                matrix[pointO+i][pointO+lengthPrima] = bufferOne;
                bufferOne = bufferTwo;
                bufferTwo = matrix[pointO+lengthPrima][pointO+lengthPrima-i];
                matrix[pointO+lengthPrima][pointO+lengthPrima-i] = bufferOne;
                bufferOne = bufferTwo;
                bufferTwo = matrix[pointO+lengthPrima-i][pointO];
                matrix[pointO+lengthPrima-i][pointO] = bufferOne;
                bufferOne = bufferTwo;
                matrix[pointO][pointO+i] = bufferOne;
            }
        }
    }

    public void reverseString(char[] s) {
        char temp; 
        for(int i=0;i<s.length/2;i++) {
            temp = s[i];
            s[i] = s[s.length-1-i];
            s[s.length-1-i] = temp;
        }
    }

    public int reverse(int x) {
        //What to do with negatives
        //How to test it exceeds 32 bits
        char []numToBeInverted = Integer.toString(x).toCharArray();
        char temp;
        int offset = numToBeInverted[0] == '-'?1:0;
        //int upTo = numToBeInverted[0] == '-'?(numToBeInverted.length/2):numToBeInverted.length/2;
        for(int i=0; i<numToBeInverted.length/2;i++) {
            temp = numToBeInverted[i+offset];
            numToBeInverted[i+offset] = numToBeInverted[numToBeInverted.length-1-i];
            numToBeInverted[numToBeInverted.length-1-i] = temp;
        }
        try {
            return Integer.parseInt(new String(numToBeInverted));
        }catch(NumberFormatException e) {
            return 0;
        }   
    }

    public int firstUniqChar(String s) {
        int []nonRepeatedAppearance = new int[26];
        Set<Integer> repeated = new HashSet<>();
        int currentChar; 
        int offset = 97;
        int firstAppearance = -1;
        for(int i = 0; i<s.length(); i++) {
            currentChar = s.codePointAt(i);    
            if(!repeated.contains(currentChar)) {
                repeated.add(currentChar);
                nonRepeatedAppearance[currentChar-offset] = i+1;
            }
            else {
                nonRepeatedAppearance[currentChar-offset] = 0;
            }
        }
        for(int c : nonRepeatedAppearance) {
            if(c>0) {
                if(firstAppearance==-1 || c-1<firstAppearance) {
                    firstAppearance = c-1;
                }
            }
        }
        return firstAppearance;
    }

    public boolean isAnagram(String s, String t) {
        if(s.length()!=t.length()) {
            return false; 
        } 
        else {
            int []charCount = new int[26];
            int index;
            //Is it valid to use this lambda?
            s.codePoints().forEach(ch -> charCount[ch-97]= charCount[ch-97]+1);
            for(int i = 0; i<t.length();i++) {
                index = t.codePointAt(i);
                if(charCount[index-97]==0) {
                    return false;
                }
                else {
                    --charCount[index-97];
                }
            }
            
        }
        return true;

        /*StringBuilder tBuild = new StringBuilder(t);
        int charIndex;
        for(int i = 0 ; i<s.length();i++) {
            charIndex = tBuild.indexOf(s.charAt(i)+"");
            if(charIndex == -1) {
                return false; 
            }
            else {
                tBuild.deleteCharAt(charIndex);
            }
        }
        return tBuild.isEmpty()?true:false;*/
    }


    public boolean isPalindrome(String s) {
        if(s.isEmpty()) {
            return true; 
        }
        StringBuilder sbuilder = new StringBuilder();
        s.toLowerCase().codePoints().forEach(c-> {
            if(Character.isAlphabetic(c) || Character.isDigit(c)) {
                sbuilder.appendCodePoint(c);
            }
        });
        for(int i = 0; i<sbuilder.length()/2; i++) {
            if(sbuilder.charAt(i)!=sbuilder.charAt(sbuilder.length()-i-1)) {
                return false; 
            }
        }
        return true;    
    }

    //Estudiar solución más óptima 
    public int myAtoi(String s) {
        s = s.trim();
        StringBuilder intBuilder = new StringBuilder();
        char currentChar;
        boolean positive = true;
        if(s.length()>0) {
            for(int i=0;i<s.length();i++) {
                currentChar = s.charAt(i);
                if(i==0 && currentChar == '-') {
                    positive = false;
                    continue;
                }
                else if(i==0 && currentChar == '+') {
                    continue;
                }
                if(Character.isDigit(currentChar)) {
                    intBuilder.append(currentChar);
                }
                else {
                    break; 
                }
            }
            if(intBuilder.length()>0) {
                try {
                    if(!positive) intBuilder.insert(0, "-");
                    
                    int ret = Integer.parseInt(intBuilder.toString());
                    return ret;
                }
                catch(NumberFormatException e) {
                    return positive?Integer.MAX_VALUE:Integer.MIN_VALUE;
                }
            }
        }
        return 0;
    }

    public int strStr(String haystack, String needle) {
        return haystack.indexOf(needle);
    }

    public String longestCommonPrefix(String[] strs) {
        boolean done = false;
        StringBuilder currentPrefix = new StringBuilder();
        int shortestString = strs[0].length();
        end: for(int i = 0; !done && i<shortestString;i++) {
            char curChar = strs[0].charAt(i);
            for(int j = 1;j<strs.length;j++) {
                if(strs[j].length()<shortestString) {
                    shortestString = strs[j].length();
                }
                if(strs[j].length()==i || strs[j].charAt(i)!=curChar) {
                    done = true;
                    continue end;
                }
            }
            currentPrefix.append(curChar);
        }
        return currentPrefix.toString();

        /*Non performant
        StringBuilder currentPrefix = new StringBuilder(strs[0]);   
        for(int i = 1;i<strs.length;i++) {
            currentPrefix.setLength(Math.min(currentPrefix.length(), strs[i].length()));
            for(int j = 0;j<Math.min(currentPrefix.length(), strs[i].length());j++) {
                if(currentPrefix.charAt(j)!=strs[i].charAt(j)) {
                    currentPrefix.setLength(j);
                    continue;
                } 
            }
        }       
        return currentPrefix.toString();*/
    }

    class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int x) { val = x; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    } 

    public void deleteNode(ListNode node) {
        node.val = node.next.val;
        node.next = node.next.next;    
    }

    //@TODO: I cannot make it to work.
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode minusNthNode=head,currentNode=head;
        int i=1;
        while(currentNode!=null) {
            if(i==n) {
                minusNthNode = minusNthNode.next;
            }
            else {
                ++i;
            }
            currentNode = currentNode.next;
        }
        if(minusNthNode==head) {
            return head.next;
        }
        else if(head.next == minusNthNode) {
            head.next = null;
            return head;
        }
        else {
            minusNthNode.val = minusNthNode.next.val;
            minusNthNode.next = minusNthNode.next.next;
            return head;
        }
    }

    
    public ListNode reverseList(ListNode head) {
        ListNode current = head, prev = null, next; 
        while(current!=null) {
            next = current.next;
            current.next = prev;
            prev = current;
            current = next;
        } 
        return prev;
    }
    
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        if(list1==null && list2==null) return null;
        
        ListNode current = new ListNode(), newListHead = current;
        while(list1!=null || list2!=null) {
            if(list1!=null && list2!=null) {
                if(list1.val<=list2.val) {
                    current.val = list1.val;
                    list1 = list1.next;
                }
                else {
                    current.val = list2.val;
                    list2 = list2.next;
                }
            }
            else if(list1!=null && list2==null) {
                current.val = list1.val;
                list1 = list1.next;
            }
            else {
                current.val = list2.val;
                list2 = list2.next;
            }
            if(list1!=null || list2!=null) {
                current.next = new ListNode();
                current = current.next;
            }
        }
        return newListHead;
    }

    //Did the brute force approach with a StringBuilder
    public boolean isPalindrome(ListNode head) {
        StringBuilder palindromBuilder = new StringBuilder();
        while(head!=null) {
            palindromBuilder.append(head.val);
            head = head.next;
        }        
        for(int i = 0 ; i< palindromBuilder.length()/2 ;i++) {
            if(palindromBuilder.charAt(i)!=palindromBuilder.charAt(palindromBuilder.length()-i-1)) {
                return false;
            }
        }
        return true;     
    }


    //Very poor performance but it works. 
    public boolean hasCycle(ListNode head) {
        ListNode monitor = head, xNode;
        if(monitor!= null && monitor.next!=null && monitor==monitor.next) {
            return true;
        }
        while(monitor!= null && monitor.next!=null) {
            xNode = head;
            monitor = monitor.next;
            if(monitor == monitor.next) {
                return true; 
            }
            while(xNode!=monitor) {                
                 if(xNode == monitor.next) {
                    return true;
                 }
                 else {
                    xNode = xNode.next;
                 }
            }
        }
        return false;  
    }

    /**
     * Definition for a binary tree node.
     * public class TreeNode {
     *     int val;
     *     TreeNode left;
     *     TreeNode right;
     *     TreeNode() {}
     *     TreeNode(int val) { this.val = val; }
     *     TreeNode(int val, TreeNode left, TreeNode right) {
     *         this.val = val;
     *         this.left = left;
     *         this.right = right;
     *     }
     * }
     */

     public class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;
        TreeNode() {}
        TreeNode(int val) { this.val = val; }
        TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
            this.left = left;
            this.right = right;
        }
    }

    class Solution {
        public int maxDepth(TreeNode root) {
            if(root == null) {
                return 0;
            }
            if(root.left != null && root.right != null) {
                int left = maxDepth(root.left);
                int right = maxDepth(root.right); 
                return left>=right?left+1:right+1;
            }
            else if(root.left != null && root.right == null) {
                int left = maxDepth(root.left);
                return left+1;
            }
            else if(root.left == null && root.right != null) {
                int right = maxDepth(root.right);
                return right+1;
            }
            else {
                return 1;
            }
        }
    }

    
    public boolean isValidBST(TreeNode root) {
        if(root == null) {
            return true;
        }  
        return isValidBST(root, null, null);
    }

    private boolean isValidBST(TreeNode root, Integer notLessThan, Integer notGreaterThan) {
        boolean ret = false;  
        if(root.left!= null) {
            if(root.left.val<root.val && (notLessThan==null || root.left.val>notLessThan)) {
                ret = isValidBST(root.left,notLessThan,root.val);
            }
            else {
                ret=false;
            }
        }
        else {
            ret = true;  
        }
        if(!ret) {
            return false;
        }        
        if(root.right!= null) {
            if(root.right.val>root.val && (notGreaterThan==null || root.right.val<notGreaterThan)) {
                ret = isValidBST(root.right,root.val,notGreaterThan);
            }
            else {
                ret = false;
            }
        }
        else {
            ret = true; 
        }
        return ret;

    }

    public boolean isSymmetric(TreeNode root) {
        Deque<TreeNode> currQueue = new LinkedList<>();
        Deque<TreeNode> nextQueue;
        if(root.left==null) {
            currQueue.add(new TreeNode(-500));    
        }
        else {
            currQueue.add(root.left);    
        }
        if(root.right==null) {
            currQueue.add(new TreeNode(-500));
        }
        else {
            currQueue.add(root.right);
        }
        
        
        while(!currQueue.isEmpty()) {
            Iterator<TreeNode> it = currQueue.iterator();
            nextQueue = new LinkedList<>();
            while(it.hasNext()) {
                var curNode = it.next();
                if(curNode!=null) {
                    if(curNode.left==null && curNode.right!=null) {
                        nextQueue.add(new TreeNode(-500));
                    }
                    else {
                        nextQueue.add(curNode.left);    
                    }
                    if(curNode.right==null && curNode.left!=null) {
                        nextQueue.add(new TreeNode(-500));
                    }
                    else {
                        nextQueue.add(curNode.right);
                    }
                }
            }
            while(currQueue.peek()!=null && currQueue.peekLast()!=null) {
                TreeNode head=currQueue.pollFirst();
                TreeNode tail=currQueue.pollLast();
                if( head.val!=tail.val ) {
                    return false;
                }    
            }
            currQueue = nextQueue;
        }
        return true;
    }
    
}
