package simplifii.framework.rest.enums;

/**
 * Created by robin on 11/23/16.
 */

public enum ActionTypeEnum {

    RESIGNATION("resignation"), LEAVE("leave"), POSITION("position"), SMILEY("smiley"), STAR("star"), ATTENDANCE("attendance");

    private String type;

    public String getType() {
        return type;
    }

    private ActionTypeEnum(String type){
        this.type = type;
    }

    public static ActionTypeEnum getByType(String type){
        for(ActionTypeEnum actionEnum : ActionTypeEnum.values()){
            if(actionEnum.type.equalsIgnoreCase(type)){
                return actionEnum;
            }
        }
        return null;
    }
}
