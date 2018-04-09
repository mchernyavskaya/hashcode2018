package org.hedgebits.codejam.troublesort;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.IntStream;

public class Solution {
    private static final String FILE = "./input_trouble.txt";

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        // Scanner in = new Scanner(Resources.getResource(FILE).openStream());
        int t = Integer.parseInt(in.nextLine());
        Data data = new Data(t);
        for (int i = 1; i <= t; ++i) {
            int n = Integer.parseInt(in.nextLine());
            String s = in.nextLine();
            data.addCase(n, s);
        }
        for (int i = 0; i < data.cases.size(); i++) {
            Case cs = data.cases.get(i);
            final int errorIndex = cs.troubleSortOptimized();
            System.out.print(String.format("Case #%s: ", i + 1));
            System.out.println(errorIndex < 0 ? "OK" : errorIndex);
        }
    }
}

class Data {
    int t;
    List<Case> cases;

    Data(int t) {
        this.t = t;
        this.cases = new ArrayList<>();
    }

    void addCase(int d, String p) {
        cases.add(new Case(d, p));
    }

    @Override
    public String toString() {
        return "Data{" +
                "t=" + t +
                ", cases=" + cases +
                '}';
    }
}

@SuppressWarnings("Duplicates")
class Case {
    private int errorIndex;

    int count;
    int[] values;

    Case(int count, String values) {
        this.count = count;
        this.values = Arrays.stream(values.split("\\s+"))
                .filter(s -> s.trim().length() > 0)
                .flatMapToInt(s -> IntStream.builder()
                        .add(Integer.parseInt(s))
                        .build())
                .toArray();
        errorIndex = calculateErrorIndex();
    }

    int troubleSort() {
        if (errorIndex >= 0) {
            boolean done = false;
            while (!done) {
                done = true;
                for (int i = 0; i < values.length - 2; i++) {
                    int left = values[i];
                    int right = values[i + 2];
                    if (left > right) {
                        done = false;
                        values[i] = right;
                        values[i + 2] = left;
                    }
                }

            }
            errorIndex = calculateErrorIndex();
        }
        return errorIndex;
    }

    int troubleSortOptimized() {
        if (errorIndex >= 0) {
            final int[] evens = IntStream.range(0, values.length)
                    .filter(i -> i % 2 == 0)
                    .map(i -> values[i])
                    .sorted().toArray();
            final int[] odds = IntStream.range(0, values.length)
                    .filter(i -> i % 2 != 0)
                    .map(i -> values[i])
                    .sorted().toArray();
            for (int i = 0, j = 0; i < evens.length || j < odds.length; i++, j++) {
                if (i < evens.length) {
                    values[i * 2] = evens[i];
                }
                if (j < odds.length) {
                    values[j * 2 + 1] = odds[j];
                }
            }
            errorIndex = calculateErrorIndex();
        }
        return errorIndex;
    }

    private int calculateErrorIndex() {
        int i = 0;
        if (values != null && values.length > 1) {
            while (i < values.length - 1) {
                Integer prev = values[i];
                Integer next = values[i + 1];
                if (prev > next) {
                    return i;
                }
                i++;
            }
        }
        return -1;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return count == aCase.count &&
                Objects.equals(values, aCase.values);
    }

    @Override
    public int hashCode() {
        return Objects.hash(count, values);
    }

    @Override
    public String toString() {
        return "Case{" +
                "count=" + count +
                ", values=" + Arrays.toString(values) +
                '}';
    }
}