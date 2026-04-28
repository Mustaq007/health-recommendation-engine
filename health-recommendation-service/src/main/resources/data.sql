insert into users
(email_id,password,roles)
values('mark_lepak@gmail.com','12345','user');

insert into users
(email_id,password,roles)
values('john_doe@gmail.com','12345','admin');

insert into users
(email_id,password,roles)
values('thomas_briggs@gmail.com','12345','doctor');


insert into users
(email_id,password,roles)
values('koo_lee@gmail.com','12345','user');


INSERT INTO risk_assessment (id, min_score, max_score, recommendation)
VALUES 
(1,0, 2, 'Low risk - Maintain a healthy lifestyle.'),
(2,3, 4, 'Moderate risk - Regular monitoring and healthy lifestyle advised.'),
(3,5, 100, 'High risk - Immediate lifestyle changes recommended.');


insert into user_recomendation
(id,disease,recommendation)
values(1,'Diabetes','Eat a balanced diet with whole grains, lean proteins, and healthy fats and Avoid sugary foods and drinks.');

insert into user_recomendation
(id,disease,recommendation)
values(2,'Hypertension','Reduce salt intake and avoid processed foods');

insert into user_recomendation
(id,disease,recommendation)
values(3,'Acid Reflux','Eat smaller meals and avoid eating late at night');

INSERT into user_details (ADDRESS, AGE, CONTACT_NUMBER, DOB, DOCTORS_RECOMMENDATION, EMAIL_ADDRESS, GENDER,LIFE_STYLE, MEDICAL_HISTORY, NAME, PERSONALIZED_RECOMMENDATION, RISK_RECOMMENDATION,REQUIRE_CONSULTATION)
VALUES 
('123 Main Street',61, '1234567890', '1990-05-15', 'Insuline 1 times per day', 'mark_lepak@gmail.com', 'Male', 'smoking', 'Diabetes', 'Mark Lepak', 'Eat a balanced diet with whole grains, lean proteins, and healthy fats and Avoid sugary foods and drinks','Moderate risk - Regular monitoring and healthy lifestyle advised.',true)






