/*
 * This program is free software: you can redistribute it and/or modify it under
 * the terms of the GNU General Public License as published by the Free Software
 * Foundation, either version 3 of the License, or (at your option) any later
 * version.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU General Public License for more
 * details.
 *
 * You should have received a copy of the GNU General Public License along with
 * this program. If not, see <http://www.gnu.org/licenses/>.
 */
package net.sf.l2j.gameserver.handler;

import java.util.HashMap;
import java.util.Map;

import net.sf.l2j.Config;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.BankingCmd;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.BossInfo;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.EventsVoicedInfo;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Menu;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.Online;
import net.sf.l2j.gameserver.handler.voicedcommandhandlers.TvTEventCommand;

public class VoicedCommandHandler
{
    private final Map<Integer, IVoicedCommandHandler> _datatable = new HashMap<>();
    
    public static VoicedCommandHandler getInstance()
    {
        return SingletonHolder._instance;
    }
    
    protected VoicedCommandHandler()
    {
    	if (Config.BANKING_SYSTEM_ENABLED)
    	{
    		registerHandler(new BankingCmd());
    	}
    	registerHandler(new Menu());
    	registerHandler(new TvTEventCommand());
		registerHandler(new Online());
		if (Config.EVENTS_ALLOW_VOICED_COMMAND)
			registerHandler(new EventsVoicedInfo());
		registerHandler(new BossInfo());
    }
    
    public void registerHandler(IVoicedCommandHandler handler)
    {
        String[] ids = handler.getVoicedCommandList();
        
        for (int i = 0; i < ids.length; i++)        
            _datatable.put(ids[i].hashCode(), handler);
    }
        
    public IVoicedCommandHandler getHandler(String voicedCommand)
    {
        String command = voicedCommand;
        
        if (voicedCommand.indexOf(" ") != -1)        
            command = voicedCommand.substring(0, voicedCommand.indexOf(" "));        

        return _datatable.get(command.hashCode());        
    }
    
    public int size()
    {
        return _datatable.size();
    }
    
    private static class SingletonHolder
    {
        protected static final VoicedCommandHandler _instance = new VoicedCommandHandler();
    }
}