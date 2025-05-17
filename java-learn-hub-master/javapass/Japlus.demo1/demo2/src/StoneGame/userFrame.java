package StoneGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.Random;

public class userFrame extends JFrame {
    private int[][] imagesData={{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}, {13, 14, 15,16}};
    private int count=0;
    private int wps =0 ;
    public userFrame(){
        initFrame();
        initRandimage();
        initfile();
        initImages();
        initMenu();
        initkeypress();
        this.setVisible(true);
    }
    private void initwr() {
        try(
                PrintWriter bfw = new PrintWriter(new FileWriter("D:\\Git提交文件集合\\GitHub\\Daily-code-submission\\java-learn-hub-master\\javapass\\Japlus.demo1\\demo2\\src\\StoneGame\\score.txt"));
        ){
            bfw.println(wps);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    private void initfile() {
        try(
                BufferedReader reader = new BufferedReader(new FileReader("D:\\Git提交文件集合\\GitHub\\Daily-code-submission\\java-learn-hub-master\\javapass\\Japlus.demo1\\demo2\\src\\StoneGame\\score.txt"));
        )
        {
            String line = reader.readLine();
            wps=Integer.valueOf(line);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private int[] initfind() {
        int[] arr = new int[2];
        for (int i = 0; i < imagesData.length; i++) {
            for (int j = 0; j < imagesData[i].length; j++) {
                if (imagesData[i][j] == 16) {
                    arr[0] = i;
                    arr[1] = j;
                    return arr;
                }
            }
        }
    return null;
    }

    private void initkeypress() {
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                switch(e.getKeyCode()) {
                    case  KeyEvent.VK_UP:
                        showKeyboard(Dirction.UP);
                        break;
                    case KeyEvent.VK_DOWN:
                        showKeyboard(Dirction.DOWN);
                        break;
                    case KeyEvent.VK_LEFT:
                        showKeyboard(Dirction.LEFT);
                        break;
                    case KeyEvent.VK_RIGHT:
                        showKeyboard(Dirction.RIGHT);
                        break;
                    default:
                        System.out.println("按键非法");
                }
            }
        });


    }
    private  void showKeyboard(Dirction r) {
        int[] arr= initfind();

        switch (r){
            case UP -> {
                if(arr[0]==0)break;
                int temp =imagesData[arr[0]][arr[1]];
                imagesData[arr[0]][arr[1]]=imagesData[arr[0]-1][arr[1]];
                imagesData[arr[0]-1][arr[1]]=temp;
                arr[0]--;
                count++;
            }
            case DOWN -> {
                if(arr[0]==3)break;
                int temp =imagesData[arr[0]][arr[1]];
                imagesData[arr[0]][arr[1]]=imagesData[arr[0]+1][arr[1]];
                imagesData[arr[0]+1][arr[1]]=temp;
                count++;
                arr[0]++;
            }
            case LEFT-> {
                if(arr[1]==0)break;
                int temp =imagesData[arr[0]][arr[1]];
                imagesData[arr[0]][arr[1]]=imagesData[arr[0]][arr[1]-1];
                imagesData[arr[0]][arr[1]-1]=temp;
                count++;
                arr[1]--;
            }
            case RIGHT-> {
                if (arr[1]==3)break;
                int temp =imagesData[arr[0]][arr[1]];
                imagesData[arr[0]][arr[1]]=imagesData[arr[0]][arr[1]+1];
                imagesData[arr[0]][arr[1]+1]=temp;
                count++;
                arr[1]++;
            }
        }
        initImages();

    }

    private boolean JudeMent() {

        for (int i = 0; i <imagesData.length ; i++) {
            for (int j = 0; j <imagesData[i].length ; j++) {

                if (imagesData[i][j] != i*4+j+1) {
                    return false;
                }

            }
        }
        return true;
    }

    private void initRandimage() {
        Random r = new Random();
        int rows = imagesData.length;
        int cols = imagesData[0].length;
        
        // 将二维数组转换为一维数组进行打乱
        int[] flatArray = new int[rows * cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                flatArray[i * cols + j] = imagesData[i][j];
            }
        }

        // 使用Fisher-Yates算法打乱一维数组
        for (int i = flatArray.length - 1; i > 0; i--) {
            int index = r.nextInt(i + 1);
            int temp = flatArray[i];
            flatArray[i] = flatArray[index];
            flatArray[index] = temp;
        }

        // 校验并修正可解性
        if (!isSolvable(flatArray, rows, cols)) {
            // 不可解时交换两个非空格元素
            for (int i = 0; i < flatArray.length; i++) {
                if (flatArray[i] != 16 && flatArray[(i+1)%flatArray.length] != 16) {
                    int temp = flatArray[i];
                    flatArray[i] = flatArray[(i+1)%flatArray.length];
                    flatArray[(i+1)%flatArray.length] = temp;
                    break;
                }
            }
        }

        // 重新填充二维数组
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                imagesData[i][j] = flatArray[i * cols + j];
            }
        }

    }

    // 新增：判断排列是否可解
    private boolean isSolvable(int[] puzzle, int rows, int cols) {
        int inversions = 0;
        for (int i = 0; i < puzzle.length; i++) {
            for (int j = i + 1; j < puzzle.length; j++) {
                if (puzzle[i] != 16 && puzzle[j] != 16 && puzzle[i] > puzzle[j]) {
                    inversions++;
                }
            }
        }

        // 查找空格所在行（从下往上数）
        int blankRow = 0;
        for (int i = puzzle.length - 1; i >= 0; i -= cols) {
            for (int j = i; j > i - cols && j >= 0; j--) {
                if (puzzle[j] == 16) {
                    blankRow = (puzzle.length - j) / cols;
                    break;
                }
            }
            break;
        }

        // 奇数行移动 + 奇数逆序数 => 不可解
        return (inversions + blankRow) % 2 == 0;
    }

    private void initMenu() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("系统");
        JMenuItem mi = new JMenuItem("退出");
        menu.add(mi);
        mi.addActionListener(e->{
            dispose();
        });
        JMenuItem si=new JMenuItem("重启");
        menu.add(si);
        si.addActionListener(e->{
            count=0;
                initRandimage();
                initImages();
        });
        menuBar.add(menu);
        this.setJMenuBar(menuBar);
    }

    private void initImages() {
        this.getContentPane().removeAll();
        JLabel stepLabel = new JLabel("当前步数"+count);
        stepLabel.setBounds(0,0,100,20);
        stepLabel.setForeground(Color.BLUE);
        JLabel hests=new JLabel("历史最小步数:"+wps);
        hests.setBounds(300,0,100,20);
        this.add(hests);
        this.add(stepLabel);
        if (JudeMent()) {
            JLabel i18n = new JLabel(new ImageIcon("D:\\Git提交文件集合\\GitHub\\Daily-code-submission\\java-learn-hub-master\\javapass\\Japlus.demo1\\demo2\\src\\image\\win.png"));
            i18n.setBounds(124, 240, 266, 88);
            this.add(i18n);



            if(wps==0){
                wps=count;
            }else {
                if(wps>count)wps=count;
            }
            initwr();


        }
        for (int i = 0; i <imagesData.length ; i++) {
            for (int j = 0; j <imagesData[i].length ; j++) {
                String image = imagesData[i][j]+".png";
                JLabel label = new JLabel();
                label.setIcon(new ImageIcon("D:\\Git提交文件集合\\GitHub\\Daily-code-submission\\java-learn-hub-master\\javapass\\Japlus.demo1\\demo2\\src\\image\\"+image));
                label.setBounds(30+j*100,70+i*100,100,100);
                this.add(label);
            }

        }
        JLabel bRound = new JLabel(new ImageIcon("D:\\Git提交文件集合\\GitHub\\Daily-code-submission\\java-learn-hub-master\\javapass\\Japlus.demo1\\demo2\\src\\image\\background.png"));
        bRound.setBounds(0,10,450,484);

        this.add(bRound);


        this.repaint();
    }

    private void initFrame() {
        this.setTitle("石头迷宫 V 1.0 yubing");
        this.setSize(470, 560);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setLayout(null);
    }


    }




