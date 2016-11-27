package fetchinglist.sample.android.com.fetchinglist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jade on 26/11/16.
 */

public class StateParser implements IParser<BaseModal> {
    @Override
    public Response<BaseModal> parseResponse(String data) {
        return parseData(data);
    }

    private Response<BaseModal> parseData(String data) {

        try {
            JSONObject object = new JSONObject(data).optJSONObject("RestResponse");
            JSONArray array = object.optJSONArray("result");
            if (array != null && array.length() > 0) {
                ArrayList<BaseModal> list = new ArrayList<>();
                for (int i = 0; i < array.length(); i++) {
                    JSONObject itemObject = array.getJSONObject(i);
                    if (itemObject != null) {
                        BaseModal modal = new BaseModal();
                        modal.setmAbbr(itemObject.optString("abbr"));
                        modal.setmCountry(itemObject.optString("country"));
                        modal.setmStateName(itemObject.optString("name"));
                        list.add(modal);
                    }
                }
                return new StateResponse().setData(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
