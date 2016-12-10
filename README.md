## SETUP ##
1. ant setup-db
2. ant jar
3. ant run

## Run Web App ##
1. ant war
2. cp libmgmt.war /usr/local/apache-tomcat-7.0.73/webapps/
3. tomcat-shutdown
4. tomcat-startup