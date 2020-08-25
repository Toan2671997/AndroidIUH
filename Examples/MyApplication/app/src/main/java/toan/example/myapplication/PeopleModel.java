package toan.example.myapplication;

public class PeopleModel {
    private int id;
    private String name;
    private String phone;
    private String groupName;
    private String email;
    private boolean isTempDelete;

    public PeopleModel(int id, String name, String phone, String email,  String groupName, boolean isTempDelete) {
        this.id = id;
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.groupName = groupName;
        this.isTempDelete = isTempDelete;
    }

    public PeopleModel() {
    }

    @Override
    public String toString() {
        return "PeopleModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", email='" + email + '\'' +
                ", groupName='" + groupName + '\'' +
                ", isTempDelete=" + isTempDelete +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getGroup() {
        return groupName;
    }

    public void setGroup(String groupName) {
        this.groupName = groupName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isTempDelete() {
        return isTempDelete;
    }

    public void setTempDelete(boolean tempDelete) {
        isTempDelete = tempDelete;
    }
}
