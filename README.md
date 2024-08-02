# jpa_study


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
## Dept Table
<img src="https://github.com/user-attachments/assets/d7c78897-ded1-4d39-91e2-80a504c106bb">

<br>

### 🎱 문제입니다!:
<img src="https://github.com/user-attachments/assets/e6831267-e428-435f-966b-33f7043ccf1f" width="600"> <br />
1. 현재 Dept객체로 Select시 위와 같은 에러가 발생하는데 왜 나는것일까요??
