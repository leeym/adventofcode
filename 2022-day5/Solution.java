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

    public String part1(String pathname) {
        Queue<String> file = readFile(pathname);
        boolean rearrangement = false;
        List<Deque<Character>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (!file.isEmpty()) {
            String line = file.remove();
            if (!rearrangement) {
                if (line.isEmpty()) {
                    rearrangement = true;
                } else if (line.indexOf('[') >= 0) {
                    int i = -1;
                    while ((i = line.indexOf('[', i + 1)) >= 0) {
                        int j = i / 4;
                        while (list.size() <= j)
                            list.add(new LinkedList<>());
                        char c = line.charAt(i + 1);
                        list.get(j).addLast(c);
                        i = i + 1;
                    }
                }
            } else {
                String[] parts = line.split(" ");
                int move = Integer.parseInt(parts[1]);
                int from = Integer.parseInt(parts[3]);
                int to = Integer.parseInt(parts[5]);
                for (int i = 0; i < move; i++) {
                    list.get(to - 1).addFirst(list.get(from - 1).removeFirst());
                }
            }
        }
        for (Deque<Character> deque : list)
            sb.append(deque.peekFirst());
        return sb.toString();
    }

    public String part2(String pathname) {
        Queue<String> file = readFile(pathname);
        boolean rearrangement = false;
        List<Deque<Character>> list = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        while (!file.isEmpty()) {
            String line = file.remove();
            if (!rearrangement) {
                if (line.isEmpty()) {
                    rearrangement = true;
                } else if (line.indexOf('[') >= 0) {
                    int i = -1;
                    while ((i = line.indexOf('[', i + 1)) >= 0) {
                        int j = i / 4;
                        while (list.size() <= j)
                            list.add(new LinkedList<>());
                        char c = line.charAt(i + 1);
                        list.get(j).addLast(c);
                        i = i + 1;
                    }
                }
            } else {
                String[] parts = line.split(" ");
                int move = Integer.parseInt(parts[1]);
                int from = Integer.parseInt(parts[3]);
                int to = Integer.parseInt(parts[5]);
                Stack<Character> stack = new Stack<>();
                for (int i = 0; i < move; i++)
                    stack.add(list.get(from-1).removeFirst());
                while (!stack.isEmpty())
                    list.get(to - 1).addFirst(stack.pop());
            }
        }
        for (Deque<Character> deque : list)
            sb.append(deque.peekFirst());
        return sb.toString();
    }

    @Test
    public void small1() {
        Assert.assertEquals("CMZ", part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals("TDCHVHJTG", part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals("MCD", part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals("NGCMPJLHV", part2("large"));
    }
}
