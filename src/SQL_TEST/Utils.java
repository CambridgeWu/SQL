package SQL_TEST;

import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.util.Properties;

public class Utils {
    private static Connection conn;
    private static PreparedStatement ps;
    private static ResultSet rs;

    /*private static String ur1;
    private static String user;
    private static String password;
    private static String driver;*/

    //静态代码块进行访问
   /* static{
        try {
            Properties pro=new Properties();

            //获取src路径下的文件
            ClassLoader classLoader = Utils.class.getClassLoader();
            URL res = classLoader.getResource("jdbc.properties");
            //获取路径
            String path=res.getPath();
            pro.load(new FileReader(path));

            ur1=pro.getProperty("ur1");
            user=pro.getProperty("user");
            password=pro.getProperty("password");
            driver=pro.getProperty("driver");

            try {
                Class.forName(driver);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }*/

    //获取连接
    public static Connection getConnection() {
        try {
            Class.forName(Contans.driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        try {
            conn=DriverManager.getConnection(Contans.ur1,Contans.user,Contans.password);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return conn;
    }


    //释放资源1(输入对应参数)，根据参数不同选择不同的方法
    public static void close(Statement sta,Connection conn){
        if(sta!=null) {
            try {
                sta.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
    //释放资源2
    public static void close(ResultSet rs,Statement sta, Connection conn){
        if(rs!=null) {
            try {
                rs.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        if(sta!=null) {
            try {
                sta.close();
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }

            if(conn!=null) {
                try {
                    conn.close();
                } catch (SQLException throwables) {
                    throwables.printStackTrace();
                }
            }
        }
    }
}
