package com.attacktimer.VariableSpeed;

/*
 * Copyright (c) 2022, Nick Graves <https://github.com/ngraves95>
 * Copyright (c) 2024, Lexer747 <https://github.com/Lexer747>
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 *
 * 1. Redistributions of source code must retain the above copyright notice, this
 *    list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright notice,
 *    this list of conditions and the following disclaimer in the documentation
 *    and/or other materials provided with the distribution.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND
 * ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE LIABLE FOR
 * ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */

import com.attacktimer.AnimationData;
import com.attacktimer.AttackProcedure;
import com.attacktimer.AttackStyle;
import com.attacktimer.ClientUtils.Utils;

import net.runelite.api.Client;
import net.runelite.api.Varbits;
import net.runelite.api.WorldType;
import net.runelite.api.events.GameTick;

import java.util.Map;

public class Leagues implements IVariableSpeed
{
    public int apply(final Client client, final AnimationData curAnimation, final AttackProcedure atkProcedure, final int baseSpeed, final int curSpeed)
    {
        int leagueRelicVarbit = 0;
        if (client.getWorldType().contains(WorldType.SEASONAL))
        {
            leagueRelicVarbit = client.getVarbitValue(Varbits.LEAGUE_RELIC_1);
        }
        
        if (leagueRelicVarbit == 0)
        {
            return baseSpeed;
        }

        AttackStyle attackStyle = Utils.getAttackStyle(client);
    
        int meleeMastery = client.getVarbitValue(Varbits.LEAGUES_MELEE_COMBAT_MASTERY_LEVEL);
        int rangedMastery = client.getVarbitValue(Varbits.LEAGUES_RANGED_COMBAT_MASTERY_LEVEL);
        int magicMastery = client.getVarbitValue(Varbits.LEAGUES_MAGIC_COMBAT_MASTERY_LEVEL);
        
        Map<AttackStyle, Integer> masteryMap = Map.of(
                AttackStyle.RANGING, rangedMastery,
                AttackStyle.LONGRANGE, rangedMastery,
                AttackStyle.ACCURATE, meleeMastery,
                AttackStyle.AGGRESSIVE, meleeMastery,
                AttackStyle.CONTROLLED, meleeMastery,
                AttackStyle.DEFENSIVE, meleeMastery,
                AttackStyle.CASTING, magicMastery,
                AttackStyle.DEFENSIVE_CASTING, magicMastery
        );
    
        if (masteryMap.containsKey(attackStyle)) {
            int masteryLevel = masteryMap.get(attackStyle);
            return applySpeedReduction(baseSpeed, masteryLevel);
        }
    
        return baseSpeed;
    }
    
    private int applySpeedReduction(int baseSpeed, int masteryLevel) {
        if (masteryLevel >= 5) {
            return applyHalfSpeedReduction(baseSpeed);
        } else if (masteryLevel >= 3) {
            return apply80PercentReduction(baseSpeed);
        }
        return baseSpeed;
    }
    
    private int apply80PercentReduction(int baseSpeed)
    {
        return  (int) Math.floor(baseSpeed * 0.8);
    }

    private int applyHalfSpeedReduction(int baseSpeed)
    {
        if (baseSpeed >= 4) {
            return baseSpeed / 2;
        } else {
            return (baseSpeed + 1) / 2;
        }
    }

    public void onGameTick(Client client, GameTick tick) {}
}
