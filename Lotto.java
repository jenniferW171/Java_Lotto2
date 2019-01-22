import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.LayoutManager;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.NavigableSet;
import java.util.TreeSet;

//import javax.swing.ImageIcon;
//import javax.swing.JFrame;
//import javax.swing.JLabel;
//import javax.swing.JOptionPane;
//import javax.swing.JPanel;
//import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
//import javax.swing.JTable;
//import javax.swing.JTextField;
//import javax.swing.JButton;
//import javax.swing.JCheckBox;
import javax.swing.*;

import java.awt.Cursor;

public class Lotto extends JFrame {
	
	Integer intTemp[]= new Integer[6];
	
    JPanel contentPane = null, imagePanel = null;
    JLabel lblCh1 = null, lblCh2 = null, lblCh3 = null, lblCh4 = null,
    	   lblCh1_1= null, lblCh2_1 = null, lblCh3_1 = null, 
    	   lblCh4_1 = null, lblCh4_2 = null, lblCh4_3 = null, 
    	   lblBg = null, lblgo = null,
    	   lblCh1_0= null, lblCh2_0 = null, lblCh3_0 = null;
    ImageIcon background = null;
    JRadioButton rbtnCh1 = null, /*rbtnCh2 = null ,*/ rbtnCh3 = null,
    		     rbtnCh4 = null;
    JTextField txtCh1_1 = null, txtCh1_2 = null, txtCh1_3 = null,
    		   txtCh1_4 = null, txtCh1_5 = null, txtCh1_6 = null,
    		   txtCh2 = null, txtCh3 = null, 
    		   txtCh4_1 = null, txtCh4_2 = null;
    JButton btn1 = null, btn1check = null, btn2 = null, btn2check = null,
    		/*btn3 = null, */btn3check = null, btn4 = null, btn4check = null,
    		btnLottery = null, btn1Clear = null, btn3Clear = null;   
    JButton btn2Lotto[] = new JButton[49];
    JTextField txtCh1[] = new JTextField[6];
    JTextField txtLotto[] = new JTextField[6];
   
    boolean ischeck_R1 = false, ischeck_R2 = false, ischeck_R3 = false,
    		ischeck_R4 = false;    
    int[] rdmAll = new int[49];
    int[] arrayChoice; //電腦選號
    int[] arrayNum = new int[6]; //投注區每筆資料
    ArrayList<int[]> listLottery = new ArrayList<int[]>();
    
    
    
    int lotto_sp;/*半電腦特別號*/
	int btnSelf = 0; //部份電腦選號~自選號的數量 
	boolean btnchiocebool = false;
	String strlblCh="";
	TreeSet<Integer> lottoset0 = new TreeSet<Integer>(); //rdmCh2的TreeSet
	TreeSet<Integer> lottoset1 = new TreeSet<Integer>(); //Ch1的TreeSet
	TreeSet<Integer> lottoset3 = new TreeSet<Integer>(); //開獎的TreeSet 
	TreeSet<Integer> lottosetTemp = new TreeSet<Integer>(); //暫存開獎數,對獎用		
	int lottoset3_1 = 0; //開獎的特別號
	JTextArea textArea = new JTextArea();
	int inttxtCh1[] = new int[6];
	ArrayList<Integer> lottoMoney_Num = new ArrayList<Integer>(); //中獎組號碼
	ArrayList<Integer> groupNum = new ArrayList<Integer>(); //中獎組號碼
	int group =0; //記錄組別
	int lottoMoney_Money_Count = 0;
	ArrayList<Integer> lottoMoney_Money = new ArrayList<>(); //對應中獎組別的獎金
	ArrayList<Integer> lottoMoney_SP = new ArrayList<>(); //對應中獎組別是否有特別號,0=否/1=是
	
	JScrollPane myscro = new JScrollPane(textArea);
	
	ArrayList<List> al = new ArrayList<List>();
	ArrayList<Integer> MoneyTemp = new ArrayList<Integer>();  //暫存有中的號碼用,每個組會重新歸零
	boolean isSP = false; //判斷是否有中特別號
	int intYes = 0; //記錄第幾筆獎金
	String strYes = "";
	ArrayList<ArrayList> ListlottoMoney = new ArrayList<ArrayList>();
    
    public Lotto(){
        // 1.設置frame title及Layout之類型
        this.setTitle("黑板上的大樂透");
        getContentPane().setLayout(new BorderLayout());           
        
        Ch1(); //自選+電腦
        Ch3(); //投注組數
        Ch4(); //開獎              
        
        this.getContentPane().add(contentPane, BorderLayout.CENTER);   

        // 3.於JFrame中設置背景圖片 - 圖片無法縮放大小
        background = new ImageIcon((Lotto.class.getResource("/HWimg/bg.jpg")));       // 背景圖片
        lblBg = new JLabel(background);      // 把背景圖顯示在Label中
        lblBg.setBounds(0, 0, background.getIconWidth(), background.getIconHeight());    // 把含有背景圖之Label位置設置為圖片剛好填充整個版面
        // 把内容視窗轉為JPanel，否則不能使用setOpaque()來使視窗變成透明
        imagePanel = (JPanel) this.getContentPane();
        imagePanel.setOpaque(false);
        this.getLayeredPane().add(lblBg, new Integer(Integer.MIN_VALUE));     // 把背景圖添加到分層窗格的最底層以作為背景
        
        // 4.設置frame之基本設定
        this.setMinimumSize(new java.awt.Dimension(1060, 810));
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }
    
    public void Ch4() {
    	//2.4.1 ch4       
        lblCh4 = new JLabel("Chapter 3");
        lblCh4.setBounds(560, 400, 150, 100);        // 設置位置跟寬高
        lblCh4.setForeground(Color.WHITE);
        lblCh4.setFont(new Font("consolas", Font.BOLD, 26));
        contentPane.add(lblCh4);        
      //2.4.2        
        lblCh3_0 = new JLabel("--開獎 & 對獎");
        lblCh3_0.setBounds(710, 400, 220, 100);
        lblCh3_0.setForeground(Color.WHITE);
        lblCh3_0.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
        contentPane.add(lblCh3_0);
      //2.4.3
        lblCh4_1 = new JLabel("開獎號碼：");
      	lblCh4_1.setBounds(560, 480, 180, 30);        // 設置位置跟寬高
      	lblCh4_1.setForeground(Color.WHITE);
      	lblCh4_1.setFont(new Font("微軟正黑體", Font.BOLD, 22));
        contentPane.add(lblCh4_1);        
        
        //動態textfield
        for(int i=0; i<6; i++){
      	  txtLotto[i] = new JTextField(30);
      	  txtLotto[i].setOpaque(false); //背景為透明        
      	  txtLotto[i].setBounds(670+(i*45), 480, 40, 40);
      	  txtLotto[i].setForeground(Color.WHITE);
      	  txtLotto[i].setFont(new Font("微軟正黑體", Font.BOLD, 20));
      	  txtLotto[i].setText("");
      	  txtLotto[i].setBorder(BorderFactory.createCompoundBorder(
      		txtLotto[i].getBorder(), BorderFactory.createEmptyBorder(3, 5, 3, 3)));
            contentPane.add(txtLotto[i]);
        }      
     
        lblCh4_2 = new JLabel(" + ");
        lblCh4_2.setBounds(935, 480, 35, 40);        // 設置位置跟寬高
        lblCh4_2.setForeground(Color.WHITE);
        lblCh4_2.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        contentPane.add(lblCh4_2);    
        
        txtCh4_1 = new JTextField(10);
        txtCh4_1.setOpaque(false); //背景為透明        
        txtCh4_1.setBounds(960, 480, 40, 40);
        txtCh4_1.setForeground(Color.WHITE);
        txtCh4_1.setFont(new Font("微軟正黑體", Font.BOLD, 20));
        txtCh4_1.setText("");
        txtCh4_1.setBorder(BorderFactory.createCompoundBorder(
        		txtCh4_1.getBorder(), BorderFactory.createEmptyBorder(3, 5, 3, 3)));
        contentPane.add(txtCh4_1);
      //2.4.4 兌獎結果
        lblgo = new JLabel(" ← 按下圖案即開獎 ");
        lblgo.setOpaque(false); //背景為透明        
        lblgo.setBounds(779, 558, 230, 30);
        lblgo.setForeground(new Color(255,217,130));
        lblgo.setFont(new Font("微軟正黑體", Font.BOLD, 26));
        contentPane.add(lblgo);

//抽獎按鈕        
        btnLottery = new JButton("開獎");
        lottoset3.clear();
        btnLottery.setOpaque(false); //背景為透明  
        btnLottery.setIcon(new ImageIcon((Lotto.class.getResource("/HWimg/Lottery.jpg"))));
        btnLottery.setBounds(565, 533, 155, 158);
        btnLottery.setFont(new Font("微軟正黑體", Font.BOLD, 18));
        contentPane.add(btnLottery);
        btnLottery.addActionListener(new ActionListener(){
  			public void actionPerformed(ActionEvent e){
  				lottoset3.clear();
  				rdmCh2(0,4);    
  				lottoMoney();
  				if(lottoMoney_Money_Count>0) {
  					strYes = "太棒了！恭喜您中獎了！\n";
  					strYes += "您共有" + lottoMoney_Money_Count + "組中獎！\n\n";  					
  				//取list中單筆array的個別值
  			 		//ArrayList<Integer> arrayTemp; 
  			 		Iterator<ArrayList> listIt = ListlottoMoney.iterator();
  			 		int z = 0; //迭代器暫存用  			 		
  			 		while (listIt.hasNext()) {   
  			 			ArrayList<Integer> arrayTemp = new ArrayList<>();
  			 			arrayTemp = listIt.next(); //'0' is the index
  			 			strYes += "→第" + groupNum.get(z) + "組：";
  			 			for (int aNumber : arrayTemp ) {
  			 				strYes += String.valueOf(aNumber) + ". "; //取第z筆陣列的每個值
  			 			}
  			 			if(lottoMoney_SP.get(z)==1) {
  			 				strYes += "(含特別號 " + lottoset3_1 + " )\n";
  			 			}
  			 			strYes += "　獎金: " + lottoMoney_Money.get(z) + " 元。\n";
  			 			z++;
  			 		}			 		
  				}
  				else { strYes = "唉呀～～太可惜了都沒有中呢，\n請再接再勵!繼續加油喔！"; } 
  				JOptionPane.showMessageDialog(null, strYes);
  			}});	
	}

	public void Ch3() {
    	//2.3.1 ch3
      lblCh3 = new JLabel("Chapter 2");
      lblCh3.setBounds(560, 20, 150, 100);        // 設置位置跟寬高
      lblCh3.setForeground(Color.WHITE);
		lblCh3.setFont(new Font("consolas", Font.BOLD, 26));
      contentPane.add(lblCh3);        
      //2.1.2
      lblCh2_1 = new JLabel("--投注號組");
      lblCh2_1.setBounds(710, 20, 120, 100);
      lblCh2_1.setForeground(Color.WHITE);
      lblCh2_1.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
      contentPane.add(lblCh2_1); 
      
      myscro.setViewportView(textArea);
      myscro.setBounds(560, 100, 420, 210);
      myscro.setEnabled(false);
      contentPane.add(myscro);
      myscro.setViewportView(textArea);
      textArea.setBorder(BorderFactory.createCompoundBorder(
    		  textArea.getBorder(), BorderFactory.createEmptyBorder(3, 10, 0, 0)));
      textArea.setFont(new Font("微軟正黑體", Font.BOLD, 22));
      textArea.setEnabled(false);
      textArea.setBackground(Color.BLACK);
      textArea.setText("（目前未有任何投注）");
      
      btn3Clear = new JButton("清空投注資料");
      btn3Clear.setOpaque(false); //背景為透明        
      btn3Clear.setBounds(800, 320, 180, 30);
      btn3Clear.setFont(new Font("微軟正黑體", Font.BOLD, 22));
      contentPane.add(btn3Clear);
      btn3Clear.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				 textArea.setText("（目前未有任何投注）");
				 listLottery.clear();
			}
      }); 
			
      
	}

	public void Ch1() {
    	// 2.元件       
        //2.1.1 ch1     
          contentPane = new JPanel();
          contentPane.setLayout(null);
          contentPane.setOpaque(false);      
          lblCh1 = new JLabel("Chapter 1");
          lblCh1.setBounds(45, 20, 150, 100);        // 設置位置跟寬高
          lblCh1.setForeground(Color.WHITE);
          lblCh1.setFont(new Font("consolas", Font.BOLD, 26));
          contentPane.add(lblCh1);        
        //2.1.2 選擇
          lblCh1_1 = new JLabel("--投注");
          lblCh1_1.setBounds(205, 20, 230, 100);
          lblCh1_1.setForeground(Color.WHITE);
          lblCh1_1.setFont(new Font("微軟正黑體", Font.PLAIN, 22));
          contentPane.add(lblCh1_1);
          
         //加入動態button          
          int x2 = 0;
          for(int i=0; i<7; i++){
          	for(int j=0; j<7; j++) {
          	btn2Lotto[x2] = new JButton();
          	btn2Lotto[x2].setSize(55, 40);
          	btn2Lotto[x2].setBackground(new Color(248, 231, 237));
          	btn2Lotto[x2].setForeground(Color.BLUE);
          	btn2Lotto[x2].setFont(new Font("微軟正黑體",Font.BOLD,16));
          	btn2Lotto[x2].setText(Integer.toString(x2+1));
          	btn2Lotto[x2].setBounds(50+(j*65), 110+(i*50), 55, 45);
         		contentPane.add(btn2Lotto[x2]);	      			
          	String strMsg = String.valueOf(x2+1);
          	btn2Lotto[x2].addActionListener(new ActionListener(){
          		public void actionPerformed(ActionEvent e){         			
          			for(int i=0; i<49; i++) {
    					if(e.getSource()==btn2Lotto[i]) {   
    						//if(btn2Lotto[i].getText())
    						
    						
    						if((btn2Lotto[i].getForeground()==Color.BLUE) && lottoset1.size()<6) {    						
    							btn2Lotto[i].setBackground(new Color(255,158,159));
    							btn2Lotto[i].setForeground(Color.WHITE);
    							if(lottoset1.size()<6)
    								lottoset1.add(i+1);
    							else
    								JOptionPane.showMessageDialog(null, "您已選取六個號碼");    								
    						}
    						else if(btn2Lotto[i].getForeground()==Color.WHITE) {
    							btn2Lotto[i].setBackground(new Color(248, 231, 237));
    							btn2Lotto[i].setForeground(Color.BLUE);
    							lottoset1.remove(i+1);
    							btn1.setEnabled(true);
    						}						
    					}
    				}
    				if(lottoset1.size()<7) {
    					txtCh1Clear();
    					Iterator it =lottoset1.iterator();
    					int z = 0, zzz=0;
    					while (it.hasNext()) {  
    						inttxtCh1[z] = (int) it.next();
    						txtCh1[z].setText(String.valueOf(inttxtCh1[z]));
    						z++;
    					}	    					   
    				}        			
          		}				
          	});
          	x2++;   		
          }}
          
          
          
          Action action = new AbstractAction()
          {
              @Override
              public void actionPerformed(ActionEvent e)
              {
            	  TreeSet<Integer> xxx = new TreeSet<Integer>(); //rdmCh2的TreeSet
            	  Integer aa = null;
            	  for(int i=0; i<6; i++) {
            		 
            		  /*try{
            			 
    					  Integer.parseInt(txtCh1[i].getText(),aa);
    				  }catch(Exception xx) {
    					  JOptionPane.showMessageDialog(null, "非數字");
    					  break;
    				  } */      
            		  
            		  
            		  
            		  try {
            			  aa=Integer.valueOf(txtCh1[i].getText());            			       			  
            			  try {          				  
                			  if((aa>=0) && (aa<=50)) {
                				  aa=Integer.valueOf(txtCh1[i].getText());
                				  btn2Lotto[(aa-1)].setBackground(new Color(255,158,159));
                				  btn2Lotto[(aa-1)].setForeground(Color.WHITE);
                				  if(lottoset1.size()<6) {
                					  if(xxx.add((aa-1)))
                						  lottoset1.add((aa-1));
                					  else {       						  
                						  JOptionPane.showMessageDialog(null, (aa) + " ← 這個號碼您已輸入過，\n請重新輸入，謝謝！");
                						  txtCh1[i].setText("");
                					  }       					  
                				  }
                			  }
            			  }     	
                			  catch(Exception e3){
                				  JOptionPane.showMessageDialog(null, "您輸入的值【" + aa + "】不在 1~49 的範圍內喔！\n請重新輪入，謝謝！");
                				  txtCh1[i].setText("");
                			  }           			   
            			  
            		  }
            		  catch(Exception e2){ }
            	  }            			
              }
            		  
           };
          
          //動態textfield
          for(int i=0; i<6; i++){
        	  txtCh1[i] = new JTextField(30);
        	  txtCh1[i].setOpaque(false); //背景為透明        
        	  txtCh1[i].setBounds(160+(i*42), 550, 37, 50);
        	  txtCh1[i].setForeground(Color.WHITE);
        	  txtCh1[i].setFont(new Font("微軟正黑體", Font.BOLD, 18));
        	  txtCh1[i].setText("");
        	  txtCh1[i].setBorder(BorderFactory.createCompoundBorder(
        	  txtCh1[i].getBorder(), BorderFactory.createEmptyBorder(3, 5, 3, 3)));       	  
        	  
        	  txtCh1[i].addActionListener( action );
        	  
        	  
              contentPane.add(txtCh1[i]);
          }           
          
          btn1check = new JButton("投注");
          btn1check.setOpaque(false); //背景為透明        
          btn1check.setBounds(420, 550, 75, 50);
          //txtCh1.setForeground(Color.WHITE);
          btn1check.setFont(new Font("微軟正黑體", Font.BOLD, 18));
          //txtCh1Clear();
        
          
          //2.1.3
          btn1 = new JButton("快速電腦選號（含自選號補齊）");
          btn1.setOpaque(false); //背景為透明        
          btn1.setBounds(50, 480, 360, 50);
          //txtCh1.setForegroud(Color.WHITE);
          btn1.setFont(new Font("微軟正黑體", Font.BOLD, 22));             	  
          contentPane.add(btn1);
          btn1.addActionListener(new ActionListener(){
          	public void actionPerformed(ActionEvent e){         		
				btnSelf = lottoset1.size();
				//for(int n = 0; n<btn2Lotto.length; n++) {
					//btn2Lotto[n].setEnabled(false);
				//}
				if(btnSelf<6) {
					rdmCh2(btnSelf,1);
				}
				else
					JOptionPane.showMessageDialog(null, "您已選擇六個號碼了喔！\n請按下「重新」按鍵重新投注，"
							+ "\n或點選部份已投注的號碼進行更改"); 
       		} 				
       	});          
          btn1Clear = new JButton("重選");
          btn1Clear.setOpaque(false); //背景為透明        
          btn1Clear.setBounds(420, 480, 75, 50);
          //txtCh1.setForegroud(Color.WHITE);
          btn1Clear.setFont(new Font("微軟正黑體", Font.BOLD, 18));
          contentPane.add(btn1Clear);
          btn1Clear.addActionListener(new ActionListener(){
          	public void actionPerformed(ActionEvent e){
          		txtCh1Clear();
          		btn1.setEnabled(true);
				btnSelf = lottoset1.size();
				lottoset1.clear();
				for(int n = 0; n<btn2Lotto.length; n++) {
					btn2Lotto[n].setBackground(new Color(248, 231, 237));
		          	btn2Lotto[n].setForeground(Color.BLUE);
		          	btn2Lotto[n].setEnabled(true);
				}
				txtCh1Clear();
       		} 				
       	});    
          
          //2.1.4 txt
          lblCh1_1 = new JLabel("投注號碼：");
          lblCh1_1.setBounds(50, 550, 180, 50);        // 設置位置跟寬高
          lblCh1_1.setForeground(Color.WHITE);
          lblCh1_1.setFont(new Font("微軟正黑體", Font.BOLD, 22));
          contentPane.add(lblCh1_1);       
          
         /* //動態textfield
          for(int i=0; i<6; i++){
        	  txtCh1[i] = new JTextField(30);
        	  txtCh1[i].setOpaque(false); //背景為透明        
        	  txtCh1[i].setBounds(160+(i*42), 550, 37, 50);
        	  txtCh1[i].setForeground(Color.WHITE);
        	  txtCh1[i].setFont(new Font("微軟正黑體", Font.BOLD, 18));
        	  txtCh1[i].setText("");
        	  txtCh1[i].setBorder(BorderFactory.createCompoundBorder(
        		txtCh1[i].getBorder(), BorderFactory.createEmptyBorder(3, 5, 3, 3)));
              contentPane.add(txtCh1[i]);
          }           
          
          btn1check = new JButton("投注");
          btn1check.setOpaque(false); //背景為透明        
          btn1check.setBounds(420, 550, 75, 50);
          //txtCh1.setForeground(Color.WHITE);
          btn1check.setFont(new Font("微軟正黑體", Font.BOLD, 18));
          //txtCh1Clear();
        */
          contentPane.add(btn1check);
          btn1check.addActionListener(new ActionListener() {       	  
          	public void actionPerformed(ActionEvent arg0) {         		
          		int cc = 0;
          		if(lottoset1.size()<6)
          			JOptionPane.showMessageDialog(null, "投注號碼須有 6 個方能投注"); //有六個號碼才能投注
          		else {
          		String strTemp=""; //迭代器暫存用
          		arrayNum = new int[6];
          		for(int j=0; j<arrayNum.length; j++) {
          			arrayNum[j] = Integer.valueOf(txtCh1[j].getText()); //將六個號碼存入arrayNum陣列
          			}          		   
          		listLottery.add(arrayNum);         		
       //取list中單筆array的個別值
          		int[] arrayTemp; 
          		Iterator<int[]> listIt = listLottery.iterator();
          		int z = 0; //迭代器暫存用
          		while (listIt.hasNext()) {          			
          			arrayTemp = listIt.next();
          			strTemp +="第" + (z+1) + "組：";
            	    for (int aNumber : arrayTemp ) {       	    	
            	        strTemp += String.valueOf(aNumber) + ". "; //取第z筆陣列的每個值            	        
            	    }
            	    strTemp += "\n";      
            	    textArea.setText(strTemp);   
            	    z++;
            	    
            	    
            	    for(int i=0;i<6;i++) {
              			txtCh1[i].setText("");
              		}
            	    for(int i=0;i<49;i++) {
            	    	btn2Lotto[i].setBackground(new Color(248, 231, 237));
						btn2Lotto[i].setForeground(Color.BLUE);
						//btn1.setEnabled(true);
            	    }
            	}         	
          		}         		
             }
          	
          });
          
          JLabel lblMoney = new JLabel("※本期總獎金為1,000萬元※");
          lblMoney.setBounds(100, 620, 400, 100); 
          lblMoney.setForeground(new Color(255,130,171));
          lblMoney.setFont(new Font("微軟正黑體", Font.BOLD, 26));
          contentPane.add(lblMoney);       
          
          JLabel lblPic = new JLabel();
          lblPic.setOpaque(false); //背景為透明  
          lblPic.setIcon(new ImageIcon((Lotto.class.getResource("/HWimg/pan.jpg"))));
          lblPic.setBounds(55, 680, 500, 30);
          lblPic.setFont(new Font("微軟正黑體", Font.BOLD, 18));
          contentPane.add(lblPic);
          
          
          
          }
	
	private void islottery() {
		lottosetTemp = new TreeSet<Integer>(); //暫存開獎數,對獎用		
		for(int temp : lottoset3) {
			lottosetTemp.add(temp); //將lottoset3的數copy到lottosetTemp
		}
	}
	
	private void lottoMoney() {//取list中單筆array的個別值
		islottery();  //對獎數用來比對用的code
		group=1;
		groupNum.clear();
		lottoMoney_Money_Count=0;
		ListlottoMoney = new ArrayList<ArrayList>();
		Iterator<int[]> listIt = listLottery.iterator();		
		while (listIt.hasNext()) {
			int[] arrayTemp = new int[6]; //歸零
			MoneyTemp.clear(); //歸零
			islottery();
			isSP = false;			
			arrayTemp = listIt.next();				
			for (int zz=0; zz<arrayTemp.length; zz++ ) {				
				if(arrayTemp[zz]==lottoset3_1) { 
					isSP = true;   //判斷有無對中特別號
				}
				else { 
					if(!lottosetTemp.add(arrayTemp[zz]))
						MoneyTemp.add(arrayTemp[zz]);   //第zz個對號結束
				}
			}
			if(((MoneyTemp.size()>=2) && (isSP==true)) || (MoneyTemp.size()>=3)) {
				lottoMoneyOK(); //獎金及輸出
				}
			group++;
		}
	}
		

	private void lottoMoneyOK() {	
		lottoMoney_Money_Count++; //記錄共幾筆中獎
		lottoMoney_Num = new ArrayList<Integer>();		
		for(int x:MoneyTemp) {  //存入中獎號
			lottoMoney_Num.add(x);		
		}
		groupNum.add(group); //存入中獎第幾組
		ListlottoMoney.add(lottoMoney_Num); //存入list
		if(isSP==true) {  //有特別號
			lottoMoney_SP.add(1); //存入有中特別號
			switch(MoneyTemp.size()) {
			case 2: //2(+1)
				lottoMoney_Money.add(400);	 //存入中獎金額	
				break;
			case 3: //3(+1)
				lottoMoney_Money.add(1000);  //存入中獎金額	
				break;
			case 4: //4(+1)
				lottoMoney_Money.add(450000);  //存入中獎金額	
				break;
			case 5: //5(+1)
				lottoMoney_Money.add(650000);  //存入中獎金額	
				break;
			}
		}
		else {
			lottoMoney_SP.add(0);
			switch(MoneyTemp.size()) {
			case 3: 
				lottoMoney_Money.add(400);	 //存入中獎金額	
				break;
			case 4: //
				lottoMoney_Money.add(2000);  //存入中獎金額	
				break;
			case 5: //
				lottoMoney_Money.add(700000);  //存入中獎金額	
				break;
			case 6: //
				lottoMoney_Money.add(8200000);  //存入中獎金額	
				break;
			}
		}		
	intYes++;
	}

	public void rdmCh2(int a, int b) {    //自動選號,a=己有值的數量,b=要回傳的Ch
    	//不能再選(除非點重選)btn1.setEnabled(false);
		strlblCh="";
		arrayChoice = new int[6]; //號碼陣列歸零
		//System.out.printf("a=%d\n",a);
		int intRondom=0;		
		int i=0;
		Iterator it =lottoset1.iterator();
		//Iterator it3 =lottoset3.iterator();
		int z = 0;
		if(b==1) {
			while (it.hasNext()) {
				arrayChoice[z] = (int) it.next(); //將現有的號碼加入陣列
				z++;
				}
			}
		/*else if (b==4) {
			while (it3.hasNext()) {
				arrayChoice[z] = (int) it.next(); //將現有的號碼加入陣列
				z++;
				}
		}*/
		
		i=a;		
		if(b==1) {
			while(i<6) {
				intRondom = 1+(int)(Math.random()*49);
				if(lottoset0.add(intRondom)) {				
					arrayChoice[i] = intRondom;
					btn2Lotto[intRondom-1].setBackground(new Color(255,158,159));
					btn2Lotto[intRondom-1].setForeground(Color.WHITE);
					lottoset1.add(intRondom);
					i++;
				}				
			}		
		}
		else if(b==4) {
			while(i<6) {
				intRondom = 1+(int)(Math.random()*49);
				if(lottoset0.add(intRondom)) {
					arrayChoice[i] = intRondom;
					lottoset3.add(intRondom);
					i++;
				}	
			}
			lottoset3_1 = 1+(int)(Math.random()*49);
		}
			
		Arrays.sort(arrayChoice);			
		/*for(int x=0;x<6;x++) {
			if(x==(6-1))
				strlblCh += " " + String.valueOf(arrayChoice[x]);
			else
				strlblCh += " " + String.valueOf(arrayChoice[x]) + ",";				
		}*/
				
		
			for(int j=0; j<6; j++) {
				if(b==1) 
					txtCh1[j].setText(String.valueOf(arrayChoice[j]));
				else if(b==4)
					txtLotto[j].setText(String.valueOf(arrayChoice[j]));
			}
			if(b==4)
				txtCh4_1.setText(String.valueOf(lottoset3_1));
		
		lottoset0.removeAll(lottoset0); //set歸零
}
    
	private void txtCh1Clear() {
		for(int i=0; i<6; i++) {
			txtCh1[i].setText("");
		}		
	} 
    
    public static void main(String[] args) {
        //預設總獎金1000萬    	
    	new Lotto();
    	JOptionPane.showMessageDialog(null, "手動輸入號碼時，請輸入1~49的號碼,\n輸入完畢請記得按下Enter喔！謝謝！\n"
    			+ "若是要手動輸入的話，六個號碼都要手動輸入並按下投注，\n才會順利投注喔!\n"
    			+ "若要搭配電腦選號，請直接用按鍵輸入喔！^_^");
    }
}
 

 