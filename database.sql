-- =====================================
-- Healthcare Management System Database
-- =====================================

CREATE DATABASE IF NOT EXISTS healthcare;
USE healthcare;

-- ================= USERS =================
CREATE TABLE users (
user_id INT AUTO_INCREMENT PRIMARY KEY,
username VARCHAR(50),
password VARCHAR(50),
role VARCHAR(20)
);

-- ================= DOCTORS =================
CREATE TABLE doctors (
doctor_id INT AUTO_INCREMENT PRIMARY KEY,
name VARCHAR(100),
specialization VARCHAR(100),
phone VARCHAR(15),
user_id INT,
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ================= PATIENTS =================
CREATE TABLE patients (
patient_id INT AUTO_INCREMENT PRIMARY KEY,
user_id INT,
name VARCHAR(100),
age INT,
gender VARCHAR(10),
phone VARCHAR(15),
address VARCHAR(255),
blood_group VARCHAR(5),
FOREIGN KEY (user_id) REFERENCES users(user_id)
);

-- ================= APPOINTMENTS =================
CREATE TABLE appointments (
appointment_id INT AUTO_INCREMENT PRIMARY KEY,
patient_id INT,
doctor_id INT,
appointment_date DATE,
status ENUM('Pending','Approved','Rejected') DEFAULT 'Pending',
reason VARCHAR(255),
FOREIGN KEY (patient_id) REFERENCES patients(patient_id),
FOREIGN KEY (doctor_id) REFERENCES doctors(doctor_id)
);

-- ================= MEDICAL HISTORY =================
CREATE TABLE medical_history (
history_id INT AUTO_INCREMENT PRIMARY KEY,
patient_id INT,
diagnosis VARCHAR(255),
treatment VARCHAR(255),
visit_date DATE,
FOREIGN KEY (patient_id) REFERENCES patients(patient_id)
);

-- ================= INSERT USERS =================
INSERT INTO users (username, password, role) VALUES
('admin','1234','admin'),

('drarun','1234','doctor'),
('drmeera','1234','doctor'),
('drrahul','1234','doctor'),
('drneha','1234','doctor'),
('drsanjay','1234','doctor'),
('dranjali','1234','doctor'),
('drvivek','1234','doctor'),
('drpriya','1234','doctor'),
('drkiran','1234','doctor'),
('drrakesh','1234','doctor'),

('john','1234','patient'),
('mary','1234','patient'),
('alex','1234','patient'),
('riya','1234','patient'),
('arjun','1234','patient'),
('sneha','1234','patient'),
('rahul','1234','patient'),
('divya','1234','patient'),
('manu','1234','patient'),
('anu','1234','patient'),
('nikhil','1234','patient'),
('lakshmi','1234','patient'),
('ajay','1234','patient'),
('megha','1234','patient'),
('suresh','1234','patient'),
('neethu','1234','patient'),
('vishnu','1234','patient'),
('asha','1234','patient'),
('deepak','1234','patient'),
('kavya','1234','patient');

-- ================= INSERT DOCTORS =================
INSERT INTO doctors (name, specialization, phone, user_id) VALUES
('Dr. Arun','Cardiologist','9000011111',2),
('Dr. Meera','Dermatologist','9000022222',3),
('Dr. Rahul','General Physician','9000033333',4),
('Dr. Neha','Pediatrician','9000044444',5),
('Dr. Sanjay','Orthopedic','9000055555',6),
('Dr. Anjali','Gynecologist','9000066666',7),
('Dr. Vivek','Neurologist','9000077777',8),
('Dr. Priya','ENT Specialist','9000088888',9),
('Dr. Kiran','Ophthalmologist','9000099999',10),
('Dr. Rakesh','Psychiatrist','9000000000',11);

-- ================= INSERT PATIENTS =================
INSERT INTO patients (user_id, name, age, gender, phone, address, blood_group) VALUES
(12,'John Mathew',25,'Male','9001111111','Kochi','O+'),
(13,'Mary Thomas',30,'Female','9001111112','Trivandrum','A+'),
(14,'Alex George',28,'Male','9001111113','Kottayam','B+'),
(15,'Riya Nair',22,'Female','9001111114','Calicut','O-'),
(16,'Arjun Das',35,'Male','9001111115','Kollam','AB+'),
(17,'Sneha Menon',27,'Female','9001111116','Thrissur','A-'),
(18,'Rahul Nair',40,'Male','9001111117','Kannur','B-'),
(19,'Divya Pillai',33,'Female','9001111118','Palakkad','O+'),
(20,'Manu Varghese',29,'Male','9001111119','Alleppey','A+'),
(21,'Anu Joseph',24,'Female','9001111120','Kochi','B+'),
(22,'Nikhil Kumar',31,'Male','9001111121','Trivandrum','O+'),
(23,'Lakshmi Nair',26,'Female','9001111122','Kollam','A+'),
(24,'Ajay Krishnan',38,'Male','9001111123','Calicut','B+'),
(25,'Megha Raj',21,'Female','9001111124','Kannur','O-'),
(26,'Suresh Babu',45,'Male','9001111125','Thrissur','AB-'),
(27,'Neethu Das',29,'Female','9001111126','Palakkad','A+'),
(28,'Vishnu Mohan',34,'Male','9001111127','Kottayam','B+'),
(29,'Asha Nair',32,'Female','9001111128','Alleppey','O+'),
(30,'Deepak Raj',27,'Male','9001111129','Kochi','A-'),
(31,'Kavya Nair',23,'Female','9001111130','Trivandrum','B+');

-- ================= INSERT APPOINTMENTS =================
INSERT INTO appointments (patient_id, doctor_id, appointment_date, status, reason) VALUES
(1,1,'2026-04-10','Approved','Chest pain'),
(2,1,'2026-04-12','Pending','Heart palpitations'),
(3,1,'2026-04-14','Approved','High BP'),
(4,2,'2026-04-11','Approved','Skin allergy'),
(5,2,'2026-04-13','Pending','Acne issue'),
(6,2,'2026-04-15','Approved','Rash'),
(7,3,'2026-04-10','Approved','Fever'),
(8,3,'2026-04-12','Rejected','Cold'),
(9,3,'2026-04-16','Approved','Body pain'),
(10,4,'2026-04-11','Approved','Child fever'),
(11,4,'2026-04-14','Pending','Cough'),
(12,4,'2026-04-18','Approved','Vaccination'),
(13,5,'2026-04-10','Approved','Back pain'),
(14,5,'2026-04-13','Approved','Knee pain'),
(15,5,'2026-04-17','Pending','Fracture'),
(16,6,'2026-04-12','Approved','Routine check'),
(17,6,'2026-04-15','Pending','Hormonal issue'),
(18,7,'2026-04-11','Approved','Migraine'),
(19,7,'2026-04-16','Approved','Headache'),
(20,8,'2026-04-13','Approved','Ear pain');

-- ================= INSERT MEDICAL HISTORY =================
INSERT INTO medical_history (patient_id, diagnosis, treatment, visit_date) VALUES
(1,'Hypertension','BP tablets prescribed','2026-04-10'),
(3,'High BP','Lifestyle changes','2026-04-14'),
(4,'Skin Allergy','Antihistamine','2026-04-11'),
(6,'Skin Rash','Ointment','2026-04-15'),
(7,'Viral Fever','Paracetamol','2026-04-10'),
(9,'Body Pain','Painkillers','2026-04-16'),
(10,'Child Fever','Syrup','2026-04-11'),
(12,'Vaccination','Routine vaccine','2026-04-18'),
(13,'Back Pain','Physiotherapy','2026-04-10'),
(14,'Knee Pain','Exercise','2026-04-13');
