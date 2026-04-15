# 🏥 Healthcare Management System

A desktop-based **Healthcare Management System** built using **Java Swing** and **MySQL**, designed to efficiently manage patients, doctors, appointments, and medical records.

---

## 🚀 Features

### 🔐 Authentication & Role-Based Access

* Secure login system
* Separate dashboards for:

  * 👨‍⚕️ Doctors
  * 🧑 Patients
  * 🛠 Admin

---

### 👨‍⚕️ Doctor Module

* View assigned appointments (only approved)
* Access patient medical history
* Add new diagnosis and treatment records

---

### 🧑 Patient Module

* View personal appointments
* Track appointment status (Approved / Pending / Rejected)
* View complete medical history

---

### 🛠 Admin Module

* Add new patient registration
* Manage patients data
* Manage Appointments

---

### 📅 Appointment System

* Book and manage appointments
* Status-based filtering
* Proper doctor-patient linking using relational database

---

### 📜 Medical History

* Store diagnosis, treatment, and visit date
* Linked with patients and appointments
* Accessible to both doctors and patients

---

## 🛠 Tech Stack

* **Frontend:** Java Swing (GUI)
* **Backend:** Java (JDBC)
* **Database:** MySQL

---

## 🗂 Database Design

The system uses a relational database with the following tables:

* `users` → Stores login credentials and roles
* `patients` → Patient details
* `doctors` → Doctor details
* `appointments` → Appointment records
* `medical_history` → Diagnosis & treatment history

---

## ⚙️ How to Run

1. Clone the repository:

   ```bash
   git clone https://github.com/yourusername/healthcare-management-system.git
   ```

2. Import the project into your IDE (IntelliJ / Eclipse)

3. Set up MySQL:

   * Create database: `healthcare`
   * Import provided SQL data

4. Update database connection in:

   ```
   DBConnection.java
   ```

5. Run:

   ```
   Main.java
   ```

---

## 💡 Key Highlights

* Clean separation of roles and responsibilities
* Real-world database relationships (FK constraints)
* Scalable structure for future enhancements

---

## 📈 Future Improvements

* Appointment approval system for doctors
* Bill generation
* Search and filtering features
* UI enhancement using modern frameworks
* Web-based version 

---

Developed for college project.
