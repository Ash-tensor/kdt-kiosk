#!/bin/bash

# AWS Secrets Manager에서 비밀 가져오기
GCP_KEY=$(aws secretsmanager get-secret-value --secret-id gcp-key --query "SecretString" --output json) #| jq -r .SecretString)

# GCP 키를 환경 변수로 설정하거나 파일로 저장
export GCP_KEY
echo $GCP_KEY > /app/gcp-key.json
#chmod 644 /app/gcp-key.json

# GOOGLE_APPLICATION_CREDENTIALS 환경 변수 설정
export GOOGLE_APPLICATION_CREDENTIALS=/app/gcp-key.json

# 이후 필요한 Docker 명령 실행
exec "$@"