mvn liquibase:update --settings db\settings.xml -Dclient.db.url=jdbc:postgresql://localhost/dummy -Dclient.db.schema=com.example.order.service-product -Denv=postgreslocal