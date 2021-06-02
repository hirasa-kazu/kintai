create table employee(
	employee_code varchar(20) not null primary key,
	last_name varchar(20),
	first_name varchar(20),
	last_kana_name varchar(20),
	first_kana_name varchar(20),
	gender int,
	section_code varchar(20),
	birth_day varchar(20),
	hire_date varchar(20),
	password varchar(30)
);