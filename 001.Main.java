package p;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.apache.catalina.webresources.DirResourceSet;
import org.apache.catalina.webresources.StandardRoot;

import java.io.File;

public class Main {
    public static void main(String[] args) {
        Tomcat tomcat=new Tomcat();
        String tmpdir=System.getProperty("java.io.tmpdir");
        tomcat.setBaseDir(tmpdir);
        tomcat.setPort(8080);
        Context context=tomcat.addWebapp("/abc",new File("web/").getAbsolutePath());
        StandardRoot resources=new StandardRoot(context);
        resources.addPreResources(new DirResourceSet(
                resources,"D:/WEB-INF/classes",
                new File(".").getAbsolutePath(),
                "/"
        ));
        context.setResources(resources);
        tomcat.getConnector();
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            e.printStackTrace();
        }

        tomcat.getServer().await();
    }
}
