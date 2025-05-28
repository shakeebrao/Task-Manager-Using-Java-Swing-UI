import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.net.URI;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.ArrayList;
import javax.swing.*;
import java.awt.*;
import org.jdatepicker.impl.DateComponentFormatter;
import org.jdatepicker.impl.UtilDateModel;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import org.jdatepicker.impl.JDatePanelImpl;
import org.jdatepicker.impl.JDatePickerImpl;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.text.ParseException;
//real code starts from line 119
//task class is a console based shit that i did not used, it is there just like disclaimer warnings.
class Task{
    public String title;
    public String description;
    public String dueDate;
    public String priority;
    public String status;
    Scanner input=new Scanner(System.in);
    public void titleTask(String title){
        //System.out.print("ENTER TITLE OF YOUR TASK: ");
        this.title=title;
    }
    public void descriptionTask(String description){
        //System.out.print("ENTER DESCRIPTION ABOUT HOW YOU ARE GOING TO DO YOUR TASKS: ");
        this.description=description;
    }
    public void due(String dueDate){
       // System.out.print("Select your Duedate: ");
        this.dueDate=dueDate;
    }
    public void taskState(){
        String[]taskStatus={"active","done"};
       // System.out.print("Set task as active or done: ");
        status=input.next();
        String ans=status.toLowerCase();
        if(ans.equals(taskStatus[0])){
           // System.out.println("TASK IS IN YOUR LIST NOW.");

        }
        else if(ans.equals(taskStatus[1])){
           // System.out.println("YOUR TASK HAS BEEN DONE AND SO REMOVED FROM YOUR LIST.");
        }
        else{
          //  System.out.println("INCORRECT INPUT.");
        }
    }
    public void priorityTask(String priority){
       // System.out.print("Set priority of task to Low,medium or high: ");
        this.priority=priority;
    }

}
class WorkTask extends Task{
    public String officeName;
    public String head;
    public String typeWork;
    WorkTask(){
        //System.out.print("ENTER THE NAME OF YOUR WORKSPACE: ");
        officeName=input.nextLine();
        //System.out.print("ENTER NAME OF WORK SUPERVISOR: ");
        head=input.nextLine();
        //System.out.print("SELECT TYPE OF WORK WHICH IS ASSIGNED TO YOU FROM THE LIST GIVEN BELOW");
        //String[]listWork={"1: Freelancing\n","2: Video Editing\n","3: Programming Using Python\n","4: Programming regarding Web\n","5: Database handling\n","6: Documentation\n"};
        ArrayList<String> listOfWork=new ArrayList<>();
        listOfWork.add("freelancing");
        listOfWork.add("video editing");
        listOfWork.add("programming using python");
        listOfWork.add("programming regarding web");
        listOfWork.add("database handling");
        listOfWork.add("documentation");
        typeWork=input.nextLine();
        if(listOfWork.contains(typeWork)){
          //  System.out.println(typeWork+" has been added");
        }
        else{
            //System.out.print("Kindly write your own type of work: ");
            typeWork=input.nextLine();
            //System.out.print(typeWork + "has been added");
        }

    }
}
class personalTask extends Task{
    public String taskType;
    personalTask(String type,String title,String description,String dueDate){
        taskType=type;
        titleTask(title);
        descriptionTask(description);
        due(dueDate);
    }
}
class RecurringTask extends Task{
    public String environment;
    public int duration;

    RecurringTask() {
        System.out.print("Task is Commercial or Domestic: ");
        environment = input.nextLine();
        System.out.print("Enter duration of your task in Days: ");
        duration = input.nextInt();
        taskState();
    }
}
//real code begins here
class Graph extends JFrame{
    public static void buttonModifier(JButton one){
        one.setBorder(BorderFactory.createLineBorder(Color.darkGray,3,true));
        one.setBackground(Color.white);
    }
    public static JLabel iconLabel(String iconPath,String link){
        ImageIcon newIcon=new ImageIcon(iconPath);
        Image icon=newIcon.getImage();
        Image revised=icon.getScaledInstance(40,40,Image.SCALE_SMOOTH);
        ImageIcon realIcon=new ImageIcon(revised);
        JLabel viewIcon=new JLabel(realIcon);
        viewIcon.setCursor(new Cursor(Cursor.HAND_CURSOR));
        viewIcon.setToolTipText(link);
        viewIcon.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                try{
                    Desktop.getDesktop().browse(new URI(link));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
        return viewIcon;
    }
    public Graph(){
        setTitle("TASK MANAGER");
        setSize(1080,720);
        setLayout(new BorderLayout());
        CardLayout changes=new CardLayout();
        JPanel pageSwitch=new JPanel(changes);
        JPanel backgroundImg=new JPanel(){
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                ImageIcon newBg=new ImageIcon("C:\\Users\\shake\\IdeaProjects\\heloworld\\src\\black-and-yellow-wallpaper-ifgw4o2oi6blbk2t-2179867199.jpg");
                Image takeImg=newBg.getImage();
                g.drawImage(takeImg,0,0,getWidth(),getHeight(),this);
            }
        };
        backgroundImg.setLayout(new GridBagLayout());
        add(backgroundImg);
        pageSwitch.setOpaque(false);
        GridBagConstraints pageConstraints = new GridBagConstraints();
        pageConstraints.gridx = 0;
        pageConstraints.gridy = 1;
        pageConstraints.weightx = 1.0;
        pageConstraints.weighty = 1.0;
        pageConstraints.fill = GridBagConstraints.BOTH; // Ensure full expansion
        //bar
        GridBagConstraints barSpace=new GridBagConstraints();
        barSpace.gridx=0;
        barSpace.gridy=0;
        barSpace.weighty=0;
        barSpace.weightx=1;
        barSpace.fill=GridBagConstraints.HORIZONTAL;
        barSpace.anchor=GridBagConstraints.NORTH;
        //add task
        GridBagConstraints labelGbc=new GridBagConstraints();
        labelGbc.gridx=0;
        labelGbc.gridy=1;
        labelGbc.anchor=GridBagConstraints.CENTER;
        labelGbc.insets=new Insets(0,355,0,0);
        //button
        GridBagConstraints gbc=new GridBagConstraints();
        gbc.gridx=0;
        gbc.gridy=1;
        gbc.anchor=GridBagConstraints.SOUTHWEST;
        gbc.weighty=0;
        gbc.weightx=0.8;
        gbc.insets=new Insets(0,10,10,0);
        //view tasks
        GridBagConstraints view=new GridBagConstraints();
        view.gridx=1;
        view.gridy=1;
        view.anchor=GridBagConstraints.SOUTHEAST;
        view.weighty=1.0;
        view.weightx=0.8;
        view.insets=new Insets(0,0,10,10);
        JLabel name=new JLabel("CLICK TO ADD TASK");
        JButton button=new JButton("NEW TASK");
        buttonModifier(button);
        JButton viewTask=new JButton("VIEW YOUR TASKS");
        buttonModifier(viewTask);
        JPanel homeInterface=new JPanel(new GridBagLayout());
        homeInterface.setOpaque(false);
        homeInterface.add(name,labelGbc);
        homeInterface.add(button,gbc);
        homeInterface.add(viewTask,view);
        button.setPreferredSize(new Dimension(135,55));
        viewTask.setPreferredSize(new Dimension(145,55));
        pageSwitch.add(homeInterface,"HOME");
        //menubar
        JPanel topBar=new JPanel(new BorderLayout());
        topBar.setPreferredSize(new Dimension(1080,50));
        topBar.setBackground(Color.darkGray);
        //left side of menubar
        ImageIcon logoImg=new ImageIcon("C:\\Users\\shake\\IdeaProjects\\heloworld\\src\\logo-removebg-preview.png");
        Image img=logoImg.getImage();
        Image revised=img.getScaledInstance(98,48,Image.SCALE_SMOOTH);
        ImageIcon resizedLogo=new ImageIcon(revised);
        JLabel logo=new JLabel(resizedLogo);
        logo.setHorizontalAlignment(JLabel.LEFT);
        logo.setBorder(BorderFactory.createEmptyBorder(3,10,3,10));
        topBar.add(logo,BorderLayout.WEST);
        logo.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                changes.show(pageSwitch,"HOME");
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                logo.setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                logo.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
            }
        });
        //right side
        JPanel rightSideMenu=new JPanel(new FlowLayout(FlowLayout.RIGHT,20,15));
        JButton aboutDev=new JButton("About");
        aboutDev.setOpaque(false);
        JButton signIn=new JButton("Sign-in");
        signIn.setOpaque(false);
        signIn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel accountInterface=new JPanel(new GridBagLayout());
                accountInterface.setOpaque(false);
                JLabel welcome=new JLabel("Welcome!");
                welcome.setForeground(Color.white);
                welcome.setFont(new Font("Impact",Font.PLAIN,35));
                GridBagConstraints welcomeCentre=new GridBagConstraints();
                welcomeCentre.gridx=0;
                welcomeCentre.gridy=0;
                welcomeCentre.anchor=GridBagConstraints.ABOVE_BASELINE;
                welcomeCentre.insets=new Insets(25,0,125,0);
                welcomeCentre.fill=GridBagConstraints.HORIZONTAL;
                accountInterface.add(welcome,welcomeCentre);
                JLabel user=new JLabel("Username:");
                JLabel pass=new JLabel("Password:");
                JTextField userName=new JTextField(25);
                JTextField passWord=new JTextField(25);
                GridBagConstraints userPosition=new GridBagConstraints();
                userPosition.gridx=0;
                userPosition.gridy=1;
                userPosition.anchor=GridBagConstraints.NORTH;
                userPosition.insets=new Insets(35,0,0,0);
                accountInterface.add(userName,userPosition);
                userPosition.gridy=1;
                userPosition.weighty=0.3;
                userPosition.insets=new Insets(0,0,0,0);
                accountInterface.add(user,userPosition);
                GridBagConstraints passPosition=new GridBagConstraints();
                passPosition.gridx=0;
                passPosition.gridy=2;
                passPosition.anchor=GridBagConstraints.NORTH;
                accountInterface.add(pass,passPosition);
                GridBagConstraints passInput=new GridBagConstraints();
                passInput.anchor=GridBagConstraints.SOUTH;
                passInput.fill=GridBagConstraints.HORIZONTAL;
                passInput.gridy=3;
                passInput.insets=new Insets(15,0,155,0);
                accountInterface.add(passWord,passInput);
                pageSwitch.add(accountInterface,"SIGN-IN");
                changes.show(pageSwitch,"SIGN-IN");
            }
        });
        rightSideMenu.add(aboutDev);
        rightSideMenu.add(signIn);
        rightSideMenu.setOpaque(false);
        topBar.add(rightSideMenu,BorderLayout.EAST);
        //About button reaction
        aboutDev.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel credits=new JPanel(new GridBagLayout());
                credits.setOpaque(false);
                JLabel info=new JLabel("Reach Out");
                info.setFont(new Font("DialogInput",Font.BOLD,12));
                info.setFont(new Font("DialogInput",Font.BOLD,12));
                info.setForeground(Color.white);
                GridBagConstraints text=new GridBagConstraints();
                text.gridy=0;
                text.insets=new Insets(15,0,15,0);
                text.anchor=GridBagConstraints.NORTH;
                text.weighty=0;
                credits.add(info,text);
                JLabel insta=iconLabel("C:\\Users\\shake\\IdeaProjects\\heloworld\\src\\1715966585instagram-lite-logo.png","https://www.instagram.com/ashleyrao2005/");
                JLabel linkedin=iconLabel("C:\\Users\\shake\\IdeaProjects\\heloworld\\src\\1715491541linkedin-logo-transparent.png","www.linkedin.com/in/muhammad-shakeeb-amin-4307951a5");
                JLabel twitter=iconLabel("C:\\Users\\shake\\IdeaProjects\\heloworld\\src\\1729449055_twitter-logo-square shape-png.png","https://x.com/Updates1Top5");
                GridBagConstraints logos=new GridBagConstraints();
                logos.gridx=0;
                logos.gridy=1;
                logos.weighty=1;
                logos.anchor=GridBagConstraints.CENTER;
                logos.insets=new Insets(20,10,20,0);
                credits.add(insta,logos);
                logos.gridx=1;
                credits.add(linkedin,logos);
                logos.gridx=2;
                credits.add(twitter,logos);
                pageSwitch.add(credits,"ABOUT");
                changes.show(pageSwitch,"ABOUT");
             }
        });
        //new task action
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GridBagConstraints task=new GridBagConstraints();
                task.gridx=0;
                task.gridy=0;
                task.weightx=0;
                task.weighty=0;
                task.fill=GridBagConstraints.HORIZONTAL;
                task.insets=new Insets(10,10,10,10);
                JPanel newTask=new JPanel(new GridBagLayout());
                newTask.setOpaque(false);
                JButton personalBtn=new JButton("PERSONAL TASK");
                JButton recurring=new JButton("RECURRING TASK");
                JButton work=new JButton("WORK TASK");
                newTask.add(personalBtn,task);
                task.gridy=1;
                newTask.add(recurring,task);
                task.gridy=2;
                newTask.add(work,task);
                pageSwitch.add(newTask,"newTask");
                changes.show(pageSwitch,"newTask");
                personalBtn.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JPanel firstWindow=new JPanel(new GridBagLayout());
                        firstWindow.setOpaque(false);
                        //for task title,task description and task priority
                        JLabel first=new JLabel("Task Title");
                        JLabel second=new JLabel("Task Description");
                        JLabel third=new JLabel("Task Priority");
                        JTextField firstTitle=new JTextField(15);
                        JTextArea secondDescription=new JTextArea(5,30);
                        //lets add a priority bar
                        JLabel priorityLabel=new JLabel("Select Priority");
                        String[]priorityChoices={"Low","High"};
                        JComboBox<String>priorityBox=new JComboBox<>(priorityChoices);
                        GridBagConstraints priorityDirect=new GridBagConstraints();
                        priorityDirect.gridx=0;
                        priorityDirect.gridy=0;
                        priorityDirect.anchor=GridBagConstraints.NORTHEAST;
                        priorityDirect.insets=new Insets(30,15,15,20);
                        firstWindow.add(priorityBox,priorityDirect);
                        priorityDirect.insets=new Insets(5,15,5,20);
                        priorityLabel.setForeground(Color.white);
                        firstWindow.add(priorityLabel,priorityDirect);
                        //for automatically go to next line when a typed word reaches textArea boundary
                        secondDescription.setLineWrap(true);
                        secondDescription.setWrapStyleWord(true);
                        //scrollbar
                        JScrollPane scroll=new JScrollPane(secondDescription);
                        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
                        secondDescription.setFont(new Font("SansSerif",Font.PLAIN,14));
                        GridBagConstraints taskScale=new GridBagConstraints();
                        taskScale.gridx=0;
                        taskScale.gridy=0;
                        taskScale.fill=GridBagConstraints.NONE;
                        taskScale.anchor=GridBagConstraints.NORTHWEST;
                        taskScale.insets=new Insets(30,10,10,10);
                        firstWindow.add(firstTitle,taskScale);
                        taskScale.insets=new Insets(5,10,10,10);
                        first.setForeground(Color.white);

                        firstWindow.add(first,taskScale);
                        //description
                        GridBagConstraints descript=new GridBagConstraints();
                        descript.gridx=0;
                        descript.gridy=0;
                        descript.fill=GridBagConstraints.NONE;
                        descript.insets=new Insets(30,0,10,10);
                        descript.anchor=GridBagConstraints.NORTH;
                        firstWindow.add(secondDescription,descript);
                        descript.insets=new Insets(5,0,10,10);
                        second.setForeground(Color.white);

                        firstWindow.add(second,descript);
                        JLabel dateLabel = new JLabel("Select Task Date:");
                        dateLabel.setForeground(Color.white);
                        GridBagConstraints localGbc = new GridBagConstraints();
                        localGbc.insets = new Insets(10, 10, 10, 10);
                        localGbc.gridx = 0;
                        localGbc.gridy = 0;
                        localGbc.weighty=1.0;
                        localGbc.fill=GridBagConstraints.NONE;
//                        localGbc.gridwidth=2;
                        firstWindow.add(Box.createVerticalGlue(),localGbc);
                        localGbc.weighty=0;
                        localGbc.gridy=1;
                        localGbc.gridx=0;
                        localGbc.insets=new Insets(0,0,40,0);
                        firstWindow.add(dateLabel, localGbc);
                        // --- Create date picker widget ---
                        UtilDateModel model = new UtilDateModel();
                        Properties p = new Properties();
                        p.put("text.today", "Today");
                        p.put("text.month", "Month");
                        p.put("text.year", "Year");
                        JDatePanelImpl datePanel = new JDatePanelImpl(model, p);
                        JDatePickerImpl datePicker = new JDatePickerImpl(datePanel, new DateComponentFormatter());
                        localGbc.gridy=2;
                        localGbc.weighty=0;
                        localGbc.insets=new Insets(0,0,55,0);

                        firstWindow.add(datePicker, localGbc);
                        JButton showDateBtn = new JButton("Submit");
                        localGbc.gridy = 3;
                        localGbc.weightx=1;
                        localGbc.anchor=GridBagConstraints.CENTER;
                        firstWindow.add(showDateBtn, localGbc);
                        showDateBtn.addActionListener(event -> {
                            Date selectedDate = (Date) datePicker.getModel().getValue();
                            String title=firstTitle.getText();
                            String description=secondDescription.getText();
                            String choicePriority=(String)priorityBox.getSelectedItem();

                            try(BufferedWriter check=new BufferedWriter(new FileWriter("tasks.txt",true))){
                                firstTitle.setText("");
                                secondDescription.setText("");
                                priorityBox.setSelectedIndex(0);
                                if (selectedDate != null) {
                                    SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
                                    SimpleDateFormat timeSdf=new SimpleDateFormat("HH:mm:ss");
                                    String formattedDate = sdf.format(selectedDate);
                                    String formattedTime=timeSdf.format(new Date());
                                    String sentence=title+"|"+description+"|"+choicePriority+"|"+formattedDate+"|"+formattedTime;
                                    check.write(sentence);
                                    check.newLine();
                                    JOptionPane.showMessageDialog(pageSwitch, "Selected Date: " + formattedDate);
                                } else {
                                    JOptionPane.showMessageDialog(pageSwitch, "Please select a date first.");
                                }
                                JOptionPane.showMessageDialog(pageSwitch,"Task Created Successfully");
                                System.out.println("hi");
                                //for notifications when task is near to 60 minutes
                                    ScheduledExecutorService alarm=Executors.newScheduledThreadPool(1);
                                System.out.println("helloe");
                                    alarm.scheduleAtFixedRate(()-> {

                                        try(BufferedReader checker=new BufferedReader(new FileReader("tasks.txt"))){

                                            String line;
                                            while((line=checker.readLine())!=null){

                                                String[]parts=line.split("\\|");
                                                if(parts.length==5){
                                                    String dateTime=parts[3].trim();
                                                    String hrMinSec=parts[4].trim();
                                                    SimpleDateFormat newTime=new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
                                                    Date finalized=newTime.parse(dateTime+" "+hrMinSec);
                                                    Date now=new Date();
                                                    long diffMilli=finalized.getTime()-now.getTime();
                                                    long min=TimeUnit.MILLISECONDS.toMinutes(diffMilli);
                                                    if(min<=60&&min>=58){
                                                        JOptionPane.showMessageDialog(pageSwitch,"REMAINDER! TASK "+title+" IS SUBJECTED TO BE COMPLETED WITHIN 1 HOUR. KINDLY HURRY");
                                                    }
                                                }
                                            }
                                        }
                                        catch (IOException | ParseException ex){
                                            ex.printStackTrace();
                                        }
                                    },0,1,TimeUnit.MINUTES);


                            }
                            catch(IOException ex){
                                ex.printStackTrace();
                                JOptionPane.showMessageDialog(pageSwitch,"Error Creating Task");
                            }
                        });
                        pageSwitch.add(firstWindow,"PERSONAL");
                        changes.show(pageSwitch,"PERSONAL");

                    }
                });
            }
        });
        //view your tasks action
        viewTask.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel listOfTask=new JPanel(new GridBagLayout());
                listOfTask.setOpaque(false);
                JTextArea displayTasks=new JTextArea(15,40);
                displayTasks.setOpaque(false);
                displayTasks.setForeground(Color.white);
                GridBagConstraints taskPosition=new GridBagConstraints();
                taskPosition.gridx=0;
                taskPosition.gridy=0;
                taskPosition.fill=GridBagConstraints.BOTH;
                taskPosition.anchor=GridBagConstraints.CENTER;
                displayTasks.setEditable(false);
                JScrollPane tasksScroll=new JScrollPane(displayTasks);
                try(BufferedReader savingTask=new BufferedReader(new FileReader("tasks.txt")))  {
                    String lines;
                    ArrayList<String[]>taskData=new ArrayList<>();
                    while((lines=savingTask.readLine())!=null){
                        String[]parts=lines.split("\\|");
                        if(parts.length==5){
                            taskData.add(parts);
                            displayTasks.append( "Title: " + parts[0] + "\n" +
                                    "Description: " + parts[1] + "\n" +
                                    "Priority: " + parts[2] + "\n" +
                                    "Date: " + parts[3] + "\n" +
                                    "Time: " + parts[4] + "\n" +
                                    "------------------------\n");
                        }
                    }
                }
                catch(IOException fileNotFound){
                    fileNotFound.printStackTrace();
                    JOptionPane.showMessageDialog(pageSwitch,"Error Finding File.");
                }
                displayTasks.setFont(new Font("Serif",Font.BOLD,30));
                listOfTask.add(displayTasks,taskPosition);
                pageSwitch.add(listOfTask,"ListOfTask");
                changes.show(pageSwitch,"ListOfTask");
            }
        });

        backgroundImg.add(pageSwitch,pageConstraints);
        backgroundImg.add(topBar,barSpace);
        setContentPane(backgroundImg);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}
public class TaskManager {
    public static void main(String[]args){
        SwingUtilities.invokeLater(Graph::new);

    }
}
