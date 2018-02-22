public enum Option {
    ADD_EXPENDITURE("Dodaj wydatek"),
    SHOW_EXPENDITURES("Pokaż listę wydatków"),
    SAVE_EXPENDITURES("Zapisz listę wydatków"),
    END_PROGRAM("Zakończ program");

    private String optionDescription;

    Option(String optionDescription){
        this.optionDescription = optionDescription;
    }

    @Override
    public String toString() {
        return ordinal() + " - " + optionDescription;
    }
}
