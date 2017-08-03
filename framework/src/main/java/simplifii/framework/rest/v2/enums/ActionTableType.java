package simplifii.framework.rest.v2.enums;

/**
 * Created by robin on 12/21/16.
 */

public enum ActionTableType {

    LEAVE("leave_applications"),
    LOCATION("marked_locations"),
    SMILEY("smileys"),
    STAR("stars"),
    TRANSACTION("transactions"),
    RESIGN("lwd");

    private String tableName;

    public String getTableName() {
        return tableName;
    }

    private ActionTableType(String tableName) {
        this.tableName = tableName;
    }

    public static ActionTableType findByName(String tableName){
        for(ActionTableType actionTableType : ActionTableType.values()){
            if(actionTableType.getTableName().equals(tableName)){
                return actionTableType;
            }
        }
        return null;
    }
}
