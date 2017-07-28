package org.kscy.db.kscydatabase.model;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by pyh42 on 2017-07-28.
 */

public class MultiMatch{

    private String[] fields;
    private String query;

    public void setFields(String[] fields){
        this.fields = fields;
    }
    public String[] getFields(){
        return this.fields;
    }
    public void setQuery(String query){
        this.query = query;
    }
    public String getQuery(){
        return this.query;
    }

    public JSONObject toJsonObject()
    {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("fields", fields);
            jsonObject.put("query", query);
        } catch (JSONException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return jsonObject;
    }
}