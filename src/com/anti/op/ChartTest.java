package com.anti.op;
/*
 * 
 * ͼ����ز���
 * 
 * FileName: ChartTest.java
 * @author Zhangqin
 */
import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JFrame;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.CategoryAxis;
import org.jfree.chart.axis.NumberAxis;
import org.jfree.chart.axis.NumberTickUnit;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.CategoryPlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.data.category.DefaultCategoryDataset;
import com.anti.utils.JDBCUtils;

public class ChartTest {
	//ĳ��ȷ������ͳ��
	public void getChart1(int date) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//�������������������ݿ�����
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//ִ��SQL���
			String sql="select sex,count(id) as total from anti_mes where sy0='��' and DATE_FORMAT(Dtime,'%Y%m%d') = '"+date+"' group by sex";
			rs=stmt.executeQuery(sql);
			//�������ݼ�
			DefaultCategoryDataset dataset=new DefaultCategoryDataset();
			while(rs.next()){
			    dataset.setValue(
				    rs.getInt("total"),
				    "����",
				    rs.getString("sex")
			    );
			}
	        	// �����򵥵�����ͼ
			JFreeChart freeChart=ChartFactory.createBarChart(
				"ȷ����Ϣ",// ͼ�����
				"�Ա�",
				"����",
				dataset,//���ݼ�����Ҫ��ʾ��ͼ���ϵ�����
				PlotOrientation.VERTICAL,
				true,//�Ƿ���ʾͼ��
				false,//�Ƿ���ʾ��ʾ
				false//�Ƿ�����URL����
			);
			//��״ͼ��ʾ
			SHOW(freeChart,600,500);
			//�ر����ݿ�����
			JDBCUtils.release(rs, stmt, conn);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
	//��һ��ȷ������
	public void getChart2() throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//�������������������ݿ�����
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//ִ��SQL���
			String sql="select sex,Dtime,count(id) as total from anti_mes where sy0='��' and  to_days( now( ) ) - to_days(Dtime) <=5 group by sex,Dtime";
			rs=stmt.executeQuery(sql);
			//�������ݼ�
			DefaultCategoryDataset dataset=new DefaultCategoryDataset();
			while(rs.next()){
			    dataset.setValue(
					rs.getInt("total"),
				    rs.getString("sex"),
				    rs.getString("Dtime")
			    );
			}
			// �����򵥵�����ͼ
			JFreeChart freeChart=ChartFactory.createBarChart(
				"��һ��ȷ����Ϣ",// ͼ�����
				"ʱ��",
				"����",
				dataset,//���ݼ�����Ҫ��ʾ��ͼ���ϵ�����
				PlotOrientation.VERTICAL,
				true,//�Ƿ���ʾͼ��
				false,//�Ƿ���ʾ��ʾ
				false//�Ƿ�����URL����
			);
			SHOW(freeChart,1500,600);
			JDBCUtils.release(rs, stmt, conn);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
	
	//������Ϣͳ��
	public void getChart3() throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//�������������������ݿ�����
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//ִ��SQL���
			String sql="select sex, Dtime,count(id) as total from anti_mes where sy0='��' and DATE_FORMAT(Dtime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) group by sex,Dtime";
			rs=stmt.executeQuery(sql);
			//�������ݼ�
			DefaultCategoryDataset dataset=new DefaultCategoryDataset();
			while(rs.next()){
			    dataset.addValue(
				 rs.getInt("total"),
				 rs.getString("sex"),
				 rs.getString("Dtime")

			    );
			}
			// �����򵥵�����ͼ
			JFreeChart freeChart=ChartFactory.createBarChart(
				"����ȷ����Ϣͳ��",// ͼ�����
				"����",
				"����",
				dataset,//���ݼ�����Ҫ��ʾ��ͼ���ϵ�����
				PlotOrientation.VERTICAL,
				true,//�Ƿ���ʾͼ��
				false,//�Ƿ���ʾ��ʾ
				false//�Ƿ�����URL����
			);
			SHOW(freeChart,1800,800);
			JDBCUtils.release(rs, stmt, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
	//��һ������Ϣͳ��
		public void getChart4() throws SQLException {
			Connection conn=null;
			Statement stmt=null;
			ResultSet rs=null;		
			try {
				//�������������������ݿ�����
				conn=JDBCUtils.getConnection();						
				stmt=conn.createStatement();
				//ִ��SQL���
				String sql="select sex, Dtime,count(id) as total from anti_mes where sy0='��' and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format(Dtime, '%Y%m' ) ) =1  group by sex,Dtime";
				rs=stmt.executeQuery(sql);
				//�������ݼ�
				DefaultCategoryDataset dataset=new DefaultCategoryDataset();
				while(rs.next()){
				    dataset.addValue(
					 rs.getInt("total"),
					 rs.getString("sex"),
					 rs.getString("Dtime")

				    );
				}
				// �����򵥵�����ͼ
				JFreeChart freeChart=ChartFactory.createBarChart(
					"����ȷ����Ϣ",// ͼ�����
					"����",
					"����",
					dataset,//���ݼ�����Ҫ��ʾ��ͼ���ϵ�����
					PlotOrientation.VERTICAL,
					true,//�Ƿ���ʾͼ��
					false,//�Ƿ���ʾ��ʾ
					false//�Ƿ�����URL����
				);
				SHOW(freeChart,1800,800);
				JDBCUtils.release(rs, stmt, conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}  
		}
		//��״ͼ��ʾ����
		public void SHOW(JFreeChart freeChart,int width,int height) {
			//�������ʾ������һ��ͼ�����
		ChartPanel chartPanel=new ChartPanel(freeChart);
		//���ô�С
		chartPanel.setPreferredSize(new java.awt.Dimension(560,400));
		//����һ������������ʾ���
		JFrame frame=new JFrame("����ͳ��ͼ");
		frame.setLocation(500,400);
		frame.setSize(width,height);
		//��ͼ���������Ϊ�����ڵ��������
		frame.setContentPane(chartPanel);
		//��ʾ������
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setVisible(true);
        
        	//����ͼ��
        	freeChart.getTitle().setFont(new Font("����", Font.ITALIC, 15));//���ñ���
		//����ͼ���������         
		freeChart.getLegend().setItemFont(new Font("����", Font.BOLD, 15));  
		freeChart.setBackgroundPaint(Color.WHITE);
		CategoryPlot categoryPlot=freeChart.getCategoryPlot();//����������ʾ����
		categoryPlot.setBackgroundPaint(Color.WHITE);
		categoryPlot.setDomainGridlinePaint(Color.BLACK);//����������������ɫ
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setRangeGridlinePaint(Color.GREEN);//����������������ɫ
		
		CategoryAxis domainAxis=categoryPlot.getDomainAxis(); //ˮƽ�ײ��б� 
		domainAxis.setLabelFont(new Font("����",Font.BOLD,14)); //ˮƽ�ײ����� 
		domainAxis.setTickLabelFont(new Font("����",Font.BOLD,12)); //��ֱ����
		ValueAxis rangeAxis=categoryPlot.getRangeAxis();//��ȡ��״ 
		rangeAxis.setLabelFont(new Font("����",Font.BOLD,15)); //������״����
		
		CategoryAxis axis = categoryPlot.getDomainAxis(); //x��
		axis.setMaximumCategoryLabelLines(10); //����������ÿ������ʾһ��
		axis.setMaximumCategoryLabelWidthRatio(0.5f); //ÿ�������ȣ�����Ϊ1���ֵĿ��
		
		NumberAxis axis1 = (NumberAxis)freeChart.getCategoryPlot().getRangeAxis();
		//axis1.setTickUnit(new NumberTickUnit(0.5D);//0.5Ϊһ�������λ
		axis1.setTickUnit(new NumberTickUnit(1D));//1Ϊһ�������λ
	}
}
