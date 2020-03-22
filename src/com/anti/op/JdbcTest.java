package com.anti.op;
/*
 * 
 * ���ݿ���ز���
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
	//ȫ��ȷ����Ϣ
	public void findAll() throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//�������������������ݿ�����
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//ִ��SQL���
			String sql="select * from anti_mes where sy0 ='��' order by Dtime";
			try {
				 rs=stmt.executeQuery(sql);
			} catch (SQLException e) {
				System.out.println("��ѯ����!");
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			GET(rs);
		        JDBCUtils.release(rs, stmt, conn);
		} catch (ClassNotFoundException e) {
			System.out.println("���ݿ����Ӵ���!");
			e.printStackTrace();
		}

	}
	//��ʱ�����
	public void findByTime(int date) throws SQLException {
		//long startTime = System.currentTimeMillis();//��ȡ��ǰʱ��
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//�������������������ݿ�����
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//ִ��SQL���
			String sql="select * from anti_mes  where sy0='��' and DATE_FORMAT(Dtime,'%Y%m%d') = '"+date+"'";
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
		//System.out.println("��������ʱ�䣺"+(endTime-startTime)+"ms");

	}
	//��ѧ�Ų���
	public void findById(int ids) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//�������������������ݿ�����
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//ִ��SQL���
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
	//�������(ĳ��ĳ��)
	public void findByIdAndTime(int ids,int date) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//�������������������ݿ�����
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//ִ��SQL���
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
	//�������
	public boolean insert(int ids,String name,String sex,String area,String sy0,String sy1,String sy2,String sy3)
	{
		
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;
		boolean b = false;
		try {
			//�������������������ݿ�����
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
			//ִ��SQL���
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
	//�����ݿ��ȡ��Ϣ�����
	public void GET(ResultSet rs) {
		System.out.print("ѧ��\t"+"����\t"+"�Ա�\t"+"����\t"+"�Ƿ�ȷ��\t   ");
        	System.out.println("�Ƿ�������֢״\t"+"�Ƿ����人\t"+"�Ƿ��������Ա�Ӵ�\t"+"ʱ��\t");
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
            			System.out.println("\n��ǰ��Ϣ����ƥ����!");
            		}
		} catch (SQLException e) {
			System.out.println("\n��Ϣ��ȡ������ȷ����Ϣ�����Ƿ���ȷ!");
		}	
	}
	//��Ϣ��ʾ
	public void PRINT(int id,String name,String sex,String area,String sy0,String sy1,String sy2,String sy3,Date time) {
        	System.out.print(id + "\t" +name +"\t " + sex +"\t" + area +"\t");
        	System.out.print("   "+sy0 + "\t           " +sy1 +"     \t     " + sy2 +"\t       \t\t" + sy3 +"\t\t");
        	System.out.println(time);
	}

}
