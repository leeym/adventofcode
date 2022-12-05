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
        String line = file.remove();
        List<Integer> list = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
        int left = list.stream().mapToInt(Integer::intValue).min().orElseThrow();
        int right = list.stream().mapToInt(Integer::intValue).max().orElseThrow();
        int minDistance = Integer.MAX_VALUE;
        while (left < right) {
            int middle = left + (right - left) / 2;
            int leftDistance = distance1(list, left);
            int rightDistance = distance1(list, right);
            int middleDistance = distance1(list, middle);
            minDistance = Math.min(minDistance, middleDistance);
            if (leftDistance > rightDistance) {
                left++;
            } else if (leftDistance < rightDistance) {
                right--;
            } else {
                left++;
                right--;
            }
        }
        return minDistance;
    }

    private int distance1(List<Integer> list, int position) {
        return list.stream().mapToInt(i -> Math.abs(i - position)).sum();
    }

    public int part2(String pathname) {
        Queue<String> file = readFile(pathname);
        String line = file.remove();
        List<Integer> list = Arrays.stream(line.split(",")).map(Integer::parseInt).toList();
        int left = list.stream().mapToInt(Integer::intValue).min().orElseThrow();
        int right = list.stream().mapToInt(Integer::intValue).max().orElseThrow();
        int minDistance = Integer.MAX_VALUE;
        while (left < right) {
            int middle = left + (right - left) / 2;
            int leftDistance = distance2(list, left);
            int rightDistance = distance2(list, right);
            int middleDistance = distance2(list, middle);
            minDistance = Math.min(minDistance, middleDistance);
            if (leftDistance > rightDistance) {
                left++;
            } else if (leftDistance < rightDistance) {
                right--;
            } else {
                left++;
                right--;
            }
        }
        return minDistance;
    }

    private int distance2(List<Integer> list, int position) {
        int sum = 0;
        for (int i : list) {
            int d = Math.abs(i - position);
            int fuel = (1 + d) * d / 2;
            sum += fuel;
        }
        return sum;
    }

    @Test
    public void small1() {
        Assert.assertEquals(37, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(336721, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(168, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(91638945, part2("large"));
    }
}
