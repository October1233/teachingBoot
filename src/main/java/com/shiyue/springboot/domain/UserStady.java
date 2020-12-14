package com.shiyue.springboot.domain;

import java.util.Objects;

public class UserStady {


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStady userStady = (UserStady) o;
        return Objects.equals(school, userStady.school) &&
                Objects.equals(classroom, userStady.classroom) &&
                Objects.equals(peoNum, userStady.peoNum) &&
                Objects.equals(teacher, userStady.teacher);
    }

    @Override
    public int hashCode() {
        return Objects.hash(school, classroom, peoNum, teacher);
    }

    private String school;

    private String classroom;

    private Integer peoNum;

    private String teacher;

    @Override
    public String toString() {
        return "UserStady{" +
                "school='" + school + '\'' +
                ", classroom='" + classroom + '\'' +
                ", peoNum=" + peoNum +
                ", teacher='" + teacher + '\'' +
                '}';
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getClassroom() {
        return classroom;
    }

    public void setClassroom(String classroom) {
        this.classroom = classroom;
    }

    public Integer getPeoNum() {
        return peoNum;
    }

    public void setPeoNum(Integer peoNum) {
        this.peoNum = peoNum;
    }

    public String getTeacher() {
        return teacher;
    }

    public void setTeacher(String teacher) {
        this.teacher = teacher;
    }

}
