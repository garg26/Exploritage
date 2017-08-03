package simplifii.framework.utility;

import java.util.Collection;
import java.util.Map;

import simplifii.framework.rest.v2.responses.Employee;

/**
 * Created by robin on 11/17/16.
 */

public class CollectionsUtils {

    public static boolean isEmpty(Collection collection){
        return (collection==null||collection.isEmpty())?true:false;
    }

    public static boolean isNotEmpty(Collection collection){
        return !isEmpty(collection);
    }

    public static boolean isMapEmpty(Map map) {
        if(map==null||map.isEmpty())
            return true;
        return false;
    }
}
