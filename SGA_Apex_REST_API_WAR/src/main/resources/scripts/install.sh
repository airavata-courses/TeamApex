echo 'Installing Science_Gateway_Apex to Maven'
cd '/home/ec2-user/Science_Gateway_Apex'
mvn -e clean install
echo 'Installing SGA_Apex_REST_API_WAR'
cd '/home/ec2-user/SGA_Apex_REST_API_WAR'
mvn -e clean install
mvn jetty:run >> /var/log/sga-apex-api-jetty.log 2>&1 &
sleep 60
echo 'Installing SGA_Apex_UI_WEB'
cd '/home/ec2-user/SGA_Apex_UI_WEB'
mvn -e clean install
mvn jetty:run >> /var/log/sga-apex-ui-jetty.log 2>&1 &
sleep 60