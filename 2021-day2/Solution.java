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
        int depth = 0;
        int position = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            String[] parts = line.split(" ");
            int units = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "forward" -> position += units;
                case "up" -> depth -= units;
                case "down" -> depth += units;
            }
        }
        return position * depth;
    }

    public long part2(String pathname) {
        Queue<String> file = readFile(pathname);
        long depth = 0;
        long position = 0;
        long aim = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            String[] parts = line.split(" ");
            int units = Integer.parseInt(parts[1]);
            switch (parts[0]) {
                case "down" -> aim += units;
                case "up" -> aim -= units;
                case "forward" -> {
                    position += units;
                    depth += aim * units;
                }
            }
        }
        return (position * depth);
    }

    @Test
    public void small1() {
        Assert.assertEquals(150, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(2322630, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(900, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(2105273490, part2("large"));
    }
}
