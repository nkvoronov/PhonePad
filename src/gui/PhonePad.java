package gui;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableModel;

import common.DBTableModel;
import common.DataModule;

@SuppressWarnings("serial")
public class PhonePad extends JFrame implements ActionListener {
	private JTable jtbMain;
	private JPanel jpnButtons;
	private JButton jbtAdd;
	private JButton jbtEdt;
	private JButton jbtDel;
	private DBTableModel dtmMain;
	private DataModule dataModule;

	public static void main(String[] args) {		
		 try {
	            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
	        } catch (ClassNotFoundException | InstantiationException | UnsupportedLookAndFeelException | IllegalAccessException e) {
	            e.printStackTrace();
	        }
	        EventQueue.invokeLater(() -> new PhonePad().setVisible(true));
	}

	public PhonePad() {
		dataModule = new DataModule("notepad.sqlite");
		dataModule.openMain();
		dtmMain = new DBTableModel();
		dtmMain.initData(dataModule.getRsMain());
		initGUI();
		setPosToCenterScreen();
	}
	
	private void initGUI() {
		setTitle(Messages.getString("TitleApp"));
        setIconImage(Toolkit.getDefaultToolkit().getImage(PhonePad.class.getResource("/resources/main.png")));
		getContentPane().setLayout(new BorderLayout(0, 0));		
        setSize(640, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        jtbMain = new JTable();
        jtbMain.addMouseListener(new MouseAdapter() {
        	@Override
        	public void mouseClicked(MouseEvent e) {
        		if (e.getClickCount() == 2) {
        			edtRecords();		
        		}	
        	}
        });
        jtbMain.setModel(dtmMain);
        jtbMain.setFillsViewportHeight(true);
        jtbMain.setRowHeight(21);
        jtbMain.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        jtbMain.getTableHeader().setResizingAllowed(false);
        jtbMain.getTableHeader().setReorderingAllowed(false);
        jtbMain.setGridColor(Color.LIGHT_GRAY);
        jtbMain.setIntercellSpacing(new Dimension(0, 1));
        
        if (jtbMain.getColumnModel().getColumnCount() > 0) {
        	jtbMain.getColumnModel().getColumn(0).setMinWidth(30);
        	jtbMain.getColumnModel().getColumn(0).setPreferredWidth(0);
        	jtbMain.getColumnModel().getColumn(0).setMaxWidth(0);
        	jtbMain.getColumnModel().getColumn(1).setMinWidth(200);
        	jtbMain.getColumnModel().getColumn(1).setPreferredWidth(0);
        	jtbMain.getColumnModel().getColumn(1).setMaxWidth(0);
        	jtbMain.getColumnModel().getColumn(2).setMinWidth(200);
        	jtbMain.getColumnModel().getColumn(2).setPreferredWidth(0);
        	jtbMain.getColumnModel().getColumn(2).setMaxWidth(0);
        	jtbMain.getColumnModel().getColumn(3).setMinWidth(80);
        	jtbMain.getColumnModel().getColumn(3).setPreferredWidth(0);
        	jtbMain.getColumnModel().getColumn(3).setMaxWidth(0);
        	jtbMain.getColumnModel().getColumn(4).setMinWidth(100);
        	jtbMain.getColumnModel().getColumn(4).setPreferredWidth(0);
        	jtbMain.getColumnModel().getColumn(4).setMaxWidth(0);
        }       
        JScrollPane jspMain = new JScrollPane(jtbMain);
        jtbMain.setPreferredScrollableViewportSize(new Dimension(450, 110));
        getContentPane().add(jspMain, BorderLayout.CENTER);
        jtbMain.setRowSelectionInterval(0, 0);
        jpnButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jbtAdd = new JButton(Messages.getString("ButtonAdd"));
        jbtAdd.setActionCommand("add");
        jbtAdd.addActionListener(this);
        jbtEdt = new JButton(Messages.getString("ButtonEdit"));
        jbtEdt.setActionCommand("edt");
        jbtEdt.addActionListener(this);
        jbtDel = new JButton(Messages.getString("ButtonDel"));
        jbtDel.setActionCommand("del");
        jbtDel.addActionListener(this);
        jpnButtons.add(jbtAdd);
        jpnButtons.add(jbtEdt);
        jpnButtons.add(jbtDel);
		addWindowListener(new WindowAdapter() {
			@Override
			public void windowClosing(WindowEvent e) {
				dataModule.closeDB();
			}
		});
        getContentPane().add(jpnButtons, BorderLayout.SOUTH);		
	}
	
	private void setPosToCenterScreen() {
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        this.setLocation(dim.width / 2 - this.getSize().width / 2, dim.height / 2 - this.getSize().height / 2);		
	}
	
	@Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("add")) {
            addRecords();
        } else if (e.getActionCommand().equals("edt")) {
            edtRecords();
        } else if (e.getActionCommand().equals("del")) {
            delRecords();
        }
    }

    public void addRecords(){
        TableModel tm = jtbMain.getModel();
        DialogAddEdt dialogEdit = new DialogAddEdt(this, Messages.getString("TitleDlgAdd"), dataModule);
        dialogEdit.setVisible(true);
        if (dialogEdit.getModalResult() != 0) {
            if (dataModule.editMain(-1, dialogEdit.getFirstName(), dialogEdit.getLastName(), dialogEdit.getTypes(), dialogEdit.getPhone()) != -1){
                refreshTable(tm.getRowCount());
            };
        }
    }

    public void edtRecords(){
        String id;
        int row = jtbMain.getSelectedRow();
        TableModel tm = jtbMain.getModel();
        if (row != -1) {
            DialogAddEdt dialogEdit = new DialogAddEdt(this, Messages.getString("TitleDlgEdt"), dataModule);
            id = (String) tm.getValueAt(row, 0);
            dialogEdit.setFirstName((String) tm.getValueAt(row, 1));
            dialogEdit.setLastName((String) tm.getValueAt(row,2));
            dialogEdit.setTypes((String) tm.getValueAt(row,3));
            dialogEdit.setPhone((String) tm.getValueAt(row,4));
            dialogEdit.setVisible(true);
            if (dialogEdit.getModalResult() != 0) {
                if (dataModule.editMain(Integer.parseInt(id),dialogEdit.getFirstName(),dialogEdit.getLastName(),dialogEdit.getTypes(),dialogEdit.getPhone()) != -1){
                    refreshTable(row);
                };
            }
        }
    }

    public void delRecords(){
        String id;
        int row = jtbMain.getSelectedRow();
        TableModel tm = jtbMain.getModel();
        if (row != -1) {
        	if (JOptionPane.showConfirmDialog(this, Messages.getString("ConfimDel"), Messages.getString("TitleConfirmDel"), JOptionPane.YES_NO_OPTION) == 0) {       	
        		id = (String) tm.getValueAt(row, 0);
        		if (dataModule.deleteMain(Integer.parseInt(id)) != -1) {
        			if (row != 0) {row--;}
        			refreshTable(row);
        		}
        	}
        }
    }

    public void refreshTable(int row){
    	dataModule.reopenMain();
    	dtmMain.initData(dataModule.getRsMain());
        jtbMain.setVisible(false);
        jtbMain.setVisible(true);
        jtbMain.setRowSelectionInterval(row,row);
    }
    
}
