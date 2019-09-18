package Items;

public class ItemExchange {
    private ItemEnum src;
    private ItemEnum dest;

    public ItemExchange(ItemEnum src, ItemEnum dest) {
        this.src = src;
        this.dest = dest;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ItemExchange) {
            ItemExchange ie = (ItemExchange)obj;
            return ie.src == this.src && ie.dest == this.dest;
        } else {
            return false;
        }
    }

    public static ItemEnum toItemEnum(BaseItem baseItem) {
        if (baseItem instanceof CoinBronze) {
            return ItemEnum.COIN_BRONZE;
        } else if (baseItem instanceof CoinSilver) {
            return ItemEnum.COIN_SILVER;
        } else if (baseItem instanceof CoinGold) {
            return ItemEnum.COIN_GOLD;
        } else if (baseItem instanceof EmblemSilver) {
            return ItemEnum.EMBLEM_SILVER;
        } else if (baseItem instanceof EmblemGold) {
            return ItemEnum.EMBLEM_GOLD;
        } else {
            return ItemEnum.UNKNOWN;
        }
    }

    @Override
    public String toString() {
        return String.format("%s to %s", this.src, this.dest);
    }

    @Override
    public int hashCode() {
        return this.src.hashCode() + this.dest.hashCode();
    }
}
