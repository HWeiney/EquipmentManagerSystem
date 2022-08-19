CREATE VIEW list_VIEW AS 
((SELECT Eno,Ename,Ecount FROM equipment WHERE Eno NOT IN (select Eno FROM es_view)
UNION 
SELECT Eno,Ename,SUM(Ecount) FROM es_view GROUP BY Eno)
ORDER BY Eno)