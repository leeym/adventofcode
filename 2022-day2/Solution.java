import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;
import java.util.Scanner;

public class Solution {

    static enum Choice {
        ROCK, PAPER, SCISSORS
    }

    private Queue<String> readFile(String pathname) {
        Queue<String> list = new LinkedList<>();
        try {
            Scanner scanner = new Scanner(new File(pathname));
            while (scanner.hasNextLine())
                list.add(scanner.nextLine());
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }

    public int part1(String pathname) {
        int sum = 0;
        Map<String, Choice> map1 = Map.of(
                "A", Choice.ROCK,
                "B", Choice.PAPER,
                "C", Choice.SCISSORS,
                "X", Choice.ROCK,
                "Y", Choice.PAPER,
                "Z", Choice.SCISSORS
        );
        Map<Choice, Integer> map2 = Map.of(
                Choice.ROCK, 1,
                Choice.PAPER, 2,
                Choice.SCISSORS, 3
        );
        Map<Choice, Map<Choice, Integer>> map3 = Map.of(
                Choice.ROCK, Map.of(Choice.PAPER, 0, Choice.ROCK, 3, Choice.SCISSORS, 6),
                Choice.PAPER, Map.of(Choice.SCISSORS, 0, Choice.PAPER, 3, Choice.ROCK, 6),
                Choice.SCISSORS, Map.of(Choice.ROCK, 0, Choice.SCISSORS, 3, Choice.PAPER, 6)
        );
        Queue<String> list = readFile(pathname);
        while (!list.isEmpty()) {
            String line = list.remove();
            String[] array = line.split(" ");
            Choice opponent = map1.get(array[0]);
            Choice player = map1.get(array[1]);
            int score = map2.get(player) + map3.get(player).get(opponent);
            sum += score;
        }
        return sum;
    }

    public int part2(String pathname) {
        int sum = 0;
        Map<String, Choice> map1 = Map.of(
                "A", Choice.ROCK,
                "B", Choice.PAPER,
                "C", Choice.SCISSORS
        );
        Map<Choice, Integer> map2 = Map.of(
                Choice.ROCK, 1,
                Choice.PAPER, 2,
                Choice.SCISSORS, 3
        );
        Map<Choice, Map<String, Choice>> map3 = Map.of(
                Choice.ROCK, Map.of("Z", Choice.PAPER, "Y", Choice.ROCK, "X", Choice.SCISSORS),
                Choice.PAPER, Map.of("Z", Choice.SCISSORS, "Y", Choice.PAPER, "X", Choice.ROCK),
                Choice.SCISSORS, Map.of("Z", Choice.ROCK, "Y", Choice.SCISSORS, "X", Choice.PAPER)
        );
        Queue<String> queue = readFile(pathname);
        while (!queue.isEmpty()) {
            String line = queue.remove();
            Map<String, Integer> map5 = Map.of("X", 0, "Y", 3, "Z", 6);
            String[] array = line.split(" ");
            Choice opponent = map1.get(array[0]);
            Choice player = map3.get(opponent).get(array[1]);
            int score = map2.get(player) + map5.get(array[1]);
            sum += score;
        }
        return sum;
    }

    @Test
    public void small1() {
        Assert.assertEquals(15, part1("small"));
    }

    @Test
    public void large1() {
        Assert.assertEquals(12645, part1("large"));
    }

    @Test
    public void small2() {
        Assert.assertEquals(12, part2("small"));
    }

    @Test
    public void large2() {
        Assert.assertEquals(11756, part2("large"));
    }
}
