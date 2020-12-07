scp gateway/target/gateway-0.0.1-SNAPSHOT.jar ubuntu@49.234.12.16:/data/www
#scp api/target/api-0.0.1-SNAPSHOT.jar ubuntu@49.234.12.16:/data/www
scp mail-service/target/mail-service-0.0.1-SNAPSHOT.jar ubuntu@49.234.12.16:/data/www
scp order-service/target/order-service-0.0.1-SNAPSHOT.jar ubuntu@49.234.12.16:/data/www
scp user-service/target/user-service-0.0.1-SNAPSHOT.jar ubuntu@49.234.12.16:/data/www

scp run.sh ubuntu@49.234.12.16:/data/www
scp startup.sh ubuntu@49.234.12.16:/data/www
scp stop.sh ubuntu@49.234.12.16:/data/www