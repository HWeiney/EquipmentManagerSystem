CREATE VIEW Storelist AS
SELECT list_view.Eno,list_view.Ename,(Ecount-Lcount-Rcount) StoreCount,Lcount,Rcount 
FROM list_view,lendlist,repairlist WHERE list_view.Eno = lendlist.Eno AND lendlist.Eno = repairlist.Eno