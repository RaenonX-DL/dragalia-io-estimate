package Items;

import java.util.*;
import java.util.stream.IntStream;

public abstract class BackPackBase implements Comparable {
    protected Map<ItemExchange, Integer> exchangeRecord;
    private int simulatedPlayed;

    BackPackBase(Map<ItemExchange, Integer> exchangeRecord, int simulatedPlayed) {
        this.exchangeRecord = exchangeRecord;
        this.simulatedPlayed = simulatedPlayed;
    }

    abstract BackPackBase deepCopy();

    public void playGames(int games) {
        simulatedPlayed += games;
        playGamesAccumulate(games);
    }

    abstract void playGamesAccumulate(int games);

    abstract BaseItem getItemViaCode(int code);

    public int gamesToComplete() {
        return gamesToComplete(false);
    }

    public int gamesToComplete(boolean includingSimulated) {
        OptionalInt s = IntStream.range(1, getItemTypesCount() + 1).map(x -> this.getItemViaCode(x).gamesLeft()).max();

        if (s.isPresent()) {
            int ret = s.getAsInt();
            if (includingSimulated) {
                ret += simulatedPlayed;
            }
            return ret;
        } else {
            throw new IllegalArgumentException();
        }
    }

    abstract int[][] getItemCombinationsByCode();

    public int getSimulatedPlayed() {
        return simulatedPlayed;
    }

    public HashSet<BackPackBase> getExchangeOncePossibilities() {
        HashSet<BackPackBase> ret = new HashSet<>();

        int[][] combinations = getItemCombinationsByCode();

        for (int[] cmb : combinations) {
            int srcCode = cmb[0];
            int destCode = cmb[1];

            BackPackBase newPack = this.deepCopy();
            try {
                BaseItem srcItem = newPack.getItemViaCode(srcCode);
                BaseItem destItem = newPack.getItemViaCode(destCode);

                ItemEnum srcEnum = ItemExchange.toItemEnum(srcItem);
                ItemEnum destEnum = ItemExchange.toItemEnum(destItem);

                ItemExchange ie = new ItemExchange(srcEnum, destEnum);

                int srcAmount = Exchanger.minSrcAmount(srcItem, destItem);
                int destAmount = Exchanger.exchangeItem(srcEnum, srcAmount, destEnum);

                if (exchangeRecord.containsKey(ie)) {
                    newPack.exchangeRecord.put(ie, exchangeRecord.get(ie) + srcAmount);
                } else {
                    newPack.exchangeRecord.put(ie, srcAmount);
                }

                srcItem.exchangeOut(srcAmount);
                destItem.exchangeIn(destAmount);

                if (newPack.gamesToComplete() < this.gamesToComplete()) {
                    ret.add(newPack);
                }
            } catch (IndexOutOfBoundsException | UnexchangeableException ignored) { }
        }
        return ret;
    }

    public Map<ItemExchange, Integer> getExchangeRecord() {
        return exchangeRecord;
    }

    abstract int getItemTypesCount();

    abstract String getPrintStatusFmt();

    public void printStatus() {
        StringBuilder nums = new StringBuilder();
        StringBuilder games = new StringBuilder();

        Object[] currentNums = IntStream.range(1, getItemTypesCount() + 1)
                .mapToObj(x -> this.getItemViaCode(x).getCurrent())
                .toArray();
        Object[] currentGames = IntStream.range(1, getItemTypesCount() + 1)
                .mapToObj(x -> this.getItemViaCode(x).gamesLeft())
                .toArray();

        String fmt = getPrintStatusFmt();

        new Formatter(nums).format(fmt, currentNums);
        new Formatter(games).format(fmt, currentGames);

        System.out.println(
                String.format(
                        "-- Items count --\n%s\n-- Games left (excluding simulated %d) --\n%s\n-- Exchanges --\n%s",
                        nums.toString(), this.getSimulatedPlayed(), games.toString(), exchangeRecord));
    }

    public boolean equals(Object obj) {
        if (obj instanceof BackPackBase) {
            BackPackBase bp = (BackPackBase)obj;
            return IntStream.range(1, getItemTypesCount() + 1).allMatch(x -> this.getItemViaCode(x).getCurrent() == bp.getItemViaCode(x).getCurrent());
        } else {
            return false;
        }
    }

    @Override
    public int compareTo(Object o) {
        if (o instanceof BackPackBase) {
            BackPackBase bp = (BackPackBase)o;
            return this.gamesToComplete() - bp.gamesToComplete();
        } else {
            throw new IllegalArgumentException("Incomparable.");
        }
    }

    @Override
    public String toString() {
        return String.format("%s - %d games left", this.getClass().getName(), this.gamesToComplete());
    }
}
