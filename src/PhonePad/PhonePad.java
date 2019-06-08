package PhonePad;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.table.TableModel;

@SuppressWarnings("serial")
public class PhonePad extends JFrame implements WindowListener, ActionListener {
	private JTable jtabMain;
	private JPanel jbtPanel;
	private JButton jbtAdd;
	private JButton jbtEdt;
	private JButton jbtDel;
	private PhonePadTableModel maintm;
	private DataModulePhonePad dm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					PhonePad frame = new PhonePad(PhonePadStrings.TitleApp);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public PhonePad(String title) {
		super(title);
		getContentPane().setLayout(new BorderLayout(0, 0));		
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        dm = new DataModulePhonePad("notepad.sqlite");
        dm.openMain();
        maintm = new PhonePadTableModel();
        maintm.initData(dm.getRsMain());
        jtabMain = new JTable(maintm);
        JScrollPane jscrlp = new JScrollPane(jtabMain);
        jtabMain.setPreferredScrollableViewportSize(new Dimension(450, 110));
        add(jscrlp, BorderLayout.CENTER);
        jtabMain.setRowSelectionInterval(0, 0);
        jbtPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jbtAdd = new JButton(PhonePadStrings.TitleBtAdd);
        jbtAdd.setActionCommand("add");
        jbtAdd.addActionListener(this);
        jbtEdt = new JButton(PhonePadStrings.TitleBtEdt);
        jbtEdt.setActionCommand("edt");
        jbtEdt.addActionListener(this);
        jbtDel = new JButton(PhonePadStrings.TitleBtDel);
        jbtDel.setActionCommand("del");
        jbtDel.addActionListener(this);
        jbtPanel.add(jbtAdd);
        jbtPanel.add(jbtEdt);
        jbtPanel.add(jbtDel);
        add(jbtPanel, BorderLayout.SOUTH);
        this.addWindowListener(this);
		setPosToCenterScreen();
	}
	
    @Override
    public void windowClosed(WindowEvent e) {}

    @Override
    public void windowIconified(WindowEvent e) {}

    @Override
    public void windowDeiconified(WindowEvent e) {}

    @Override
    public void windowActivated(WindowEvent e) {}
    @Override
    public void windowDeactivated(WindowEvent e) {}

    @Override
    public void windowOpened(WindowEvent e) {}

    @Override
    public void windowClosing(WindowEvent e) {
        dm.closeDB();
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
        TableModel tm = jtabMain.getModel();
        DialogAddEdtMain dae = new DialogAddEdtMain(this,PhonePadStrings.TitleDlgAdd, dm);
        dae.setVisible(true);
        if (dae.getModalResult() != 0) {
            if (dm.editMain(-1, dae.tfFirstName.getText(), dae.tfLastName.getText(), dae.cbTypes.getSelectedIndex(), dae.tfPhone.getText()) != -1){
                refreshTable(tm.getRowCount());
            };
        }
    }

    public void edtRecords(){
        String id;
        int row = jtabMain.getSelectedRow();
        TableModel tm = jtabMain.getModel();
        if (row != -1) {
            DialogAddEdtMain dae = new DialogAddEdtMain(this, PhonePadStrings.TitleDlgEdt, dm);
            id = (String) tm.getValueAt(row, 0);
            dae.tfFirstName.setText((String) tm.getValueAt(row, 1));
            dae.tfLastName.setText((String) tm.getValueAt(row,2));
            dae.cbTypes.setSelectedItem((String) tm.getValueAt(row,3));
            dae.tfPhone.setText((String) tm.getValueAt(row,4));
            dae.setVisible(true);
            if (dae.getModalResult() != 0) {
                if (dm.editMain(Integer.parseInt(id),dae.tfFirstName.getText(),dae.tfLastName.getText(),dae.cbTypes.getSelectedIndex(),dae.tfPhone.getText()) != -1){
                    refreshTable(row);
                };
            }
        }
    }

    public void delRecords(){
        String id;
        int row = jtabMain.getSelectedRow();
        TableModel tm = jtabMain.getModel();
        if (row != -1) {
            id = (String) tm.getValueAt(row, 0);
            if (dm.deleteMain(Integer.parseInt(id)) != -1) {
                if (row != 0) {row--;}
                refreshTable(row);
            }
        }
    }

    public void refreshTable(int row){
        dm.reopenMain();
        maintm.initData(dm.getRsMain());
        jtabMain.setVisible(false);
        jtabMain.setVisible(true);
        jtabMain.setRowSelectionInterval(row,row);
    }

}
