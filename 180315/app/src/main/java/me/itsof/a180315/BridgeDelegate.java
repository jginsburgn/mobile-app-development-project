package me.itsof.a180315;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by jonathan on 3/15/18.
 */

public interface BridgeDelegate {
    public void Result(Object data);
    public void Result(JSONObject data);
    public void Result(JSONArray data);
}
