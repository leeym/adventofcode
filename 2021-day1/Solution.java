import org.junit.Test;

import javax.swing.plaf.IconUIResource;
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
        Integer previous = null;
        int count = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            int current = Integer.parseInt(line);
            if (previous != null && previous < current)
                count++;
            previous = current;
        }
        System.out.println(count);
    }

    @Test
    public void part2() {
        Queue<String> file = readInput();
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
        System.out.println(count);
    }
}
