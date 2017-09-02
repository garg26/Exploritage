package simplifii.framework.utility;

import java.util.Collection;
import java.util.Map;

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

    public static boolean isEmpty(String str){
        return (str==null||str.isEmpty())?true:false;
    }

    public static boolean isNotEmpty(String str){
        return !isEmpty(str);
    }



    public static boolean isMapEmpty(Map map) {
        if(map==null||map.isEmpty())
            return true;
        return false;
    }
}
