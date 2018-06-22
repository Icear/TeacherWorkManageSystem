#!/bin/bash

scp -i id_rsa -P $PORT ./target/ROOT.war root@project.oooooreo.xyz:/root/docker-TMS/webapps/
ssh -i id_rsa -p $PORT root@project.oooooreo "/root/docker-TMS/depoloy-docker-TMS.sh"