package pres.hw.ems.domain;

public class RepairEm {
	private String Repairno;
	private String Repairdate;
	private int Eno;
	private String Ename;
	private int Rcount;
	private String Rreason;
	private String username;
	
	public RepairEm(int eno, String ename, int rcount) {
		super();
		Eno = eno;
		Ename = ename;
		Rcount = rcount;
	}
	
	public RepairEm(String repairno, String ename, int rcount, String rreason, String username) {
		super();
		Repairno = repairno;
		Ename = ename;
		Rcount = rcount;
		Rreason = rreason;
		this.username = username;
	}

	public RepairEm(String repairno,String repairdate, int eno, String ename, int rcount, String rreason, String username) {
		super();
		Repairno = repairno;
		Repairdate = repairdate;
		Eno = eno;
		Ename = ename;
		Rcount = rcount;
		Rreason = rreason;
		this.username = username;
	}

	public String getRepairdate() {
		return Repairdate;
	}

	public void setRepairdate(String repairdate) {
		Repairdate = repairdate;
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

	public int getRcount() {
		return Rcount;
	}

	public void setRcount(int rcount) {
		Rcount = rcount;
	}

	public String getRreason() {
		return Rreason;
	}

	public void setRreason(String rreason) {
		Rreason = rreason;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getRepairno() {
		return Repairno;
	}

	public void setRepairno(String repairno) {
		Repairno = repairno;
	}
	
	
	
}
