#!/bin/bash -e

info() {
  echo '[INFO] ' "$@"
}

warn() { 
  echo '[WARN] ' "$@" >&2 
}

error() { 
  echo '[ERROR] ' "$@" >&2 
  exit 1
}

syntax() {
  warn "Missing arguments: $0 -b <S3 BUCKET NAME> -w <WAR> -n <UPLOAD_NAME> -v <VERSION>"
}

BUCKET="funfact-wars"
VERSION="1.0.0"

while :; do
  case $1 in
    -b|--bucket) 
      shift
      BUCKET=$1
    ;;
    -w|--war) 
      shift
      WAR=$1
    ;;
    -n|--name) 
      shift
      UPLOAD_NAME=$1
    ;;
    -v|--version) 
      shift
      VERSION=$1
    ;;
    *) 
    break
  esac
  shift
done


if [ -z "$WAR" ]; then
  syntax
  exit 1
fi

if [ ! -f "$WAR" ]; then
  error "$WAR not found"
fi

UPLOAD_NAME=${UPLOAD_NAME:-funfact.war}
UPLOAD_PATH="$VERSION/$UPLOAD_NAME"
MD5SUM=$(openssl md5 -binary "$WAR" | base64)
info "Uploading to s3://$BUCKET/$VERSION/$(basename $WAR)"

aws s3api put-object \
  --bucket $BUCKET \
  --key "$UPLOAD_PATH" \
  --content-md5 $MD5SUM \
  --tagging "version=$VERSION" \
  --body $WAR --output json

info "Upload successful"
