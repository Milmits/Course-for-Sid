package course_work;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URI;

/**
 * Главный класс приложения, представляющий окно конвертера с графическим интерфейсом.
 * Обрабатывает создание и отображение различных панелей (главное меню, конвертер, информация об авторе).
 * Обеспечивает взаимодействие с пользователем через кнопки и поля ввода.
 *
 * @author Милан Михайлович Кучук
 * @version 1.0
 */

public class MainFrame extends JFrame {
    private JComboBox<String> fromBaseBox;
    private JComboBox<String> toBaseBox;
    private JTextField inputField;
    private JLabel resultLabel;
    private CardLayout cardLayout;  // Для переключения экранов
    private JPanel contentPanel;
    /**
     * Конструктор класса MainFrame.
     * Инициализирует компоненты, настраивает окно, добавляет слушатели событий.
     */

    public MainFrame() {
        setTitle("Converter");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);  // Чтобы добавить подтверждение выхода
        setLocationRelativeTo(null); // Центрируем окно на экране
        setResizable(false); // Отключение возможности делать полноэкранный режим

        // Устанавливаем иконку
        ImageIcon icon = new ImageIcon(getClass().getClassLoader().getResource("photo/icon.png"));
        setIconImage(icon.getImage());

        // Добавляем слушатель на закрытие окна
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                if (JOptionPane.showConfirmDialog(MainFrame.this,
                        "Вы уверены, что хотите выйти?", "Подтверждение",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0);
                }
            }
        });

        // Создаем основную панель с фоном
        contentPanel = new JPanel(new CardLayout());
        cardLayout = (CardLayout) contentPanel.getLayout();
        add(contentPanel);

        // Добавляем экраны
        contentPanel.add(wrapWithScrollPane(createStartScreenPanel()), "StartScreen");
        contentPanel.add(wrapWithScrollPane(createMainMenuPanel()), "MainMenu");
        contentPanel.add(wrapWithScrollPane(createConverterPanel()), "Converter");
        contentPanel.add(wrapWithScrollPane(createAboutAuthorPanel()), "AboutAuthor");
        contentPanel.add(wrapWithScrollPane(createAboutProgramPanel()), "AboutProgram");

        // Показываем главное меню при запуске
        cardLayout.show(contentPanel, "StartScreen");
    }

    private JPanel createStartScreenPanel() {
        JPanel startScreenPanel = new JPanel();
        startScreenPanel.setLayout(new BorderLayout());

        // Центральная часть для текста и изображения
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new BoxLayout(centerPanel, BoxLayout.Y_AXIS));
        centerPanel.setOpaque(false);

        // Первый заголовок
        JLabel universityLabel = new JLabel(
                "<html><div style='text-align: center;'>"
                        + "Белорусский национальный технический университет<br>"
                        + "Факультет информационных технологий и робототехники<br>"
                        + "Кафедра программного обеспечения информационных систем и технологий"
                        + "</div></html>"
        );
        universityLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        universityLabel.setHorizontalAlignment(SwingConstants.CENTER);
        universityLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Пробел в две строки
        JLabel spacing1 = new JLabel("<html><br><br></html>");

        // Название работы
        JLabel courseWorkLabel = new JLabel("Курсовая работа");
        courseWorkLabel.setFont(new Font("Arial", Font.PLAIN, 17));
        courseWorkLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Подзаголовок
        JLabel disciplineLabel = new JLabel("по дисциплине «Программирование на языке Java»");
        disciplineLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        disciplineLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Тема курсовой работы
        JLabel projectTitleLabel = new JLabel("Конвертор чисел и системы счисления");
        projectTitleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        projectTitleLabel.setAlignmentX(Component.CENTER_ALIGNMENT);

        // Пробел
        JLabel spacing2 = new JLabel("<html><br></html>");

        // Фото и информация об авторе и руководителе
        JPanel authorPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        authorPanel.setOpaque(false);

        ImageIcon imageIcon = new ImageIcon(
                new ImageIcon(getClass().getClassLoader().getResource("photo/start.png"))
                        .getImage()
                        .getScaledInstance(100, 100, Image.SCALE_SMOOTH) // Уменьшаем изображение
        );
        JLabel imageLabel = new JLabel(imageIcon);

        JLabel authorInfoLabel = new JLabel(
                "<html><div style='text-align: left;'>"
                        + "Выполнил: студент гр.10702322<br>"
                        + "Кучук Милан Михайлович<br><br>"
                        + "Руководитель проекта: доцент<br>"
                        + "Сидорик Валерий Владимирович"
                        + "</div></html>"
        );
        authorInfoLabel.setFont(new Font("Arial", Font.PLAIN, 14));

        // Пробел
        JLabel spacing3 = new JLabel("<html><br></html>");

        // Город и год
        JLabel projectCityLabel = new JLabel("Минск 2024");
        projectCityLabel.setFont(new Font("Arial", Font.PLAIN, 15)); // Применяем корректный шрифт
        projectCityLabel.setAlignmentX(Component.CENTER_ALIGNMENT); // Центрируем компонент


        authorPanel.add(imageLabel);
        authorPanel.add(authorInfoLabel);

        // Низ экрана с кнопками
        JPanel buttonPanel = new JPanel(new BorderLayout());
        buttonPanel.setBorder(new EmptyBorder(20, 40, 20, 40));
        buttonPanel.setOpaque(false);

        JButton nextButton = createStyledButton("Далее");
        JButton exitButton = createStyledButton("Выход");

        nextButton.addActionListener(e -> cardLayout.show(contentPanel, "MainMenu"));
        exitButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(MainFrame.this,
                    "Вы уверены, что хотите выйти?", "Подтверждение",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        JPanel buttonsRow = new JPanel(new GridLayout(1, 2, 10, 0));
        buttonsRow.setOpaque(false);
        buttonsRow.add(exitButton);
        buttonsRow.add(nextButton);


        buttonPanel.add(buttonsRow, BorderLayout.CENTER);

        // Добавление элементов в центральную часть
        centerPanel.add(universityLabel);
        centerPanel.add(spacing1);
        centerPanel.add(courseWorkLabel);
        centerPanel.add(disciplineLabel);
        centerPanel.add(projectTitleLabel);
        centerPanel.add(spacing2);
        centerPanel.add(authorPanel);
        centerPanel.add(spacing3);
        centerPanel.add(projectCityLabel);

        // Добавление центральной части и кнопок на панель
        startScreenPanel.add(centerPanel, BorderLayout.CENTER);
        startScreenPanel.add(buttonPanel, BorderLayout.SOUTH);

        return startScreenPanel;
    }

    /**
     * Создает и возвращает панель главного меню.
     * Содержит кнопки для перехода к конвертеру и информации об авторе.
     *
     * @return Панель главного меню.
     */
    // Панель главного меню
    private JPanel createMainMenuPanel() {
        JPanel mainMenuPanel = new JPanel() {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Задний фон
                ImageIcon background = new ImageIcon(getClass().getClassLoader().getResource("photo/fon.jpg"));
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };
        mainMenuPanel.setLayout(new BorderLayout());
        mainMenuPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Уменьшаем рамку

        // Центральная панель для заголовка и кнопок
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setOpaque(false); // Прозрачный фон для центральной панели
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);

        // Заголовок "Converter" красного цвета
        JLabel titleLabel = new JLabel("Converter");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setForeground(Color.RED); // Красный цвет для заголовка
        gbc.gridx = 0;
        gbc.gridy = 0;
        centerPanel.add(titleLabel, gbc);

        // Создаем и добавляем кнопки с одинаковым размером
        JButton startButton = createStyledButton("Start");
        JButton aboutButton = createStyledButton("About Author");
        JButton aboutProgramButton = createStyledButton("About Program");
        JButton exitButton = createStyledButton("EXIT");

        // Устанавливаем одинаковый размер для кнопок
        Dimension buttonSize = new Dimension(150, 35);
        startButton.setPreferredSize(buttonSize);
        aboutButton.setPreferredSize(buttonSize);
        aboutProgramButton.setPreferredSize(buttonSize);
        exitButton.setPreferredSize(buttonSize);

        // Добавляем кнопки на центральную панель
        gbc.gridy = 1;
        centerPanel.add(startButton, gbc);
        gbc.gridy = 2;
        centerPanel.add(aboutButton, gbc);
        gbc.gridy = 3; // Новая кнопка под остальными
        centerPanel.add(aboutProgramButton, gbc);
        gbc.gridy = 4;
        centerPanel.add(exitButton, gbc);

        // Добавляем центральную панель в основной экран
        mainMenuPanel.add(centerPanel, BorderLayout.CENTER);

        // Панель для кнопки "Back"
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setOpaque(false); // Прозрачный фон
        JButton backButton = createStyledButton("Back");
        backButton.setPreferredSize(new Dimension(75, 17)); // Уменьшенный размер кнопки "Back"
        bottomPanel.add(backButton);
        mainMenuPanel.add(bottomPanel, BorderLayout.SOUTH);

        // Обработчики для кнопок
        startButton.addActionListener(e -> cardLayout.show(contentPanel, "Converter"));
        aboutButton.addActionListener(e -> cardLayout.show(contentPanel, "AboutAuthor"));
        aboutProgramButton.addActionListener(e -> cardLayout.show(contentPanel, "AboutProgram"));
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "StartScreen"));
        exitButton.addActionListener(e -> {
            if (JOptionPane.showConfirmDialog(MainFrame.this,
                    "Вы уверены, что хотите выйти?", "Подтверждение",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                System.exit(0);
            }
        });

        return mainMenuPanel;
    }

    /**
     * Создает и возвращает панель для конвертации чисел между системами счисления.
     * Включает поля для ввода и кнопки для выполнения конвертации.
     *
     * @return Панель конвертера.
     */
    // Панель конвертера
    private JPanel createConverterPanel() {
        JPanel converterPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Отступы между компонентами
        gbc.fill = GridBagConstraints.HORIZONTAL;

        // Заголовок "Converter" красного цвета над всей панелью
        JLabel converterTitle = new JLabel("Converter");
        converterTitle.setFont(new Font("Arial", Font.BOLD, 24));
        converterTitle.setForeground(Color.RED);
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Чтобы занять две колонки
        converterPanel.add(converterTitle, gbc);
        gbc.gridwidth = 1; // Сброс ширины

        JLabel fromLabel = new JLabel("Из системы счисления:");
        fromLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 1;
        converterPanel.add(fromLabel, gbc);

        fromBaseBox = new JComboBox<>(new String[]{"Двоичная", "Восьмеричная", "Десятичная", "Шестнадцатеричная"});
        gbc.gridx = 1;
        gbc.gridy = 1;
        converterPanel.add(fromBaseBox, gbc);

        JLabel toLabel = new JLabel("В систему счисления:");
        toLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 2;
        converterPanel.add(toLabel, gbc);

        toBaseBox = new JComboBox<>(new String[]{"Двоичная", "Восьмеричная", "Десятичная", "Шестнадцатеричная"});
        gbc.gridx = 1;
        gbc.gridy = 2;
        converterPanel.add(toBaseBox, gbc);

        JLabel inputLabel = new JLabel("Введите число:");
        inputLabel.setFont(new Font("Arial", Font.BOLD, 14));
        gbc.gridx = 0;
        gbc.gridy = 3;
        converterPanel.add(inputLabel, gbc);

        inputField = new JTextField();
        gbc.gridx = 1;
        gbc.gridy = 3;
        converterPanel.add(inputField, gbc);

        JButton convertButton = createStyledButton("Конвертировать");
        gbc.gridx = 1;
        gbc.gridy = 4;
        converterPanel.add(convertButton, gbc);

        resultLabel = new JLabel("Результат:");
        resultLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 2;
        resultLabel.setHorizontalAlignment(JLabel.CENTER);
        converterPanel.add(resultLabel, gbc);

        // Добавляем кнопку "Назад" в конвертере
        JButton backButton = createStyledButton("Back");
        gbc.gridy = 7;
        converterPanel.add(backButton, gbc);

        // Обработчик конвертации
        convertButton.addActionListener(e -> {
            try {
                int fromBase = getBase(fromBaseBox.getSelectedItem().toString());
                int toBase = getBase(toBaseBox.getSelectedItem().toString());
                String inputNumber = inputField.getText();
                String result = Converter.convert(inputNumber, fromBase, toBase);
                resultLabel.setText("Результат: " + result);
            } catch (NumberFormatException ex) {
                resultLabel.setText("Ошибка: неверный ввод числа!");
            }
        });

        // Обработчик для кнопки "Назад"
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "MainMenu"));

        return converterPanel;
    }

    // Панель с информацией об авторе
    private JPanel createAboutAuthorPanel() {
        JPanel aboutPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Задний фон для окна "Об авторе"
                ImageIcon background = new ImageIcon(getClass().getClassLoader().getResource("photo/aut.jpg"));
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Левая часть - информация об авторе
        JPanel infoPanel = new JPanel();
        infoPanel.setOpaque(false); // Прозрачная панель, чтобы был виден фон
        infoPanel.setLayout(new BoxLayout(infoPanel, BoxLayout.Y_AXIS));
        infoPanel.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Устанавливаем одинаковый отступ для меток
        EmptyBorder labelBorder = new EmptyBorder(5, 5, 5, 5);

        // Заголовок "Об авторе"
        JLabel aboutTitle = new JLabel("Об авторе:");
        aboutTitle.setFont(new Font("Arial", Font.BOLD, 24));
        aboutTitle.setForeground(Color.RED);
        aboutTitle.setBorder(labelBorder);
        infoPanel.add(aboutTitle);

        // Добавление имени и группы
        JLabel nameLabel = new JLabel("Кучук Милан Михайлович");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
        nameLabel.setForeground(Color.RED);  // Красный цвет текста
        nameLabel.setBorder(labelBorder);  // Установка отступа
        infoPanel.add(nameLabel);

        JLabel groupLabel = new JLabel("гр. 10702322");
        groupLabel.setFont(new Font("Arial", Font.BOLD, 14));
        groupLabel.setForeground(Color.RED);  // Красный цвет текста
        groupLabel.setBorder(labelBorder);  // Установка отступа
        infoPanel.add(groupLabel);

        // Текст о проекте
        JTextArea aboutText = new JTextArea(
                "Работа над проектом велась 21 день.\n"
                        + "На данный момент работаю над проектом\n 'Offliner'.\n"
                        + "Можете посмотреть и поддержать меня: ");
        aboutText.setFont(new Font("Arial", Font.PLAIN, 14));
        aboutText.setForeground(Color.RED);
        aboutText.setOpaque(false); // Прозрачный фон
        aboutText.setEditable(false);
        aboutText.setWrapStyleWord(true);
        aboutText.setLineWrap(true);
        aboutText.setBorder(labelBorder); // Установка отступа

        JScrollPane scrollPane = new JScrollPane(aboutText);
        scrollPane.setOpaque(false); // Прозрачная область
        scrollPane.getViewport().setOpaque(false);  // Чтобы был виден задний фон
        scrollPane.setBorder(BorderFactory.createEmptyBorder());
        infoPanel.add(scrollPane);

        infoPanel.add(Box.createVerticalStrut(20));

        // Создаем кликабельную ссылку
        JLabel githubLink = new JLabel("<html><a href=''>https://github.com/Milmits</a></html>");
        githubLink.setFont(new Font("Arial", Font.PLAIN, 14));
        githubLink.setForeground(Color.RED);
        githubLink.setCursor(new Cursor(Cursor.HAND_CURSOR));
        githubLink.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent e) {
                try {
                    Desktop.getDesktop().browse(new URI("https://github.com/Milmits"));
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        // Поднимаем ссылку повыше
        infoPanel.add(githubLink);

        // Кнопка "Назад" в панели об авторе
        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "MainMenu"));
        infoPanel.add(backButton); // Добавляем кнопку "Назад" в панель информации



        // Правая часть - фото автора
        ImageIcon authorPhoto = new ImageIcon(getClass().getClassLoader().getResource("photo/1.jpg"));
        JLabel photoLabel = new JLabel();
        photoLabel.setIcon(new ImageIcon(authorPhoto.getImage().getScaledInstance(250, 350, Image.SCALE_SMOOTH))); // Уменьшаем изображение
        photoLabel.setBorder(BorderFactory.createLineBorder(Color.RED, 4));  // Красная рамка вокруг изображения



        // Добавляем на панель
        aboutPanel.add(infoPanel, BorderLayout.EAST);
        aboutPanel.add(photoLabel, BorderLayout.WEST);

        return aboutPanel;
    }

    private JPanel createAboutProgramPanel() {
        JPanel aboutProgramPanel = new JPanel(new BorderLayout()) {
            @Override
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                // Используем изображение aut.jpg как фон
                ImageIcon background = new ImageIcon(getClass().getClassLoader().getResource("photo/aut.jpg"));
                g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), this);
            }
        };

        // Текстовое поле с информацией о программе
        JTextArea aboutProgramText = new JTextArea(
                "Converter - это удобный инструмент для конвертации чисел между различными системами счисления.\n\n" +
                        "Поддерживаемые системы счисления:\n" +
                        "- Двоичная (Binary)\n" +
                        "- Восьмеричная (Octal)\n" +
                        "- Десятичная (Decimal)\n" +
                        "- Шестнадцатеричная (Hexadecimal)\n\n" +
                        "Основные возможности программы:\n" +
                        "1. Удобный пользовательский интерфейс.\n" +
                        "2. Быстрая и точная конвертация чисел.\n" +
                        "3. Отображение сообщений об ошибках для некорректного ввода.\n\n" +
                        "Спасибо, что пользуетесь программой!"
        );
        aboutProgramText.setFont(new Font("Arial", Font.BOLD, 14));
        aboutProgramText.setForeground(Color.RED);  // Красный цвет текста
        aboutProgramText.setOpaque(false);          // Прозрачный фон
        aboutProgramText.setEditable(false);
        aboutProgramText.setWrapStyleWord(true);
        aboutProgramText.setLineWrap(true);
        aboutProgramText.setBorder(new EmptyBorder(20, 20, 20, 20));

        // Прокрутка для текстового поля
        JScrollPane scrollPane = new JScrollPane(aboutProgramText);
        scrollPane.setOpaque(false);
        scrollPane.getViewport().setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder());  // Без рамок

        // Кнопка "Назад"
        JButton backButton = createStyledButton("Back");
        backButton.addActionListener(e -> cardLayout.show(contentPanel, "MainMenu"));

        // Добавляем элементы на панель
        aboutProgramPanel.add(scrollPane, BorderLayout.CENTER);
        aboutProgramPanel.add(backButton, BorderLayout.SOUTH);

        return aboutProgramPanel;
    }

    private JScrollPane wrapWithScrollPane(JPanel panel) {
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollPane.getViewport().setOpaque(false); // Чтобы фон был виден
        scrollPane.setOpaque(false);
        scrollPane.setBorder(BorderFactory.createEmptyBorder()); // Без рамок
        return scrollPane;
    }

    /**
     * Создает кнопку с заданным текстом и стилем.
     *
     * @param text Текст на кнопке.
     * @return Стилизованная кнопка.
     */
    // Создание стилизованной кнопки
    private JButton createStyledButton(String text) {
        JButton button = new JButton(text);
        button.setFont(new Font("Arial", Font.BOLD, 14));
        button.setBackground(Color.RED);  // Красный цвет кнопки
        button.setForeground(Color.WHITE);  // Белый цвет текста
        button.setBorder(BorderFactory.createLineBorder(new Color(30, 144, 255), 2));  // Рамка синего цвета вокруг кнопки
        return button;
    }
    /**
     * Получает числовое значение системы счисления по ее имени.
     *
     * @param base Система счисления (например, "Десятичная").
     * @return Числовое значение системы счисления.
     */
    // Метод для получения системы счисления на основе выбора пользователя
    private int getBase(String base) {
        switch (base) {
            case "Двоичная":
                return 2;
            case "Восьмеричная":
                return 8;
            case "Десятичная":
                return 10;
            case "Шестнадцатеричная":
                return 16;
            default:
                throw new IllegalArgumentException("Неизвестная система счисления");
        }
    }
    /**
     * Запускает приложение.
     *
     * @param args Аргументы командной строки.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MainFrame frame = new MainFrame();
            frame.setVisible(true);
        });
    }
}






