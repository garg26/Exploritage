package simplifii.framework.rest.v2.responses;

/**
 * Created by robin on 12/20/16.
 */

public class ResponseDataContainer<T> {

    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

}
