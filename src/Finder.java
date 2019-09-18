import Items.BackPack;
import Items.BackPackBase;
import Items.ItemProps;

import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

public class Finder {
    public static BackPackBase estimateFinal(BackPackBase initBackPack) {
        BackPackBase pack = initBackPack;

        // Result & Statistical variables
        int gamesLeft = estimateGames(pack).gamesToComplete();
        int gamesPlayed = 0;
        int gamesEstimateActual = 0, gamesEstimatePrev;
        int estActualStuckCounter = 0;

        while (gamesLeft > 0) {
            // Create a new backpack with initial state and simulate n games played.
            pack = new BackPack();
            pack.playGames(gamesPlayed + 1);
            gamesPlayed++;

            // Start BFS search on a single pack for the optimized status the of back pack
            pack = estimateGames(pack);

            // Update result and vars
            gamesLeft = Math.min(pack.gamesToComplete(), gamesLeft - 1);
            gamesEstimatePrev = gamesEstimateActual;
            gamesEstimateActual = gamesPlayed + gamesLeft;

            // Print current progress
            System.out.println();
            System.out.println(String.format("Simulated %d games. Estimated %d games left.", gamesPlayed, gamesEstimateActual));
            if (Boolean.parseBoolean(ItemProps.props.getProperty("sys.detail"))) {
                pack.printStatus();
            }

            // If the same result is produced n times, terminate the while loop.
            if (gamesEstimatePrev == gamesEstimateActual) {
                estActualStuckCounter++;
                if (estActualStuckCounter > 10) {
                    System.out.println("Terminated as the same result of estimate reaches to the limit(10).");
                    break;
                }
            }

            // if the games left is 0 (Done), also terminate the while loop.
            if (gamesLeft == 0) {
                System.out.println("Done farming. Estimated %d games left from now.");
                break;
            }
        }

        return pack;
    }

    public static BackPackBase estimateGames(BackPackBase initBackPack) {
        PriorityQueue<BackPackBase> frontier = new PriorityQueue<>();
        PriorityQueue<BackPackBase> explored = new PriorityQueue<>();
        frontier.add(initBackPack);

        while (!frontier.isEmpty()) {
            BackPackBase current = frontier.poll();

            if (current.gamesToComplete() == 0) {
                System.out.println("Solution found.");
                break;
            }

            explored.add(current);

            List<BackPackBase> successors = current.getExchangeOncePossibilities()
                    .stream()
                    .filter(x -> !explored.contains(x) && x.gamesToComplete() < Objects.requireNonNull(explored.peek()).gamesToComplete())
                    .collect(Collectors.toList());

            frontier.addAll(successors);
        }

        if (explored.size() > 0) {
            return explored.poll();
        } else {
            throw new IllegalArgumentException("Frontier is empty.");
        }
    }
}
