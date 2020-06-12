package styleshare.task;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;

import java.util.List;

public class DataGenFactory {
    EnumeratedDistribution<Integer> dist;

    public DataGenFactory(List<Pair<Integer, Double>> list) {
        dist = new EnumeratedDistribution<>(list);
    }

    public int getWeightedRandomSample() {
        return dist.sample();
    }
}
