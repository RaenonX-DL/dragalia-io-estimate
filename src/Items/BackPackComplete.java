package Items;

import java.util.*;
import java.util.stream.Collectors;

public class BackPackComplete extends BackPackBase {
    private CoinGold coinGold;
    private CoinSilver coinSilver;
    private CoinBronze coinBronze;

    private EmblemGold emblemFireGold;
    private EmblemSilver emblemFireSilver;
    private EmblemGold emblemWaterGold;
    private EmblemSilver emblemWaterSilver;
    private EmblemGold emblemWindGold;
    private EmblemSilver emblemWindSilver;
    private EmblemGold emblemLightGold;
    private EmblemSilver emblemLightSilver;
    private EmblemGold emblemDarkGold;
    private EmblemSilver emblemDarkSilver;

    public BackPackComplete() {
        this(
                Integer.parseInt(ItemProps.props.getProperty("coin.current.gold")),
                Integer.parseInt(ItemProps.props.getProperty("coin.current.silver")),
                Integer.parseInt(ItemProps.props.getProperty("coin.current.bronze")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.fire.gold")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.fire.silver")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.water.gold")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.water.silver")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.wind.gold")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.wind.silver")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.light.gold")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.light.silver")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.dark.gold")),
                Integer.parseInt(ItemProps.props.getProperty("emblem.current.dark.silver")),
                new HashMap<>(), 0);
    }

    public BackPackComplete(
            int coinGD, int coinSV, int coinBR,
            int emFireGD, int emFireSV, int emWaterGD, int emWaterSV,
            int emWindGD, int emWindSV, int emLightGD, int enLightSV,
            int emDarkGD, int emDarkSV,
            Map<ItemExchange, Integer> exchangeRecord, int simulatedPlayed) {
        super(exchangeRecord, simulatedPlayed);

        this.coinGold = new CoinGold(coinGD, Integer.parseInt(ItemProps.props.getProperty("coin.needed.gold")));
        this.coinSilver = new CoinSilver(coinSV, Integer.parseInt(ItemProps.props.getProperty("coin.needed.silver")));
        this.coinBronze = new CoinBronze(coinBR, Integer.parseInt(ItemProps.props.getProperty("coin.needed.bronze")));

        this.emblemFireGold = new EmblemGold(emFireGD, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.fire.gold")));
        this.emblemFireSilver = new EmblemSilver(emFireSV, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.fire.silver")));

        this.emblemWaterGold = new EmblemGold(emWaterGD, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.water.gold")));
        this.emblemWaterSilver = new EmblemSilver(emWaterSV, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.water.silver")));

        this.emblemWindGold = new EmblemGold(emWindGD, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.wind.gold")));
        this.emblemWindSilver = new EmblemSilver(emWindSV, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.wind.silver")));

        this.emblemLightGold = new EmblemGold(emLightGD, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.light.gold")));
        this.emblemLightSilver = new EmblemSilver(enLightSV, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.light.silver")));

        this.emblemDarkGold = new EmblemGold(emDarkGD, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.dark.gold")));
        this.emblemDarkSilver = new EmblemSilver(emDarkSV, Integer.parseInt(ItemProps.props.getProperty("emblem.needed.dark.silver")));
    }

    @Override
    public BackPackComplete deepCopy() {
        return new BackPackComplete(
                this.coinGold.getCurrent(), this.coinSilver.getCurrent(), this.coinBronze.getCurrent(),
                this.emblemFireGold.getCurrent(), this.emblemFireSilver.getCurrent(),
                this.emblemWaterGold.getCurrent(), this.emblemWaterSilver.getCurrent(),
                this.emblemWindGold.getCurrent(), this.emblemWindSilver.getCurrent(),
                this.emblemLightGold.getCurrent(), this.emblemLightSilver.getCurrent(),
                this.emblemDarkGold.getCurrent(), this.emblemDarkSilver.getCurrent(),
                this.getExchangeRecord()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), this.getSimulatedPlayed());
    }

    @Override
    void playGamesAccumulate(int games) {
        for (int i = 11; i <= 13; i++) {
            this.getItemViaCode(i).accumulate((int)Math.round(this.getItemViaCode(i).getAverageDrop() * games));
        }

        for (int i = 1; i <= 10; i += 2) {
            int GD = i;
            int SV = i + 1;

            if (this.getItemViaCode(GD).getNeeded() > 0 || this.getItemViaCode(SV).getNeeded() > 0) {
                this.getItemViaCode(GD).accumulate((int)Math.round(this.getItemViaCode(GD).getAverageDrop() * games));
                this.getItemViaCode(SV).accumulate((int)Math.round(this.getItemViaCode(SV).getAverageDrop() * games));
                break;
            }
        }
    }

    @Override
    int[][] getItemCombinationsByCode() {
        return new int[][] {
                {1, 2}, {2, 1}, {3, 4}, {4, 3}, {5, 6}, {6, 5}, {7, 8}, {8, 7}, {9, 10}, {10, 9},
                {11, 12}, {11, 13}, {12, 13}, {12, 11}, {13, 11}, {13, 12}
        };
    }

    @Override
    int getItemTypesCount() { return 13; }

    @Override
    public BaseItem getItemViaCode(int code) {
        if (code == 1) {
            return emblemFireGold;
        } else if (code == 2) {
            return emblemFireSilver;
        } else if (code == 3) {
            return emblemWaterGold;
        } else if (code == 4) {
            return emblemWaterSilver;
        } else if (code == 5) {
            return emblemWindGold;
        } else if (code == 6) {
            return emblemWindSilver;
        } else if (code == 7) {
            return emblemLightGold;
        } else if (code == 8) {
            return emblemLightSilver;
        } else if (code == 9) {
            return emblemDarkGold;
        } else if (code == 10) {
            return emblemDarkSilver;
        } else if (code == 11) {
            return coinGold;
        } else if (code == 12) {
            return coinSilver;
        } else if (code == 13) {
            return coinBronze;
        } else {
            throw new IllegalArgumentException(String.format("Code %d doesn't correspond to anything.", code));
        }
    }

    @Override
    String getPrintStatusFmt() {
        return "Fire Emblem\t\tGD: %4d | SV: %4d\n" +
                "Water Emblem\tGD: %4d | SV: %4d\n" +
                "Wind Emblem\t\tGD: %4d | SV: %4d\n" +
                "Light Emblem\tGD: %4d | SV: %4d\n" +
                "Dark Emblem\t\tGD: %4d | SV: %4d\n" +
                "Coin\t\t\tGD: %4d | SV: %4d | BR: %4d";
    }
}
