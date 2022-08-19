CREATE VIEW ER_VIEW AS
SELECT 
Repairno,Repairdate,e_repair.Eno,equipment.Ename,Rcount,Rreason,e_repair.username 
FROM e_repair,equipment WHERE e_repair.Eno = equipment.Eno