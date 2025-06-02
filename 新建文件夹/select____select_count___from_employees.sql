CREATE TABLE MY_TABLE (
  department_id INT,
  department_name VARCHAR,
  location VARCHAR,
  created_at TIMESTAMP,
  `(select count(*) from employees e where e.department_id = d.department_id)` BIGINT
);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (1, '人力资源', '北京', '2025-05-31 19:01:16', 2);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (2, '研发部', '上海', '2025-05-31 19:01:16', 4);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (3, '市场部', '广州', '2025-05-31 19:01:16', 2);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (4, '财务部', '深圳', '2025-05-31 19:01:16', 1);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (5, '销售部', '杭州', '2025-05-31 19:01:16', 1);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (6, '人力资源', '北京', '2025-05-31 19:02:25', 0);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (7, '研发部', '上海', '2025-05-31 19:02:25', 0);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (8, '市场部', '广州', '2025-05-31 19:02:25', 0);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (9, '财务部', '深圳', '2025-05-31 19:02:25', 0);
INSERT INTO MY_TABLE(department_id, department_name, location, created_at, `(select count(*) from employees e where e.department_id = d.department_id)`) VALUES (10, '销售部', '杭州', '2025-05-31 19:02:25', 0);
