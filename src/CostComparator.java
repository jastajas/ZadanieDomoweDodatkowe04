import java.util.Comparator;

public class CostComparator implements Comparator<Expenditure> {

    @Override
    public int compare(Expenditure o1, Expenditure o2) {
        if(o1.getCost() > o2.getCost())
            return 1;
        else if(o1.getCost() == o2.getCost())
            return 0;
        else
            return -1;
    }
}
