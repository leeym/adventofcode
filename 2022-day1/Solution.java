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
        int sum = 0;
        int max = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            if (!line.isEmpty()) {
                sum += Integer.parseInt(line);
            } else {
                max = Math.max(max, sum);
                sum = 0;
            }
        }
        return max;
    }

    public int part2(String pathname) {
        Queue<String> file = readFile(pathname);
        Queue<Integer> queue = new PriorityQueue<>(Comparator.naturalOrder());
        int sum = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            if (!line.isEmpty()) {
                sum += Integer.parseInt(line);
            } else {
                queue.add(sum);
                while (queue.size() > 3)
                    queue.remove();
                sum = 0;
            }
        }
        return queue.stream().mapToInt(Integer::intValue).sum();
    }

    @Test
    public void small1() {
        Assert.assertEquals(24000, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(69883, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(45000, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(207576, part2("large"));
    }
}
