package Grade4.Algorithm.week7.dp;

public class Knapsack {


    //main method 호출용
    public int solveKnapsack(int[] profits, int[] weights, int capacity) {
        return this.knapsackRecursive(profits, weights, capacity, 0);
    }

    //Recursive
    private int knapsackRecursive(int[] profits, int[] weights, int capacity, int currentIndex) {
        if (capacity <= 0 || currentIndex >= profits.length)
            return 0;
        int profit1 = 0;
        if( weights[currentIndex] <= capacity )
            profit1 = profits[currentIndex] + knapsackRecursive(profits, weights,
                    capacity - weights[currentIndex], currentIndex + 1);

        int profit2 = knapsackRecursive(profits, weights, capacity, currentIndex + 1);
        return Math.max(profit1, profit2);
    }

    //DP
    public int knapsackDP(int[] profits, int[] weights, int capacity) {
        if (capacity <= 0 || profits.length == 0 || weights.length != profits.length)
            return 0;

        int n = profits.length;
        int[][] dp = new int[n][capacity + 1];

        for(int i = 0; i < n; i++)
            dp[i][0] = 0;

        for(int c = 0; c <= capacity; c++) {
            if(weights[0] <= c)
                dp[0][c] = profits[0];
        }

        for(int i = 1; i < n; i++) {
            for(int c = 1; c <= capacity; c++) {
                int profit1= 0, profit2 = 0;
                if(weights[i] <= c)
                    profit1 = profits[i] + dp[i-1][c-weights[i]];
                profit2 = dp[i-1][c];
                dp[i][c] = Math.max(profit1, profit2);
            }
        }
        return dp[n-1][capacity];
    }

    public static void main(String[] args) {
        Knapsack knapsack = new Knapsack();
        int[] profits = {1, 6, 10, 16};
        int[] weights = {1, 2, 3, 5};
        System.out.println("-------------DP-------------------");
        int maxProfit = knapsack.knapsackDP(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = knapsack.knapsackDP(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        System.out.println("-------------Recursive-------------");
        maxProfit = knapsack.solveKnapsack(profits, weights, 7);
        System.out.println("Total knapsack profit ---> " + maxProfit);
        maxProfit = knapsack.solveKnapsack(profits, weights, 6);
        System.out.println("Total knapsack profit ---> " + maxProfit);
    }

}
