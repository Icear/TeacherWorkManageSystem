#!/bin/bash

echo $PWD

scp -i $TRAVIS_BUILD_DIR/id_rsa -P $PORT -o 'StrictHostKeyChecking=no' $TRAVIS_BUILD_DIR/target/ROOT.war root@project.oooooreo.xyz:/root/docker-TMS/webapps/
ssh -i $TRAVIS_BUILD_DIR/id_rsa -p $PORT root@project.oooooreo.xyz "/root/docker-TMS/depoloy-docker-TMS.sh"