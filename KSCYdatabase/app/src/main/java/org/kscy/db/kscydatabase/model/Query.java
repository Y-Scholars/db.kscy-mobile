package org.kscy.db.kscydatabase.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pyh42 on 2017-07-28.
 */

public class Query{

    private MultiMatch multi_match;

    public Query(MultiMatch multimatch) {
        this.multi_match = multimatch;
    }

    public void setMultiMatch(MultiMatch multiMatch){
        this.multi_match = multiMatch;
    }
    public MultiMatch getMultiMatch(){
        return this.multi_match;
    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("multi_match", multi_match.toJsonObject());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}