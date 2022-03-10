# 3. 스프링 핵심 원리 이해2 - 객체 지향 원리 적용

## 새로운 할인 정책 개발

* 새로운 정률 할인 정책 구현 - RateDiscountPolicy

## 새로운 할인 정책 적용과 문제점

### (새로운 할인 정책을 적용시 발견된) 문제점

* OrderServiceImpl - DiscountPolicy 와 FixDiscountPolicy 모두에 의존
  * DIP 위반 - 구체에 의존하지 말고, 인터페이스만 봐야한다는 법칙
* OrderServiceImpl에 FixDiscountPolicy -> DiscountPolicy로 변경
  * OCP 위반 - 확장된 기능을 적용하기 위해 client를 변경 (i.e 확장에
    열려있지 않음)

### 해결

* DIP 위반 -> 인터페이스에만 의존하도록 코드 수정.
  * `private final DiscountPolicy discountPolicy = new
    FixDiscountPolicy()` -> `private DiscountPolicy discountPolicy` 수정
  * **NPE(Null Point Exception) 발생** -> 구체화 클래스를 넣어줄 다른
    존재가 필요.

## 관심사의 분리

각 서비스(MemberServiceImpl, OrderServiceImpl)들은 자신에게 필요한 객체들(MemberRepository, DiscountPolicy)이 있을 때, 그 객체의 인터페이스를
아는 것 뿐만 아니라 구현체까지 선택하고 있다. \
각 서비스들은 자신에 필요한 객체들(즉, 의존관계 객체)들의 인터페이스만
알도록하고, 구현체 선택 및 생성은 다른 객체(AppConfig)에게 맡기자.

### AppConfig 등장

* 애플리케이션의 전체 동작 방식을 구성(Config)하기 위해, **구현 객체를
  생성**하고, **연결**하는 책임을 가지는 별도의 설정 클래스를 만들자.

### AppConfig 역할

* 애플리케이션의 실제 동작에 필요한 **구현 객체를 생성**한다.
* 생성한 객체 인스턴스의 참조(레퍼런스)를 **생성자를 통해서
  주입(연결)**한다.
  * 생성자 주입: Constructor injection

-> `객체를 생성하고 연결하는 역할`과 `실행하는 역할이 명확히 분리`:
관심사의 분리 \
-> `MemberServiceImpl`에서 보면 **자신의 의존 관계(ex.
memberRepository)**를 외부에서 주입해주는 것처럼 보여서, \
이를 **DI(Dependency Injection, 의존관계 or 의존성 주입)**이라고 한다.

### 정리

* AppConfig 역할 - 객체를 생성하고 주입
  * 생성자 주입
* DI (Dependency Injection)
* DIP, OCP 위반 -> DIP, OCP 준수

## AppConfig 리팩토링
## 새로운 구조와 할인 정책 적용
## 전체 흐름 정리
## 좋은 객체 지향 설계의 5가지 원칙의 적용
## IoC, DI, 그리고 컨테이너
## 스프링으로 전환하기

