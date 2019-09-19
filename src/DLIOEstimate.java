import Items.BackPackBase;
import Items.BackPackComplete;
import Items.BackPackSimple;
import Items.ItemProps;

public class DLIOEstimate {
    public static void main(String[] args) {
        String propFilePath = "configs.properties";
        if (args.length > 0) {
            propFilePath = args[0];
        }
        if (!ItemProps.loadProperties(propFilePath)) {
            System.out.println(String.format("Property file loading failed (Given Path: %s)", propFilePath));
            System.exit(1);
        }

        BackPackBase finalPack;
        if (Boolean.parseBoolean(ItemProps.props.getProperty("sys.coinonly"))) {
            finalPack = Finder.estimateFinal(new BackPackSimple());
        } else {
            finalPack = Finder.estimateFinal(new BackPackComplete());
        }
        System.out.println("----------------------------------");
        if (finalPack != null) {
            System.out.println(String.format("*** Estimate %d games left***", finalPack.gamesToComplete(true)));
            System.out.println();
            System.out.println("Final backpack status:");
            finalPack.printStatus();
        } else {
            System.out.println("No Solution.");
        }
    }
}
