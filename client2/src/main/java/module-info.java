module ru.bmstu.curs_project_strpo.client2 {
    requires javafx.controls;
    requires javafx.fxml;
    requires com.fasterxml.jackson.databind;


    opens ru.bmstu.curs_project_strpo.client2 to javafx.fxml;
    exports ru.bmstu.curs_project_strpo.client2;
    exports ru.bmstu.curs_project_strpo.client2.Controller;
    opens ru.bmstu.curs_project_strpo.client2.Controller to javafx.fxml;
    exports ru.bmstu.curs_project_strpo.client2.DataObjects;
    opens ru.bmstu.curs_project_strpo.client2.DataObjects to javafx.fxml;
}