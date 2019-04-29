import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import services.IServer;
import ui.Controller;
import ui.LogIn;

public class StartClient extends Application {

    @Override
    public void start(Stage stage) throws Exception {
        ApplicationContext factory = new ClassPathXmlApplicationContext("classpath:spring-client.xml");
        IServer server = (IServer) factory.getBean("server");
        System.out.println("Obtained a reference to remote server");
        Controller ctrl = new Controller(server);
        LogIn logIn = new  LogIn(ctrl);

        Scene scene = new Scene(logIn.logInStage());
        stage.setResizable(false);
        stage.setTitle("Log In");
        stage.setScene(scene);
        stage.show();
    }
}
