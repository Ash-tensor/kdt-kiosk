# FROM [base 이미지][:버전], 미지정시 가장 최신버전을 사용
# amazon corretto 17 를 기본 이미지로 설정
FROM amazoncorretto:17

# 컨테이너가 실행 된 후 내부에서 작업되는 기본 디렉터리 위치를 설정 (리눅스 cd 명령어와 같은 역할)
WORKDIR /app

# 데이터소스, 로그파일, 설정파일등을 docker container 외부로 노출 시키고자 할 때 사용
# VOLUME [호스트컴퓨터 디렉토리]

# LABEL maintainer = 관리자 정보 입력

# ENV = 컨테이너 내의 환경변수 설정
# docker 이미지 파일 생성 시 .env 파일도 포함시키는 방법 = COPY .env /app/.env

# RUN = 이미지 생성 시 필요한 명령 실행
# RUN npm install

# AWS CLI 설치
RUN yum update -y && \
    yum install -y unzip && \
    curl "https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip" -o "awscliv2.zip" && \
    unzip awscliv2.zip && \
    ./aws/install && \
    rm -rf awscliv2.zip aws && \
    # jq 설치
    curl -LO "https://github.com/stedolan/jq/releases/download/jq-1.6/jq-linux64" && \
    chmod +x jq-linux64 && \
    mv jq-linux64 /usr/local/bin/jq


# AWS CLI에서 사용할 리전 설정
ENV AWS_REGION=ap-northeast-2

#GCP 키를 가져오는 스크립트 복사 및 실행 권한 부여
COPY fetch-gcp-key.sh /usr/local/bin/fetch-gcp-key.sh
RUN chmod +x /usr/local/bin/fetch-gcp-key.sh

# build/libs/XXXX.jar로 빌드된 파일의 위치를 환경변수 설정
ARG JAR_FILE=build/libs/*.jar

# 프로젝트의 jar 위치를 참조하여 jar 파일을 참조해서 컨테이너의 루트 디렉토리에 app.jar의 이름으로 복사
COPY ${JAR_FILE} /app.jar

# 도커파일이 도커엔진을 통해서 컨테이너로 올라갈 때, 도커 컨테이너의 시스템 진입점이 어디인지를 선언
# java -jar 명령어를 이용해서 컨테이너의 루트에 위치한 app.jar을 실행
ENTRYPOINT ["/usr/local/bin/fetch-gcp-key.sh","java","-jar","/app.jar"]