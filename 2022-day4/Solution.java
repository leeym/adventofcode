import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    private Queue<String> readFile() {
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
        Queue<String> queue = readFile();
        int count = 0;
        while (!queue.isEmpty()) {
            String line = queue.remove();
            String[] parts = line.split(",");
            int[] one = range(parts[0]);
            int[] two = range(parts[1]);
            if (fullyOverlap(one, two) || fullyOverlap(two, one))
                count++;
        }
        System.out.println(count);
    }

    private int[] range(String s) {
        String[] parts = s.split("-");
        return new int[]{Integer.parseInt(parts[0]), Integer.parseInt(parts[1])};
    }

    private boolean fullyOverlap(int[] one, int[] two) {
        return one[0] <= two[0] && one[1] >= two[1];
    }

    private boolean partiallyOverlap(int[] one, int[] two) {
        return (one[0] <= two[0] && two[0] <= one[1]) || (one[0] <= two[1] && two[1] <= one[1]);
    }

    @Test
    public void test2() {
        Queue<String> queue = readFile();
        int count = 0;
        while (!queue.isEmpty()) {
            String line = queue.remove();
            String[] parts = line.split(",");
            int[] one = range(parts[0]);
            int[] two = range(parts[1]);
            if (partiallyOverlap(one, two) || partiallyOverlap(two, one))
                count++;
        }
        System.out.println(count);
    }
}
