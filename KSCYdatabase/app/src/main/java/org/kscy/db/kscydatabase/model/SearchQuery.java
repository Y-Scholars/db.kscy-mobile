package org.kscy.db.kscydatabase.model;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pyh42 on 2017-07-28.
 */

public class SearchQuery {
    private Query query;

    public SearchQuery(Query query) {
        this.query = query;
    }

    public void setQuery(Query query){
        this.query = query;
    }
    public Query getQuery(){
        return this.query;
    }
    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("query", query.toJsonObject());
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}
