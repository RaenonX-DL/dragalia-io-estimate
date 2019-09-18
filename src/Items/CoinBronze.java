package Items;

public class CoinBronze extends BaseItem {
    public CoinBronze(int current, int needed) {
        super(current, needed);
    }

    @Override
    public double getAverageDrop() {
        return Double.parseDouble(ItemProps.props.getProperty("coin.avg.bronze"));
    }
}
