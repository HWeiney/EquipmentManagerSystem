CREATE VIEW EL_VIEW AS 
SELECT
Lendno,Lenddate,e_lend.Eno,equipment.Ename,Lcount,Lendname,Lendphone,Returndate,e_lend.username 
FROM e_lend,equipment WHERE e_lend.Eno=equipment.Eno