import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class ExpenditureMain {
    public static void main(String[] args) {

        ArrayList<Expenditure> expenditures = null;
        try {
            expenditures = ExpenditureManage.readExpenditure();
        } catch (IOException e) {
            System.out.println("Nie odnaleziono pliku źródłowego.");
            expenditures = new ArrayList<>();
        }

        Scanner scanner = new Scanner(System.in);
        Option[] options = Option.values();

        System.out.println("Program: Wydatki++ (ver. 0.1)");
        String optionName = "";
        Boolean saveControl = true;

        do{
            System.out.println("Wybierz numer funkcji: ");
            for (Option option : options) {
                System.out.println(option.toString());
            }
            System.out.print("Nr funkcji: ");

            int userChoice = 0;
            try {
                userChoice = scanner.nextInt();
            } catch (InputMismatchException a){
                userChoice = 4;
                scanner.nextLine();
            }

            switch (userChoice){
                case 0:
                    ExpenditureManage.addExpenditure(expenditures);
                    optionName = Option.ADD_EXPENDITURE.name();
                    saveControl = false;
                    break;
                case 1:
                    ExpenditureManage.showExpenditures(expenditures);
                    optionName = Option.SHOW_EXPENDITURES.name();
                    break;
                case 2:
                    try {
                        ExpenditureManage.saveExpenditure(expenditures);
                        saveControl = true;
                    } catch (IOException e) {
                        System.out.println("Brak pliku docelowego. Wprowadzone dane nie zostaną zapisane.");
                    }
                    optionName = Option.SAVE_EXPENDITURES.name();
                    break;
                case 3:
                    optionName = Option.END_PROGRAM.name();
                    break;
                default:
                    System.out.println("Wprowadzono niepoprawne dane. Spróbuj ponownie.");
            }

        }while (!optionName.equals("END_PROGRAM"));

        boolean userDecision = false;

        if (!saveControl){
            System.out.println("Nie zapisano wprowadzonych zamian. Czy chcesz zapisać wprowadzone wydatki?");
            System.out.println("true / false: ");
            try {
                userDecision = scanner.nextBoolean();
            } catch (InputMismatchException a){
                System.out.println("Wprowadzone dane są niepoprawne. Zmiany nie zostaną zapisane.");
                userDecision = false;
            }
        }

        if (userDecision){
            try {
                ExpenditureManage.saveExpenditure(expenditures);
            } catch (IOException e) {
                System.out.println("Brak pliku docelowego. Wprowadzone dane nie zostaną zapisane.");
            }
        }

    }
}
