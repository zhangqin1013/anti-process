package com.anti.test;
/*
 * 
 * 显示界面
 * 
 * FileName: Show.java
 * @author Zhangqin
 */

import java.util.Scanner;

import com.anti.op.ChartTest;
import com.anti.op.JdbcTest;
public class Show {
	public void show() throws Exception {
		JdbcTest jdbc = new JdbcTest();
		ChartTest chart = new ChartTest();
		System.out.print("---------------------------- 疫情数据 -------------------------\n");
		System.out.print("                        1.当前确诊信息查看                             \n");
		System.out.print("                        2.疫情具体信息查询                             \n");
		System.out.print("                        3.疫情具体信息录入                             \n");
		System.out.print("                        4.疫情信息图表显示                             \n");
		System.out.print("---------------------------------------------------------------\n");
		Scanner out = new Scanner(System.in);
		System.out.println("请输入您的选择：");
		int a= out.nextInt();
		while(a != 0) {
	        	switch(a){
		       		case 1:
					jdbc.findAll();
		        		break;
		        	case 2:
		        		System.out.print("***1.某天确诊信息查询(输入日期)   \n");
		        		System.out.print("***2.最近信息查询(输入学号)   \n");
		        		System.out.print("***3.具体信息查询(输入学号和日期)  \n");
		        		//Scanner out = new Scanner(System.in);
		    			System.out.println("请输入您的选择：");
		    			int c= out.nextInt();
		        		if(c==1) {
		        			//Scanner out = new Scanner(System.in);
		        			System.out.println("请输入具体日期(例如:20200304):");
		        			int date= out.nextInt();
		        			jdbc.findByTime(date);
		        		}else if(c==2) {
		        			System.out.println("请输入学号:");
		        			int ids= out.nextInt();
		        			jdbc.findById(ids);
		        		
		        		}else if(c==3) {
		        		
		        			System.out.println("请输入学号：");
		        			int ids= out.nextInt();
		        			System.out.println("请输入具体日期(例如:20200304):");
		        			int date= out.nextInt();
		        			jdbc.findByIdAndTime(ids, date);
		        		}else {
		        			System.out.println("输入错误!");
		        		}
		               		 break;
		        	case 3:
		        		System.out.println("请输入学号:");
	        			int ids= out.nextInt();
					out.nextLine();
					System.out.println("请输入姓名:");
					String name= out.nextLine();
					//out.nextLine();
					System.out.println("请输入性别:");
					String sex= out.nextLine();
					//out.nextLine();
					System.out.println("请输入省份:");
					String area= out.nextLine();
					//out.nextLine();
					System.out.println("是否确诊(输入“是”或“否”):");
					String sy0= out.nextLine();
					//out.nextLine();
					System.out.println("是否有疑似病例(输入“是”或“否”):");
					String sy1= out.nextLine();
					//out.nextLine();
					System.out.println("是否在武汉(输入“是”或“否”):");
					String sy2= out.nextLine();
					//out.nextLine();
					System.out.println("是否与相关人员接触(输入“是”或“否”):");
					String sy3= out.nextLine();
					boolean flag=jdbc.insert(ids, name, sex, area, sy0, sy1, sy2, sy3);
					if(flag==false) {
						System.out.println("录入成功!");
					}else {
						System.out.println("录入失败!");
					}
					break;
				case 4:
					System.out.print("***1.某天确诊信息查询(输入日期) \n");
					System.out.print("***2.最近一周确诊信息统计 \n");
					System.out.print("***3.本月确诊信息统计 \n");
					System.out.print("***4.上一个月确诊信息统计 \n");
					//Scanner out = new Scanner(System.in);
					System.out.println("请输入您的选择：");
					int b= out.nextInt();
					if(b==1) {
						//Scanner out = new Scanner(System.in);
						System.out.println("请输入具体日期(例如:20200304):");
						int date= out.nextInt();
						chart.getChart1(date);
					}else if(b==2) {
						chart.getChart2();
					}else if(b==3) {
						chart.getChart3();
					}else if(b==4) {
						chart.getChart4();
					}else {
						System.out.println("输入错误!");
					}
					break;
				default:
					System.out.println("default,please input choice number:");
				     break;
			}
			System.out.println("\n若要结束请输入0，否则输入选项号码继续查询!\n\n");

			System.out.print("---------------------------- 疫情数据 -------------------------\n");
				System.out.print("                        1.当前确诊信息查看                             \n");
				System.out.print("                        2.疫情具体信息查询                             \n");
				System.out.print("                        3.疫情具体信息录入                             \n");
				System.out.print("                        4.疫情信息图表显示                             \n");
				System.out.print("---------------------------------------------------------------\n");
			System.out.println("请输入您的选择：");
				a= out.nextInt();
		}
		if(a==0) {
			System.out.print("已退出!");
			System.exit(0);
		}
	}
}
