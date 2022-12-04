import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Solution {

    Queue<String> readInput() {
        Queue<String> queue = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new File("input"));
            while (scanner.hasNextLine())
                queue.add(scanner.nextLine());
            scanner.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return queue;
    }

    @Test
    public void part1() {
        Queue<String> file = readInput();
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
        System.out.println(max);
    }

    @Test
    public void test2() {
        Queue<String> file = readInput();
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
        System.out.println(queue.stream().mapToInt(Integer::intValue).sum());
    }
}
