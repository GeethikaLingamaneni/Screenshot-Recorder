import java.util.*;  

import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
public class SendMail{  
 public static void Send(String managermailid,String Username){  
  
  //String to="prathap93g@gmail.com";//change accordingly  
  final String user="techfortdummy@gmail.com";//change accordingly  
  final String password="40062299";//change accordingly  
   
  //1) get the session object     
  Properties props = new Properties();
	props.put("mail.smtp.auth", "true");
	props.put("mail.smtp.starttls.enable", "true");
	props.put("mail.smtp.host", "smtp.gmail.com");
	props.put("mail.smtp.port", "587");
  Session session = Session.getDefaultInstance(props,  
   new javax.mail.Authenticator() {  
   protected PasswordAuthentication getPasswordAuthentication() {  
   return new PasswordAuthentication(user,password);  
   }  
  });  
  //2) compose message     
  try{  
    MimeMessage message = new MimeMessage(session);  
    message.setFrom(new InternetAddress(user));  
    message.addRecipient(Message.RecipientType.TO,new InternetAddress(managermailid));  
    message.setSubject("Message Aleart");  
      
    //3) create MimeBodyPart object and set your message text     
    BodyPart messageBodyPart1 = new MimeBodyPart();  
    messageBodyPart1.setText("This is message body");  
      
    //4) create new MimeBodyPart object and set DataHandler object to this object      
    MimeBodyPart messageBodyPart2 = new MimeBodyPart();  
  
    String filename = "D://Test/doc/"+Username+".docx";//change accordingly  
    DataSource source = new FileDataSource(filename);  
    messageBodyPart2.setDataHandler(new DataHandler(source));  
    messageBodyPart2.setFileName(filename);  
     
     
    //5) create Multipart object and add MimeBodyPart objects to this object      
    Multipart multipart = new MimeMultipart();  
    multipart.addBodyPart(messageBodyPart1);  
    multipart.addBodyPart(messageBodyPart2);  
  
    //6) set the multiplart object to the message object  
    message.setContent(multipart );  
     
    //7) send message  
    Transport.send(message);  
   
   System.out.println("message sent....");  
   }catch (MessagingException ex) {ex.printStackTrace();} }  } 

UserLoginUI.java

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.TimerTask;
import java.util.Timer;
import javax.swing.*;

import databaseconnection.databasecon;
 
public class UseLoginUI{
   private static JFrame mainFrame;
   private JLabel headerLabel;
   private JLabel statusLabel;
   private JPanel controlPanel;
   String managermailid="";
   String Username=null;
   boolean isClose = false;
   
   public UseLoginUI(){
      prepareGUI();
      //addWindowListener(this);  
   }
  /* public static void showMessage() {
       System.out.println("Bye Bye!");
   }
*/
   public static void main(String[] args){
	   UseLoginUI  swingControlDemo = new UseLoginUI();      
      swingControlDemo.showTextFieldDemo();
    //  Frame f = new Frame("Say Bye Bye!");
     

   }
   private void prepareGUI(){
      mainFrame = new JFrame("Screen Shot Reader");
      mainFrame.setSize(400,400);
      mainFrame.setLayout(new GridLayout(3, 1));
      headerLabel = new JLabel("", JLabel.CENTER);        
      statusLabel = new JLabel("",JLabel.CENTER);    
      statusLabel.setSize(350,100);
    
    	   
    	    mainFrame.setVisible(true);

      controlPanel = new JPanel();
      controlPanel.setLayout(new FlowLayout());

      mainFrame.add(headerLabel);
      mainFrame.add(controlPanel);
      mainFrame.add(statusLabel);
      mainFrame.setVisible(true); 
      mainFrame.addWindowListener(new WindowListener() {
		
		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
		System.out.println("window clsoign");	
			isClose = true;
		}
		
		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub
			
		}
      });

          /*@Override
          public void windowClosing(WindowEvent e) {
              if (JOptionPane.showConfirmDialog(mainFrame, "Are you sure you want to quit?", "Confirm exit.", JOptionPane.OK_OPTION, 0, new ImageIcon("")) != 0) {
            	  System.out.println("exit the app");
                  return;
              }
              System.exit(-1);
          }});*/

   }
   private void showTextFieldDemo(){
      headerLabel.setText("Employee Login Here"); 

      JLabel  namelabel= new JLabel("User ID: ", JLabel.RIGHT);
      JLabel  passwordLabel = new JLabel("Password: ", JLabel.CENTER);
      final JTextField userText = new JTextField(6);
      final JPasswordField passwordText = new JPasswordField(6);      

      JButton loginButton = new JButton("Login");
      /*mainFrame.addWindowListener(new WindowAdapter() {
    	  @Override
    	  public void windowClosing(WindowEvent e) {
System.out.println("closing");
SendMail send=new SendMail();
send.Send(managermailid,Username);
    	      System.exit(0);

    	  }

    	    });
*/
      loginButton.addActionListener(new ActionListener() {
         public void actionPerformed(ActionEvent e) {     
           /* String data = "Username " + userText.getText();
            data += ", Password: " + new String(passwordText.getPassword()); */
        	 Username=userText.getText();
        	 char[] Password=passwordText.getPassword();
        	 String Password1 = String.valueOf(Password);
        	 System.out.println("username is"+Username);
        	 System.out.println("password is"+Password1);
        	 Connection con=databasecon.getconnection();
        	 try {
				Statement st=con.createStatement();
				
				String Project="";
				String sql="select * from employee where username='"+Username+"' and password='"+Password1+"' and NOT(Role='Manager')";
				ResultSet rs1=st.executeQuery(sql);
				if(rs1.next()){
					Project=rs1.getString("project");
					String sql1="select empemail from employee where Role='Manager' and project='"+Project+"'";
					ResultSet rs2=st.executeQuery(sql1);
					if (rs2.next()) {
						managermailid=rs2.getString("empemail");
					}
					System.out.println("validated");
					mainFrame.setState(Frame.ICONIFIED);
					 
			    	final ScreenDoc s2i = new ScreenDoc();
			      
			            /*try {
			            	 while(!isClose)
			                 {
			                     s2i.robo(Username);
			                     System.out.println(isClose +">>>>");
			                     if(!isClose){ 
			                    	 System.out.println(isClose +"<<<<");
			                      	 Thread.sleep(10000);
			                     }
			                 }
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
			            */
			    	TimerTask task = new TimerTask() {
			    	      @Override
			    	      public void run() {
			    	        // task to run goes here
			    	    	  try {
			    	    		  if (isClose) {
			    	    			  System.out.println("window closed syso");
			    	    			  SendMail send=new SendMail();
			    	    			  send.Send(managermailid,Username);
			    	    			System.exit(0);  
								}
			    	    		  else {
			    	    			  s2i.robo(Username);
				    	    		  System.out.println("Hello !!!");
								}
			    	    		 
							} catch (Exception e2) {
								// TODO: handle exception
							};
			    	       
			    	      }
			    	    };
			    	    Timer timer = new Timer();
			    	    long delay = 0;
			    	    long intevalPeriod = 1 * 10000; 
			    	    // schedules the task to be run in an interval 
			    	    timer.scheduleAtFixedRate(task, delay,
			    	                                intevalPeriod);
					
				}
				
				else{
					JOptionPane.showMessageDialog(mainFrame, "Username or Password not Valid ", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
           // statusLabel.setText(data);  
           // mainFrame.setState(Frame.ICONIFIED);
         }
      }); 
      controlPanel.add(namelabel);
      controlPanel.add(userText);
      controlPanel.add(passwordLabel);       
      controlPanel.add(passwordText);
      controlPanel.add(loginButton);
      mainFrame.setVisible(true);  
      
      /*mainFrame.addWindowListener( new WindowAdapter() {
          @Override
          public void windowClosing(WindowEvent we) {
              showMessage();
              System.exit(0);
          }
      } );*/
     /* mainFrame.setSize(300,200);
      mainFrame.setLocationByPlatform(true);
      mainFrame.setVisible(true);*/
}}




