package net.sf.l2j.gameserver.handler.skillhandlers;

import net.sf.l2j.gameserver.event.EventManager;
import net.sf.l2j.gameserver.handler.ISkillHandler;
import net.sf.l2j.gameserver.model.actor.Creature;
import net.sf.l2j.gameserver.model.WorldObject;
import net.sf.l2j.gameserver.model.L2Skill;
import net.sf.l2j.gameserver.templates.skills.L2SkillType;
import net.sf.l2j.gameserver.model.actor.instance.Player;

public class Bomb implements ISkillHandler
{
	private static final L2SkillType[] SKILL_IDS = {L2SkillType.BOMB};
	
	@Override
	public void useSkill(Creature activeChar, L2Skill skill, WorldObject[] targets)
	{
		if (!(activeChar instanceof Player))
			return;
		EventManager.getInstance().getCurrentEvent().dropBomb((Player)activeChar);
		rentEvent().dropBomb((Creature)activeChar);
	}
	
	@Override
	public L2SkillType[] getSkillIds()
	{
		return SKILL_IDS;
	}
}