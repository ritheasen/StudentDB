import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class Main extends JFrame implements ActionListener {

    void liveUpdate (String query){
        try{
            String searchQuery = (query);
            JOptionPane.showMessageDialog(null,"Search");
            rs = stmt.executeQuery(searchQuery);

            model = new DefaultTableModel();
            String[] columns = {"Student ID", "Student Name", "Gender", "Hobby"};
            model.setColumnIdentifiers(columns);
            table.setModel(model);
            String studentID ,studentName ,studentGender ,studentHobby;

            while(rs.next()) {
                studentID = rs.getString(1);
                studentName = rs.getString(2);
                studentGender = rs.getString(3);
                studentHobby = rs.getString(4);
                model.addRow(new Object[]{studentID, studentName, studentGender, studentHobby});
            }
        }catch (Exception g){
            g.printStackTrace();
        }
    }
    static JTable table;
    static DefaultTableModel model;

    static JScrollPane scroll;

    static JLabel lbStudentID, lbStudentName, lbStudentGender, lbStudentHobby;

    static JTextField txtStudentID , txtStudentName, txtStudentGender, txtStudentHobby, txtSearch;
    static JButton btnAdd, btnDelete, btnUpdate, btnSearch;

    static Connection con;
    static Statement stmt;
    static ResultSet rs;

    public static void main(String[] args) {

//        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
//        int width = (int)dim.getWidth();
//        int height = (int) dim.getHeight();

        Main fram = new Main();
        table = new JTable();
        model = new DefaultTableModel();
        scroll = new JScrollPane(table);

        String[] cols = {"Student ID", "Student Name", "Gender", "Hobby"};
        model.setColumnIdentifiers(cols);
        table.setModel(model);


//        man.fram = new JFrame("List of Student");


//        man.table = new JTable();
//        man.model = new DefaultTableModel();
//        man.model.setColumnIdentifiers(cols);
//        man.scroll = new JScrollPane(man.table);
//        man.table.setModel(man.model);

        // setup label
        lbStudentID = new JLabel("Student ID");
        lbStudentName = new JLabel("Student Name");
        lbStudentGender = new JLabel("Gender");
        lbStudentHobby = new JLabel("Hobby");
        //setup textfield
        txtStudentID = new JTextField();
        txtStudentName = new JTextField();
        txtStudentGender = new JTextField();
        txtStudentHobby = new JTextField();
        txtSearch = new JTextField();
        //setup button
        btnAdd = new JButton("Add");
        btnDelete = new JButton("Delete");
        btnUpdate = new JButton("Update");
        btnSearch = new JButton("Search");

        //position & size label
        lbStudentID.setBounds(50,100,100,50);
        lbStudentName.setBounds(50,200,100,50);
        lbStudentGender.setBounds(50,300,100,50);
        lbStudentHobby.setBounds(50,400,100,50);

        //position & size textfield
        txtStudentID.setBounds(150,100,100,50);
        txtStudentName.setBounds(150,200,100,50);
        txtStudentGender.setBounds(150,300,100,50);
        txtStudentHobby.setBounds(150,400,100,50);
        txtSearch.setBounds(50,500,400,50);
        //position & size button
        btnAdd.setBounds(100,600,100,50);
        btnDelete.setBounds(250,600,100,50);
        btnUpdate.setBounds(400,600,100,50);
        btnSearch.setBounds(500,500,100,50);
        //position & size scroll
        scroll.setBounds(300,100,400,350);

        fram.setLayout(null); //important

        // ADD element on form
        fram.add(txtStudentID);
        fram.add(txtStudentName);
        fram.add(txtStudentGender);
        fram.add(txtStudentHobby);
        fram.add(txtSearch);

        fram.add(btnAdd);
        fram.add(btnDelete);
        fram.add(btnUpdate);
        fram.add(btnSearch);

        fram.add(lbStudentID);
        fram.add(lbStudentName);
        fram.add(lbStudentGender);
        fram.add(lbStudentHobby);

        fram.add(scroll);
        //Setting frame
        fram.setSize(800,800);
        fram.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        fram.setResizable(false);
        fram.setVisible(true);

        //ActionListener
        btnAdd.addActionListener(fram);
        btnDelete.addActionListener(fram);
        btnUpdate.addActionListener(fram);
        btnSearch.addActionListener(fram);

        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost:3307/studeng_class","root","");
            stmt = con.createStatement();
            rs = stmt.executeQuery("select * from class10");
            String studentID ,studentName ,studentGender ,studentHobby;

            while(rs.next()) {
                studentID = rs.getString(1);
                studentName = rs.getString(2);
                studentGender = rs.getString(3);
                studentHobby = rs.getString(4);
                model.addRow(new Object[]{studentID, studentName,studentGender ,studentHobby});
            }
        }
            catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        String student_id = txtStudentID.getText();
        String student_name = txtStudentName.getText();
        String student_gender = txtStudentGender.getText();
        String student_hobby = txtStudentHobby.getText();
        String search = txtSearch.getText();
        String studentID ,studentName ,studentGender ,studentHobby;


        //add
        if (e.getSource() == btnAdd){
            try{
                stmt.execute("INSERT INTO class10(student_id, student_name, student_gender, student_hobby) VALUES ('"+student_id+"','"+student_name+"','"+student_gender+"','"+student_hobby+"')");
                JOptionPane.showMessageDialog(null,"HEllo KIT Saved");
                rs = stmt.executeQuery("select * from class10");

                model = new DefaultTableModel();
                String[] columns = {"Student ID", "Student Name", "Gender", "Hobby"};
                model.setColumnIdentifiers(columns);
                table.setModel(model);

                while(rs.next()) {
                    studentID = rs.getString(1);
                    studentName = rs.getString(2);
                    studentGender = rs.getString(3);
                    studentHobby = rs.getString(4);
                    model.addRow(new Object[]{studentID, studentName, studentGender, studentHobby});
                }
            }catch (Exception g){
                g.printStackTrace();
            }
        }
        //delete
        else if (e.getSource() == btnDelete){
            try{
                stmt.execute("DELETE FROM class10 WHERE (student_id = '" + search + "') OR (student_name = '" + search + "')");
                JOptionPane.showMessageDialog(null,"Delete");
                rs = stmt.executeQuery("select * from class10");

                model = new DefaultTableModel();
                String[] columns = {"Student ID", "Student Name", "Gender", "Hobby"};
                model.setColumnIdentifiers(columns);
                table.setModel(model);

                while(rs.next()) {
                    studentID = rs.getString(1);
                    studentName = rs.getString(2);
                    studentGender = rs.getString(3);
                    studentHobby = rs.getString(4);
                    model.addRow(new Object[]{studentID, studentName, studentGender, studentHobby});
                }
            }catch (Exception g){
                g.printStackTrace();
            }
        }
        //update
        else if (e.getSource() == btnUpdate){
            try{
                stmt.execute("UPDATE class10 SET student_id = '"+student_id+"',student_name = '"+student_name+"',student_gender = '"+student_gender+"',student_hobby = '"+student_hobby+"' WHERE (student_id = '"+search+"') OR (student_name = '" + search + "')");
                JOptionPane.showMessageDialog(null,"Update");
                rs = stmt.executeQuery("select * from class10");

                model = new DefaultTableModel();
                String[] columns = {"Student ID", "Student Name", "Gender", "Hobby"};
                model.setColumnIdentifiers(columns);
                table.setModel(model);

                while(rs.next()) {
                    studentID = rs.getString(1);
                    studentName = rs.getString(2);
                    studentGender = rs.getString(3);
                    studentHobby = rs.getString(4);
                    model.addRow(new Object[]{studentID, studentName, studentGender, studentHobby});
                }
            }catch (Exception g){
                g.printStackTrace();
            }
        }
        //search
        else if (e.getSource() == btnSearch){
            liveUpdate("SELECT * FROM `class10` WHERE student_name LIKE '"+search+"%'");
//            try{
//                String searchQuery = ("SELECT * FROM `class10` WHERE student_name LIKE '"+search+"%'");
//                JOptionPane.showMessageDialog(null,"Search");
//                rs = stmt.executeQuery(searchQuery);
//
//                model = new DefaultTableModel();
//                String[] columns = {"Student ID", "Student Name", "Gender", "Hobby"};
//                model.setColumnIdentifiers(columns);
//                table.setModel(model);
//
//                while(rs.next()) {
//                    studentID = rs.getString(1);
//                    studentName = rs.getString(2);
//                    studentGender = rs.getString(3);
//                    studentHobby = rs.getString(4);
//                    model.addRow(new Object[]{studentID, studentName, studentGender, studentHobby});
//                }
//            }catch (Exception g){
//                g.printStackTrace();
//            }
        }

    }
}