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

    public long part1(String pathname, int days) {
        Queue<String> file = readFile(pathname);
        String line = file.remove();
        Map<Integer, Long> map = new HashMap<>();
        for (int num : Arrays.stream(line.split(",")).map(Integer::parseInt).toList())
            map.put(num, map.getOrDefault(num, 0L) + 1);
        List<Long> list = new LinkedList<>();
        for (int n = 0; n <= 8; n++)
            list.add(map.getOrDefault(n, 0L));
        for (int d = 0; d < days; d++) {
            long zero = list.remove(0);
            list.set(6, list.get(6) + zero);
            list.add(zero);
        }
        return list.stream().mapToLong(Long::longValue).sum();
    }

    public long part2(String pathname, int days) {
        return part1(pathname, days);
    }

    @Test
    public void small18() {
        Assert.assertEquals(26, part1("small", 18));
    }

    @Test
    public void small80() {
        Assert.assertEquals(5934, part1("small", 80));
    }

    @Test
    public void large80() {
        Assert.assertEquals(390011, part1("large", 80));
    }

    @Test
    public void small256() {
        Assert.assertEquals(26984457539L, part2("small", 256));
    }

    @Test
    public void large256() {
        Assert.assertEquals(1746710169834L, part2("large", 256));
    }
}
