#!/bin/bash

##############################################
# Script to replace properties before build. #
##############################################

# Retrieving the values from env variables.
aws_db_password=$AWS_DB_PASSWORD

# Replacing DB password properties.
sed -i 's~$awsdbpass~'"$aws_db_password"'~g' ../../../../jetty-jdbcConfig.properties

