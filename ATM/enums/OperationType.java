package ATM.enums;

public enum OperationType {
    WITHDRAW_CASH("WITHDRAW_CASH"),
    DEPOSIT_CASH("DEPOSIT_CASH"),
    CHECK_BALANCE("CHECK_BALANCE");

    private final String description;

    private OperationType(String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}