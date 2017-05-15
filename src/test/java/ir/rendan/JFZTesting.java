package ir.rendan;

import ir.rendan.dao.TeamDAO;
import ir.rendan.dao.UserDAO;
import ir.rendan.model.Team;
import ir.rendan.model.UserInfo;

/**
 * Created by SalehJFZ on 14/05/2017.
 */
public class JFZTesting {
    public static void main(String[] args) {
       userInit("user",5);

        UserDAO UD = new UserDAO();
        UserInfo ui = UD.getByUserName("user0");

        TeamDAO TD = new TeamDAO();
        Team ti1= new Team("چموش ها",ui);
        TD.insert(ti1);

        Team ti = TD.getByName("چموش ها");

        ti.addMember(UD.getByUserName("user1"));
        TD.update(ti);

        ti.deleteMember(UD.getByUserName("user1"));

        TD.update(ti);


    }

    public static void userInit(String tempname, int count){
        UserDAO UD = new UserDAO();
        for (int i = 0; i < count; i++) {
            UserInfo ui = new UserInfo(tempname+""+i,"pass"+i, (short) 1,"admin");
            UD.insert(ui);
        }
    }


}
