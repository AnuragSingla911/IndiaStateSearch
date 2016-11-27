package fetchinglist.sample.android.com.fetchinglist;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by jade on 26/11/16.
 */

public class IpParser implements IParser<IPModal> {

    @Override
    public Response<IPModal> parseResponse(String data) {
        return parseData(data);
    }

    private Response<IPModal> parseData(String data) {

        try {
            JSONObject object = new JSONObject(data).optJSONObject("RestResponse");
            JSONObject array = object.optJSONObject("result");
            if (array != null) {
                ArrayList<IPModal> list = new ArrayList<>();
                IPModal modal = new IPModal();
                modal.setmAbbr(array.optString("stateAbbr"));
                modal.setmCountry(array.optString("country"));
                modal.setmStateName(array.optString("city"));
                modal.setLatitude(array.optDouble("latitude"));
                modal.setLongitude(array.optDouble("longitude"));
                if(modal.getmStateName() != null && !modal.getmStateName().equalsIgnoreCase(""))
                list.add(modal);
                return new IpResponse().setData(list);
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }
}
