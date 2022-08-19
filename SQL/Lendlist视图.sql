CREATE VIEW Lendlist AS 
((SELECT Eno,Ename,Ecount Lcount FROM equipment WHERE Eno NOT IN (select Eno FROM el_view)
UNION 
SELECT Eno,Ename,SUM(Lcount) FROM el_viewlendlist GROUP BY Eno)
ORDER BY Eno)