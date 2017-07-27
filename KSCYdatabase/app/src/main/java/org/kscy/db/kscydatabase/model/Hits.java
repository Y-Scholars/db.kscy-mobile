package org.kscy.db.kscydatabase.model;

import java.util.ArrayList;

/**
 * Created by pyh42 on 2017-07-27.
 */

public class Hits {
    private int total;

    private String max_score;

    private ArrayList<Hits> hits;

    private String _type;

    private _source _source;

    private String _id;

    private String _index;

    private String _score;

    public String get_type ()
    {
        return _type;
    }

    public void set_type (String _type)
    {
        this._type = _type;
    }

    public _source get_source ()
    {
        return _source;
    }

    public void set_source (_source _source)
    {
        this._source = _source;
    }

    public String get_id ()
    {
        return _id;
    }

    public void set_id (String _id)
    {
        this._id = _id;
    }

    public String get_index ()
    {
        return _index;
    }

    public void set_index (String _index)
    {
        this._index = _index;
    }

    public String get_score ()
    {
        return _score;
    }

    public void set_score (String _score)
    {
        this._score = _score;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public String getMax_score() {
        return max_score;
    }

    public void setMax_score(String max_score) {
        this.max_score = max_score;
    }

    public ArrayList<Hits> getHits() {
        return hits;
    }

    public void setHits(ArrayList<Hits> hits) {
        this.hits = hits;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [_type = "+_type+", _source = "+_source+", _id = "+_id+", _index = "+_index+", _score = "+_score+"]";
    }
}
