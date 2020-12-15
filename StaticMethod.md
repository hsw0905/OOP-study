# 정적 메서드

### 생각의 시작

- 이런 단순한 계산도 개체를 만들어서 써야 하는가?

```java
int Math.abs(int); // 절대값 구하기
StringUtils.countSpaces(string str); // 공백 문자 개수 구하기
```

- 개체 단위가 아닌 클래스 단위에서 무언가 하고 싶을 때? ( 예 : 개체 카운트)
- 모든 것이 개체 속에 있는 불편함

### → static이 이런 불편함을 해결해 줄 수 있음

```java
// Math.java
public class Math {
	// 아래 static 맴버 함수듣은 소유주가 개체가 아닌 클래스다
	public static int abs(int n) {
		return n < 0 ? -n : n;
	}
	public static int min(int a, int b) {
		return a < b ? a : b;
	}
	public static int max(int a, int b) {
		return a > b ? a : b;
	}
}
...

// Main
int absValue = Math.abs(-2) // 2
int minValue = Math.min(100, -200) // -200
```

- 클래스에서 바로 맴버 함수 사용

### 소유주가 클래스라면 생성자는?

```java
// Main
Math math = new Math(); // 물론 생성 가능하다
int absValue = math.abs(-2);
```

### 클래스에 속한 메서드인데 이게 왜 되는 걸까?

- 개체 수에 상관없이 **클래스는 단 하나**만 존재하기 때문
- 즉 어떤 개체던지 호출해야 하는 메서드를 특정할 수 있음

### 그러면 개체 만들때마다 이득이 없는데 굳이 new를 써서 메모리를 할당해야 하는건가?

- 그러면 위 상황에서 차라리 개체를 못 만들게 하는게 낫지 않을까? 가능!

  ```java
  // Math.java
  public class Math {
  	// 생성자를 private로 만듬
  	private Math () {
  	}
  	public static int abs(int n) {
  		return n < 0 ? -n : n;
  	...
  }
  ...

  // Main
  Math math = new Math(); // private에 접근하므로 컴파일 오류
  ```

  ### 클래스 단위에서 뭔가 하고 싶을 때?

  - 마찬가지로 static을 붙여서 변수를 쓰면 된다

  ```java
  public class ColaCan {
  	private int remainingMl;
  	private static int NumCreated; // 정적 맴버 변수

  	public ColaCan(int initialMl) {
  		this.remainingML = initialMl;
  		++NumCreated; // 개체가 생성될때마다 증가
  	}

  	public void pour(int ml) {
  		// X ml만큼 따라 부어 비우는 코드
  	}
  	...
  	// 정적 맴버 함수에 접근(정적 메서드)
  	public static void printStats() {
  		System.out.println("# Cola Produced: " + numCreated);
  	}
  }

  //Main
  ColaCan.printStats();
  ColaCan cola = new ColaCan(500);
  cola.printStats(); // 개체 통해서도 정적 메서드 접근 가능
  ```

  ### 그렇다면, 위 상황에서 printStats()에서 remainingMl 변수도 접근 가능할까? 불가능!

  ```java
  public class ColaCan {
  	private int remainingMl;
  	private static int NumCreated; // 정적 맴버 변수
  	...
  		// 정적 맴버 함수에 접근(정적 메서드)
  	public static void printStats() {
  		System.out.println("# Cola Produced: " + numCreated);
  		System.out.println("# ml left: " + this.remainingMl); // 컴파일 오류
  	}

  }
  ```

  - **클래스에 속한 메서드가 개체에 속한 맴버 (함수/변수)에 접근 불가**

  ### 정리

  - static 맴버 변수 및 맴버 함수는 클래스에 속함 (딱 하나만 존재)
  - static이 아닌 것은 개체에 속함 (따라서 개체 수만큼 존재)
  - 비정적 → 정적 : 접근 가능
  - 정적 → 비정적 : 접근 불가능
