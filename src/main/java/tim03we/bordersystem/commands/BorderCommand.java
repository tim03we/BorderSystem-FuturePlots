package tim03we.bordersystem.commands;

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
import cn.nukkit.command.CommandSender;
import cn.nukkit.form.element.ElementButton;
import cn.nukkit.form.window.FormWindowSimple;
import tim03we.bordersystem.BorderSystem;
import tim03we.bordersystem.Language;
import tim03we.futureplots.FuturePlots;
import tim03we.futureplots.commands.BaseCommand;
import tim03we.futureplots.provider.DataProvider;
import tim03we.futureplots.utils.Plot;
import tim03we.futureplots.utils.PlotPlayer;

public class BorderCommand extends BaseCommand {

    public BorderCommand(String name, String description, String usage) {
        super(name, description, usage);
    }

    @Override
    public void execute(CommandSender sender, String command, String[] args) {
        if(sender instanceof Player) {
            if(sender.hasPermission("futureplots.command.border")) {
                DataProvider provider = FuturePlots.provider;
                PlotPlayer plotPlayer = new PlotPlayer((Player) sender);
                Plot plot = plotPlayer.getPlot();
                if (plot == null) {
                    sender.sendMessage(Language.translate(true, "not.on.plot"));
                    return;
                }
                if (!provider.getOwner(plot).equals(sender.getName())) {
                    sender.sendMessage(Language.translate(true, "not.the.owner"));
                    return;
                }
                FormWindowSimple gui = new FormWindowSimple(Language.translate(false, "form.title"), " ");
                for (String rands : BorderSystem.borders) {
                    String[] ex = rands.split(":");
                    gui.addButton(new ElementButton(ex[0]));
                }
                ((Player) sender).showFormWindow(gui);
            } else {
                sender.sendMessage(Language.translate(true, "no.perms"));
            }
        }
    }
}

