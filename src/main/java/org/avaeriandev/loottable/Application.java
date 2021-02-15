package org.avaeriandev.loottable;

import org.avaeriandev.loottable.testing.TestReward;

import java.util.Scanner;

public class Application {

    public static void main(String[] args) {

        LootTable<TestReward> rewardLootTable = new LootTable<>();
        rewardLootTable.put(TestReward.EXCALIBUR, 10);
        rewardLootTable.put(TestReward.DAGGER, 30);
        rewardLootTable.put(TestReward.IRON_SHIELD, 50);
        rewardLootTable.put(TestReward.APPLE, 80);

        Scanner scanner = new Scanner(System.in);
        while(!scanner.nextLine().equals("exit")) {
            System.out.println(rewardLootTable.roll());
        }

    }

}
