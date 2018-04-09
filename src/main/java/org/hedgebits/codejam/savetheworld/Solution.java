package org.hedgebits.codejam.savetheworld;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Solution {
    private static final String FILE = "./input.txt";

    public static void main(String[] args) throws IOException {
        Scanner in = new Scanner(new BufferedReader(new InputStreamReader(System.in)));
        // Scanner in = new Scanner(Resources.getResource(FILE).openStream());
        int t = in.nextInt();
        Data data = new Data(t);
        for (int i = 1; i <= t; ++i) {
            int n = in.nextInt();
            String s = in.next();
            data.addCase(n, s);
        }
        for (int i = 0; i < data.cases.size(); i++) {
            Case cs = data.cases.get(i);
            while (cs.canHack && !cs.isWon()) {
                cs.hack();
            }
            System.out.print(String.format("Case #%s: ", i + 1));
            System.out.println(cs.isWon() ? cs.hackCount : "IMPOSSIBLE");

        }
        data.cases.forEach(cs -> {

        });
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

enum Action {
    CHARGE,
    SHOOT;

    static Action parse(char c) {
        return (c == 'C') ? CHARGE : SHOOT;
    }
}

class Case {
    long maxDamage;
    long minDamage = 0;
    String actions;
    final Set<String> currentHacks = new HashSet<>();
    int hackCount = 0;
    boolean canHack = true;

    Case(Integer maxDamage, String actions) {
        this.maxDamage = maxDamage;
        this.actions = actions;
        currentHacks.add(actions);
        final char[] chars = actions.toCharArray();
        minDamage = calculateDamage(chars);
        canHack = numberOfShoots(chars) <= maxDamage;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Case aCase = (Case) o;
        return Objects.equals(maxDamage, aCase.maxDamage) &&
                Objects.equals(actions, aCase.actions);
    }

    void hack() {
        final Set<String> hacks = Case.hack(currentHacks);
        hacks.forEach(hack -> {
            final int damage = calculateDamage(hack.toCharArray());
            if (damage < minDamage) {
                minDamage = damage;
            }
        });
        canHack = currentHacks.addAll(hacks);
        hackCount++;
    }

    boolean isWon() {
        return minDamage <= maxDamage;
    }

    int numberOfShoots(char[] chars) {
        int count = 0;
        for (char c : chars) {
            Action action = Action.parse(c);
            if (action == Action.SHOOT) {
                count++;
            }
        }
        return count;
    }

    static int calculateDamage(char[] chars) {
        int strength = 1;
        int damage = 0;
        for (char c : chars) {
            Action action = Action.parse(c);
            if (action == Action.CHARGE) {
                strength *= 2;
            } else {
                damage += strength;
            }
        }
        return damage;
    }

    static Set<String> hack(Set<String> source) {
        Set<String> hacks = new HashSet<>();
        for (String s : source) {
            final int length = s.length();
            for (int i = 0; i < length - 1; i++) {
                hacks.add(swap(s, i, i + 1));
            }
        }
        return hacks;
    }

    static String swap(String s, int x, int y) {
        char[] src = s.toCharArray();
        if (x < 0 || y < 0 || x > src.length - 1 || y > src.length - 1) {
            return null;
        }
        char tmp = src[x];
        src[x] = src[y];
        src[y] = tmp;
        return new String(src);
    }

    @Override
    public int hashCode() {
        return Objects.hash(maxDamage, actions);
    }

    @Override
    public String toString() {
        return "Case{" +
                "maxDamage=" + maxDamage +
                ", actions='" + actions + '\'' +
                '}';
    }
}