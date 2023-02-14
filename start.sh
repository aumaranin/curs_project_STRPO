#bash ./build_jar.sh

java -jar ./ApiGateway/target/apigateway.jar &
java -jar ./CustomerMS/target/customerms.jar &
java -jar ./PurchaseMS/target/purchasems.jar &
java -jar ./StorehouseMS/target/storehousems.jar &
