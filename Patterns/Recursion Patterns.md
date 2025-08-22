# Quick take aways

- if you wanna unique subsets or combination or permutation remove the duplicate elements it always creates a duplicate combination or subset in it.
- Suppose if you’re allowing the element for duplicate entries you should travel in the same index multiple times (in both taken and non-taken) so that you’ll get the same index combination / subset multiple times - handle the base condition clear for it.

## 1. Take / Not Take (Binary Choice Tree)

### ✨ Concept:

At every step, choose to either include the current element (“take”) or not include it (“not take”).

### 🔍 Use Cases:

- Generating all subsets
- Subset sum problems

### 💡 Intuition:

Recursive tree branches into two choices at each index.

### 🧠 Code Walkthrough:

```java
void dfs(int i, int[] nums, List<Integer> curr, List<List<Integer>> ans) {    
if (i == nums.length) {        
ans.add(new ArrayList<>(curr));        
return;    
}    
dfs(i + 1, nums, curr, ans); // Not take    
curr.add(nums[i]);    
dfs(i + 1, nums, curr, ans); // Take    
curr.remove(curr.size() - 1);
}
```

### 🕒 Time Complexity:

O(2^n) — Each element has two choices (include or skip), forming a binary decision tree.

### 💾 Space Complexity:

O(n) recursion depth + O(2^n * n) output space (since each subset can be up to length n).

### 🎯 Tips & Tricks:

- Base case: `index == nums.length`
- Clone list before adding to result (avoid referencing issues)
- Remember to backtrack after “take”
- Convert to iteration with explicit stack (index, path)
- Dry run with [1,2] gives: [], [1], [2], [1,2]
- Ladder: LC 78 → 90 → 39 → 416
- Use for questions with “include or skip” logic (subsets, sum targets)
- Common pitfalls: missing backtrack or adding same list reference

---

## 2. For-Loop Based Recursion (Pick from a Range)

### ✨ Concept:

Use a loop inside your recursive call to try different starting points and generate combinations.

### 🔍 Use Cases:

- Combination problems
- Palindrome partitioning

### 💡 Intuition:

Loop allows multiple branches to be explored from one point.

### 🧠 Code Walkthrough:

```java
void dfs(int start, int n, int k, List<Integer> curr, List<List<Integer>> ans) {    
    if (curr.size() == k) {        
        ans.add(new ArrayList<>(curr));        
        return;    }    
        for (int i = start; i <= n; i++) {        
             curr.add(i);      
          dfs(i + 1, n, k, curr, ans);        
          curr.remove(curr.size() - 1);    
          }
          }
```

### 🕒 Time Complexity:

O(C(n, k)) — Each k-length combination from n elements is visited once.

### 💾 Space Complexity:

O(k) — Depth of recursive call stack = length of combination.

### 🎯 Tips & Tricks:

- Always backtrack after loop recursion
- Avoid duplicates by increasing `start`
- Use when picking elements from a range
- Dry run on n=3, k=2 shows: [1,2], [1,3], [2,3]
- Use combination-based questions to practice (LC 77, 216)

---

## 3. Permutations (Position Focused)

### ✨ Concept:

Fix one position at a time and try all unused numbers at that position.

### 🔍 Use Cases:

- Generating permutations

### 💡 Intuition:

At each level, fix one value and recurse on rest.

### 🧠 Code Walkthrough:

```java
void permute(List<Integer> nums, List<Integer> curr, boolean[] used, List<List<Integer>> ans) {    if (curr.size() == nums.size()) {        ans.add(new ArrayList<>(curr));        return;    }    for (int i = 0; i < nums.size(); i++) {        if (used[i]) continue;        used[i] = true;        curr.add(nums.get(i));        permute(nums, curr, used, ans);        used[i] = false;        curr.remove(curr.size() - 1);    }}
```

### 🕒 Time Complexity:

O(n!) — n factorial permutations exist

### 💾 Space Complexity:

O(n) stack + O(n!) result

### 🎯 Tips & Tricks:

- Always undo `used[i]` to backtrack
- Each level → fixed position
- Avoid duplicates with extra used set or sorting
- Common bug: modifying input list in-place
- Ladder: LC 46 → 47 → 31 → 267

## 4. Subsequence Generation

### ✨ Concept:

Generate all subsequences by making binary decisions at each character (take or not take).

### 🔍 Use Cases:

- Substring/subsequence generation
- Power set of strings

### 💡 Intuition:

Each character creates two paths: included or excluded.

### 🧠 Code Walkthrough:

```java
void subseq(String s, int index, String curr) {    if (index == s.length()) {        System.out.println(curr);        return;    }    subseq(s, index + 1, curr);              // not take    subseq(s, index + 1, curr + s.charAt(index)); // take}
```

### 🕒 Time Complexity:

O(2^n) — each character has two options.

### 💾 Space Complexity:

O(n) — recursion depth.

### 🎯 Tips & Tricks:

- Same tree as Take/Not-Take pattern
- Use for string subset generation and pattern enumeration
- Ladder: LC 78 → 131 → 93

## 5. Palindrome Partitioning

### ✨ Concept:

Partition the string and recursively check if substrings are palindromes.

### 🔍 Use Cases:

- Palindromic substrings

### 💡 Intuition:

Explore all partitions; only go forward with palindromes.

### 🧠 Code Walkthrough:

```java
void dfs(String s, int start, List<String> curr, List<List<String>> res) {    if (start == s.length()) {        res.add(new ArrayList<>(curr));        return;    }    for (int end = start; end < s.length(); end++) {        if (isPalindrome(s, start, end)) {            curr.add(s.substring(start, end + 1));            dfs(s, end + 1, curr, res);            curr.remove(curr.size() - 1);        }    }}
```

### 🕒 Time Complexity:

O(2^n) worst case (every partition)

### 💾 Space Complexity:

O(n) recursion depth + output list

### 🎯 Tips & Tricks:

- Pre-check palindrome condition
- Ladder: LC 131 → 132

## 6. Combination Sum (Allow Repetition)

### ✨ Concept:

Include same element multiple times to reach a target.

### 🔍 Use Cases:

- Coin change
- Unbounded knapsack

### 💡 Intuition:

At each step, reuse current element or move forward.

### 🧠 Code Walkthrough:

```java
void dfs(int[] nums, int index, int target, List<Integer> curr, List<List<Integer>> ans) {    if (target == 0) {        ans.add(new ArrayList<>(curr));        return;    }    if (target < 0 || index == nums.length) return;    curr.add(nums[index]);    dfs(nums, index, target - nums[index], curr, ans); // pick same    curr.remove(curr.size() - 1);    dfs(nums, index + 1, target, curr, ans);           // skip}
```

### 🕒 Time Complexity:

Exponential (target depth)

### 💾 Space Complexity:

O(target) depth

### 🎯 Tips & Tricks:

- Can pick same element multiple times
- Use index-based control instead of loop
- Ladder: LC 39 → 216

## 7. Subset Sum (Target Sum Path)

### ✨ Concept:

Generate all combinations whose sum equals target.

### 🔍 Use Cases:

- Subsets that match condition

### 💡 Intuition:

Tree of take/not-take paths + sum tracking

### 🧠 Code Walkthrough:

```java
void subsetSum(int[] arr, int index, int sum, List<Integer> curr, List<List<Integer>> res) {    if (index == arr.length) {        if (sum == 0) res.add(new ArrayList<>(curr));        return;    }    curr.add(arr[index]);    subsetSum(arr, index + 1, sum - arr[index], curr, res);    curr.remove(curr.size() - 1);    subsetSum(arr, index + 1, sum, curr, res);}
```

### 🕒 Time Complexity:

O(2^n)

### 💾 Space Complexity:

O(n)

### 🎯 Tips & Tricks:

- Often a constraint version of subset generation
- Base case checks condition
- Ladder: LC 416, 698

## 8. Grid Based Recursion (2D DFS)

### ✨ Concept:

Explore in 4 or 8 directions on a grid recursively

### 🔍 Use Cases:

- Island count
- Word search

### 💡 Intuition:

DFS through neighboring cells with boundary conditions

### 🧠 Code Walkthrough:

```java
void dfs(char[][] grid, int i, int j) {    if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] == '0') return;    grid[i][j] = '0';    dfs(grid, i + 1, j);    dfs(grid, i - 1, j);    dfs(grid, i, j + 1);    dfs(grid, i, j - 1);}
```

### 🕒 Time Complexity:

O(m × n)

### 💾 Space Complexity:

O(m × n) in worst case stack

### 🎯 Tips & Tricks:

- Always mark visited
- Prevent overflow via bounds check
- Ladder: LC 200 → 695 → 130

## 9. Recursion + Memoization (Top-Down DP)

### ✨ Concept:

Use recursion + caching to avoid recomputation

### 🔍 Use Cases:

- Fibonacci, climbing stairs
- 0/1 Knapsack

### 💡 Intuition:

Recursive overlapping subproblems

### 🧠 Code Walkthrough:

```java
int fib(int n, int[] memo) {    if (n <= 1) return n;    if (memo[n] != -1) return memo[n];    return memo[n] = fib(n-1, memo) + fib(n-2, memo);}
```

### 🕒 Time Complexity:

O(n)

### 💾 Space Complexity:

O(n) stack + memo

### 🎯 Tips & Tricks:

- Always check cache before recursion
- Initialize memo with -1
- Ladder: LC 70 → 746 → 198

## 10. Recursive Backtracking with Pruning

### ✨ Concept:

Explore all paths but cut off unproductive branches early

### 🔍 Use Cases:

- Sudoku solver
- N-Queens

### 💡 Intuition:

Use rules to prune invalid states before going deeper

### 🧠 Code Walkthrough:

```java
boolean solve(char[][] board) {    for (...) {        for (...) {            if (board[i][j] == '.') {                for (char c : '1' to '9') {                    if (isValid(board, i, j, c)) {                        board[i][j] = c;                        if (solve(board)) return true;                        board[i][j] = '.';                    }                }                return false;            }        }    }    return true;}
```

### 🕒 Time Complexity:

Highly variable, depends on pruning effectiveness

### 💾 Space Complexity:

Depth of recursive tree (O(n^2) for boards)

### 🎯 Tips & Tricks:

- Always apply pruning conditions early
- Use helper like isValid()
- Ladder: LC 37 → 51 → 52