package hulleza_morales_nagnal_gui;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LottoNumsGen_GUI extends JFrame{
    private JPanel mainPanel;
    private JPanel topPanel;
    private JPanel historyPanel;
    private JPanel panel_lottoNumsContainer;
    private JRadioButton button_showHistory;
    private JButton button_generate;
    private JButton button_clear;
    private JList<String> list_history;
    private JPanel panel_inputs;
    private JTextField textField_limit;
    private JTextField textField_range;
    private JTextField textField_num;
    private JList<String> list_generated;
    private JLabel label_warning;
    private JLabel label_limit;
    private JLabel label_range;
    private JLabel label_num;

    // List Models for Jlist
    private final DefaultListModel<String> listHistoryModel;
    private final DefaultListModel<String> listGeneratedModel;

    public LottoNumsGen_GUI() {

        // code for history list
        historyPanel.setVisible(false);
        listHistoryModel = new DefaultListModel<>();
        list_history.setModel(listHistoryModel);

        // code for generated list
        listGeneratedModel = new DefaultListModel<>();
        list_generated.setModel(listGeneratedModel);
        DefaultListCellRenderer renderer = (DefaultListCellRenderer) list_generated.getCellRenderer();
        renderer.setHorizontalAlignment(SwingConstants.CENTER);

        label_warning.setVisible(false);

        button_generate.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String str_limit = textField_limit.getText();
                String str_range = textField_range.getText();
                String str_num = textField_num.getText();

                // input error catcher
                if (str_limit.isEmpty() && str_range.isEmpty() && str_num.isEmpty()) {
                    label_warning.setVisible(true);
                    label_warning.setText("please fill in required fields!");
                    label_limit.setForeground(Color.decode("#E0564C"));
                    label_range.setForeground(Color.decode("#E0564C"));
                    label_num.setForeground(Color.decode("#E0564C"));
                }
                else if (str_limit.isEmpty() || str_range.isEmpty() || str_num.isEmpty()) {
                    if (str_limit.isEmpty()) {
                        label_warning.setVisible(true);
                        label_warning.setText("please fill in required fields!");
                        label_limit.setForeground(Color.decode("#E0564C"));
                    }
                    else if (str_range.isEmpty()) {
                        label_warning.setVisible(true);
                        label_warning.setText("please fill in required fields!");
                        label_range.setForeground(Color.decode("#E0564C"));
                    }
                    else {
                        label_warning.setVisible(true);
                        label_warning.setText("please fill in required fields!");
                        label_num.setForeground(Color.decode("#E0564C"));
                    }
                }
                else {
                    // convert string input to int
                    int limit = Integer.parseInt(str_limit);
                    int range = Integer.parseInt(str_range);
                    int num = Integer.parseInt(str_num);

                    listGeneratedModel.removeAllElements(); // clear generated list

                    for (int i = 1; i <= num; i++) {
                        String lotto_generated = LottoNumbersGenerator.generate(limit, range);

                        listGeneratedModel.addElement(lotto_generated);
                        listHistoryModel.addElement(lotto_generated);

                        label_warning.setVisible(false); // remove warning label
                        // reset label colors
                        label_limit.setForeground(Color.white);
                        label_range.setForeground(Color.white);
                        label_num.setForeground(Color.white);
                    }
                }

            }
        });

        button_showHistory.addChangeListener(new ChangeListener() {
            @Override
            public void stateChanged(ChangeEvent e) {
                historyPanel.setVisible(button_showHistory.isSelected());
            }
        });

        button_clear.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                textField_limit.setText("");
                textField_range.setText("");
                textField_num.setText("");
                listGeneratedModel.removeAllElements(); // clear generated list
                listHistoryModel.removeAllElements(); // clear list history
            }
        });
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("Lotto Numbers Generator v1.0");
        frame.setContentPane(new LottoNumsGen_GUI().mainPanel);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

}
