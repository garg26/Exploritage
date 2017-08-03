package simplifii.framework.asyncmanager;

import org.json.JSONException;

import java.io.IOException;
import java.util.Map;

import simplifii.framework.exceptionhandler.RestException;

/**
 * Created by robin on 10/18/16.
 */

public class MultipartService extends FileUploadService {
    @Override
    public Object getData(Object... params) throws IOException, RestException, JSONException {
        if (params != null && params.length > 0) {
            HttpParamObject param = (HttpParamObject) params[0];
            MultipartUtility util = new MultipartUtility(param.getUrl(), "UTF-8");


            for (Map.Entry<String, String> pair : param.getHeaders().entrySet()) {
                util.addHeaderField(pair.getKey(), pair.getValue());
            }
            util.createConnection();
//            util.addFilePart(param.getFileKeyName(), param.getFile());
            for (Map.Entry<String, String> pair : param.getPostParams().entrySet()) {
                util.addFormField(pair.getKey(), pair.getValue());
            }

            return parseJson(util.finish(), param);
        } else {
            throw new IllegalArgumentException();
        }
    }
}
