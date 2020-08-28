CREATE TABLE IF NOT EXISTS dept (deptno INT PRIMARY KEY auto_increment,-- 部门编号
dname VARCHAR (14),-- 部门名字
loc VARCHAR (13)-- 地址
)

CREATE TABLE IF NOT EXISTS emp (empno INT PRIMARY KEY auto_increment,-- 员工编号
ename VARCHAR (10),-- 员工姓名										-
job VARCHAR (9),-- 岗位
mgr INT,-- 直接领导编号
hiredate date,-- 雇佣日期，入职日期
sal INT,-- 薪水
comm INT,-- 提成
deptno INT NOT NULL,-- 部门编号
FOREIGN KEY (deptno) REFERENCES dept (deptno));

SELECT *FROM emp;

CREATE TABLE IF NOT EXISTS admin (username VARCHAR (16) PRIMARY KEY,PASSWORD VARCHAR (18) NOT NULL)

INSERT INTO admin VALUES ('root','admin');-- 插入管理员信息

INSERT INTO dept VALUES (10,'财务部','北京');--插入部门信息
INSERT INTO dept VALUES (20,'研发部','上海');
INSERT INTO dept VALUES (30,'销售部','广州');
INSERT INTO dept VALUES (40,'行政部','深圳');

INSERT INTO emp VALUES (7369,'刘一','职员',7902,'1980-12-17',800,NULL,20);--插入员工信息
INSERT INTO emp VALUES (7499,'陈二','推销员',7698,'1981-02-20',1600,300,30);
INSERT INTO emp VALUES (7521,'张三','推销员',7698,'1981-02-22',1250,500,30);
INSERT INTO emp VALUES (7566,'李四','经理',7839,'1981-04-02',2975,NULL,20);
INSERT INTO emp VALUES (7654,'王五','推销员',7698,'1981-09-28',1250,1400,30);
INSERT INTO emp VALUES (7698,'赵六','经理',7839,'1981-05-01',2850,NULL,30);
INSERT INTO emp VALUES (7782,'孙七','经理',7839,'1981-06-09',2450,NULL,10);
INSERT INTO emp VALUES (7788,'周八','分析师',7566,'1987-06-13',3000,NULL,20);
INSERT INTO emp VALUES (7839,'吴九','总裁',NULL,'1981-11-17',5000,NULL,10);
INSERT INTO emp VALUES (7844,'郑十','推销员',7698,'1981-09-08',1500,0,30);
INSERT INTO emp VALUES (7876,'郭十一','职员',7788,'1987-06-13',1100,NULL,20);
INSERT INTO emp VALUES (7900,'钱多多','职员',7698,'1981-12-03',950,NULL,30);
INSERT INTO emp VALUES (7902,'大锦鲤','分析师',7566,'1981-12-03',3000,NULL,20);
INSERT INTO emp VALUES (7934,'木有钱','职员',7782,'1983-01-23',1300,NULL,10);

SELECT * FROM emp INNER JOIN dept ON emp.deptno=dept.deptno