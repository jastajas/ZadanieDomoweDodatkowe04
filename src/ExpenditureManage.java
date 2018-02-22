import javax.print.DocFlavor;
import java.io.*;
import java.util.*;

public class ExpenditureManage {

    public static void addExpenditure(ArrayList<Expenditure> expenditures){

        try {
            expenditures.add(createExpenditure());
        } catch (IncorrectCostValueExceptions incorrectCostValueExceptions) {
            System.out.println("Niewłaściwa wartość wydatku. Wydatek nie został dodany.");
        } catch (NullValueExeption nullValueExeption) {
            System.out.println("Nie wprowadzono podstawowych danych. Wydatek nie został dodany.");
        }
    }

    public static Expenditure createExpenditure() throws IncorrectCostValueExceptions, NullValueExeption {
        Scanner scanner = new Scanner(System.in);
        scanner.useLocale(Locale.US);

        System.out.println("Podaj nazwę produktu lub usługi: ");
        String productName = scanner.nextLine();
        System.out.println("Podaj opis wydatku: ");
        String decription = scanner.nextLine();
        System.out.println("Podaj nazwę kategori: ");
        String category = scanner.nextLine();
        System.out.println("Podaj kwotę wydatku: ");
        double cost = 0;
        if (decription.equals("") || category.equals("") || productName.equals("")){
            throw new NullValueExeption();
        }
        try {
            cost = scanner.nextDouble();
        } catch (InputMismatchException a){
            System.out.println("Wprowadzono niewłaściwe dane.");
        }
        if (cost <= 0){
            throw new IncorrectCostValueExceptions();
        }
        return new Expenditure(productName,decription,category,cost);

    }

    public static void showExpenditures(ArrayList<Expenditure> expenditures){
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dostępne metody sortowania:");

        SortMethod[] sortMethods = SortMethod.values();
        for (SortMethod sortMethod : sortMethods) {
            System.out.println(sortMethod.toString());
        }

        System.out.print("Wybierz nr metody sortowania:");
        int userChoice = 0;
        try {
            userChoice = scanner.nextInt();
        } catch (InputMismatchException e) {
            userChoice = 4;
        }

        Comparator<Expenditure> comparator = null;

        switch (userChoice){
            case 0:
                comparator = SortMethod.PROD_NAME.getComparator();
                break;
            case 1:
                comparator = SortMethod.CATEGORY.getComparator();
                break;
            case 2:
                comparator = SortMethod.COST.getComparator();
                break;
            case 3:
                comparator = SortMethod.COST_REV.getComparator();
            default:
                System.out.println("Wprowadzono niewłaściwe dane.");
        }

        System.out.println("");

        if (comparator != null) {
            Collections.sort(expenditures, comparator);
            System.out.println(expenditures.toString());
        }

    }

    public static void saveExpenditure(ArrayList<Expenditure> expenditures) throws IOException {
        FileWriter fw = new FileWriter("Expenditures.csv",false);
        BufferedWriter bfw = new BufferedWriter(fw);

        for (Expenditure expenditure : expenditures) {
            String line = expenditure.getProductName() + ";" + expenditure.getDescription() + ";"
                    + expenditure.getCategory() + ";" + expenditure.getCost();
            bfw.write(line);
            bfw.newLine();
        }
        bfw.close();
    }

    public static ArrayList<Expenditure> readExpenditure() throws IOException {

        ArrayList<Expenditure> expenditures = new ArrayList<>();

        FileReader fr = new FileReader("Expenditures.csv");
        BufferedReader bwr = new BufferedReader(fr);
        String line = null;

        while ((line = bwr.readLine()) != null){
            String[] temp  = line.split(";");
            expenditures.add(new Expenditure(temp[0],temp[1],temp[2],Double.valueOf(temp[3])));
        }
        bwr.close();
        return expenditures;
    }

}
