package Items;

public class EmblemSilver extends BaseItem {
    public EmblemSilver(int current, int goal) {
        super(current, goal);
    }

    @Override
    public double getAverageDrop() {
        return Double.parseDouble(ItemProps.props.getProperty("emblem.avg.silver"));
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
