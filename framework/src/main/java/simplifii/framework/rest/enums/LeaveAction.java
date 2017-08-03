package simplifii.framework.rest.enums;

/**
 * Created by robin on 11/23/16.
 */

public enum LeaveAction {

    WITHDRAW(2), ACCEPT(1), REJECT(0);

    private int type;

    public int getType() {
        return type;
    }

    private LeaveAction(int type){
        this.type  = type;
    }
}
