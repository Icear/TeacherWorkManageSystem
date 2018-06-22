#!/bin/bash

echo $SECRET_KEY > id_rsa
chmod 600 id_rsa
scp -i id_rsa -P $PORT ./target/ROOT.war $USER@$SERVER:$DEPLOY_FILE_LOCATION
ssh -i id_rsa $USER@$SERVER $DEPLOY_SCRIPT_LOCATION