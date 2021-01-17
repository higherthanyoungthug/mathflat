# mathflat_test 강용호

### 환경
* JDK 11
* SpringBoot 2.4
* MySql 8.0
* JPA, QueryDsl

### API 목록
* https://www.getpostman.com/collections/b321cbc81d119387fa3f
* Postman -> Import -> 위 링크

### 실행 방법
1. MySql8.0 설치(3306 default port)
  - 기존 DB를 사용하고자 한다면 application.yml에서 수정
2. MySql 사용자 추가 user: dev, pw: qwer1234
  - 다른 사용자를 이용하고 한다면 application.yml에서 수정
3. ORM이므로 별도의 DDL없이 어플리케이션 실행 가능
4. 상단 API 목록을 이용해 API 요청, 응답 확인
