package Items;

public abstract class BaseItem {
    protected int current;
    protected final int goal;

    public BaseItem(int current, int goal) {
        this.current = current;
        this.goal = goal;
    }

    abstract double getAverageDrop();

    public int getGoal() {
        return this.goal;
    }

    public int getCurrent() {
        return this.current;
    }

    public void exchangeOut(int amount) {
        this.current -= amount;
        if (this.current < 0) {
            throw new IndexOutOfBoundsException("Amount of item cannot be smaller than 0.");
        }
    }

    public void exchangeIn(int amount) {
        this.current += amount;
    }

    public int getNeeded() {
        return Math.max(this.getGoal() - this.getCurrent(), 0);
    }

    public int gamesLeft() {
        return (int)Math.round(this.getNeeded() / this.getAverageDrop());
    }

    public void accumulate(int gainedAmount) {
        this.current += gainedAmount;
    }

    @Override
    public String toString() {
        return String.format("<%s: %d/%d, %d left (%d games)>",
                this.getClass().getName(), this.getCurrent(), this.getGoal(), this.getNeeded(), this.gamesLeft());
    }
}
