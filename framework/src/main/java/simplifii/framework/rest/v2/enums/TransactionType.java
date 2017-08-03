package simplifii.framework.rest.v2.enums;

/**
 * Created by robin on 12/30/16.
 */

public enum TransactionType {

    COLLECTION("REVENUE","Collection"),PAYMENT("EXPENSE","Payment");

    private String apiText;
    private String displayText;

    public String getApiText() {
        return apiText;
    }

    public String getDisplayText() {
        return displayText;
    }

    TransactionType(String apiText, String displayText) {
        this.apiText = apiText;
        this.displayText = displayText;
    }

    public static TransactionType findByApiText(String apiText){
        for(TransactionType transactionType : TransactionType.values()){
            if(transactionType.apiText.equals(apiText)){
                return transactionType;
            }
        }
        return null;
    }
}
