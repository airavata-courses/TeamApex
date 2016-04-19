echo 'Installing Science_Gateway_Apex to Maven'
cd Science_Gateway_Apex
mvn -e clean install

echo 'Installing SGA_Apex_REST_API_WAR'
cd ../SGA_Apex_REST_API_WAR
mvn jetty:run