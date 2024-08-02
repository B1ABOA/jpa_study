package model.domain.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
//@ToString

@Table(name = "emp")
@Entity
public class Emp {
	@Id
	@Column(name = "empno")
	private long empno;
	
	@NonNull
	private String ename;
	
	@NonNull
	private String job;
	
	@NonNull
	private Integer mgr;

	@NonNull
	private Date hiredate;
	
	@NonNull
	private int sal;
	
	private Integer comm;
	
	@OneToOne
	@JoinColumn(name="deptno")
	private Dept deptno;
	
	@Override
    public String toString() {
        return "Emp{" +
                "empno=" + empno +
                ", ename='" + ename + '\'' +
                ", job='" + job + '\'' +
                ", mgr=" + mgr +
                ", hiredate=" + hiredate +
                ", sal=" + sal +
                (comm != null ? ", comm=" + comm : "") +
                ", deptno=" + deptno +
                '}';
    }
	
	
	
}
