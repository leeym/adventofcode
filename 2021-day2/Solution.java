import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

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
        System.out.println(position * depth);
    }

    @Test
    public void part2() {
        Queue<String> file = readInput();
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
        System.out.println(position * depth);
    }
}
