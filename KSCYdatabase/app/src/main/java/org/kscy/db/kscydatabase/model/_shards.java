package org.kscy.db.kscydatabase.model;

/**
 * Created by pyh42 on 2017-07-27.
 */

public class _shards {
    private String total;

    private String failed;

    private String successful;

    public String getTotal ()
    {
        return total;
    }

    public void setTotal (String total)
    {
        this.total = total;
    }

    public String getFailed ()
    {
        return failed;
    }

    public void setFailed (String failed)
    {
        this.failed = failed;
    }

    public String getSuccessful ()
    {
        return successful;
    }

    public void setSuccessful (String successful)
    {
        this.successful = successful;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [total = "+total+", failed = "+failed+", successful = "+successful+"]";
    }
}
