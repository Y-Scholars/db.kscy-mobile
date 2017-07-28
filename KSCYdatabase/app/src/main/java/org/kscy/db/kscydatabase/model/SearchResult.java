package org.kscy.db.kscydatabase.model;

/**
 * Created by pyh42 on 2017-07-27.
 */

public class SearchResult {
    private Hits hits;

    private _shards _shards;

    private String timed_out;

    private String took;

    public Hits getHits ()
    {
        return hits;
    }

    public void setHits (Hits hits)
    {
        this.hits = hits;
    }

    public _shards get_shards ()
    {
        return _shards;
    }

    public void set_shards (_shards _shards)
    {
        this._shards = _shards;
    }

    public String getTimed_out ()
    {
        return timed_out;
    }

    public void setTimed_out (String timed_out)
    {
        this.timed_out = timed_out;
    }

    public String getTook ()
    {
        return took;
    }

    public void setTook (String took)
    {
        this.took = took;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [hits = "+hits.toString()+", _shards = "+_shards.toString()+", timed_out = "+timed_out+", took = "+took+"]";
    }
}
