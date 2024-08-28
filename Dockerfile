# 기본 이미지 설정 (OpenJDK 17 사용)
FROM openjdk:17-jdk-slim

# 컨테이너 내 작업 디렉토리 설정
WORKDIR /app

# Gradle 빌드 파일 복사
COPY gradlew .
COPY gradle/ gradle/
COPY build.gradle .
COPY settings.gradle .

# 프로젝트 파일 복사
COPY src/ src/

# application.properties 파일 복사
COPY application.properties src/main/resources/application.properties

# Google Cloud 서비스 계정 키 파일 복사
COPY gcp-key.json /app/gcp-key.json

# WebConfig.java 파일의 http://localhost:3000을 https://www.aikiosk.shop으로 변경
RUN sed -i 's|http://localhost:3000|https://www.aikiosk.shop|g' src/main/java/ac/su/kiosk/config/WebConfig.java

# Gradle을 사용하여 프로젝트 빌드
RUN ./gradlew build --no-daemon

# 환경 변수 설정 (Google Cloud Credentials)
ENV GOOGLE_APPLICATION_CREDENTIALS="/app/gcp-key.json"

# 애플리케이션이 사용할 포트 노출
EXPOSE 8080

# 애플리케이션 실행 명령어
CMD ["java", "-jar", "build/libs/kiosk-0.0.1-SNAPSHOT.jar"]