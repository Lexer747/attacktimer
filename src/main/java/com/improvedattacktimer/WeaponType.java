package com.improvedattacktimer;


/*
 * Copyright (c) 2017, honeyhoney <https://github.com/honeyhoney>
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

import static com.improvedattacktimer.AttackStyle.ACCURATE;
import static com.improvedattacktimer.AttackStyle.AGGRESSIVE;
import static com.improvedattacktimer.AttackStyle.CASTING;
import static com.improvedattacktimer.AttackStyle.CONTROLLED;
import static com.improvedattacktimer.AttackStyle.DEFENSIVE;
import static com.improvedattacktimer.AttackStyle.DEFENSIVE_CASTING;
import static com.improvedattacktimer.AttackStyle.LONGRANGE;
import static com.improvedattacktimer.AttackStyle.OTHER;
import static com.improvedattacktimer.AttackStyle.RANGING;
import java.util.Map;
import com.google.common.collect.ImmutableMap;
import lombok.Getter;

enum WeaponType
{
	TYPE_0(ACCURATE, AGGRESSIVE, null, DEFENSIVE),
	TYPE_1(ACCURATE, AGGRESSIVE, AGGRESSIVE, DEFENSIVE),
	TYPE_2(ACCURATE, AGGRESSIVE, null, DEFENSIVE),
	TYPE_3(RANGING, RANGING, null, LONGRANGE),
	TYPE_4(ACCURATE, AGGRESSIVE, CONTROLLED, DEFENSIVE),
	TYPE_5(RANGING, RANGING, null, LONGRANGE),
	TYPE_6(AGGRESSIVE, RANGING, CASTING, null),
	TYPE_7(RANGING, RANGING, null, LONGRANGE),
	TYPE_8(OTHER, AGGRESSIVE, null, null),
	TYPE_9(ACCURATE, AGGRESSIVE, CONTROLLED, DEFENSIVE),
	TYPE_10(ACCURATE, AGGRESSIVE, AGGRESSIVE, DEFENSIVE),
	TYPE_11(ACCURATE, AGGRESSIVE, AGGRESSIVE, DEFENSIVE),
	TYPE_12(CONTROLLED, AGGRESSIVE, null, DEFENSIVE),
	TYPE_13(ACCURATE, AGGRESSIVE, null, DEFENSIVE),
	TYPE_14(ACCURATE, AGGRESSIVE, AGGRESSIVE, DEFENSIVE),
	TYPE_15(CONTROLLED, CONTROLLED, CONTROLLED, DEFENSIVE),
	TYPE_16(ACCURATE, AGGRESSIVE, CONTROLLED, DEFENSIVE),
	TYPE_17(ACCURATE, AGGRESSIVE, AGGRESSIVE, DEFENSIVE),
	TYPE_18(ACCURATE, AGGRESSIVE, null, DEFENSIVE, CASTING, DEFENSIVE_CASTING),
	TYPE_19(RANGING, RANGING, null, LONGRANGE),
	TYPE_20(ACCURATE, CONTROLLED, null, DEFENSIVE),
	TYPE_21(ACCURATE, AGGRESSIVE, null, DEFENSIVE, CASTING, DEFENSIVE_CASTING),
	TYPE_22(ACCURATE, AGGRESSIVE, AGGRESSIVE, DEFENSIVE),
	TYPE_23(CASTING, CASTING, null, DEFENSIVE_CASTING),
	TYPE_24(ACCURATE, AGGRESSIVE, CONTROLLED, DEFENSIVE),
	TYPE_25(CONTROLLED, AGGRESSIVE, null, DEFENSIVE),
	TYPE_26(AGGRESSIVE, AGGRESSIVE, null, AGGRESSIVE),
	TYPE_27(ACCURATE, null, null, OTHER),
	TYPE_28(ACCURATE, ACCURATE, LONGRANGE),
	TYPE_29(ACCURATE, AGGRESSIVE, AGGRESSIVE, DEFENSIVE),
	// Weird TOA shit, only seems to happen for the keris with gems
	TYPE_30(ACCURATE, AGGRESSIVE, AGGRESSIVE, DEFENSIVE);

	@Getter
	private final AttackStyle[] attackStyles;

	private static final Map<Integer, WeaponType> weaponTypes;

	static
	{
		ImmutableMap.Builder<Integer, WeaponType> builder = new ImmutableMap.Builder<>();

		for (WeaponType weaponType : values())
		{
			builder.put(weaponType.ordinal(), weaponType);
		}

		weaponTypes = builder.build();
	}

	WeaponType(AttackStyle... attackStyles)
	{
		this.attackStyles = attackStyles;
	}

	public static WeaponType getWeaponType(int id)
	{
		return weaponTypes.get(id);
	}
}