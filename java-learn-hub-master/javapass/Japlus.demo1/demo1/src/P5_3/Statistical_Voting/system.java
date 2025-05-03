package P5_3.Statistical_Voting;

import org.w3c.dom.ls.LSOutput;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.Set;

public class system {
    //    案例
//Map集合的案例-统计投票信息/
//需求
//● 某个班级80名学生,现在需要组织秋游活动,班长提供了四个景点依次是(A、B、C、D),每个学
//生只能选择一个景点,请统计出最终哪个景点想去的人数最多。
//分析
//● 将80个学生选择的数据拿到程序中去,[A,A,B,A,B, C,D, …. ]
//● 准备一个Map集合用于存储统计的结果,Map<String,Integer>,键是景点,值代表投票数量。
//●遍历80个学生选择的景点,每遍历一个景点,就看Map集合中是否存在该景点,不存在存入“景点=1“,
//存在则其对应值+1,
//黑马程序
//www.itheima.c
    public static final int CONSTANTS_=80;
    public static void main(String[] args) {
        Map<String, Integer> spot = new HashMap<>();
        String summary = "云栖梧云栖鹤云栖鹿苏晚棠苏晚柠苏晚梨谢知许谢知晏谢知遥陈叙白陈叙言陈叙然林初晴林初柔林初宁江予安江予舟江予川夏听风秋望雨冬观雪陆青梧陆青枫陆青槐沈星遥沈星澈沈星澈";
        spot.put("泰山", 0);
        spot.put("海岛", 0);
        spot.put("雪山", 0);
        spot.put("湖泊", 0);
        Student[] s = new Student[CONSTANTS_];
        String[] arr = new String[]{"泰山", "海岛", "雪山", "湖泊"};
        Random random = new Random();
        for (int j = 0; j <CONSTANTS_; j++) {
            String w = "";
            if (random.nextBoolean()) {
                for (int i = 0; i < 2; i++) {
                    w += summary.charAt(random.nextInt(summary.length() -1));
                }
            } else {
                for (int i = 0; i < 3; i++) {
                    w += summary.charAt(random.nextInt(summary.length() -1));
                }
            }
            s[j] = (new Student(w, arr[random.nextInt(arr.length)]));
            System.out.println(s[j]);
            spot.compute(s[j].getWantname(), (key, oldValue) -> oldValue + 1);
        }


        int max = 0;
        for(Map.Entry<String,Integer> sx:spot.entrySet()){
            if(max<sx.getValue()){
                max = sx.getValue();
            }
        }

        for(Map.Entry<String,Integer> sx:spot.entrySet()){
            if(max==sx.getValue()){
                System.out.println("获得投票最多的是"+sx.getKey());
            }
        }



    }

    }






