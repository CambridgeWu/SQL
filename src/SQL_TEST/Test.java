package SQL_TEST;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;

/**
 * 进行程序前请在Contans里进行内容的修改
 * 分别为你的账号密码和driver
 */
public class Test {
    public static void main(String[] args) throws AWTException {
            JDBC_util jd=new JDBC_util();
            boolean flag=true;
            while(flag){
                flag=jd.keyDown();
            }
    }
}


