package pres.hw.ems.domain;

public class LendEm {
	private String Lendno;
	private String Lenddate;
	private int Eno;
	private String Ename;
	private int Lcount;
	private String Lendname;
	private String Lendphone;
	private String Returndate;
	private String username;
	
	public LendEm() {
		// TODO Auto-generated constructor stub
	}
	
	public LendEm(int eno, String ename, int lcount) {
		super();
		Eno = eno;
		Ename = ename;
		Lcount = lcount;
	}

	public LendEm(String lendno,String lenddate, int eno, String ename, int lcount, String lendname, String lendphone,
			String returndate, String username) {
		super();
		Lendno = lendno;
		Lenddate = lenddate;
		Eno = eno;
		Ename = ename;
		Lcount = lcount;
		Lendname = lendname;
		Lendphone = lendphone;
		Returndate = returndate;
		this.username = username;
	}
	
	public LendEm(String lendno, String ename, int lcount, String lendname, String lendphone,
			String returndate, String username) {
		super();
		Lendno = lendno;
		Ename = ename;
		Lcount = lcount;
		Lendname = lendname;
		Lendphone = lendphone;
		Returndate = returndate;
		this.username = username;
	}

	public String getLenddate() {
		return Lenddate;
	}

	public void setLenddate(String lenddate) {
		Lenddate = lenddate;
	}

	public int getEno() {
		return Eno;
	}

	public void setEno(int eno) {
		Eno = eno;
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

	public String getLendname() {
		return Lendname;
	}

	public void setLendname(String lendname) {
		Lendname = lendname;
	}

	public String getLendphone() {
		return Lendphone;
	}

	public void setLendphone(String lendphone) {
		Lendphone = lendphone;
	}

	public String getReturndate() {
		return Returndate;
	}

	public void setReturndate(String returndate) {
		Returndate = returndate;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLendno() {
		return Lendno;
	}

	public void setLendno(String lendno) {
		Lendno = lendno;
	}

	
	
}
