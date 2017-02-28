package us.cyrien.MineCordBotV1.listeners;

import net.dv8tion.jda.core.JDA;
import net.dv8tion.jda.core.entities.Member;
import net.dv8tion.jda.core.entities.TextChannel;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.TabCompleteEvent;
import us.cyrien.MineCordBotV1.main.MineCordBot;

import java.util.ArrayList;
import java.util.List;

public class TabCompleteV2 implements Listener {

    private MineCordBot mcb;
    private JDA jda;
    private List<String> tcList;
    private ArrayList<String> players;

    public TabCompleteV2(MineCordBot mcb) {
        this.mcb = mcb;
        jda = mcb.getJda();
        tcList = mcb.getMcbConfig().getTextChannels();
        players = new ArrayList<>();
    }

    @EventHandler
    public void onTabComplete(TabCompleteEvent e) {
        String[] buffers = e.getBuffer().split(" ");
        List<String> all = new ArrayList<>();
        if(buffers[buffers.length - 1].startsWith("@")) {
            for(String s : tcList){
                TextChannel tc = jda.getTextChannelById(s);
                if(tc != null)
                for(Member m : tc.getMembers())
                    all.add("@" + m.getUser().getName().replaceAll(" ", "_"));
            }
            e.setCompletions(all);
        }
    }
}
