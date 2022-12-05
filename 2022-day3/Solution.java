import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

public class Solution {

    private Queue<String> readFile(String pathname) {
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
        while (!file.isEmpty()) {
            String line = file.remove();
            sum += priority(shared(line.substring(0, line.length() / 2), line.substring(line.length() / 2)));
        }
        return sum;
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

    public int part2(String pathname) {
        Queue<String> file = readFile(pathname);
        int sum = 0;
        while (!file.isEmpty()) {
            sum += priority(shared(file.remove(), file.remove(), file.remove()));
        }
        return sum;
    }


    @Test
    public void small1() {
        Assert.assertEquals(157, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(8153, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(70, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(2342, part2("large"));
    }
}
