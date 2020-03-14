package com.anti.op;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.DriverManager;
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
	//某天确诊人数统计
	public void getChart1(int date) throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//加载驱动，并建立数据库连接
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//执行SQL语句
			String sql="select sex,count(id) as total from anti_mes where sy0='是' and DATE_FORMAT(Dtime,'%Y%m%d') = '"+date+"' group by sex";
			rs=stmt.executeQuery(sql);
			//创建数据集
	        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
	        while(rs.next()){
	            dataset.setValue(
	                    rs.getInt("total"),
	                    "数量",
	                    rs.getString("sex")
	            );
	        }
	        // 创建简单的条形图
	        JFreeChart freeChart=ChartFactory.createBarChart(
	                "确诊信息",// 图表标题
	                "性别",
	                "人数",
	                dataset,//数据集，即要显示在图表上的数据
	                PlotOrientation.VERTICAL,
	                true,//是否显示图例
	                false,//是否显示提示
	                false//是否生成URL连接
	        );
	        //柱状图显示
	        SHOW(freeChart,600,500);
	        //关闭数据库；连接
			JDBCUtils.release(rs, stmt, conn);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
	//近一周确诊人数
	public void getChart2() throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//加载驱动，并建立数据库连接
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//执行SQL语句
			String sql="select sex,Dtime,count(id) as total from anti_mes where sy0='是' and  to_days( now( ) ) - to_days(Dtime) <=5 group by sex,Dtime";
			rs=stmt.executeQuery(sql);
			//创建数据集
	        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
	        while(rs.next()){
	            dataset.setValue(
	            		rs.getInt("total"),
	                    rs.getString("sex"),
	                    rs.getString("Dtime")
	            );
	        }
	        // 创建简单的条形图
	        JFreeChart freeChart=ChartFactory.createBarChart(
	                "近一周确诊信息",// 图表标题
	                "时间",
	                "人数",
	                dataset,//数据集，即要显示在图表上的数据
	                PlotOrientation.VERTICAL,
	                true,//是否显示图例
	                false,//是否显示提示
	                false//是否生成URL连接
	        );
	        SHOW(freeChart,1500,600);
			JDBCUtils.release(rs, stmt, conn);
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
	
	//本月信息统计
	public void getChart3() throws SQLException {
		Connection conn=null;
		Statement stmt=null;
		ResultSet rs=null;		
		try {
			//加载驱动，并建立数据库连接
			conn=JDBCUtils.getConnection();						
			stmt=conn.createStatement();
			//执行SQL语句
			String sql="select sex, Dtime,count(id) as total from anti_mes where sy0='是' and DATE_FORMAT(Dtime, '%Y%m' ) = DATE_FORMAT( CURDATE( ) , '%Y%m' ) group by sex,Dtime";
			rs=stmt.executeQuery(sql);
			//创建数据集
	        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
	        while(rs.next()){
	            dataset.addValue(
	    	         rs.getInt("total"),
	    	         rs.getString("sex"),
	    	         rs.getString("Dtime")
	    	            
	            );
	        }
	        // 创建简单的条形图
	        JFreeChart freeChart=ChartFactory.createBarChart(
	                "本月确诊信息统计",// 图表标题
	                "日期",
	                "人数",
	                dataset,//数据集，即要显示在图表上的数据
	                PlotOrientation.VERTICAL,
	                true,//是否显示图例
	                false,//是否显示提示
	                false//是否生成URL连接
	        );
	        SHOW(freeChart,1800,800);
			JDBCUtils.release(rs, stmt, conn);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}  
	}
	//上一个月信息统计
		public void getChart4() throws SQLException {
			Connection conn=null;
			Statement stmt=null;
			ResultSet rs=null;		
			try {
				//加载驱动，并建立数据库连接
				conn=JDBCUtils.getConnection();						
				stmt=conn.createStatement();
				//执行SQL语句
				String sql="select sex, Dtime,count(id) as total from anti_mes where sy0='是' and PERIOD_DIFF( date_format( now( ) , '%Y%m' ) , date_format(Dtime, '%Y%m' ) ) =1  group by sex,Dtime";
				rs=stmt.executeQuery(sql);
				//创建数据集
		        DefaultCategoryDataset dataset=new DefaultCategoryDataset();
		        while(rs.next()){
		            dataset.addValue(
		    	         rs.getInt("total"),
		    	         rs.getString("sex"),
		    	         rs.getString("Dtime")
		    	            
		            );
		        }
		        // 创建简单的条形图
		        JFreeChart freeChart=ChartFactory.createBarChart(
		                "上月确诊信息",// 图表标题
		                "日期",
		                "人数",
		                dataset,//数据集，即要显示在图表上的数据
		                PlotOrientation.VERTICAL,
		                true,//是否显示图例
		                false,//是否显示提示
		                false//是否生成URL连接
		        );
		        SHOW(freeChart,1800,800);
				JDBCUtils.release(rs, stmt, conn);
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}  
		}
	//柱状图显示设置
	public void SHOW(JFreeChart freeChart,int width,int height) {
		//以面板显示，创建一个图表面板
        ChartPanel chartPanel=new ChartPanel(freeChart);
        //设置大小
        chartPanel.setPreferredSize(new java.awt.Dimension(560,400));
        //创建一个主窗口来显示面板
        JFrame frame=new JFrame("疫情统计图");
        frame.setLocation(500,400);
        frame.setSize(width,height);
        //将图表面板设置为主窗口的内容面板
        frame.setContentPane(chartPanel);
        //显示主窗口
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        
        //设置图表
        freeChart.getTitle().setFont(new Font("隶书", Font.ITALIC, 15));//设置标题
		//设置图例类别字体         
		freeChart.getLegend().setItemFont(new Font("宋体", Font.BOLD, 15));  
		freeChart.setBackgroundPaint(Color.WHITE);
		CategoryPlot categoryPlot=freeChart.getCategoryPlot();//用于设置显示特性
		categoryPlot.setBackgroundPaint(Color.WHITE);
		categoryPlot.setDomainGridlinePaint(Color.BLACK);//分类轴网格线条颜色
		categoryPlot.setDomainGridlinesVisible(true);
		categoryPlot.setRangeGridlinePaint(Color.GREEN);//数据轴网格线条颜色
		
		CategoryAxis domainAxis=categoryPlot.getDomainAxis(); //水平底部列表 
		domainAxis.setLabelFont(new Font("黑体",Font.BOLD,14)); //水平底部标题 
		domainAxis.setTickLabelFont(new Font("宋体",Font.BOLD,12)); //垂直标题
		ValueAxis rangeAxis=categoryPlot.getRangeAxis();//获取柱状 
		rangeAxis.setLabelFont(new Font("黑体",Font.BOLD,15)); //设置柱状标题
		
		CategoryAxis axis = categoryPlot.getDomainAxis(); //x轴
		axis.setMaximumCategoryLabelLines(10); //标题行数，每个字显示一行
		axis.setMaximumCategoryLabelWidthRatio(0.5f); //每个标题宽度，控制为1个字的宽度
		
		NumberAxis axis1 = (NumberAxis)freeChart.getCategoryPlot().getRangeAxis();
		//axis1.setTickUnit(new NumberTickUnit(0.5D);//0.5为一个间隔单位
		axis1.setTickUnit(new NumberTickUnit(1D));//1为一个间隔单位
	}
}