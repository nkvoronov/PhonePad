package GUI;

import java.awt.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

import Common.DataModule;

import static Common.Strings.*;

@SuppressWarnings("serial")
public class DialogAddEdt extends JDialog implements ActionListener {	
    private int ModalResult = 0;
    private DataModule datamodule;
    private JButton btOK;
    private JButton btCancel;

    public JTextField tfFirstName;
    public JTextField tfLastName;
    public JComboBox<String> cbTypes;
    public JTextField tfPhone;

    public int getModalResult() {
        return ModalResult;
    }
    
    public DialogAddEdt(Frame owner, String title, DataModule datamodule) {
        super(owner, title);
        this.datamodule = datamodule;
        getContentPane().setLayout(new BorderLayout());

        JPanel pnContent = new JPanel();
        GridBagLayout gbl_pnContent = new GridBagLayout();
        gbl_pnContent.columnWidths = new int[] {0, 0, 30, 30};
        gbl_pnContent.rowHeights = new int[] {0, 0, 0, 0};
        gbl_pnContent.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_pnContent.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0};
        pnContent.setLayout(gbl_pnContent);

        JLabel lbFirstName = new JLabel(FieldsName[1]);
        GridBagConstraints gbc_lbFirstName = new GridBagConstraints();
        gbc_lbFirstName.insets = new Insets(4, 8, 4, 4);
        gbc_lbFirstName.anchor = GridBagConstraints.EAST;
        gbc_lbFirstName.gridx = 0;
        gbc_lbFirstName.gridy = 0;
        pnContent.add(lbFirstName, gbc_lbFirstName);

        tfFirstName = new JTextField();
        lbFirstName.setLabelFor(tfFirstName);
        GridBagConstraints gbc_tfFirstName = new GridBagConstraints();
        gbc_tfFirstName.gridwidth = 4;
        gbc_tfFirstName.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfFirstName.insets = new Insets(4, 4, 4, 8);
        gbc_tfFirstName.gridx = 1;
        gbc_tfFirstName.gridy = 0;
        pnContent.add(tfFirstName, gbc_tfFirstName);
        tfFirstName.setColumns(10);

        JLabel lbLastName = new JLabel(FieldsName[2]);
        GridBagConstraints gbc_lbLastName = new GridBagConstraints();
        gbc_lbLastName.anchor = GridBagConstraints.EAST;
        gbc_lbLastName.insets = new Insets(4, 8, 4, 4);
        gbc_lbLastName.gridx = 0;
        gbc_lbLastName.gridy = 1;
        pnContent.add(lbLastName, gbc_lbLastName);

        tfLastName = new JTextField();
        lbLastName.setLabelFor(tfLastName);
        GridBagConstraints gbc_tfLastName = new GridBagConstraints();
        gbc_tfLastName.gridwidth = 4;
        gbc_tfLastName.insets = new Insets(4, 4, 4, 8);
        gbc_tfLastName.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfLastName.gridx = 1;
        gbc_tfLastName.gridy = 1;
        pnContent.add(tfLastName, gbc_tfLastName);
        tfLastName.setColumns(10);

        JLabel lbTypes = new JLabel("Тип");
        GridBagConstraints gbc_lbTypes = new GridBagConstraints();
        gbc_lbTypes.anchor = GridBagConstraints.EAST;
        gbc_lbTypes.insets = new Insets(4, 8, 4, 4);
        gbc_lbTypes.gridx = 0;
        gbc_lbTypes.gridy = 2;
        pnContent.add(lbTypes, gbc_lbTypes);

        cbTypes = new JComboBox<String>();
        addTypes();
        lbTypes.setLabelFor(cbTypes);
        GridBagConstraints gbc_cbTypes = new GridBagConstraints();
        gbc_cbTypes.gridwidth = 4;
        gbc_cbTypes.fill = GridBagConstraints.HORIZONTAL;
        gbc_cbTypes.insets = new Insets(4, 4, 4, 8);
        gbc_cbTypes.gridx = 1;
        gbc_cbTypes.gridy = 2;
        pnContent.add(cbTypes, gbc_cbTypes);

        JLabel lbPhone = new JLabel(FieldsName[4]);
        GridBagConstraints gbc_lbPhone = new GridBagConstraints();
        gbc_lbPhone.anchor = GridBagConstraints.EAST;
        gbc_lbPhone.insets = new Insets(4, 8, 4, 4);
        gbc_lbPhone.gridx = 0;
        gbc_lbPhone.gridy = 3;
        pnContent.add(lbPhone, gbc_lbPhone);

        tfPhone = new JTextField();
        lbPhone.setLabelFor(tfPhone);
        GridBagConstraints gbc_tfPhone = new GridBagConstraints();
        gbc_tfPhone.gridwidth = 4;
        gbc_tfPhone.insets = new Insets(4, 4, 4, 8);
        gbc_tfPhone.fill = GridBagConstraints.HORIZONTAL;
        gbc_tfPhone.gridx = 1;
        gbc_tfPhone.gridy = 3;
        pnContent.add(tfPhone, gbc_tfPhone);
        tfPhone.setColumns(10);

        JPanel pnButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btOK = new JButton(TitleBtOk);
        btOK.setActionCommand("ok");
        btOK.addActionListener(this);
        btCancel = new JButton(TitleBtCancel);
        btCancel.setActionCommand("cancel");
        btCancel.addActionListener(this);
        pnButtons.add(btOK);
        pnButtons.add(btCancel);

        getContentPane().add(pnContent, BorderLayout.CENTER);
        getContentPane().add(pnButtons, BorderLayout.SOUTH);

        setSize(300, 200);
        setModalityType(ModalityType.APPLICATION_MODAL);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(getParent());
        setModal(true);
        setResizable(false);
        getRootPane().setDefaultButton(btOK);
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
        ModalResult = 1;
        dispose();
    }

    private void onCancel() {
        ModalResult = 0;
        dispose();
    }

	private void addTypes() {
        datamodule.openTypes();
        ResultSet rs = this.datamodule.getRsTypes();
        String it;
        try {
            while (rs.next()) {
                it = rs.getString("name");
                cbTypes.addItem(it);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
		
	}

}
