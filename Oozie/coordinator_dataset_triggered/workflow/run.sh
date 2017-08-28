#!/bin/bash

date=`expr "$1" : '.*\([0-9][0-9][0-9][0-9]-[0-9][0-9]-[0-9][0-9]\)'`

chmod 0600 id_rsa
ssh -o StrictHostKeyChecking=no -i id_rsa hadoop@X.X.X.X some_task.sh  $date
