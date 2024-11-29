package ru.shift.view.others;

import ru.shift.model.gameField.listeners.WinAndLoseListener;
import ru.shift.view.listeners.RecordListener;

import javax.swing.*;
import java.awt.*;

public class RecordsWindow extends JDialog implements WinAndLoseListener {
    private RecordListener recordListener;

    public RecordsWindow(JFrame frame) {
        super(frame, "New Record", true);

        JTextField nameField = new JTextField();

        GridLayout layout = new GridLayout(3, 1);
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        contentPane.add(new JLabel("Enter your name:"));
        contentPane.add(nameField);
        contentPane.add(createOkButton(nameField));

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setPreferredSize(new Dimension(210, 120));
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        //setVisible(true);
    }

    public void setNameListener(RecordListener nameListener) {
        this.recordListener = nameListener;
    }

    private JButton createOkButton(JTextField nameField) {
        JButton button = new JButton("OK");
        button.addActionListener(e -> {
            dispose();

            if (recordListener != null) {
                recordListener.setNewRecord(nameField.getText());
            }
        });
        return button;
    }

    @Override
    public void showContent() {
        setVisible(true);
    }
}
