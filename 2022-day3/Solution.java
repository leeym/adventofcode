import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Solution {

    private Queue<String> readInput() {
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
        while (!file.isEmpty()) {
            String line = file.remove();
            sum += priority(shared(line.substring(0, line.length() / 2), line.substring(line.length() / 2)));
        }
        System.out.println(sum);
    }

    private Character shared(String... strings) {
        final int N = strings.length;
        Map<Character, Set<Integer>> map = new HashMap<>();
        for (int i = 0; i < N; i++)
            for (char c : strings[i].toCharArray())
                map.computeIfAbsent(c, k -> new HashSet<>()).add(i);
        return map.entrySet().stream().filter(entry -> entry.getValue().size() == N).findFirst().orElseThrow().getKey();
    }

    private int priority(char c) {
        assert Character.isAlphabetic(c);
        if (Character.isLowerCase(c))
            return c - 'a' + 1;
        else
            return c - 'A' + 27;
    }

    @Test
    public void test2() {
        Queue<String> file = readInput();
        int sum = 0;
        while (!file.isEmpty()) {
            sum += priority(shared(file.remove(), file.remove(), file.remove()));
        }
        System.out.println(sum);
    }
}