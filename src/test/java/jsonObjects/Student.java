package jsonObjects;

public class Student {
    private String firstName;
    private String lastName;
    private Integer age;
    private boolean hasScholarship;
    private String[] favoriteSubjects;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public boolean isHasScholarship() {
        return hasScholarship;
    }

    public void setHasScholarship(boolean hasScholarship) {
        this.hasScholarship = hasScholarship;
    }

    public String[] getFavoriteSubjects() {
        return favoriteSubjects;
    }

    public void setFavoriteSubjects(String[] favoriteSubjects) {
        this.favoriteSubjects = favoriteSubjects;
    }
}
