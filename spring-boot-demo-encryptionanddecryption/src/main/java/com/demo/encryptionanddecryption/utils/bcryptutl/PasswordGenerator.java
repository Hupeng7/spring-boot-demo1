package com.demo.encryptionanddecryption.utils.bcryptutl;

import javax.swing.*;
import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.security.SecureRandom;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PasswordGenerator extends JFrame {
    private JPasswordField passwordField;
    private JButton generateButton;
    private JButton copyButton;
    private JToggleButton showHideButton;
    private JLabel strengthLabel;
    private JCheckBox uppercaseCheckbox;
    private JCheckBox lowercaseCheckbox;
    private JCheckBox numbersCheckbox;
    private JCheckBox specialCharsCheckbox;
    private JSpinner lengthSpinner;

    // 定义字符集
    private static final String UPPERCASE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
    private static final String LOWERCASE = "abcdefghijklmnopqrstuvwxyz";
    private static final String NUMBERS = "0123456789";
    private static final String SPECIAL_CHARS = "+-=?!@#$%&";

    private SecureRandom random = new SecureRandom();

    public PasswordGenerator() {
        setTitle("复杂密码生成器");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));

        // 创建UI组件
        createComponents();

        setVisible(true);
    }

    private void createComponents() {
        // 主面板
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));

        // 顶部面板 - 密码显示
        JPanel topPanel = new JPanel(new BorderLayout(5, 5));
        passwordField = new JPasswordField();
        passwordField.setFont(new Font("Monospaced", Font.BOLD, 16));
        passwordField.setEditable(false);

        // 显示/隐藏按钮
        showHideButton = new JToggleButton("显示");
        showHideButton.setPreferredSize(new Dimension(80, 30));

        // 密码字段和显示按钮的面板
        JPanel passwordPanel = new JPanel(new BorderLayout(5, 5));
        passwordPanel.add(passwordField, BorderLayout.CENTER);
        passwordPanel.add(showHideButton, BorderLayout.EAST);

        topPanel.add(new JLabel("生成的密码:"), BorderLayout.NORTH);
        topPanel.add(passwordPanel, BorderLayout.CENTER);

        // 按钮面板
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 5));
        generateButton = new JButton("生成密码");
        copyButton = new JButton("复制密码");
        buttonPanel.add(generateButton);
        buttonPanel.add(copyButton);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        // 选项面板
        JPanel optionsPanel = new JPanel(new GridLayout(5, 1, 5, 5));
        optionsPanel.setBorder(BorderFactory.createTitledBorder("密码选项"));

        // 长度选择
        JPanel lengthPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        lengthPanel.add(new JLabel("密码长度:"));
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(10, 8, 20, 1);
        lengthSpinner = new JSpinner(spinnerModel);
        lengthPanel.add(lengthSpinner);
        optionsPanel.add(lengthPanel);

        // 字符集选择
        uppercaseCheckbox = new JCheckBox("包含大写字母 (A-Z)", true);
        lowercaseCheckbox = new JCheckBox("包含小写字母 (a-z)", true);
        numbersCheckbox = new JCheckBox("包含数字 (0-9)", true);
        specialCharsCheckbox = new JCheckBox("包含特殊字符 (+-!@#$%&)", true);

        optionsPanel.add(uppercaseCheckbox);
        optionsPanel.add(lowercaseCheckbox);
        optionsPanel.add(numbersCheckbox);
        optionsPanel.add(specialCharsCheckbox);

        // 密码强度指示器
        strengthLabel = new JLabel("密码强度: 未生成");
        strengthLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        strengthLabel.setHorizontalAlignment(SwingConstants.CENTER);

        // 添加面板到主界面
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(optionsPanel, BorderLayout.CENTER);
        mainPanel.add(strengthLabel, BorderLayout.SOUTH);

        add(mainPanel);

        // 添加事件监听器
        generateButton.addActionListener(e -> generatePassword());
        copyButton.addActionListener(e -> copyToClipboard());
        showHideButton.addActionListener(e -> togglePasswordVisibility());
    }

    private void generatePassword() {
        int length = (int) lengthSpinner.getValue();
        boolean useUppercase = uppercaseCheckbox.isSelected();
        boolean useLowercase = lowercaseCheckbox.isSelected();
        boolean useNumbers = numbersCheckbox.isSelected();
        boolean useSpecial = specialCharsCheckbox.isSelected();

        // 验证至少选择了三种字符类型
        int selectedTypes = 0;
        if (useUppercase) selectedTypes++;
        if (useLowercase) selectedTypes++;
        if (useNumbers) selectedTypes++;
        if (useSpecial) selectedTypes++;

        if (selectedTypes < 3) {
            JOptionPane.showMessageDialog(this, "请至少选择三种字符类型!", "错误", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 构建字符集
        StringBuilder charSet = new StringBuilder();
        if (useUppercase) charSet.append(UPPERCASE);
        if (useLowercase) charSet.append(LOWERCASE);
        if (useNumbers) charSet.append(NUMBERS);
        if (useSpecial) charSet.append(SPECIAL_CHARS);

        // 生成密码 - 确保至少包含三种字符类型
        List<Character> passwordChars = new ArrayList<>();

        // 确保至少包含一个选中的字符类型
        if (useUppercase) {
            passwordChars.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        }
        if (useLowercase) {
            passwordChars.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        }
        if (useNumbers) {
            passwordChars.add(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        }
        if (useSpecial) {
            passwordChars.add(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));
        }

//        // 如果选择了四种类型但只需要三种，随机移除一种
//        while (passwordChars.size() > 3) {
//            passwordChars.remove(random.nextInt(passwordChars.size()));
//        }

        // 填充剩余字符
        while (passwordChars.size() < length) {
            passwordChars.add(charSet.charAt(random.nextInt(charSet.length())));
        }

        // 随机打乱字符顺序
        Collections.shuffle(passwordChars, random);

        // 转换为字符数组
        char[] password = new char[length];
        for (int i = 0; i < length; i++) {
            password[i] = passwordChars.get(i);
        }

        // 显示密码
        passwordField.setText(new String(password));

        // 评估密码强度
        evaluatePasswordStrength(password);
    }

    private void togglePasswordVisibility() {
        if (showHideButton.isSelected()) {
            passwordField.setEchoChar((char) 0); // 显示密码
            showHideButton.setText("隐藏");
        } else {
            passwordField.setEchoChar('•'); // 隐藏密码
            showHideButton.setText("显示");
        }
    }

    private void evaluatePasswordStrength(char[] password) {
        int strength = 0;
        boolean hasUpper = false;
        boolean hasLower = false;
        boolean hasDigit = false;
        boolean hasSpecial = false;

        for (char c : password) {
            if (Character.isUpperCase(c)) hasUpper = true;
            else if (Character.isLowerCase(c)) hasLower = true;
            else if (Character.isDigit(c)) hasDigit = true;
            else hasSpecial = true;
        }

        // 计算复杂度分数
        if (hasUpper) strength++;
        if (hasLower) strength++;
        if (hasDigit) strength++;
        if (hasSpecial) strength++;
        if (password.length >= 12) strength++;

        // 设置强度标签
        String strengthText;
        Color color;
        switch (strength) {
            case 5:
                strengthText = "非常强";
                color = new Color(0, 128, 0); // 深绿色
                break;
            case 4:
                strengthText = "强";
                color = new Color(0, 180, 0); // 绿色
                break;
            case 3:
                strengthText = "中等";
                color = Color.ORANGE;
                break;
            case 2:
                strengthText = "弱";
                color = new Color(255, 140, 0); // 深橙色
                break;
            default:
                strengthText = "非常弱";
                color = Color.RED;
        }

        strengthLabel.setText("密码强度: " + strengthText);
        strengthLabel.setForeground(color);
    }

    private void copyToClipboard() {
        if (passwordField.getPassword().length == 0) {
            JOptionPane.showMessageDialog(this, "没有密码可复制!", "提示", JOptionPane.WARNING_MESSAGE);
            return;
        }

        StringSelection selection = new StringSelection(passwordField.getText());
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
        clipboard.setContents(selection, null);

        JOptionPane.showMessageDialog(this, "密码已复制到剪贴板!", "成功", JOptionPane.INFORMATION_MESSAGE);
    }

    // 静态方法，可以直接调用生成密码
    public static String generateComplexPassword(int length) {
        SecureRandom random = new SecureRandom();
        StringBuilder charSet = new StringBuilder();
        charSet.append(UPPERCASE);
        charSet.append(LOWERCASE);
        charSet.append(NUMBERS);
        charSet.append(SPECIAL_CHARS);

        // 确保密码包含至少三种字符类型
        List<Character> passwordChars = new ArrayList<>();

        // 从每种字符类型中至少添加一个字符
        passwordChars.add(UPPERCASE.charAt(random.nextInt(UPPERCASE.length())));
        passwordChars.add(LOWERCASE.charAt(random.nextInt(LOWERCASE.length())));
        passwordChars.add(NUMBERS.charAt(random.nextInt(NUMBERS.length())));
        passwordChars.add(SPECIAL_CHARS.charAt(random.nextInt(SPECIAL_CHARS.length())));

        // 随机移除一种，确保只有三种
        passwordChars.remove(random.nextInt(passwordChars.size()));

        // 填充剩余字符
        while (passwordChars.size() < length) {
            passwordChars.add(charSet.charAt(random.nextInt(charSet.length())));
        }

        // 随机打乱字符顺序
        Collections.shuffle(passwordChars, random);

        // 转换为字符串
        StringBuilder password = new StringBuilder();
        for (char c : passwordChars) {
            password.append(c);
        }

        return password.toString();
    }

    public static void main(String[] args) {
        // 使用GUI版本
        SwingUtilities.invokeLater(PasswordGenerator::new);

        // 或者直接使用静态方法生成密码
        // String password = PasswordGenerator.generateComplexPassword(10);
        // System.out.println("生成的密码: " + password);
    }
}