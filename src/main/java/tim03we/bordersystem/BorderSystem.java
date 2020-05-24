package tim03we.bordersystem;

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

import cn.nukkit.event.Listener;
import cn.nukkit.plugin.PluginBase;
import tim03we.bordersystem.commands.BorderCommand;
import tim03we.bordersystem.listener.PlayerFormResponded;
import tim03we.futureplots.handler.CommandHandler;

import java.util.ArrayList;

public class BorderSystem extends PluginBase implements Listener {

    private static BorderSystem instance;
    public static ArrayList<String> borders = new ArrayList<>();

    public static BorderSystem getInstance() {
        return instance;
    }

    @Override
    public void onEnable() {
        instance = this;
        saveDefaultConfig();
        Language.init();
        getServer().getPluginManager().registerEvents(new PlayerFormResponded(), this);
        registerCommands();
        borders.addAll(getConfig().getStringList("borders"));
    }

    private void registerCommands() {
        new CommandHandler().registerCommand("border", new BorderCommand("border", "Run this command", "/plot border"), getConfig().getStringList("aliases").toArray(new String[0]));
    }
}
