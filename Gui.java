import java.awt.*;
import javax.swing.*;
import javax.swing.JPanel;
import javax.swing.text.DefaultCaret;


public class Gui {
    public static void main(String[] args) {
        frame frames = new frame();
        frames.setVisible(true);
    }
}

class frame extends JFrame {
    private JPanel Cpanel; // เก็บ reference ของ center panel

    public frame() {
        initializeFrame();
        createPanels();
    }
    // สร้างFrame
    private void initializeFrame() {

        setTitle("My Frame");
        setExtendedState(JFrame.MAXIMIZED_BOTH);//เต็มจอ
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//กดxปิดได้
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
    }
    //เรียกPanels
    private void createPanels() {
        createCenterPanel();
        createTopPanel();
        createWestPanel();
        createSouthPanel();
        createEastPanel();
    }
    //ส่วนบน
    private void createTopPanel() {
        JPanel Tpanel = new JPanel();
        Tpanel.setLayout(new FlowLayout());
        Tpanel.setBackground(new  Color(255,245,243));
        Tpanel.setPreferredSize(new Dimension(40,50));//กำหนดขนากของส่วนบน
        add(Tpanel, BorderLayout.NORTH);
    }
    //ส่วนด้านซ้ายมือ
    private void createWestPanel() {
        JPanel Wpanel = new JPanel();
        Wpanel.setLayout(new FlowLayout());
        Wpanel.setBackground(new  Color(255,245,243));
        Wpanel.setPreferredSize(new Dimension(200,20));
        add(Wpanel, BorderLayout.WEST);//เพิ่มPanelลงในFrame
    }
    //ส่วนตาราง (ทำไม่เป็นAiเจนมา)
    private void createCenterPanel() {
        // สร้าง Custom Panel ที่วาดตารางแบบง่าย
        Cpanel = new JPanel(new FlowLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                drawSimpleGrid((Graphics2D) g);
            }
        };

        Cpanel.setBackground(new Color(255,174,188));
        Cpanel.setPreferredSize(new Dimension(1150,700));

        add(Cpanel);
    }

    // เมธอดวาดตารางแบบง่าย
    private void drawSimpleGrid(Graphics2D g2d) {
        int panelWidth = Cpanel.getWidth();
        int panelHeight = Cpanel.getHeight();

        int rows = 20;
        int cols = 25;

        int margin = 50;
        int cellWidth = (panelWidth - 2 * margin) / cols;
        int cellHeight = (panelHeight - 2 * margin) / rows;
        int startX = margin;
        int startY = margin;

        // คำนวณขนาดทั้งหมดของตาราง
        int gridWidth = cols * cellWidth;
        int gridHeight = rows * cellHeight;

        // เติมสีขาวทั้งพื้นที่ตารางด้วยการวาดสี่เหลี่ยมผืนผ้าเพียงครั้งเดียว
        g2d.setColor(Color.WHITE);
        g2d.fillRect(startX, startY, gridWidth, gridHeight);

        // ตั้งค่าสีและรูปแบบเส้น
        g2d.setColor(Color.BLACK);
        g2d.setStroke(new BasicStroke(1));

        // วาดเส้นแนวนอนและแนวตั้งด้วยลูป (ส่วนนี้ยังจำเป็นต้องใช้ลูป)
        for (int i = 0; i <= rows; i++) {
            int y = startY + i * cellHeight;
            g2d.drawLine(startX, y, startX + gridWidth, y);
        }

        for (int j = 0; j <= cols; j++) {
            int x = startX + j * cellWidth;
            g2d.drawLine(x, startY, x, startY + gridHeight);
        }
    }
    //ส่วนด้านล่าง
    private void createSouthPanel() {
        JPanel Spanel = new JPanel();
        Spanel.setLayout(new FlowLayout());
        Spanel.setBackground(new  Color(255,245,243));;
        Spanel.setPreferredSize(new Dimension(50, 80));

        //นำค่ามาจากMethod
        JButton buttonFile = createFileButton();
        JTextField textField = createTextField();
        JButton button = createOkButton();
        //เพิ่มค่าลงส่วนล่าง
        Spanel.add(buttonFile);
        Spanel.add(textField);
        Spanel.add(button);

        add(Spanel, BorderLayout.SOUTH);
    }
    //ส่วนปุ่ม รับไฟล์ลงเครื่อง
    private JButton createFileButton() {
        JButton buttonFile = new JButton("input");

        buttonFile.setPreferredSize(new Dimension(60, 30));
        buttonFile.setBackground(new Color(70,130,180));
        buttonFile.setForeground(Color.BLACK);

        buttonFile.setHorizontalAlignment(JButton.CENTER);//วางตรงกลาง
        return buttonFile;
    }
    //ส่วนเปลี่ยนค่าความลึก
    private JTextField createTextField() {
        JTextField textField = new JTextField("Adjusting Fluid Contact Depth");
        textField.setPreferredSize(new Dimension(400,30));
        textField.setBackground(Color.white);
        textField.setHorizontalAlignment(JTextField.CENTER);
        return textField;
    }
    //ุ่ป่มตกลง เมื่อป้อนข้อมูลเสร็จ
    private JButton createOkButton() {
        JButton button = new JButton("OK");
        button.setPreferredSize(new Dimension(60, 30));
        button.setBackground(new Color(160,231,229));
        button.setForeground(Color.BLACK);
        button.setHorizontalAlignment(JButton.CENTER);
        return button;
    }
    //ส่วนด้านขาวมือ
    private void createEastPanel() {
        JPanel Epanel = new JPanel();
        Epanel.setLayout(new FlowLayout(FlowLayout.CENTER,10,10));//ความห่างแต่ละช่องหลังกับความห้างแถว

        Epanel.setPreferredSize(new Dimension(200, 20));//เส้นกรอบ
        Epanel.setBackground(new  Color(255,245,243));

        //เรียกmethod
        JTextArea blueSquare = createBlueSquareTextArea();
        JTextArea Green = createGreenTextArea();
        JTextArea Red = createRedTextArea();
        JTextArea yellow = createYellowTextArea();
        //เพิ่มค่าลงในด้านขวามือ
        Epanel.add(blueSquare);
        Epanel.add(Green);
        Epanel.add(Red);
        Epanel.add(yellow);
        //เพิ่มลงในFrame
        add(Epanel, BorderLayout.EAST);
    }
    //ช่องสี่เหลี่ยมด้านข้าง (เอาใว้แสดงค่า%ของแต่ละพื้นที่)
    private JTextArea createBlueSquareTextArea() {

        JTextArea blueSquare = new JTextArea(10, 16);

        blueSquare.setText("Show the density of the gas");
        Font font=new Font("Show the density of the gas",Font.CENTER_BASELINE,11);
        blueSquare.setFont(font);
        blueSquare.setEditable(false);
        blueSquare.setBackground(Color.white);
        blueSquare.setBorder(BorderFactory.createLineBorder(new Color(74,85,104), 4));
        blueSquare.setLineWrap(true);
        blueSquare.setWrapStyleWord(true);
        return blueSquare;
    }
    //แสดง%ของแต่ละสีว่าสีไหนหมายถึงอะไร
    private JTextArea createGreenTextArea() {
        JTextArea Green = new JTextArea(3, 2);

        Green.setText(">50%");
        Font font=new Font(">50%",Font.BOLD,11);
        Green.setFont(font);
        Green.setEditable(false);
        Green.setBackground(new Color(180,248,200));
        Green.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        Green.setBounds(380, 150, 100, 60);
        return Green;
    }

    private JTextArea createRedTextArea() {
        JTextArea Red = new JTextArea(3, 3);
        Red.setText("0%");
        Red.setEditable(false);
        Red.setBackground(new Color(255,174,188));
        Red.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        Red.setBounds(175, 380, 50, 50);
        return Red;
    }

    private JTextArea createYellowTextArea() {
        JTextArea yellow = new JTextArea(3, 2);
        yellow.setText("<50%");
        yellow.setEditable(false);
        yellow.setBackground(new Color(251,231,198));
        yellow.setBorder(BorderFactory.createLineBorder(Color.WHITE, 2));
        yellow.setBounds(235, 380, 50, 50);
        return yellow;
    }
}