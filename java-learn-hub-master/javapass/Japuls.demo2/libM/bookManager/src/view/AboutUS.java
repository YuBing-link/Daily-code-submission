package view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Image;

public class AboutUS extends JFrame {
    public static void main(String[] args) {
        new AboutUS();
    }

    public AboutUS() {
        setTitle("关于我们");
        setSize(700, 389);
        // 关键修改：将 EXIT_ON_CLOSE 改为 DISPOSE_ON_CLOSE，避免关闭此窗口时退出整个应用
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // 添加图片
        // 创建 ImageIcon 对象，加载图片
        ImageIcon icon = new ImageIcon("D:\\AAAAAAAAAAAAAAA\\【哲风壁纸】二次元少女-动漫少女.png");
        // 可选：如果图片尺寸过大，可缩放图片
        Image image = icon.getImage().getScaledInstance(700, 389, Image.SCALE_SMOOTH);
        icon = new ImageIcon(image);

        // 创建 JLabel 用于显示图片
        JLabel imageLabel = new JLabel(icon);
        // 将 JLabel 添加到窗口的内容面板
        getContentPane().add(imageLabel);

        setVisible(true);
    }
}