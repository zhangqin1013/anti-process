package com.anti.test;
/*
 * 
 * ��ʾ����
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
		System.out.print("---------------------------- �������� -------------------------\n");
		System.out.print("                        1.��ǰȷ����Ϣ�鿴                             \n");
		System.out.print("                        2.���������Ϣ��ѯ                             \n");
		System.out.print("                        3.���������Ϣ¼��                             \n");
		System.out.print("                        4.������Ϣͼ����ʾ                             \n");
		System.out.print("---------------------------------------------------------------\n");
		Scanner out = new Scanner(System.in);
		System.out.println("����������ѡ��");
		int a= out.nextInt();
		while(a != 0) {
	        	switch(a){
		       		case 1:
					jdbc.findAll();
		        		break;
		        	case 2:
		        		System.out.print("***1.ĳ��ȷ����Ϣ��ѯ(��������)   \n");
		        		System.out.print("***2.�����Ϣ��ѯ(����ѧ��)   \n");
		        		System.out.print("***3.������Ϣ��ѯ(����ѧ�ź�����)  \n");
		        		//Scanner out = new Scanner(System.in);
		    			System.out.println("����������ѡ��");
		    			int c= out.nextInt();
		        		if(c==1) {
		        			//Scanner out = new Scanner(System.in);
		        			System.out.println("�������������(����:20200304):");
		        			int date= out.nextInt();
		        			jdbc.findByTime(date);
		        		}else if(c==2) {
		        			System.out.println("������ѧ��:");
		        			int ids= out.nextInt();
		        			jdbc.findById(ids);
		        		
		        		}else if(c==3) {
		        		
		        			System.out.println("������ѧ�ţ�");
		        			int ids= out.nextInt();
		        			System.out.println("�������������(����:20200304):");
		        			int date= out.nextInt();
		        			jdbc.findByIdAndTime(ids, date);
		        		}else {
		        			System.out.println("�������!");
		        		}
		               		 break;
		        	case 3:
		        		System.out.println("������ѧ��:");
	        			int ids= out.nextInt();
					out.nextLine();
					System.out.println("����������:");
					String name= out.nextLine();
					//out.nextLine();
					System.out.println("�������Ա�:");
					String sex= out.nextLine();
					//out.nextLine();
					System.out.println("������ʡ��:");
					String area= out.nextLine();
					//out.nextLine();
					System.out.println("�Ƿ�ȷ��(���롰�ǡ��򡰷�):");
					String sy0= out.nextLine();
					//out.nextLine();
					System.out.println("�Ƿ������Ʋ���(���롰�ǡ��򡰷�):");
					String sy1= out.nextLine();
					//out.nextLine();
					System.out.println("�Ƿ����人(���롰�ǡ��򡰷�):");
					String sy2= out.nextLine();
					//out.nextLine();
					System.out.println("�Ƿ��������Ա�Ӵ�(���롰�ǡ��򡰷�):");
					String sy3= out.nextLine();
					boolean flag=jdbc.insert(ids, name, sex, area, sy0, sy1, sy2, sy3);
					if(flag==false) {
						System.out.println("¼��ɹ�!");
					}else {
						System.out.println("¼��ʧ��!");
					}
					break;
				case 4:
					System.out.print("***1.ĳ��ȷ����Ϣ��ѯ(��������) \n");
					System.out.print("***2.���һ��ȷ����Ϣͳ�� \n");
					System.out.print("***3.����ȷ����Ϣͳ�� \n");
					System.out.print("***4.��һ����ȷ����Ϣͳ�� \n");
					//Scanner out = new Scanner(System.in);
					System.out.println("����������ѡ��");
					int b= out.nextInt();
					if(b==1) {
						//Scanner out = new Scanner(System.in);
						System.out.println("�������������(����:20200304):");
						int date= out.nextInt();
						chart.getChart1(date);
					}else if(b==2) {
						chart.getChart2();
					}else if(b==3) {
						chart.getChart3();
					}else if(b==4) {
						chart.getChart4();
					}else {
						System.out.println("�������!");
					}
					break;
				default:
					System.out.println("default,please input choice number:");
				     break;
			}
			System.out.println("\n��Ҫ����������0����������ѡ����������ѯ!\n\n");

			System.out.print("---------------------------- �������� -------------------------\n");
				System.out.print("                        1.��ǰȷ����Ϣ�鿴                             \n");
				System.out.print("                        2.���������Ϣ��ѯ                             \n");
				System.out.print("                        3.���������Ϣ¼��                             \n");
				System.out.print("                        4.������Ϣͼ����ʾ                             \n");
				System.out.print("---------------------------------------------------------------\n");
			System.out.println("����������ѡ��");
				a= out.nextInt();
		}
		if(a==0) {
			System.out.print("���˳�!");
			System.exit(0);
		}
	}
}
