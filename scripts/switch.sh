#!/usr/bin/env bash

ABSPATH=$(readlink -f $0)
ABSDIR=$(dirname $ABSPATH)
source ${ABSDIR}/profile.sh

function switch_proxy() {
    IDLE_PORT=$(find_idle_port)

    echo "> 전환할 Port: $IDLE_PORT"
    echo "> Port 전환"
    echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" | sudo tee /etc/nginx/conf.d/service-url.inc # return value

    echo "> 엔진엑스 Reload"
    sudo service nginx reload
}

# 1. echo "set \$service_url http://127.0.0.1:${IDLE_PORT};" : return value. make proxy address that nginx will change to.
# 2. sudo tee /etc/nginx/conf.d/service-url.inc : overwrite the above commend at service-url.inc
# 3. sudo service nginx reload : reload nginx configuration. (but import setting is not reloaded, it should use restart.)