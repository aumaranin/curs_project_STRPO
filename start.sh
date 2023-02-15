#bash ./build_jar.sh

#java -jar ./ApiGateway/target/apigateway.jar &
#java -jar ./CustomerMS/target/customerms.jar &
#java -jar ./PurchaseMS/target/purchasems.jar &
#java -jar ./StorehouseMS/target/storehousems.jar &

docker compose up --build -d

cd ./client2
mvn clean javafx:run 2>/dev/null

