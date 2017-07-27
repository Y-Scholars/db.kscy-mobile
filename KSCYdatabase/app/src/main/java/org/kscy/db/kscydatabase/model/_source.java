package org.kscy.db.kscydatabase.model;

/**
 * Created by pyh42 on 2017-07-27.
 */

public class _source {
    private String researcher_id;

    private String phone_number;

    private String abstract_eng;

    private String abstract_kor;

    private String org;

    private String type;

    private String team_num;

    private String file_url;

    private String category;

    private String email;

    private String researcher_name;

    private String has_presentated;

    private String grade;

    private String research_name;

    private String team_id;

    public String getResearcher_id ()
    {
        return researcher_id;
    }

    public void setResearcher_id (String researcher_id)
    {
        this.researcher_id = researcher_id;
    }

    public String getPhone_number ()
    {
        return phone_number;
    }

    public void setPhone_number (String phone_number)
    {
        this.phone_number = phone_number;
    }

    public String getAbstract_eng ()
    {
        return abstract_eng;
    }

    public void setAbstract_eng (String abstract_eng)
    {
        this.abstract_eng = abstract_eng;
    }

    public String getAbstract_kor ()
    {
        return abstract_kor;
    }

    public void setAbstract_kor (String abstract_kor)
    {
        this.abstract_kor = abstract_kor;
    }

    public String getOrg ()
    {
        return org;
    }

    public void setOrg (String org)
    {
        this.org = org;
    }

    public String getType ()
    {
        return type;
    }

    public void setType (String type)
    {
        this.type = type;
    }

    public String getTeam_num ()
    {
        return team_num;
    }

    public void setTeam_num (String team_num)
    {
        this.team_num = team_num;
    }

    public String getFile_url ()
    {
        return file_url;
    }

    public void setFile_url (String file_url)
    {
        this.file_url = file_url;
    }

    public String getCategory ()
    {
        return category;
    }

    public void setCategory (String category)
    {
        this.category = category;
    }

    public String getEmail ()
    {
        return email;
    }

    public void setEmail (String email)
    {
        this.email = email;
    }

    public String getResearcher_name ()
    {
        return researcher_name;
    }

    public void setResearcher_name (String researcher_name)
    {
        this.researcher_name = researcher_name;
    }

    public String getHas_presentated ()
    {
        return has_presentated;
    }

    public void setHas_presentated (String has_presentated)
    {
        this.has_presentated = has_presentated;
    }

    public String getGrade ()
    {
        return grade;
    }

    public void setGrade (String grade)
    {
        this.grade = grade;
    }

    public String getResearch_name ()
    {
        return research_name;
    }

    public void setResearch_name (String research_name)
    {
        this.research_name = research_name;
    }

    public String getTeam_id ()
    {
        return team_id;
    }

    public void setTeam_id (String team_id)
    {
        this.team_id = team_id;
    }

    @Override
    public String toString()
    {
        return "ClassPojo [researcher_id = "+researcher_id+", phone_number = "+phone_number+", abstract_eng = "+abstract_eng+", abstract_kor = "+abstract_kor+", org = "+org+", type = "+type+", team_num = "+team_num+", file_url = "+file_url+", category = "+category+", email = "+email+", researcher_name = "+researcher_name+", has_presentated = "+has_presentated+", grade = "+grade+", research_name = "+research_name+", team_id = "+team_id+"]";
    }
}
