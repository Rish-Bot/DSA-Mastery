## 📝 Quick Takeaways

- Greedy works when **local optimal → global optimal** (must be provable).
- Common triggers: **sorting, intervals, ratios, deadlines, two-pointers, priority queues**.
- Greedy often fails if a single condition changes → always check **counterexamples**.
- Compare with DP: If subproblems overlap → use DP. If not → greedy might work.
- Proof styles: **Exchange argument** (swap bad choice with greedy one) or **Cut property** (for graphs).

---

# 1. Activity / Interval Scheduling

### 🏟️ Where to Use?

- Select maximum number of non-overlapping intervals (meetings, activities).

### ⏳ Time / Space Complexity:

- Sorting: **O(n log n)**
- Selection: **O(n)**
- Space: **O(1)**

### 🛣️ Approach

1. Sort intervals by **end time**.
2. Pick the first interval.
3. For each next interval, if it doesn’t overlap with last picked → select it.

### 🗒️ Notes

- Greedy works because choosing earliest finishing leaves max room for others.
- ❌ Mistake → sorting by start time instead of end time.

### ❕ Sample

```java
int maxActivities(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
    int count = 1, lastEnd = intervals[0][1];
    for (int i = 1; i < intervals.length; i++) {
        if (intervals[i][0] >= lastEnd) {
            count++;
            lastEnd = intervals[i][1];
        }
    }
    return count;
}

```

---

# 2. Minimum Number of Platforms

### 🏟️ Where to Use?

- Find min platforms so no train waits.

### ⏳ Time / Space Complexity:

- Sorting arrivals/departures: **O(n log n)**
- Merge-like traversal: **O(n)**
- Space: **O(1)**

### 🛣️ Approach

1. Sort arrival[] and departure[].
2. Use two pointers to simulate trains arriving/departing.
3. Track platforms needed.

### 🗒️ Notes

- Increment count if `arrival <= departure`.
- ❌ Bug → forgetting to decrement on departure.

### ❕ Sample

```java
int findPlatform(int[] arr, int[] dep) {
    Arrays.sort(arr);
    Arrays.sort(dep);
    int i=0, j=0, plat=0, maxPlat=0;
    while(i < arr.length && j < dep.length) {
        if(arr[i] <= dep[j]) { plat++; i++; }
        else { plat--; j++; }
        maxPlat = Math.max(maxPlat, plat);
    }
    return maxPlat;
}

```

---

# 3. Job Sequencing with Deadlines

### 🏟️ Where to Use?

- Maximize profit given jobs with deadlines & profits.

### ⏳ Time / Space Complexity:

- Sorting jobs by profit: **O(n log n)**
- Placement: **O(n^2)** (naive) / **O(n log n)** (with DSU)
- Space: **O(n)**

### 🛣️ Approach

1. Sort jobs by **profit desc**.
2. Assign job to the **latest available slot ≤ deadline**.

### 🗒️ Notes

- Always place jobs as late as possible.
- ❌ Bug → placing jobs at earliest slot (suboptimal).

### ❕ Sample

```java
class Job {int id, profit, deadline;}
int jobScheduling(Job[] jobs) {
    Arrays.sort(jobs, (a, b) -> b.profit - a.profit);
    int maxDeadline = Arrays.stream(jobs).mapToInt(j -> j.deadline).max().getAsInt();
    int[] slot = new int[maxDeadline+1];
    Arrays.fill(slot, -1);
    int profit=0;
    for(Job job : jobs) {
        for(int d = job.deadline; d>0; d--) {
            if(slot[d] == -1) {
                slot[d] = job.id;
                profit += job.profit;
                break;
            }
        }
    }
    return profit;
}

```

---

# 4. Fractional Knapsack

### 🏟️ Where to Use?

- Maximize profit when items can be taken fractionally.

### ⏳ Time / Space Complexity:

- Sorting by ratio: **O(n log n)**
- Selection: **O(n)**
- Space: **O(1)**

### 🛣️ Approach

1. Sort items by value/weight ratio.
2. Pick until bag full.
3. If partial item fits, take fraction.

### 🗒️ Notes

- Works for **fractional**, fails for **0/1 knapsack**.
- ❌ Bug → forgetting partial item case.

### ❕ Sample

```java
class Item {int val, wt;}

double fractionalKnapsack(Item[] items, int W) {
    Arrays.sort(items, (a, b) -> Double.compare((double)b.val/b.wt, (double)a.val/a.wt));
    double profit=0;
    for(Item it : items) {
        if(W >= it.wt) { profit += it.val; W -= it.wt; }
        else { profit += (double)it.val * W / it.wt; break; }
    }
    return profit;
}

```

---

# 5. Huffman Coding

### 🏟️ Where to Use?

- Build prefix-free codes (compression).

### ⏳ Time / Space Complexity:

- Priority queue operations: **O(n log n)**
- Space: **O(n)**

### 🛣️ Approach

1. Push all frequencies into min-heap.
2. Extract two smallest, merge, reinsert.
3. Continue until 1 tree remains.

### 🗒️ Notes

- Greedy works because combining smallest keeps optimal code.
- ❌ Bug → forgetting to merge properly.

### ❕ Sample

```java
class Node {int freq; Node left, right;}
Node huffman(int[] freq) {
    PriorityQueue<Node> pq = new PriorityQueue<>((a,b) -> a.freq - b.freq);
    for(int f: freq) pq.add(new Node(f));
    while(pq.size()>1) {
        Node a = pq.poll(), b = pq.poll();
        Node merged = new Node(a.freq+b.freq);
        merged.left=a; merged.right=b;
        pq.add(merged);
    }
    return pq.poll();
}

```

---

# 6. Minimum Spanning Tree (Prim’s / Kruskal’s)

### 🏟️ Where to Use?

- Connect all nodes with min cost (roads, networks).

### ⏳ Time / Space Complexity:

- Kruskal: **O(E log E)** with DSU
- Prim: **O(E log V)** with heap
- Space: **O(V+E)**

### 🛣️ Approach

- **Kruskal**: sort edges, add if no cycle (DSU).
- **Prim**: grow tree from one node using PQ.

### 🗒️ Notes

- Greedy works because local min edge always safe.
- ❌ Bug → forgetting cycle check in Kruskal.

### ❕ Sample (Kruskal)

```java
class Edge {int u,v,w;}
int mstKruskal(int n, List<Edge> edges) {
    Collections.sort(edges, (a,b)->a.w-b.w);
    DSU dsu = new DSU(n);
    int cost=0;
    for(Edge e: edges) {
        if(dsu.find(e.u)!=dsu.find(e.v)) {
            dsu.union(e.u,e.v);
            cost+=e.w;
        }
    }
    return cost;
}

```

---

# 7. Dijkstra’s Shortest Path

### 🏟️ Where to Use?

- Single-source shortest path (non-negative weights).

### ⏳ Time / Space Complexity:

- Using PQ: **O(E log V)**
- Space: **O(V)**

### 🛣️ Approach

1. Initialize dist[] with ∞, set source=0.
2. Use min-heap to expand nearest node.
3. Relax edges.

### 🗒️ Notes

- ❌ Bug → forgetting visited check, leads to extra work.

### ❕ Sample

```java
int[] dijkstra(int n, List<int[]>[] graph, int src) {
    int[] dist = new int[n];
    Arrays.fill(dist, Integer.MAX_VALUE);
    dist[src]=0;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a,b)->a[1]-b[1]);
    pq.add(new int[]{src,0});
    while(!pq.isEmpty()) {
        int[] cur=pq.poll();
        int u=cur[0]; int d=cur[1];
        if(d>dist[u]) continue;
        for(int[] nb: graph[u]) {
            int v=nb[0], w=nb[1];
            if(dist[u]+w < dist[v]) {
                dist[v]=dist[u]+w;
                pq.add(new int[]{v,dist[v]});
            }
        }
    }
    return dist;
}

```

---

# 8. Greedy Coin Change

### 🏟️ Where to Use?

- Minimize number of coins (when system is canonical).

### ⏳ Time / Space Complexity:

- Sorting: **O(n log n)**
- Selection: **O(n)**
- Space: **O(1)**

### 🛣️ Approach

1. Sort coins descending.
2. Pick largest possible until target covered.

### 🗒️ Notes

- Works only for standard denominations.
- ❌ Bug → assuming greedy works for any set.

### ❕ Sample

```java
int coinChange(int[] coins, int amount) {
    Arrays.sort(coins);
    int count=0;
    for(int i=coins.length-1;i>=0;i--) {
        while(amount>=coins[i]) {
            amount-=coins[i];
            count++;
        }
    }
    return count;
}

```

---

# 9. Candy Distribution

### 🏟️ Where to Use?

- Distribute candies to children based on ratings.

### ⏳ Time / Space Complexity:

- Two passes: **O(n)**
- Space: **O(n)**

### 🛣️ Approach

1. Give 1 candy to all.
2. Left to right: if rating[i]>rating[i-1], candy[i]=candy[i-1]+1.
3. Right to left: ensure higher-rated get more.

### 🗒️ Notes

- ❌ Bug → forgetting second pass.

### ❕ Sample

```java
int candy(int[] ratings) {
    int n=ratings.length;
    int[] candy=new int[n];
    Arrays.fill(candy,1);
    for(int i=1;i<n;i++) if(ratings[i]>ratings[i-1]) candy[i]=candy[i-1]+1;
    for(int i=n-2;i>=0;i--) if(ratings[i]>ratings[i+1]) candy[i]=Math.max(candy[i],candy[i+1]+1);
    return Arrays.stream(candy).sum();
}

```

---

# 10. Gas Station (Circular Tour)

### 🏟️ Where to Use?

- Find start station to complete circular route.

### ⏳ Time / Space Complexity:

- **O(n)**
- Space: **O(1)**

### 🛣️ Approach

1. Track total gas - total cost. If <0 → impossible.
2. Traverse stations, maintain tank. If tank<0, reset start.

### 🗒️ Notes

- ❌ Bug → forgetting feasibility check.

### ❕ Sample

```java
int canCompleteCircuit(int[] gas, int[] cost) {
    int total=0,tank=0,start=0;
    for(int i=0;i<gas.length;i++) {
        total+=gas[i]-cost[i];
        tank+=gas[i]-cost[i];
        if(tank<0) { start=i+1; tank=0; }
    }
    return total>=0?start:-1;
}

```

---

# 11. Jump Game (Reachability)

### 🏟️ Where to Use?

- Check if last index reachable.

### ⏳ Time / Space Complexity:

- **O(n)**
- Space: **O(1)**

### 🛣️ Approach

1. Maintain farthest reachable index.
2. If current index > farthest → fail.
3. Update farthest each step.

### 🗒️ Notes

- ❌ Bug → not checking current index ≤ farthest.

### ❕ Sample

```java
boolean canJump(int[] nums) {
    int reach=0;
    for(int i=0;i<nums.length;i++) {
        if(i>reach) return false;
        reach=Math.max(reach,i+nums[i]);
    }
    return true;
}

```

---

# 12. Jump Game II (Min Jumps)

### 🏟️ Where to Use?

- Find min jumps to reach end.

### ⏳ Time / Space Complexity:

- **O(n)**
- Space: **O(1)**

### 🛣️ Approach

1. Track current jump range and farthest.
2. Increment jumps when end of range reached.

### 🗒️ Notes

- ❌ Bug → forgetting to update farthest.

### ❕ Sample

```java
int jump(int[] nums) {
    int jumps=0,currEnd=0,farthest=0;
    for(int i=0;i<nums.length-1;i++) {
        farthest=Math.max(farthest,i+nums[i]);
        if(i==currEnd) {
            jumps++;
            currEnd=farthest;
        }
    }
    return jumps;
}

```

---

# 13. Largest Number from Array

### 🏟️ Where to Use?

- Arrange numbers to form largest concatenated number.

### ⏳ Time / Space Complexity:

- Sorting with custom comparator: **O(n log n)**
- Space: **O(n)**

### 🛣️ Approach

1. Convert to strings.
2. Sort by (a+b vs b+a).
3. Concatenate.

### 🗒️ Notes

- ❌ Bug → handling all zeros.

### ❕ Sample

```java
String largestNumber(int[] nums) {
    String[] arr=Arrays.stream(nums).mapToObj(String::valueOf).toArray(String[]::new);
    Arrays.sort(arr,(a,b)->(b+a).compareTo(a+b));
    if(arr[0].equals("0")) return "0";
    return String.join("",arr);
}

```

---

# 14. Assign Cookies

### 🏟️ Where to Use?

- Assign cookies to maximize content children.

### ⏳ Time / Space Complexity:

- Sorting: **O(n log n)**
- Greedy assignment: **O(n)**
- Space: **O(1)**

### 🛣️ Approach

1. Sort greed factors & cookies.
2. Assign smallest cookie ≥ child’s greed.

### 🗒️ Notes

- ❌ Bug → not sorting both arrays.

### ❕ Sample

```java
int findContentChildren(int[] g, int[] s) {
    Arrays.sort(g); Arrays.sort(s);
    int i=0,j=0;
    while(i<g.length && j<s.length) {
        if(s[j]>=g[i]) {i++; j++;}
        else j++;
    }
    return i;
}

```

---

# 15. Task Scheduling / CPU Scheduling

### 🏟️ Where to Use?

- Assign tasks to workers/machines minimizing overall time or maximizing utilization.
- Common in OS process scheduling, load balancing, and parallel job allocation.

### ⏳ Time / Space Complexity:

- Sorting tasks: **O(n log n)**
- Assignment: **O(n log k)** (using priority queue for k machines)
- Space: **O(k)** (storing machine loads)

### 🛣️ Approach

1. Sort tasks (by processing time or priority).
2. Use a **min-heap (priority queue)** to assign the next task to the machine with the least load.
3. Update machine loads and repeat until all tasks are assigned.

### 🗒️ Notes

- Greedy works well when jobs can be divided across identical machines.
- Common mistake → assigning tasks sequentially without balancing loads.
- For weighted or dependent tasks, this may fail → need DP or graph-based scheduling.

### ❕ Sample

```java
int minCompletionTime(int[] tasks, int k) {
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int i = 0; i < k; i++) pq.add(0); // initialize machine loads

    Arrays.sort(tasks);
    for (int i = tasks.length - 1; i >= 0; i--) {
        int load = pq.poll();
        load += tasks[i];
        pq.add(load);
    }

    int maxLoad = 0;
    while (!pq.isEmpty()) maxLoad = Math.max(maxLoad, pq.poll());
    return maxLoad;
}
```
