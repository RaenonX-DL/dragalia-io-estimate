package Items;

class UnexchangeableException extends Exception {
    public UnexchangeableException(ItemEnum srcItem, ItemEnum destItem) {
        super(String.format("Unable to exchange from %s to %s.", srcItem.getClass().getName(), destItem.getClass().getName()));
    }
}

public class Exchanger {
    public static int exchangeItem(ItemEnum srcItem, int srcAmount, ItemEnum destItem) throws UnexchangeableException {
        if (srcItem == ItemEnum.COIN_BRONZE) {
            if (destItem == ItemEnum.COIN_GOLD) {
                return Math.floorDiv(srcAmount, 12);
            } else if (destItem == ItemEnum.COIN_SILVER) {
                return Math.floorDiv(srcAmount, 4);
            }
        } else if (srcItem == ItemEnum.COIN_SILVER) {
            if (destItem == ItemEnum.COIN_GOLD) {
                return Math.floorDiv(srcAmount, 3);
            } else if (destItem == ItemEnum.COIN_BRONZE) {
                return srcAmount * 3;
            }
        } else if (srcItem == ItemEnum.COIN_GOLD) {
            if (destItem == ItemEnum.COIN_SILVER) {
                return srcAmount * 2;
            } else if (destItem == ItemEnum.COIN_BRONZE) {
                return srcAmount * 6;
            }
        } else if (srcItem == ItemEnum.EMBLEM_GOLD) {
            if (destItem == ItemEnum.EMBLEM_SILVER) {
                return srcAmount * 3;
            }
        } else if (srcItem == ItemEnum.EMBLEM_SILVER) {
            if (destItem == ItemEnum.EMBLEM_GOLD) {
                return Math.floorDiv(srcAmount, 6);
            }
        }

        throw new UnexchangeableException(srcItem, destItem);
    }

    public static int minSrcAmount(BaseItem srcItem, BaseItem destItem) {
        if (srcItem instanceof CoinBronze) {
            if (destItem instanceof CoinGold) {
                return 12;
            } else if (destItem instanceof CoinSilver) {
                return 4;
            }
        } else if (srcItem instanceof CoinSilver) {
            if (destItem instanceof CoinGold) {
                return 3;
            }
        } else if (srcItem instanceof EmblemSilver) {
            if (destItem instanceof EmblemGold) {
                return 6;
            }
        }
        return 1;
    }
}
