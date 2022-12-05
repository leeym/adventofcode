import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Solution {

    Queue<String> readFile(String pathname) {
        Queue<String> queue = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new File(pathname));
            while (scanner.hasNextLine())
                queue.add(scanner.nextLine());
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue;
    }

    public int part1(String pathname) {
        Queue<String> file = readFile(pathname);
        int X = 0;
        int Y = 0;
        List<List<Integer>> lists = new ArrayList<>();
        while (!file.isEmpty()) {
            String line = file.remove();
            String[] parts = line.split(" -> ");
            String[] p1 = parts[0].split(",");
            String[] p2 = parts[1].split(",");
            int x1 = Integer.parseInt(p1[0]);
            int y1 = Integer.parseInt(p1[1]);
            int x2 = Integer.parseInt(p2[0]);
            int y2 = Integer.parseInt(p2[1]);
            X = Math.max(X, x1);
            X = Math.max(X, x2);
            Y = Math.max(Y, y1);
            Y = Math.max(Y, y2);
            lists.add(List.of(x1, y1, x2, y2));
        }
        int[][] grid = new int[X + 1][Y + 1];
        for (List<Integer> list : lists) {
            int x1 = list.get(0);
            int y1 = list.get(1);
            int x2 = list.get(2);
            int y2 = list.get(3);
            if (x1 == x2) {
                for (int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++)
                    grid[x1][y]++;
            } else if (y1 == y2) {
                for (int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++)
                    grid[x][y1]++;
            }
        }
        // print(grid);
        int count = 0;
        for (int y = 0; y <= Y; y++) {
            for (int x = 0; x <= X; x++) {
                if (grid[x][y] >= 2)
                    count++;
            }
        }
        return count;
    }

    public int part2(String pathname) {
        Queue<String> file = readFile(pathname);
        int X = 0;
        int Y = 0;
        List<List<Integer>> lists = new ArrayList<>();
        while (!file.isEmpty()) {
            String line = file.remove();
            String[] parts = line.split(" -> ");
            String[] p1 = parts[0].split(",");
            String[] p2 = parts[1].split(",");
            int x1 = Integer.parseInt(p1[0]);
            int y1 = Integer.parseInt(p1[1]);
            int x2 = Integer.parseInt(p2[0]);
            int y2 = Integer.parseInt(p2[1]);
            X = Math.max(X, x1);
            X = Math.max(X, x2);
            Y = Math.max(Y, y1);
            Y = Math.max(Y, y2);
            lists.add(List.of(x1, y1, x2, y2));
        }
        int[][] grid = new int[X + 1][Y + 1];
        for (List<Integer> list : lists) {
            int x1 = list.get(0);
            int y1 = list.get(1);
            int x2 = list.get(2);
            int y2 = list.get(3);
            int minX = Math.min(x1, x2);
            int maxX = Math.max(x1, x2);
            int minY = Math.min(y1, y2);
            int maxY = Math.max(y1, y2);
            if (x1 == x2) {
                for (int y = minY; y <= maxY; y++)
                    grid[x1][y]++;
            } else if (y1 == y2) {
                for (int x = minX; x <= maxX; x++)
                    grid[x][y1]++;
            } else if (Math.abs(x1 - x2) == Math.abs(y1 - y2)) {
                int dx = x2 > x1 ? 1 : -1;
                int dy = y2 > y1 ? 1 : -1;
                for (int x = x1, y = y1; x != x2 && y != y2; x += dx, y += dy) {
                    grid[x][y]++;
                }
                grid[x2][y2]++;
            }
        }
        // print(grid);
        int count = 0;
        for (int y = 0; y <= Y; y++) {
            for (int x = 0; x <= X; x++) {
                if (grid[x][y] >= 2)
                    count++;
            }
        }
        return count;
    }

    private void print(int[][] grid) {
        final int X = grid.length;
        final int Y = grid[0].length;
        for (int y = 0; y < Y; y++) {
            for (int x = 0; x < X; x++) {
                System.err.printf("%c", grid[x][y] == 0 ? '.' : grid[x][y] + '0');
            }
            System.err.println();
        }
    }

    @Test
    public void small1() {
        Assert.assertEquals(5, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(5585, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(12, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(17193, part2("large"));
    }
}
