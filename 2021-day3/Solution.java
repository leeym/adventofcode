import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.*;

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
        List<String> list = new ArrayList<>();
        int R = 0;
        int C = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            R++;
            C = line.length();
            list.add(line);
        }
        StringBuilder gamma = new StringBuilder();
        StringBuilder epsilon = new StringBuilder();
        for (int c = 0; c < C; c++) {
            int zero = 0;
            int one = 0;
            for (int r = 0; r < R; r++) {
                if (list.get(r).charAt(c) == '0')
                    zero++;
                else
                    one++;
            }
            assert zero != one;
            if (zero > one) {
                gamma.append('0');
                epsilon.append('1');
            } else {
                gamma.append('1');
                epsilon.append('0');
            }
        }
        System.out.println(Integer.parseInt(gamma.toString(), 2) * Integer.parseInt(epsilon.toString(), 2));
    }

    @Test
    public void part2() {
        Queue<String> file = readInput();
        List<String> list = new ArrayList<>();
        int R = 0;
        int C = 0;
        while (!file.isEmpty()) {
            String line = file.remove();
            R++;
            C = line.length();
            list.add(line);
        }
        List<String> oxygen = new ArrayList<>(list);
        do {
            for (int c = 0; c < C; c++) {
                final int i = c;
                int zero = 0;
                int one = 0;
                for (String s : oxygen) {
                    if (s.charAt(c) == '0')
                        zero++;
                    else
                        one++;
                }
                if (zero > one) {
                    oxygen = oxygen.stream().filter(line -> line.charAt(i) == '0').toList();
                } else {
                    oxygen = oxygen.stream().filter(line -> line.charAt(i) == '1').toList();
                }
                if (oxygen.size() == 1)
                    break;
            }
        } while (oxygen.size() != 1);
        List<String> co2 = new ArrayList<>(list);
        do {
            for (int c = 0; c < C; c++) {
                final int i = c;
                int zero = 0;
                int one = 0;
                for (String s : co2) {
                    if (s.charAt(c) == '0')
                        zero++;
                    else
                        one++;
                }
                if (zero <= one) {
                    co2 = co2.stream().filter(line -> line.charAt(i) == '0').toList();
                } else {
                    co2 = co2.stream().filter(line -> line.charAt(i) == '1').toList();
                }
                if (co2.size() == 1)
                    break;
            }
        } while (co2.size() != 1);
        System.out.println(Integer.parseInt(oxygen.get(0), 2) * Integer.parseInt(co2.get(0), 2));
    }
}
