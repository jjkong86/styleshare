package styleshare.task.utils;

import org.apache.commons.math3.distribution.EnumeratedDistribution;
import org.apache.commons.math3.util.Pair;
import org.junit.Test;
import styleshare.task.DataGenFactory;

import java.util.*;

public class DataGenFactoryTest {
    @Test
    public void test1() {

        //given
        List<Pair<Integer, Double>> mileageList = new ArrayList<>();
        mileageList.add(new Pair<>(1, 80d));
        mileageList.add(new Pair<>(2, 9d));
        mileageList.add(new Pair<>(3, 1d));
        mileageList.add(new Pair<>(4, 2d));
        mileageList.add(new Pair<>(5, 3d));
        mileageList.add(new Pair<>(6, 5d));

        //then
        DataGenFactory dist = new DataGenFactory(mileageList);
        Map<Integer, Integer> map = new HashMap<>();
        int length = 10000000;
        for (int i = 0; i < length; i++) {
            Integer pair = dist.getWeightedRandomSample();
            map.put(pair, map.getOrDefault(pair, 0) + 1);
        }

        //then
        StringJoiner sj = new StringJoiner("\n");
        for (Integer integer : map.keySet()) {
            sj.add(String.valueOf((double)map.get(integer)/100000));
        }

        System.out.println(sj);
    }
}
