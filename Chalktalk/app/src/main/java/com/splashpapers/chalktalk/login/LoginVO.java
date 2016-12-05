package com.splashpapers.chalktalk.login;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by manishsharma on 11/20/16.
 */
public class LoginVO implements Serializable {

    private String instituteName;
    private ArrayList<HashMap<String, String>> studentList;

    public String getInstituteName() {
        return instituteName;
    }

    public void setInstituteName(String instituteName) {
        this.instituteName = instituteName;
    }

    public ArrayList<HashMap<String, String>> getStudentList() {
        return studentList;
    }

    public void setStudentList(ArrayList<HashMap<String, String>> studentList) {
        this.studentList = studentList;
    }
}
