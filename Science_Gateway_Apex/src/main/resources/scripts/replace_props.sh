#!/bin/bash

##############################################
# Script to replace properties before build. #
##############################################

# Retrieving the values from env variables.
username=$LOGIN_USER
login_key_path=$LOGIN_PRIVATE_KEY_PATH
login_key_passphrase=$LOGIN_KEY_PASSPHRASE

# Replacing in karst properties.
sed -i 's/$user/${username}/g' ../karst_config.properties
sed -i 's/$path/${login_key_path}/g' ../karst_config.properties
sed -i 's/$passphrase/${login_key_passphrase}/g' ../karst_config.properties

# Replacing in bigred2 properties.
sed -i 's/$user/${username}/g' ../bigred2_config.properties
sed -i 's/$path/${login_key_path}/g' ../bigred2_config.properties
sed -i 's/$passphrase/${login_key_passphrase}/g' ../bigred2_config.properties
