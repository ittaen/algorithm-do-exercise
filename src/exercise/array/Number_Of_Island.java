package exercise.array;

import algorithm.sort.base.RandomArrayGenerator;

import java.util.LinkedList;
import java.util.Queue;

/**
 * 岛屿数量
 * <p>
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * <p>
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * <p>
 * 此外，你可以假设该网格的四条边均被水包围。
 *
 * <p>
 * 示例 1：
 * <p>
 * 输入：grid = [
 * ["1","1","1","1","0"],
 * ["1","1","0","1","0"],
 * ["1","1","0","0","0"],
 * ["0","0","0","0","0"]
 * ]
 * 输出：1
 * <p>
 * 示例 2：
 * <p>
 * 输入：grid = [
 * ["1","1","0","0","0"],
 * ["1","1","0","0","0"],
 * ["0","0","1","0","0"],
 * ["0","0","0","1","1"]
 * ]
 * 输出：3
 * <p>
 * <p>
 * <p>
 * 提示：
 * <p>
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 300
 * grid[i][j] 的值为 '0' 或 '1'
 * <p>
 * https://leetcode.cn/problems/number-of-islands
 * <p>
 *
 * @author : Taen
 * @date : 2022/7/22 19:37
 */
public class Number_Of_Island {
    public static void main(String[] args) {
        // [["1"],["1"]]
        // [['1','1','1','1','0'],['1','1','0','1','0'],['1','1','0','0','0'],['0','0','0','0','0']]
        char[][] grid = RandomArrayGenerator.generateTDArray2("[['1','1','1','1','0'],['1','1','0','1','0'],['1','1','0','0','0'],['0','0','0','0','0']]");
        RandomArrayGenerator.printTDArray2(grid);
        Number_Of_Island app = new Number_Of_Island();
        System.out.println(app.numIslands(grid));
    }

    public int numIslands(char[][] grid) {
        return solution(grid);
    }

    /**
     * 使用 并查集
     *
     * @param grid
     * @return
     */
    private int solution(char[][] grid) {
        if (grid == null || grid.length == 0) {
            return 0;
        }
        // 创建并初始化 并查集对象
        UnionFind uf = new UnionFind(grid);

        // 行
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            // 列
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    grid[i][j] = '0';
                    // 合并集
                    int x = i * cols + j;
                    if (i - 1 >= 0 && grid[i - 1][j] == '1') {
                        uf.union(x, (i - 1) * cols + j);
                    }
                    if (i + 1 < rows && grid[i + 1][j] == '1') {
                        uf.union(x, (i + 1) * cols + j);
                    }
                    if (j - 1 >= 0 && grid[i][j - 1] == '1') {
                        uf.union(x, i * cols + (j - 1));
                    }
                    if (j + 1 < cols && grid[i][j + 1] == '1') {
                        uf.union(x, i * cols + (j + 1));
                    }
                }
            }
        }
        return uf.getCount();
    }

    class UnionFind {
        int count;
        int[] parent;
        int[] rank;

        public UnionFind(char[][] grid) {
            count = 0;
            int rows = grid.length;
            int cols = grid[0].length;
            rank = new int[rows * cols];
            parent = new int[rows * cols];
            for (int i = 0; i < rows; i++) {
                for (int j = 0; j < cols; j++) {
                    if (grid[i][j] == '1') {
                        count++;
                        parent[i * cols + j] = i * cols + j;
                    }
                }
            }
        }

        public int find(int index) {
            if (parent[index] != index) parent[index] = find(parent[index]);
            return parent[index];
        }

        public void union(int x, int y) {
            int xParent = find(x);
            int yParent = find(y);
            if (xParent != yParent) {
                if (rank[x] > rank[y]) {
                    parent[yParent] = xParent;
                } else if (rank[x] < rank[y]) {
                    parent[xParent] = yParent;
                } else {
                    parent[yParent] = xParent;
                    rank[xParent] += 1;
                }
                count--;
            }
        }

        public int getCount() {
            return count;
        }

    }

    /**
     * 使用 深度优先搜索 DFS
     *
     * @param grid
     * @return
     */
    private int solution1(char[][] grid) {
        int numsIsland = 0;
        if (grid == null || grid.length == 0) {
            return numsIsland;
        }
        // 行 row
        for (int row = 0; row < grid.length; row++) {
            // 列 col
            for (int col = 0; col < grid[0].length; col++) {
                if (grid[row][col] == '1') {
                    numsIsland++;
                    dfsInfect(grid, row, col);
                }
            }
        }
        return numsIsland;
    }

    private void dfsInfect(char[][] grid, int row, int col) {
        // 边界问题
        if (row < 0 || row >= grid.length || col < 0 || col >= grid[0].length || grid[row][col] == '0') {
            return;
        }
        // 清理记录过的，避免下次重复统计
        grid[row][col] = '0';
        // 上 下 左 右
        dfsInfect(grid, row - 1, col);
        dfsInfect(grid, row + 1, col);
        dfsInfect(grid, row, col - 1);
        dfsInfect(grid, row, col + 1);
    }

    /**
     * 使用 广度优先搜索 BFS
     *
     * @param grid
     * @return
     */
    private int solution2(char[][] grid) {
        int numsIsland = 0;
        if (grid == null || grid.length == 0) {
            return numsIsland;
        }
        // 借助队列
        Queue<Integer> queue = new LinkedList<>();

        // 行
        int rows = grid.length;
        int cols = grid[0].length;
        for (int i = 0; i < rows; i++) {
            // 列
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    numsIsland++;
                    // 清理原来的状态避免重复、以及跳出循环
                    grid[i][j] = '0';
                    queue.add(i * cols + j);
                    while (!queue.isEmpty()) {
                        Integer ele = queue.remove();
                        int row = ele / cols;
                        int col = ele % cols;

                        // 上 下 左 右
                        if (row - 1 >= 0 && grid[row - 1][col] == '1') {
                            queue.add((row - 1) * cols + col);
                            // 清理原来的状态避免重复、以及跳出循环
                            grid[row - 1][col] = '0';
                        }
                        if (row + 1 < rows && grid[row + 1][col] == '1') {
                            queue.add((row + 1) * cols + col);
                            // 清理原来的状态避免重复、以及跳出循环
                            grid[row + 1][col] = '0';
                        }
                        if (col - 1 >= 0 && grid[row][col - 1] == '1') {
                            queue.add(row * cols + col - 1);
                            // 清理原来的状态避免重复、以及跳出循环
                            grid[row][col - 1] = '0';
                        }
                        if (col + 1 < cols && grid[row][col + 1] == '1') {
                            queue.add(row * cols + col + 1);
                            // 清理原来的状态避免重复、以及跳出循环
                            grid[row][col + 1] = '0';
                        }
                    }
                }
            }
        }
        return numsIsland;
    }

}
