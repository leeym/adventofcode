import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
        Integer previous = null;
        int count = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            int current = Integer.parseInt(line);
            if (previous != null && previous < current)
                count++;
            previous = current;
        }
        return count;
    }

    public int part2(String pathname) {
        Queue<String> file = readFile(pathname);
        Queue<Integer> window = new LinkedList<>();
        Integer previous = null;
        int count = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            while (window.size() > 2)
                window.remove();
            window.add(Integer.parseInt(line));
            if (window.size() < 3)
                continue;
            int current = window.stream().mapToInt(Integer::intValue).sum();
            if (previous != null && previous < current)
                count++;
            previous = current;
        }
        return count;
    }

    @Test
    public void small1() {
        Assert.assertEquals(7, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(1532, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(5, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(1571, part2("large"));
    }
}
