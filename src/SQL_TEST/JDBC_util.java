package SQL_TEST;

import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.KeyEvent;
import java.sql.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class JDBC_util {
    Scanner sc=new Scanner(System.in);
    //用户登录
    /*public boolean login(String username,String password){
        //如果为空则返回false
        if(username==null||password==null){
            return false;
        }


        Connection conn=null;
        Statement sta=null;
        ResultSet rs=null;

        try {
            //获取连接对象
            conn=Utils.getConnection();

            String sql="select * from user where username=? + password=?";
            //获取sql对象
            sta=conn.createStatement();
            rs=sta.executeQuery(sql);

            return true;
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Utils.close(rs,sta,conn);
        }

        return false;
    }
*/


    //添加
    public void add(String name,int id){

        Connection conn=null;
        PreparedStatement ps=null;

        try {
            conn=Utils.getConnection();
            String sql="insert into student values(?,?)";
            ps=conn.prepareStatement(sql);
            ps.setString(1,String.valueOf(id));
            ps.setString(2,name);
            int count=ps.executeUpdate();
            if(count!=0){
                System.out.println("添加成功！");
            }else{
                System.out.println("添加失败！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Utils.close(ps,conn);
        }
    }
    //删除
    public void delete(int student_id){
        Connection conn=null;
        PreparedStatement ps=null;

        try {
            conn=Utils.getConnection();
            String sql="delete from student where Id=?";
            ps=conn.prepareStatement(sql);
            ps.setString(1,String.valueOf(student_id));
            int count=ps.executeUpdate();
            if(count!=0){
                System.out.println("删除成功！");
            }else{
                System.out.println("删除失败！");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Utils.close(ps,conn);
        }
    }

    public static int executeUpdate(Connection conn,String sql,Object[] param){
        int result=0;
        PreparedStatement ps=null;
        try {
            ps=conn.prepareStatement(sql);
            if(param!=null){
                for(int i=0;i<param.length;i++){
                    ps.setObject(i+1,param[i]);
                }
            }
            result=ps.executeUpdate();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return result;
    }

    //修改
    public void modify(int id){
        System.out.println("请重新输入该学生姓名：");
        String name=sc.next();
        System.out.println("请重新输入该学生id：");
        int ID=sc.nextInt();

        Connection conn=null;
        PreparedStatement ps=null;

        conn=Utils.getConnection();
        String sql="update student set Id = ?,student_name= ? where Id = ?";
        try {
            ps = conn.prepareStatement(sql);
            ps.setInt(1,ID);
            ps.setString(2,name);
            ps.setInt(3,id);

            int count =ps.executeUpdate();
            if(count!=0){
                System.out.println("修改成功！");
            }else{
                System.out.println("修改失败!");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Utils.close(ps,conn);
        }

    }
    //展示数据表==输出所有学生的信息
    public void show(){
        Connection conn=null;
        PreparedStatement ps=null;
        ResultSet rs=null;

        List<student> stu=new ArrayList<student>();

        try {
            conn=Utils.getConnection();
            String sql="select * from student";
            ps=conn.prepareStatement(sql);
            rs=ps.executeQuery();

            while(rs.next()){
                int id=rs.getInt("Id");
                String name=rs.getString("student_name");

                student st=new student(name,id);
                stu.add(st);
            }
            Iterator<student> it=stu.iterator();
            while(it.hasNext()){
                student st1=it.next();
                System.out.println("学生姓名："+st1.getName()+"学号："+st1.getId());
            }


        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }finally {
            Utils.close(rs,ps,conn);
        }
    }

    public boolean keyDown(){
        Scanner scan=new Scanner(System.in);
        System.out.println("************SQL***********");
        System.out.println("          1.添加           ");
        System.out.println("          2.删除           ");
        System.out.println("          3.修改           ");
        System.out.println("          4.打印           ");
        System.out.println("          5.退出           ");
        System.out.println("**************************");

        int num=sc.nextInt();
        switch(num){
            case 1:
                System.out.println("请输入学生姓名：");
                String name=scan.nextLine();
                System.out.println("请输入学生的id：");
                int ID=scan.nextInt();
                add(name,ID);
                break;
            case 2:
                show();
                System.out.println();
                System.out.println("请输入你要删除学生的id：");
                int id=sc.nextInt();
                delete(id);
                break;
            case 3:
                show();
                System.out.println("请输入你要修改的学生id：");
                int Id=scan.nextInt();
                modify(Id);
                break;
            case 4:
                show();
                break;
            case 5:
                System.out.println("欢迎下次使用！");
                return false;
            default:
                System.out.println("你的输入有误！");
                break;
        }
        return true;
    }
}
