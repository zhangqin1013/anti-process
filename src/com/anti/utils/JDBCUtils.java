package com.anti.utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class JDBCUtils {
	//加载驱动，并建立数据库连接
	public static Connection getConnection() throws ClassNotFoundException, SQLException{
		Connection conn=null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
		} catch (ClassNotFoundException e1) {
			System.out.println("加载驱动错误");
			//e1.printStackTrace();
		}
		String url = "jdbc:mysql://localhost:3306/dis_demo?useUnicode=true&characterEncoding=utf8&serverTimezone=GMT%2B8&useSSL=false";
		String user = "root";
		String pwd = "123456";
		try{
		       conn = DriverManager.getConnection(url, user, pwd);
		}
		catch(Exception e) {
			System.out.println("连接数据库失败");
                        System.out.println(e.getMessage());
		}
		return conn;
		 
	}
	//关闭数据库连接，释放资源
	public static void release(ResultSet rs,Statement stmt,Connection conn){
		if(rs!=null){
			try {
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(stmt!=null){
			try {
				stmt.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(conn!=null){
			try {
				conn.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
