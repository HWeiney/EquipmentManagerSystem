CREATE VIEW ES_VIEW AS 
SELECT e_store.Listno,e_store.Eno,equipment.Ename,Estatus,e_store.username,Buydate,e_store.Ecount,BuyPrice,SaleName,SalePhone
	FROM equipment,e_store WHERE e_store.Eno=equipment.Eno ORDER BY e_store.Listno 