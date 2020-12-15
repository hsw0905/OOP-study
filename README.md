# OOP(내용 정리)

---

### 참고

- [정적 메서드](./StaticMethod.md)

### 객체지향프로그래밍

- 프로그램을 구성하려는 기본 요소를 개체로 보려는 노력
- OOP에서 프로그래밍이란 상호 작용하는 개체들의 집합

### OOP 7대 개념

- 캡슐화(encapsulation)
- 상속(inheritance)
- 다형성(polymorphism)
- (데이터)추상화(abstraction)
- 연관(association)
- 컴포지션(composition)
- 집합(aggregation)

### 캡슐화

- 데이터와 메소드를 하나로 묶음(클래스)
- 정보 숨기기

### 상속

- 이미 존재하는 개체를 기반으로 확장된 개체를 만듬(진화의 개념)
- 용도 : 코드 중복을 막음 (공통된 데이터와 메소드를 부모 개체로 상속)

### 다형성

- 같은 지시를 내렸는데, 서로 다른 종류의 개체가 동작을 다르게 하는 것
- 동일한 함수 호출 → 실제 그 안 코드는 다른 일
- 절차적 코딩시엔 if문을 사용해야 했음
- 어떤 함수 구현이 실행될지는 실행 도중에 결정됨 (late binding)
- 일반적인 함수 호출은 컴파일 도중 결정됨 (early binding)
- 다형성 혜택을 받으려면 상속 관계가 필요하며 부모 개체에서 함수 시그니처 선언 → 자식 개체는 그 함수를 오버라이딩 함
- 실용적인 용도 : 부모 type을 저장하는 배열에 자식 개체 저장 → for 문 하나로 모든 개체 순회하며 함수 호출 → 개체 각자 다른 일을 하게 됨

### (데이터)추상화

- 개체 속 실제 데이터나 함수 구현 방법에 종속되지 않겠다는 뜻
- 개체 사용시 그 안에 정확히 어떤 데이터가 있는지 알 필요 없음 → 개체 안 데이터에 직접 접근 불가
- 대신 그 개체의 함수를 통해 접근

### 연관

- 어떤 개체가 제공하는 기능을 다른 개체가 이용하는 관계
- 상속 : 자식 개체가 부모 개체의 모든 것을 내포하는 관계
- 연관 : 한 개체가 다른 개체를 참조하는 관계
- 세부적으로 집합과 컴포지션으로 나누기도 함

### 컴포지션

- 여러개의 부품(개체)를 조립하여 새 개체를 만드는 방법
- 집합과의 차이 : 조립 부품이 소멸할 때 부품도 같이 소멸

### 집합

- 역시 여러 개체를 모아 새로운 개체를 만들지만 별도로도 존재 가능
- 각 개체들이 따로따로 살아남음

### private 메서드

- 클래스 안에서만 호출 가능
- 사용 이유 : 클래스 내부에서 코드 중복을 막기 위함

### 패키지 접근 제어자

- 접근 제어자를 안 붙일 경우 기본(default, 내가 아무것도 안 적어 주면) 혹은 패키지(package) 접근 권한 → 같은 패키지 안에 있는 클래스끼리 서로 접근 가능
- 같은 패키지 : public 처럼 작동
- 다른 패키지 : private 처럼 작동

```java
// 패키지 접근 제어자의 예시

public class Dog {
	float happiness; // 접근제어자 X (default)
	...
}

public class Human {
	...
	public void pat(Dog dog) {
		dog.happiness += 0.5f; // OK
	}
	...
}

...

Human adam = new Human();
Dog poodle = new Dog(); // 행복지수: 0

adam.pat(poodle);  // poodle의 행복지수: 0.5
```

### 패키지 접근 제어자 용도

- public 대신 패키지 접근 제어자를 사용할 수 있을때 (어떤 특정 패키지 안에서만 사용되는 클래스일 경우)
- public이 아닌 내포 클래스를 최상위 클래스로 바꿀 때
- 내포 클래스는 가독성 문제가 생길 수 있어 별도 클래스로 분리시키는 것
- 이 때 접근 권한을 패키지 내로 제한하는 것이 public 보다 나음

### 함수를 통한 데이터 접근의 객관적인 장점

- 맴버 변수를 저장하지 않고 getter를 통해 계산 가능 예) 질량과 중력 ← 매개변수로부터 무게 계산
- setter에서 추가적인 로직 구현 가능 예)음수 값으로 나이 인자가 들어오면 무시
- 상속을 통한 다형성 구현 가능

Best prectice

- 맴버 변수는 대부분의 경우 private
- 개체는 살아있는 동안 언제나 유효한 상태여야 이상적이다(그래야 실수를 막을 수 있다)
- 생성자를 사용하여 생성될 때부터 유효하도록 강제(매개변수가 바뀔 경우에도 마찬가지)
- 사용자가 알 필요 없는 정보는 안 보여주는게 맞다
- getter는 보통 자유롭게 추가할 수 있지만 경우에 따라 문제가 될 수 있다

```java
public class Family {
	private Human[] parents;
	private Cat pet;
	...
	public Cat getPet() {
		return pet;
	}
}

Family sheldons = new Family();
Family pennys = new Family();

Cat notSheldonsCat = pennys.getPet();
sheldons.setPet(notSheldonsCat); // 남의 집 고양이가 추가된 상황
```

- setter는 고민 후 추가 - 이상적으로 개체의 상태를 수정하는 방법은 그 개체의 사용자가 어떤 동작을 지시하여 그 결과로 인해 개체 스스로 상태를 변경하는 방식이다

```java
public class Classroom {
	private int[] scores;
	private float mean;
	...
	public boolean setScore(int index, int score) {
		...
		scores[index] = score;
		updateMean();  // <-- 스스로 변경

		return true;
	}
	private void updateMean() {
		this.mean = 계산 결과;
	}
}

//main
classroom.setScore(1, 50);
classroom.setScore(12, 90);
```

- setter는 데이터를 직접 바꾸게 되므로 가능한 피하는게 좋다(public 맴버 변수와 다를게 없다)
- 유연성 높은 설계가 최고가 아니다(코드 쪼개기) → 재사용성은 높아질 수 있지만 성능과 가독성이 떨어질 수 있다 → 코드의 유연성은 양날의 검 → 필요에 따라 유연하게 조정할 것

### 기본 사항

- 읽기 명확한 코드 만들기
- 실수를 저지르기 어려운 코드 만들기
- 문제를 해결하는 코드 만들기
- 문제가 생기면 디버깅하기
