# hello-spring2

<h3> H2 DATABASE 설치 </h3>

- 권한 부여: chmod 755 h2.sh 
- 실행: ./h2.sh 
    
- 데이터베이스 파일 생성 방법

    <code>
    JDBC URL: jdbc:h2:~/test (최초 한번), 로컬 경로에 test.mv.db 파일 생성 되었는지 확인 

    이후부터는 jdbc:h2:tcp://localhost/~/test  
    </code>



