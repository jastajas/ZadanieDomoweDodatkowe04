import java.util.Comparator;

public enum SortMethod {
    PROD_NAME("Nazwa produktu/usługi", new NameComparator()),
    CATEGORY("Kategoria", new CategoryComparator()),
    COST("Kwota wydatku rosnąco", new CostComparator()),
    COST_REV("Kwota wydatku malejąco", new CostComparatorRevOrder());

    private String sortType;
    private Comparator<Expenditure> comparator;

    SortMethod(String sortType, Comparator<Expenditure> comparator){
        this.sortType = sortType;
        this.comparator = comparator;
    }

    public Comparator<Expenditure> getComparator() {
        return comparator;
    }

    @Override
    public String toString() {
        return ordinal() + " - " + sortType;
    }
}
