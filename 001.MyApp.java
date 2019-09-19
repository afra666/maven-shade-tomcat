package p;
import  org.apache.catalina.*;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class MyApp {
    public static void main(String[] args) {
        Tomcat tomcat=new Tomcat();
        tomcat.setBaseDir("C:\\Users\\ADMINI~1\\AppData\\Local\\Temp\\");
        tomcat.setPort(8080);
        Context context=tomcat.addWebapp("/abc",new File("web/").getAbsolutePath());
        tomcat.getConnector();
        /**以下设置Servlet调用**/
        StandardRoot resource=new StandardRoot(context);
        resource.addPreResources(
                new DirResourceSet(
                        resource,"/WEB-INF/classes",
                        new File(".").getAbsolutePath(),
                        "/"
                ));
        /****/
        context.setResources(resource);
        tomcat.getConnector();
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }
        tomcat.getServer().await();
    }
}
