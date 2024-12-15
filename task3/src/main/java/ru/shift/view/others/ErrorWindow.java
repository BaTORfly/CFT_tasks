package ru.shift.view.others;

import ru.shift.model.gameField.listeners.ErrorListeners;

import javax.swing.*;
import java.awt.*;

public class ErrorWindow extends JDialog implements ErrorListeners {

    public ErrorWindow(JFrame owner) {
        super(owner, "Error", true); // Модальное окно с дефолтным заголовком
        setPreferredSize(new Dimension(300, 150));
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Создаем layout и контейнер для содержимого
        GridBagLayout layout = new GridBagLayout();
        Container contentPane = getContentPane();
        contentPane.setLayout(layout);

        // Устанавливаем расположение окна в центре экрана
        setLocationRelativeTo(owner);
    }

    // Метод для создания метки с сообщением об ошибке
    private JLabel createMessageLabel(String message, GridBagLayout layout) {
        JLabel messageLabel = new JLabel(message);
        messageLabel.setHorizontalAlignment(SwingConstants.CENTER);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.NONE;
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        layout.setConstraints(messageLabel, gbc);

        return messageLabel;
    }

    // Метод для создания кнопки выхода
    private JButton createExitButton(GridBagLayout layout) {
        JButton exitButton = new JButton("Закрыть программу");
        exitButton.setPreferredSize(new Dimension(160, 30));

        exitButton.addActionListener(e -> {
            dispose();  // Закрытие окна ошибки
            System.exit(0);  // Завершаем работу программы
        });

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        gbc.gridheight = 1;
        gbc.insets = new Insets(10, 10, 10, 10);
        layout.setConstraints(exitButton, gbc);

        return exitButton;
    }

    @Override
    public void showError(String title, String message) {
        setTitle(title);

        GridBagLayout layout = (GridBagLayout) getContentPane().getLayout();

        getContentPane().add(createMessageLabel(message, layout));

        getContentPane().add(createExitButton(layout));

        pack();
        setVisible(true);
    }
}
