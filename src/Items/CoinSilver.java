package Items;

public class CoinSilver extends BaseItem {
    public CoinSilver(int current, int needed) {
        super(current, needed);
    }

    @Override
    public double getAverageDrop() {
        return Double.parseDouble(ItemProps.props.getProperty("coin.avg.silver"));
    }
}
