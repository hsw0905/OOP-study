## 디자인 패턴

#### <참고> 디자인 패턴은 그저 가이드라인일 뿐, 실무에 따라 여러 변형을 사용하기도 한다

### 싱글턴 패턴

- 어떤 클래스에서 만들 수 있는 인스턴스 수를 하나로 제한하는 패턴
- 다음 조건을 만족하는 경우 적합
- 1. 프로그램 실행 중에 최대 하나만 있어야 함
- (예: 프로그램 설정, 파일 시스템 등)
- 이 개체에 전역적으로 접근이 가능해야 함

#### 일부 사람들이 Static을 싫어하는 이유

- 1. 전역 변수 같아 보여서
- 2. 개체가 아님
- 이러한 이유로 static 사용에 OO가 아니라고 주장
- 싱글턴은 이런 문제를 해결하면서 OO에서 전역 변수, 함수를 만드는 법

#### 어떻게?

- private 생성자
- static method를 통해서만 개체를 얻어올 수 있다
- 아직 만들어진 개체가 없다?
- -> 개체를 생성 후 static 변수에 저장
- -> static 변수에 저장된 개체 리턴
- 이미 만들어진 개체가 있다?
- -> static 변수에 저장된 개체 리턴

```java
public class Singleton {
    private static Singleton instance; // instance static으로 뺀다

    private Singleton() {
			//private 생성자
    }

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

// main
public class Main {
    public static void main(String[] args) {

        Singleton instance = Singleton.getInstance();
        Singleton instance1 = Singleton.getInstance();
        System.out.println("same? : " + (instance == instance1)); // true

    }

```

#### static과 크게 다르지 않아 보이는데 굳이 싱글턴을 왜 사용할까?

##### Singleton vs static

- 다음의 내용을 static은 할 수 없다
- 1. 다형성을 사용할 수 없다
- 2. 시그내처를 둔 상태로 멀티턴(multiton) 패턴으로 바꿀 수 없다
- (멀티턴이란, getInstance()를 가지고 있는 상태에서, 하나의 인스턴스만 가질수 있는게 아닌, 여러개의 인스턴스를 제한으로 만들 경우임)
- (예: 이 클래스는 인스턴스를 5개만 생성 가능해야해 (멀티턴))
- 3. 개체의 생성 시점을 제어할 수 없다
- (Java의 static은 프로그램 실행시 초기화됨, 단 싱글턴을 사용해도 제어의 어려움이 있다)

##### 어떤 어려움?

- 싱글턴을 사용할때, 여러 개체에서 getInstance() 메소드를 각자 사용하면, 누가 먼저 싱글턴 인스턴스를 만드는건지 순서를 찾기 어려운 경우가 있다
- 실무에서는 초기화의 순서를 보장해줘야 하는 경우를 만날 수 있다
- 이런 경우, 프로그램 시작할때 (Main 클래스 등) 여러 싱글턴 인스턴스를 순서대로 호출한다
- 예) B -> C -> A 순서로 초기화해야 한다

```java
// main
B.getInstance()
C.getInstance()
A.getInstance()
```

#### 파이썬 싱글톤 패턴

- 파이썬은 언어 특성상 접근 제어자(private, public 등)가 없다
- new 매직 매소드 오버라이드하여 객체 생성
- hasattr() : 해당 객체가 명시한 속성을 가지고 있는지 확인
- 이미 instance를 가지고 있다면 인스턴스 리턴

```python
class Singleton(object):
    def __new__(cls):
        if not hasattr(cls, 'instance'):
            cls.instance = super(Singleton, cls).__new__(cls)
        return cls.instance


s = Singleton()
print('오브젝트 생성 : ', s)
# 오브젝트 생성 :  <__main__.Singleton object at 0x10d99a640>
s1 = Singleton()
print('오브젝트 생성 : ', s1)
# 오브젝트 생성 :  <__main__.Singleton object at 0x10d99a640>
```

#### 모듈 싱글톤

- 파이썬의 임포트 방식 : 모든 모듈은 기본적으로 싱글톤이다
- 1. 파이썬 모듈이 임포트 되었는지 확인
- 2. 만약 임포트 되었다면 해당 객체 바로 반환
- 3. 그렇지 않다면 임포트 후 인스턴스화
- 4. 모듈은 임포트와 동시에 초기화된다. 하지만 같은 모듈을 다시 임포트하면 초기화하지 않는다(한 개의 객체만 유지하고 반환하는 싱글톤 방식)
