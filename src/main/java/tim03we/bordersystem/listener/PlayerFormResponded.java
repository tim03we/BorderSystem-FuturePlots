package tim03we.bordersystem.listener;

/*
 * This software is distributed under "GNU General Public License v3.0".
 * This license allows you to use it and/or modify it but you are not at
 * all allowed to sell this plugin at any cost. If found doing so the
 * necessary action required would be taken.
 *
 * BorderSystem-FuturePlots is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the
 * GNU General Public License v3.0 for more details.
 *
 * You should have received a copy of the GNU General Public License v3.0
 * along with this program. If not, see
 * <https://opensource.org/licenses/GPL-3.0>.
 */

import cn.nukkit.Player;
import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.player.PlayerFormRespondedEvent;
import cn.nukkit.form.window.FormWindowSimple;
import tim03we.bordersystem.BorderSystem;
import tim03we.bordersystem.Language;
import tim03we.futureplots.utils.Plot;
import tim03we.futureplots.utils.PlotPlayer;

public class PlayerFormResponded implements Listener {

    @EventHandler
    public void onFormResponded(PlayerFormRespondedEvent event) {
        Player player = event.getPlayer();
        if (event.getWindow() instanceof FormWindowSimple) {
            if(event.getResponse() == null) {
                return;
            }
            FormWindowSimple gui = (FormWindowSimple) event.getWindow();
            String responseName = gui.getResponse().getClickedButton().getText();
            if(Language.translate(false, "form.title").equals(gui.getTitle())) {
                PlotPlayer plotPlayer = new PlotPlayer(player);
                Plot plot = plotPlayer.getPlot();
                if (plot == null) {
                    player.sendMessage(Language.translate(true, "not.on.plot"));
                    return;
                }
                if (!plotPlayer.isOwner()) {
                    player.sendMessage(Language.translate(true, "not.the.owner"));
                    return;
                }
                for (String list : BorderSystem.borders) {
                    String[] ex = list.split(":");
                    if(ex[0].equals(responseName)) {
                        if(!ex[3].equals("none")) {
                            if (!player.hasPermission(ex[3])) {
                                player.sendMessage(Language.translate(true, "no.border.perm"));
                                return;
                            }
                        }
                        plot.changeBorder(Block.get(Integer.parseInt(ex[1]), Integer.parseInt(ex[2])));
                        player.sendMessage(Language.translate(true, "success"));
                    }
                }
            }
        }
    }
}
