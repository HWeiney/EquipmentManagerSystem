package pres.hw.ems.domain;

public class BuyEm {
	private String Listno;
	private int Eno;
	private String Ename;
	private String Estatus;
	private String username;
	private String Buydate;
	private int Ecount;
	private int BuyPrice;
	private String SaleName;
	private String SalePhone;
	private int Lcount;
	private int Rcount;
	
	public BuyEm() {
		// TODO Auto-generated constructor stub
	}
	
	public BuyEm(int eno, String ename, int ecount) {
		super();
		Eno = eno;
		Ename = ename;
		Ecount = ecount;
	}

	public BuyEm(int eno, String ename, int ecount, int lcount, int rcount) {
		super();
		Eno = eno;
		Ename = ename;
		Ecount = ecount;
		setLcount(lcount);
		setRcount(rcount);
	}

	public BuyEm(String listno, String ename, String username, int ecount, int buyPrice, String saleName,
			String salePhone) {
		super();
		Listno = listno;
		Ename = ename;
		this.username = username;
		Ecount = ecount;
		BuyPrice = buyPrice;
		SaleName = saleName;
		SalePhone = salePhone;
	}

	
	
	public BuyEm(String listno, int eno, String ename, String estatus, String username, String buydate, int ecount,
			int buyPrice, String saleName, String salePhone) {
		super();
		Listno = listno;
		Eno = eno;
		Ename = ename;
		Estatus = estatus;
		this.username = username;
		Buydate = buydate;
		Ecount = ecount;
		BuyPrice = buyPrice;
		SaleName = saleName;
		SalePhone = salePhone;
	}

	public String getListno() {
		return Listno;
	}

	public void setListno(String listno) {
		Listno = listno;
	}

	public int getEno() {
		return Eno;
	}

	public void setEno(int eno) {
		Eno = eno;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getEcount() {
		return Ecount;
	}

	public void setEcount(int ecount) {
		Ecount = ecount;
	}

	public String getBuydate() {
		return Buydate;
	}

	public void setBuydate(String buydate) {
		Buydate = buydate;
	}

	public int getBuyPrice() {
		return BuyPrice;
	}

	public void setBuyPrice(int buyPrice) {
		BuyPrice = buyPrice;
	}

	public String getSaleName() {
		return SaleName;
	}

	public void setSaleName(String saleName) {
		SaleName = saleName;
	}

	public String getSalePhone() {
		return SalePhone;
	}

	public void setSalePhone(String salePhone) {
		SalePhone = salePhone;
	}



	public String getEname() {
		return Ename;
	}



	public void setEname(String ename) {
		Ename = ename;
	}

	public int getLcount() {
		return Lcount;
	}

	public void setLcount(int lcount) {
		Lcount = lcount;
	}

	public int getRcount() {
		return Rcount;
	}

	public void setRcount(int rcount) {
		Rcount = rcount;
	}

	public String getEstatus() {
		return Estatus;
	}

	public void setEstatus(String estatus) {
		Estatus = estatus;
	}
	
	
}
