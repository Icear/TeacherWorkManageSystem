#!/bin/bash

scp -i id_rsa -P $PORT ./target/ROOT.war $USER@$SERVER:$DEPLOY_FILE_LOCATION
ssh -i id_rsa $USER@$SERVER $DEPLOY_SCRIPT_LOCATION