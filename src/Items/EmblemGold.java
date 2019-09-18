package Items;

public class EmblemGold extends BaseItem {
    public EmblemGold(int current, int goal) {
        super(current, goal);
    }

    @Override
    public double getAverageDrop() {
        return Double.parseDouble(ItemProps.props.getProperty("emblem.avg.gold"));
    }

    @Override
    public int getCurrent() {
        return this.current;
    }

    @Override
    public void accumulate(int gainedAmount) {
        this.current += gainedAmount;
    }
}
