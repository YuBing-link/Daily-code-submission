CREATE TABLE MY_TABLE (
  department_name VARCHAR,
  `(select avg(salary) from employees e where department_id = d.department_id)` DECIMAL
);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('人力资源', 17000.000000);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('研发部', 17250.000000);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('市场部', 12500.000000);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('财务部', 16000.000000);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('销售部', 11000.000000);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('人力资源', null);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('研发部', null);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('市场部', null);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('财务部', null);
INSERT INTO MY_TABLE(department_name, `(select avg(salary) from employees e where department_id = d.department_id)`) VALUES ('销售部', null);
