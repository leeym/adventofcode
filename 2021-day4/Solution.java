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
        List<Integer> list = new ArrayList<>();
        List<int[][]> boards = new LinkedList<>();
        while (!file.isEmpty()) {
            String line = file.remove();
            if (list.isEmpty()) {
                list = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
                assert !list.isEmpty();
                file.remove();
                continue;
            }
            int[][] board = new int[5][5];
            for (int r = 0; r < 5; r++) {
                String[] fields = line.trim().replaceAll("\\s+", " ").split(" ");
                int[] row = Arrays.stream(fields).mapToInt(Integer::parseInt).toArray();
                System.arraycopy(row, 0, board[r], 0, 5);
                if (!file.isEmpty())
                    line = file.remove();
            }
            boards.add(board);
        }
        int score = 0;
        for (int num : list) {
            for (int[][] board : boards) {
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {
                        if (board[r][c] == num) {
                            board[r][c] = -1 * (board[r][c] + 1);
                        }
                    }
                }
                score = win(board, num);
                if (score > 0) {
                    return score;
                }
            }
        }
        return -1;
    }

    private int win(int[][] board, int num) {
        int[] array = new int[10];
        int sum = 0;
        for (int r = 0; r < 5; r++) {
            for (int c = 0; c < 5; c++) {
                if (board[r][c] < 0) {
                    array[r]++;
                    array[c + 5]++;
                } else {
                    sum += board[r][c];
                }
            }
        }
        if (Arrays.stream(array).noneMatch(v -> v == 5))
            return 0;
        return sum * num;
    }

    public int part2(String pathname) {
        Queue<String> file = readFile(pathname);
        List<Integer> list = new ArrayList<>();
        List<int[][]> boards = new LinkedList<>();
        while (!file.isEmpty()) {
            String line = file.remove();
            if (list.isEmpty()) {
                list = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
                assert !list.isEmpty();
                file.remove();
                continue;
            }
            int[][] board = new int[5][5];
            for (int r = 0; r < 5; r++) {
                String[] fields = line.trim().replaceAll("\\s+", " ").split(" ");
                int[] row = Arrays.stream(fields).mapToInt(Integer::parseInt).toArray();
                System.arraycopy(row, 0, board[r], 0, 5);
                if (!file.isEmpty())
                    line = file.remove();
            }
            boards.add(board);
        }
        Set<Integer> set = new HashSet<>();
        for (int num : list) {
            for (int i = 0; i < boards.size(); i++) {
                int[][] board = boards.get(i);
                for (int r = 0; r < 5; r++) {
                    for (int c = 0; c < 5; c++) {
                        if (board[r][c] == num) {
                            board[r][c] = -1 * (board[r][c] + 1);
                        }
                    }
                }
                int score = win(board, num);
                if (score > 0) {
                    set.add(i);
                    if (set.size() == boards.size())
                        return score;
                }
            }
        }
        return -1;
    }

    @Test
    public void small1() {
        Assert.assertEquals(4512, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(11536, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(1924, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(1284, part2("large"));
    }
}
