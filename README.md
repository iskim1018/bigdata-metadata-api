# Metadata(RDF/XML형식) 교환을 위한 API 예제

## 개요 
- RDF 문서 기반의 메타데이터 등록 / 수정 / 삭제 API 동작 예제 
- Template Engine([thymeleaf](https://www.thymeleaf.org/))를 RDF(XML형식) 생성 예제


## 사전 설치 필요 패키지
- Java 8
- Mysql server 5.7
- maven

## 테스트 절차
- application.yml 설정
  - <code>src/main/resources/application-sample.yml</code>을 복사하여 동일한 디렉토리에 application.yml 생성
  - application.yml의 datasource 부분의 url, username, password를 테스트할 db에 맞게 정보 수정
- 테스트 db 생성
  - 최초 Build 수행 시 <code>src/main/resource/db/migration</code> 이하의 [flyway](https://flywaydb.org/)에 의해 sql을 순차적으로 수행함
  - Empty DB를 신규 생성하거나
  - 기존 DB를 사용할 경우는 동일 테이블 존재 등 확인 필요함
    - flyway 오류 발생 시 <code>flyway_schema_history</code> 테이블의 success 컬럼이 0인 값을 삭제 후 Build 재수행
- maven을 이용한 빌드
  ```
   $ mvn install
  ```
- API 서버 구동
  - SpringBoot 2.2.1 이용
    ```
     $ mvn spring-boot:run
    ```
  - localhost의 TCP port 8080을 사용(기존 8080 port를 사용하고 있는 daemon 확인 필요)

- API 요청 테스트(Dataset / DataService 표준규격 준수)
  - 테스트를 위한 <code>initial.rdf, upsert.rdf, delete.rdf</code>는 <code>src/test/resources/api</code>에 있음
  - 아래 curl 명령들을 순차적으로 수행하여 일련의 절차를 확인할 수 있음
  - 메타데이터 초기 등록 (기 등록된 메타데이터 삭제 후 신규 등록 처리 수행)
    ```
    $ curl -X POST http://localhost:8080/api/metadata/init \
        -H 'Authorization: Bearer test-token' -H 'Content-Type: application/xml' \
        -d @initial.rdf
    ```
  - 메타데이터 추가 및 수정 (identifier 속성값 기준으로 신규 등록 또는 수)
    ```
    $ curl -X POST http://localhost:8080/api/metadata/upsert \
      -H 'Authorization: Bearer test-token' -H 'Content-Type: application/xml' \
      -d @upsert.rdf
    ```
  - 메타데이터 삭제 (identifier 속성값 기준으로 삭제 수행)
    ```
      $ curl -X POST http://localhost:8080/api/metadata/delete \
        -H 'Authorization: Bearer test-token' -H 'Content-Type: application/xml' \
        -d @delete.rdf
    ```  
  - 메타데이터 조회 (RDF 기반 메타데이터 조회)
    ```
      $ curl -X GET http://localhost:8080/dcat/metadata \
        -H 'Authorization: Bearer test-token'
    ```
