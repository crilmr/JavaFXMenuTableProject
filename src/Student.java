import javafx.beans.property.*;

public class Student {

    private final IntegerProperty id;
    private final StringProperty firstName;
    private final StringProperty lastName;
    private final StringProperty department;
    private final StringProperty major;
    private final StringProperty email;

    public Student(int id, String firstName, String lastName,
                   String department, String major, String email) {

        this.id = new SimpleIntegerProperty(id);
        this.firstName = new SimpleStringProperty(firstName);
        this.lastName = new SimpleStringProperty(lastName);
        this.department = new SimpleStringProperty(department);
        this.major = new SimpleStringProperty(major);
        this.email = new SimpleStringProperty(email);
    }

    public IntegerProperty idProperty() { return id; }
    public StringProperty firstNameProperty() { return firstName; }
    public StringProperty lastNameProperty() { return lastName; }
    public StringProperty departmentProperty() { return department; }
    public StringProperty majorProperty() { return major; }
    public StringProperty emailProperty() { return email; }

    public void setFirstName(String value) { firstName.set(value); }
    public void setLastName(String value) { lastName.set(value); }
    public void setDepartment(String value) { department.set(value); }
    public void setMajor(String value) { major.set(value); }
    public void setEmail(String value) { email.set(value); }
}



