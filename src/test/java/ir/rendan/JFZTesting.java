//package ir.rendan;
//
//
//import ir.rendan.model.League;
//import ir.rendan.model.Match;
//import ir.rendan.model.Team;
//import ir.rendan.model.UserInfo;
//
//import java.util.HashMap;
//import java.util.HashSet;
//
///**
// * Created by SalehJFZ on 14/05/2017.
// */
//public class JFZTesting {
//    public static void main(String[] args) {
//
////
//
//        userInit("user",5);
//
//        UserDAO UD = new UserDAO();
//        UserInfo ui = UD.getByUserName("user0");
//
//        TeamDAO TD = new TeamDAO();
//        Team ti1= new Team("چموش ها",ui);
//        TD.insert(ti1);
//
//        GenericDAO GD = new GenericDAO();
//
//        League l = new League();
//        GD.insert(l);
//        l.scores = new HashMap<>();
//        l.scores.put(0, (float) 1.0);
//        GD.update(l);
//
//        Match m = new Match();
//
//        GD.insert(m);
//
//        l.matches = new HashSet<>();
//        l.matches.add(m);
//
//        l.scores.put(0, (float) 5.0);
//
//        GD.update(l);
//
//    }
//
//    public static void userInit(String tempname, int count){
//        UserDAO UD = new UserDAO();
//        for (int i = 0; i < count; i++) {
//            UserInfo ui = new UserInfo(tempname+""+i,"pass"+i, (short) 1,"admin");
//            UD.insert(ui);
//        }
//    }
//
//
//}
