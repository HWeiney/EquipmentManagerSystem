CREATE VIEW Repairlist AS 
((SELECT Eno,Ename,Ecount Rcount FROM equipment WHERE Eno NOT IN (select Eno FROM er_view)
UNION 
SELECT Eno,Ename,SUM(Rcount) FROM er_view GROUP BY Eno)
ORDER BY Eno)equipmentmanagersystem