package ru.bmstu.curs_project_strpo.client2.properties;

public class SetPropertis
{
    public static Properties docker()
    {
        Properties properties = new Properties();
        properties.setApigatewayURL("http://apigateway:8080/");
        properties.setStorehouseMsURL("http://storehousems:8040/");
        properties.setCustomerMsURL("http://customerms:8041/");
        properties.setPurchaseMsURL("http://purchasems:8042/");
        properties.setCustomerBdURL("jdbc:postgresql://customerbd:5432/customerbd");
        properties.setStorehouseBdURL("jdbc:postgresql://storehousebd:5432/storehousebd");
        properties.setPurchaseBdURL("jdbc:postgresql://purchasebd:5432/purchasebd");
        return properties;
    }

    public static Properties common()
    {
        Properties properties = new Properties();
        properties.setApigatewayURL("http://localhost:8080/");
        properties.setStorehouseMsURL("http://localhost:8040/");
        properties.setCustomerMsURL("http://localhost:8041/");
        properties.setPurchaseMsURL("http://localhost:8042/");
        properties.setStorehouseBdURL("jdbc:postgresql://localhost:5440/storehousebd");
        properties.setCustomerBdURL("jdbc:postgresql://localhost:5441/customerbd");
        properties.setPurchaseBdURL("jdbc:postgresql://localhost:5442/purchasebd");
        return properties;
    }
}
