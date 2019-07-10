package gui;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import common.DataModule;

@SuppressWarnings("serial")
public class DialogAddEdt extends JDialog implements ActionListener {	
    private int modalResult = 0;
    private DataModule dataModule;
    private JButton jbtOK;
    private JButton jbtCancel;
    private JTextField jtfFirstName;
    private JTextField jtfLastName;
    private JComboBox<String> jcbTypes;
    private JTextField jtfPhone;
       
    public DialogAddEdt(Frame owner, String title, DataModule dataModule) {
        super(owner, title);
        this.dataModule = dataModule;
        initGUI();
    }
    
    private void initGUI() {
        setTitle(Messages.getString("TitleDlgAdd"));
        getContentPane().setLayout(new BorderLayout());

        JPanel jpnContent = new JPanel();
        GridBagLayout gbljpnContent = new GridBagLayout();
        gbljpnContent.columnWidths = new int[] {0, 0, 30, 30};
        gbljpnContent.rowHeights = new int[] {0, 0, 0, 0};
        gbljpnContent.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbljpnContent.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        jpnContent.setLayout(gbljpnContent);

        JLabel jlbFirstName = new JLabel(Messages.getString("FieldFirstName"));
        GridBagConstraints gbcjlbFirstName = new GridBagConstraints();
        gbcjlbFirstName.insets = new Insets(4, 8, 4, 4);
        gbcjlbFirstName.anchor = GridBagConstraints.EAST;
        gbcjlbFirstName.gridx = 0;
        gbcjlbFirstName.gridy = 0;
        jpnContent.add(jlbFirstName, gbcjlbFirstName);

        jtfFirstName = new JTextField();
        jlbFirstName.setLabelFor(jtfFirstName);
        GridBagConstraints gbcjtfFirstName = new GridBagConstraints();
        gbcjtfFirstName.gridwidth = 4;
        gbcjtfFirstName.fill = GridBagConstraints.HORIZONTAL;
        gbcjtfFirstName.insets = new Insets(4, 4, 4, 8);
        gbcjtfFirstName.gridx = 1;
        gbcjtfFirstName.gridy = 0;
        jpnContent.add(jtfFirstName, gbcjtfFirstName);
        jtfFirstName.setColumns(10);

        JLabel jlbLastName = new JLabel(Messages.getString("FieldSecondName"));
        GridBagConstraints gbcjlbLastName = new GridBagConstraints();
        gbcjlbLastName.anchor = GridBagConstraints.EAST;
        gbcjlbLastName.insets = new Insets(4, 8, 4, 4);
        gbcjlbLastName.gridx = 0;
        gbcjlbLastName.gridy = 1;
        jpnContent.add(jlbLastName, gbcjlbLastName);

        jtfLastName = new JTextField();
        jlbLastName.setLabelFor(jtfLastName);
        GridBagConstraints gbcjtfLastName = new GridBagConstraints();
        gbcjtfLastName.gridwidth = 4;
        gbcjtfLastName.insets = new Insets(4, 4, 4, 8);
        gbcjtfLastName.fill = GridBagConstraints.HORIZONTAL;
        gbcjtfLastName.gridx = 1;
        gbcjtfLastName.gridy = 1;
        jpnContent.add(jtfLastName, gbcjtfLastName);
        jtfLastName.setColumns(10);

        JLabel jlbTypes = new JLabel(Messages.getString("FieldType"));
        GridBagConstraints gbcjlbTypes = new GridBagConstraints();
        gbcjlbTypes.anchor = GridBagConstraints.EAST;
        gbcjlbTypes.insets = new Insets(4, 8, 4, 4);
        gbcjlbTypes.gridx = 0;
        gbcjlbTypes.gridy = 2;
        jpnContent.add(jlbTypes, gbcjlbTypes);

        jcbTypes = new JComboBox<String>();
        addTypes();
        jlbTypes.setLabelFor(jcbTypes);
        GridBagConstraints gbcjcbTypes = new GridBagConstraints();
        gbcjcbTypes.gridwidth = 4;
        gbcjcbTypes.fill = GridBagConstraints.HORIZONTAL;
        gbcjcbTypes.insets = new Insets(4, 4, 4, 8);
        gbcjcbTypes.gridx = 1;
        gbcjcbTypes.gridy = 2;
        jpnContent.add(jcbTypes, gbcjcbTypes);

        JLabel jlbPhone = new JLabel(Messages.getString("FieldPhone"));
        GridBagConstraints gbcjlbPhone = new GridBagConstraints();
        gbcjlbPhone.anchor = GridBagConstraints.EAST;
        gbcjlbPhone.gridx = 0;
        gbcjlbPhone.gridy = 3;
        jpnContent.add(jlbPhone, gbcjlbPhone);

        jtfPhone = new JTextField();
        jlbPhone.setLabelFor(jtfPhone);
        GridBagConstraints gbcjtfPhone = new GridBagConstraints();
        gbcjtfPhone.gridwidth = 4;
        gbcjtfPhone.insets = new Insets(4, 4, 4, 8);
        gbcjtfPhone.fill = GridBagConstraints.HORIZONTAL;
        gbcjtfPhone.gridx = 1;
        gbcjtfPhone.gridy = 3;
        jpnContent.add(jtfPhone, gbcjtfPhone);
        jtfPhone.setColumns(10);

        JPanel jpnButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        jbtOK = new JButton(Messages.getString("TitleBtOk"));
        jbtOK.setActionCommand("ok");
        jbtOK.addActionListener(this);
        jbtCancel = new JButton(Messages.getString("TitleBtCancel"));
        jbtCancel.setActionCommand("cancel");
        jbtCancel.addActionListener(this);
        jpnButtons.add(jbtOK);
        jpnButtons.add(jbtCancel);

        getContentPane().add(jpnContent, BorderLayout.CENTER);
        getContentPane().add(jpnButtons, BorderLayout.SOUTH);

        setSize(300, 200);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
        setModal(true);
        setResizable(false);
        getRootPane().setDefaultButton(jbtOK);
	}
    
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("ok")) {
            onOK();
        } else if (e.getActionCommand().equals("cancel")) {
            onCancel();
        }
    }

    private void onOK() {
        modalResult = 1;
        dispose();
    }

    private void onCancel() {
        modalResult = 0;
        dispose();
    }

	private void addTypes() {
        dataModule.openTypes();
        ResultSet rs = this.dataModule.getRsTypes();
        String it;
        try {
            while (rs.next()) {
                it = rs.getString("name");
                jcbTypes.addItem(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}
	
	public String getFirstName() {
    	return jtfFirstName.getText();
    }
    
    public void setFirstName(String aValue) {
    	jtfFirstName.setText(aValue);
    }
    
    public String getLastName() {
    	return jtfLastName.getText();
    }
    
    public void setLastName(String aValue) {
    	jtfLastName.setText(aValue);
    }
    
    public int getTypes() {
    	return jcbTypes.getSelectedIndex();
    }
    
    public void setTypes(String aValue) {
    	jcbTypes.setSelectedItem(aValue);
    }
    
    public String getPhone() {
    	return jtfPhone.getText();
    }
    
    public void setPhone(String aValue) {
    	jtfPhone.setText(aValue);
    }


    public int getModalResult() {
        return modalResult;
    }

}
