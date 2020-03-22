package com.anti.op;
/*
 * 
 * 数据库相关操作
 * @author Zhangqin
 * FileName: JdbcTest.java
 */
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.anti.utils.JDBCUtils;

public class JdbcTest {
	//全部确诊信息
	public void findAll() throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//加载驱动，并建立数据库连接
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//执行SQL语句
			String sql="select * from anti_mes where sy0 ='是' order by Dtime";
			try {
				 rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				System.out.println("查询错误!");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GET(rs);
		        JDBCUtils.release(rs, stmt, conn);
		} catch (ClassNotFoundException e) {
			System.out.println("数据库连接错误!");
			e.printStackTrace();
		}

	}
	//按时间查找
	public void findByTime(int date) throws SQLException {
		//long startTime = System.currentTimeMillis();//获取当前时间
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//加载驱动，并建立数据库连接
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//执行SQL语句
			String sql="select * from anti_mes  where sy0='是' and DATE_FORMAT(Dtime,'%Y%m%d') = '"+date+"'";
			try {
				 rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GET(rs);
	        	JDBCUtils.release(rs, stmt, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		//long endTime = System.currentTimeMillis();
		//System.out.println("程序运行时间："+(endTime-startTime)+"ms");

	}
	//按学号查找
	public void findById(int ids) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//加载驱动，并建立数据库连接
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//执行SQL语句
			String sql="select * from anti_mes  where id= '"+ids+"'";
			try {
				 rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GET(rs);
	        	JDBCUtils.release(rs, stmt, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	//具体查找(某人某天)
	public void findByIdAndTime(int ids,int date) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//加载驱动，并建立数据库连接
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//执行SQL语句
			String sql="select * from anti_mes  where id= '"+ids+"' and DATE_FORMAT(Dtime,'%Y%m%d') = '"+date+"' order by Dtime";
			try {
				 rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GET(rs);
	        	JDBCUtils.release(rs, stmt, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

	}
	//插入语句
	public boolean insert(int ids,String name,String sex,String area,String sy0,String sy1,String sy2,String sy3)
	{
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		boolean b = false;
		try {
			//加载驱动，并建立数据库连接
			try {
				conn=JDBCUtils.getConnection();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}						
			try {
				stmt=conn.createStatement();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//执行SQL语句
			String sql="insert into anti_mes(id,name,sex,area,sy0,sy1,sy2,sy3,Dtime) values('"+ids+"','"+name+"',"
					+ "'"+sex+"','"+area+"','"+sy0+"','"+sy1+"','"+sy2+"','"+sy3+"',now())";
			try {
				 b=stmt.execute(sql);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return b;
	}
	//从数据库获取信息并输出
	public void GET(ResultSet rs) {
		System.out.print("学号\t"+"姓名\t"+"性别\t"+"地区\t"+"是否确诊\t   ");
        	System.out.println("是否有疑似症状\t"+"是否在武汉\t"+"是否与相关人员接触\t"+"时间\t");
		try {
			int id ;
            		String name = null;
            		String sex = null;
            		String area = null;
            		String sy0 = null;
            		String sy1 = null;
            		String sy2 = null;
            		String sy3 = null;
            		Date time;
            		int ans=0;
            		while(rs.next()){
                		id = rs.getInt("id");
                		name = rs.getString("name");
                		sex = rs.getString("sex");
                		area = rs.getString("area");
                		sy0 = rs.getString("sy0");
                		sy1 = rs.getString("sy1");
                		sy2 = rs.getString("sy2");
                		sy3 = rs.getString("sy3");
                		time = rs.getDate("Dtime");
                		PRINT(id,name,sex,area,sy0,sy1,sy2,sy3,time);
                		ans++;
            		}
            		if(ans==0) {
            			System.out.println("\n当前信息暂无匹配项!");
            		}
		} catch (SQLException e) {
			System.out.println("\n信息获取错误，请确认信息输入是否正确!");
		}	
	}
	//信息显示
	public void PRINT(int id,String name,String sex,String area,String sy0,String sy1,String sy2,String sy3,Date time) {
        	System.out.print(id + "\t" +name +"\t " + sex +"\t" + area +"\t");
        	System.out.print("   "+sy0 + "\t           " +sy1 +"     \t     " + sy2 +"\t       \t\t" + sy3 +"\t\t");
        	System.out.println(time);
	}

}
