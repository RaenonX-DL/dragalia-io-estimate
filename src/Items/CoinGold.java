package Items;

public class CoinGold extends BaseItem {
    public CoinGold(int current, int needed) {
        super(current, needed);
    }

    @Override
    public double getAverageDrop() {
        return Double.parseDouble(ItemProps.props.getProperty("coin.avg.gold"));
    }
}
