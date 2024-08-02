package model.domain.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
//@ToString(exclude = "emps")

@Table(name = "dept")
@Entity
public class Dept {
	
	@Id
	@Column(name = "deptno")
	private long deptno;
	
	@NonNull
	private String dname;
	
	@NonNull
	private String loc;
	
	@OneToMany(mappedBy = "deptno")
	private List<Emp> emps = new ArrayList<>();
	
    @Override
    public String toString() {
        return "Dept{" +
                "deptno=" + deptno +
                ", dname='" + dname + '\'' +
                ", loc='" + loc + '\'' +
                ", emps='" + emps + '\'' +
                '}';
    }

}
