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
        Deque<String> deque = new LinkedList<>();
        Map<String, Integer> map = new TreeMap<>();
        for (String line = file.remove(); !file.isEmpty(); line = file.remove()) {
            if (line.startsWith("dir ") || line.startsWith("$ ls") || line.equals("$ cd /"))
                continue;
            if (line.startsWith("$ cd ")) {
                String dirname = line.substring(5);
                if (dirname.equals("..")) {
                    deque.pop();
                } else {
                    deque.addLast(dirname);
                }
            } else {
                String[] parts = line.split(" ");
                map.put(path(deque, parts[1]), Integer.parseInt(parts[0]));
            }

        }
        Map<String, Integer> dirSize = new TreeMap<>();
        for (String path : map.keySet()) {
            int size = map.get(path);
            for (path = basename(path); !path.isEmpty(); path = basename(path))
                dirSize.put(path, dirSize.getOrDefault(path, 0) + size);
        }
        return dirSize.values().stream().filter(size -> size <= 100000).mapToInt(Integer::intValue).sum();
    }

    private String path(Deque<String> deque, String file) {
        return ("/" + String.join("/", deque) + "/" + file).replaceAll("//", "/");
    }

    private String basename(String path) {
        return path.substring(0, path.lastIndexOf("/"));
    }

    public int part2(String pathname) {
        Queue<String> file = readFile(pathname);
        while (!file.isEmpty()) {
            String line = file.remove();
        }
        return 0;
    }

    @Test
    public void small1() {
        Assert.assertEquals(95437, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(3059217, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(0, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(0, part2("large"));
    }
}
