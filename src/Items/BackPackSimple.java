package Items;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class BackPackSimple extends BackPackBase {
    private CoinGold coinGold;
    private CoinSilver coinSilver;
    private CoinBronze coinBronze;

    public BackPackSimple() {
        this(
                Integer.parseInt(ItemProps.props.getProperty("coin.current.gold")),
                Integer.parseInt(ItemProps.props.getProperty("coin.current.silver")),
                Integer.parseInt(ItemProps.props.getProperty("coin.current.bronze")),
                new HashMap<>(), 0);
    }

    public BackPackSimple(int coinGD, int coinSV, int coinBR, Map<ItemExchange, Integer> exchangeRecord, int simulatedPlayed) {
        super(exchangeRecord, simulatedPlayed);

        this.coinGold = new CoinGold(coinGD, Integer.parseInt(ItemProps.props.getProperty("coin.needed.gold")));
        this.coinSilver = new CoinSilver(coinSV, Integer.parseInt(ItemProps.props.getProperty("coin.needed.silver")));
        this.coinBronze = new CoinBronze(coinBR, Integer.parseInt(ItemProps.props.getProperty("coin.needed.bronze")));
    }

    @Override
    BackPackSimple deepCopy() {
        return new BackPackSimple(this.coinGold.getCurrent(), this.coinSilver.getCurrent(), this.coinBronze.getCurrent(),
                this.getExchangeRecord()
                        .entrySet()
                        .stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue)), this.getSimulatedPlayed());
    }

    @Override
    void playGamesAccumulate(int games) {
        for (int i = 1; i <= getItemTypesCount(); i++) {
            this.getItemViaCode(i).accumulate((int)Math.round(this.getItemViaCode(i).getAverageDrop() * games));
        }
    }

    @Override
    BaseItem getItemViaCode(int code) {
        return null;
    }

    @Override
    int[][] getItemCombinationsByCode() {
        return new int[][] {
                {1, 2}, {1, 3}, {2, 3}, {2, 1}, {3, 1}, {3, 2}
        };
    }

    @Override
    String getPrintStatusFmt() {
        return "Coin\t\t\tGD: %4d | SV: %4d | BR: %4d";
    }

    @Override
    int getItemTypesCount() {
        return 3;
    }
}
