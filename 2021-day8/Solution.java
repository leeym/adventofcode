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
        int count = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            String[] parts = line.split(" \\| ");
            String[] output = parts[1].trim().split(" ");
            for (String s : output) {
                switch (s.length()) {
                    case 2, 4, 3, 7 -> count++;
                }
            }
        }
        return count;
    }

    public int part2(String pathname) {
        Queue<String> file = readFile(pathname);
        int sum = 0;
        char[] array = new char[7];
        while (!file.isEmpty()) {
            String line = file.remove();
            String[] parts = line.split(" \\| ");
            String[] inputs = parts[0].trim().split(" ");
            Map<Character, Integer> map = new HashMap<>();
            for (String input : inputs) {
                for (char c : input.toCharArray())
                    map.put(c, map.getOrDefault(c, 0) + 1);
            }
            array[4] = map.entrySet().stream().filter(e -> e.getValue() == 4).findAny().orElseThrow().getKey();
            array[1] = map.entrySet().stream().filter(e -> e.getValue() == 6).findAny().orElseThrow().getKey();
            array[5] = map.entrySet().stream().filter(e -> e.getValue() == 9).findAny().orElseThrow().getKey();
            Set<Character> one = toSet(Arrays.stream(inputs).filter(s -> s.length() == 2).findAny().orElseThrow());
            Set<Character> seven = toSet(Arrays.stream(inputs).filter(s -> s.length() == 3).findAny().orElseThrow());
            Set<Character> four = toSet(Arrays.stream(inputs).filter(s -> s.length() == 4).findAny().orElseThrow());
            Set<Character> eight = toSet(Arrays.stream(inputs).filter(s -> s.length() == 7).findAny().orElseThrow());
            array[0] = diff(seven, one).iterator().next();
            array[2] = diff(one, Set.of(array[5])).iterator().next();
            array[3] = diff(diff(four, one), Set.of(array[1])).iterator().next();
            array[6] = diff(diff(eight, four), Set.of(array[0], array[4])).iterator().next();
            /*
             000
            1   2
            1   2
             333
            4   5
            4   5
             666
             */
            Map<Set<Character>, Integer> map1 = new HashMap<>();
            map1.put(Set.of(array[0], array[1], array[2], array[4], array[5], array[6]), 0);
            map1.put(Set.of(array[2], array[5]), 1);
            map1.put(Set.of(array[0], array[2], array[3], array[4], array[6]), 2);
            map1.put(Set.of(array[0], array[2], array[3], array[5], array[6]), 3);
            map1.put(Set.of(array[1], array[2], array[3], array[5]), 4);
            map1.put(Set.of(array[0], array[1], array[3], array[5], array[6]), 5);
            map1.put(Set.of(array[0], array[1], array[3], array[4], array[5], array[6]), 6);
            map1.put(Set.of(array[0], array[2], array[5]), 7);
            map1.put(Set.of(array[0], array[1], array[2], array[3], array[4], array[5], array[6]), 8);
            map1.put(Set.of(array[0], array[1], array[2], array[3], array[5], array[6]), 9);
            String[] outputs = parts[1].trim().split(" ");
            int num = 0;
            for (String output : outputs) {
                num = num * 10 + map1.get(toSet(output));
            }
            sum += num;
        }
        return sum;
    }

    private Set<Character> toSet(String s) {
        Set<Character> set = new HashSet<>();
        for (char c : s.toCharArray())
            set.add(c);
        return set;
    }

    private Set<Character> diff(Set<Character> set1, Set<Character> set2) {
        Set<Character> set = new HashSet<>(set1);
        set.removeAll(set2);
        return set;
    }

    @Test
    public void small1() {
        Assert.assertEquals(26, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(470, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(61229, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(989396, part2("large"));
    }
}
