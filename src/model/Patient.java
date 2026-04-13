package model;

public class Patient {

    private int patientId;
    private int userId;
    private String name;
    private int age;
    private String gender;
    private String phone;
    private String address;
    private String bloodGroup;

    // Constructor (without IDs - for new patient)
    public Patient(String name, int age, String gender,
            String phone, String address, String bloodGroup) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    // Constructor (with IDs - for existing patient)
    public Patient(int patientId, int userId, String name, int age,
            String gender, String phone, String address, String bloodGroup) {
        this.patientId = patientId;
        this.userId = userId;
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.phone = phone;
        this.address = address;
        this.bloodGroup = bloodGroup;
    }

    // Getters
    public int getPatientId() {
        return patientId;
    }

    public int getUserId() {
        return userId;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public String getPhone() {
        return phone;
    }

    public String getAddress() {
        return address;
    }

    public String getBloodGroup() {
        return bloodGroup;
    }

    // Setters (optional but useful)
    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}