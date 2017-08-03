package simplifii.framework.rest.enums;

/**
 * Created by robin on 11/23/16.
 */

public enum ActionTitleEnum {

    WITHDRAW("withdraw"),SUPPORT("support"), ACCEPT("accept"), REJECT("reject"), EDIT("EDIT"),
    SEE_LOCATION("see location"), SHARE("share"),  RESPOND("respond"), APPROVE("approve");

    private String title;

    public String getTitle() {
        return title;
    }

    private ActionTitleEnum(String title){
        this.title = title;
    }
}
