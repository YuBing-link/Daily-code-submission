package P5_7.Game;

import java.util.*;

public class Room {
    private static List<Card> allCards = new ArrayList<Card>();

    {
        String[] sizes = {"3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A", "2"};
        String[] colors = {"♥", "♠", "♣", "♦"};
        int num=0;
        for (String s : sizes) {
            for (String c : colors) {
                allCards.add(new Card(s, c,num++));
            }
        }
        Collections.addAll(allCards, new Card("", "😂",num++), new Card("", "🃏",num));

    }


    public void start() {
        System.out.println("牌:" + allCards);
        Collections.shuffle(allCards);
        System.out.println("洗牌:" + allCards);
        Map<String, List<Card>> players = new HashMap<>();
        List<Card> xmsw = new ArrayList<>();
        List<Card> kb = new ArrayList<>();
        List<Card> trys = new ArrayList<>();
        String[] names = {"小明神王", "魁拔", "特瑞易斯"};
        players.put("小明神王", xmsw);
        players.put("魁拔", kb);
        players.put("特瑞易斯", trys);
        List<Card> last = allCards.subList(allCards.size() - 3, allCards.size());
        int amount = (int) (Math.random() * names.length);
        players.get(names[amount]).addAll(last);


        for (int i = 0; i < allCards.size() - 3; i++) {
            switch (i % 3) {
                case 0 -> xmsw.add(allCards.get(i));
                case 1 -> kb.add(allCards.get(i));
                case 2 -> trys.add(allCards.get(i));
            }
        }
        System.out.println("地主：" + names[amount]);
        for (int i = 0; i <names.length; i++){
            Collections.sort(players.get(names[i]), new Comparator<Card>() {
                @Override
                public int compare(Card o1, Card o2) {
                    return o2.getNum()- o1.getNum();
                }
            });

        }
        for (Map.Entry<String, List<Card>> stair : players.entrySet()) {
            System.out.println(stair.getKey() + "的牌是" + stair.getValue());
        }

    }


}



