# jpa 공부해보자!!


## 👩‍💻 팀원 소개



|                                         노솔리(🩸AB)                                          |                                      박웅빈(🩸A)                                      |                                        이주원(🩸B)                                        |                                         홍민영(🩸O)                                          |
| :-------------------------------------------------------------------------------------: | :------------------------------------------------------------------------------: | :----------------------------------------------------------------------------------: | :-------------------------------------------------------------------------------------: |
| <img  width="100px" src="https://avatars.githubusercontent.com/soljjang777" /> | <img width="100px" src="https://avatars.githubusercontent.com/Ungbbi" /> | <img width="100px" src="https://avatars.githubusercontent.com/2oo1s"/> |     <img width="100px" src="https://avatars.githubusercontent.com/u/65701100?v=4"/>     |
|                       [@soljjang777](https://github.com/soljjang777)                        |           [@Ungbbi](https://github.com/Ungbbi)           |                      [@2oo1s](https://github.com/2oo1s)                      |                    [@HongMinYeong](https://github.com/HongMinYeong)                     |

# Entity

## Emp Table

| Field | Type | Null | Key | Default | Extra |
| --- | --- | --- | --- | --- | --- |
| EMPNO | int | NO | PRIMARY KEY |  |  |
| COMM | int | YES |  |  |  |
| ENAME | String | YES |  |  |  |
| JOB | String | YES |  |  |  |
| HIERDATE | String | YES |  |  |  |
| SAL | int | YES |  |  |  |
| MGR | int | YES |  |  |  |
| DEPTNO | int | YES | FOREIGN KEY |  |  |

<br>

## Dept Table

| Field | Type | Null | Key | Default | Extra |
| --- | --- | --- | --- | --- | --- |
| DEPTNO | int | NO | PRIMARY KEY |  |  |
| DNAME | String | YES |  |  |  |
| LOC | String | YES |  |  |  |

<br>

## 예제1

### 🎁 `Dept` 엔티티는 다음과 같이 정의되어 있다고 가정합니다:

```java
@Entity
public class Dept {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // Getters and Setters
}
```

### 🎁 아래는 Dept 엔티티 클래스를 사용하여 `Dept` 엔티티의 모든 레코드를 가져오는 JPQL 쿼리의 예입니다:

```java
List<Dept> datas = em.createQuery("select d from Dept d", Dept.class).getResultList();
```

<br>

### 🎱 문제입니다!:

1. **JPQL 쿼리에서 `Dept.class`를 사용하여 `Dept` 타입으로 결과를 가져오는 이유는 무엇인가요?**
2. **위의 JPQL 쿼리를 사용하여 `Dept` 엔티티의 `name` 필드가 "Sales"인 모든 `Dept` 객체를 조회하려면 쿼리를 어떻게 수정해야 하나요?**
<br>

### 답안

1. Dept.class를 사용하는 이유| Dept.class를 사용하는 이유는 createQuery 메서드의 두 번째 인자로 결과 타입을 지정하여 반환된 결과가 Dept 타입으로 캐스팅되도록 하기 위함입니다 -> 이는 타입 안전성을 보장합니다.
2. name 필드가 "Sales"인 Dept 객체를 조회하는 JPQL 쿼리 수정:

```java
List<Dept> datas = em.createQuery("select d from Dept d where d.name = :name", Dept.class)
                    .setParameter("name", "Sales")
                    .getResultList();
```

## 예제2

### 🎁 아래는 Emp 엔티티 클래스와 Emp 테이블에 있는 데이터 입니다.
```java
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
	private int mgr;

	@NonNull
	private Date hiredate;
	
	@NonNull
	private int sal;
	
	private int comm;
	
	@OneToOne
	@JoinColumn(name="deptno")
	private Dept deptno;
}

```
<img src="https://github.com/user-attachments/assets/d7c78897-ded1-4d39-91e2-80a504c106bb">
<br>

### 🎱 문제입니다!:
<img src="https://github.com/user-attachments/assets/e6831267-e428-435f-966b-33f7043ccf1f" width="600"> <br />
  현재 Dept객체로 Select시 위와 같은 에러가 발생하는데 왜 나는것일까요??
<br>

### 답안
> emp 테이블의 mgr와 sal 컬럼에 null 값이 포함된 것을 확인할 수 있습니다. 현재 Emp 엔티티를 생성할 때 mgr과 sal의 데이터 타입을 int로 설정하였는데, int 타입은 null 값을 허용하지 않습니다.<br>
> 따라서, 만약 데이터베이스에서 mgr이나 sal이 null인 경우, 엔티티에서 이를 처리할 수 없게 됩니다. <br>
> 이를 해결하기 위해 mgr과 sal의 데이터 타입을 Integer로 변경해야 합니다. Integer는 null 값을 허용하는 래퍼 클래스이므로, <br>
> 데이터베이스에서 null 값을 포함할 수 있는 컬럼을 적절히 처리할 수 있습니다. 이 변경을 통해 null 값이 있을 경우에도 Emp 엔티티가 정상적으로 동작할 수 있게 됩니다. <br>


<br />
<br /> 

# 💥 Issue
- Import 문은 제외하였습니다.
### Dept.java
```java
package model.domain.entity;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

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

}
```
### Emp.java
```java
package model.domain.entity;

@AllArgsConstructor
@NoArgsConstructor
@RequiredArgsConstructor
@Getter
@Setter
@ToString

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
	private int mgr;

	@NonNull
	private Date hiredate;
	
	@NonNull
	private int sal;
	
	private int comm;
	
	@OneToOne
	@JoinColumn(name="deptno")
	private Dept deptno;
}

```
### RunningTest.java
```
public class RunningTest {
	
	@Test
	public void stpe01Test() {
		EntityManager em = DBUtil.getEntityManager();
		Emp emp = em.find(Emp.class, 7782L);
		System.out.println("사원 아이디가 7782 사람 : " + emp.getEname());
		System.out.println("사원 아이디가 7782 사람 : " + emp.getEname() + " / 부서명 :  " + emp.getDeptno().getDeptno());
		System.out.println("사원 아이디가 7782 사람 : " + emp.getDeptno());

		
		Dept dept = em.find(Dept.class, 10L);
		System.out.println("부서 아이디가 10인 부사 : " + dept.getDname());
		
		List<Emp> emps = dept.getEmps();
		emps.forEach(System.out::println);
		
		em = null;
		
	}

}
```
### 🎱 문제입니다!:
 RunningTest.java 에서 System.out.println 으로 Deptno를 출력하고자 하면 Stackoverflow error가 발생합니다. 왜일까요?

### 답안
> 순환 참조 </br>
> 원인 : ToString으로 인해 발생하게 되는 에러입니다.</br>
> 1. Emp 객체와 Dept 객체가 있습니다. </br>
> 2. Emp 객체는 Dept 객체를 참조합니다 (emp.getDeptno()).</br>
> 3. Dept 객체도 여러 Emp 객체들을 리스트로 참조합니다 (dept.getEmps()).</br>
>
> 즉, Emp 객체가 Dept 객체를 포함하고, Dept 객체가 다시 여러 Emp 객체들을 포함하는 구조입니다.</br>
> emp.toString()이 호출되면 Dept 객체의 toString()이 호출되고, 이 Dept 객체의 toString()은 다시 그 안에 포함된 여러 Emp 객체들의 toString()을 호출하게 됩니다.</br>

> 쉽게 표현하자면 </br>
> emp.toString() -> dept.toString() -> emps.toString() -> 다시 emp.toString()으로 무한히 순환하면서 호출됩니다.</br>
> 이로 인해 StackOverflowError와 같은 에러가 발생하게 되는 것입니다.</br>

### Solution
> 두 클래스파일 중 한 곳에 ToString(exclude = )로 순환참조가 발생하게되는 멤버변수를 제외시켜줍시다.
> 예를 들자면 Dept 클래스에서 해결해주고자 한다면 
> @ToString(exclude = "emps") </br>
> 위와 같이 순환 참조가 발생하게 되는 emps를 제외시켜주면 됩니다.


