<h3>Project Assumptions</h3>

<p><b>Character:<b/></p>
<ol>
<li>Character will start moving from the Hero's Castle automatically until the character meets an enemy.</li>
<li>Character will keep moving after defeating the enemy along the path grid.</li>
<li>Character will die if the HP falls to zero, ending the game (ie. Game Over).</li>
<li>Character will have a basic attack power of 3 at the beginning of the game.</li>
<li>Character has a HP range between 0 and 100.</li>
<li>The Character's gold amount ranges between 0 and 9999.</li>
<li>The maximum number of items is 15.</li>
<li>Character moves clockwise.
</ol>

<p><b>Enemies:</b></p>
<p>- Slugs</p>
<ol>
<li>Slugs population will increase with maximum 3 on one path grid after finish 5 cycles. However, before 5 cycles are completed, only 1 slug will be randomly produced on each path grid.</li>
<li>Slugs won't drop rare items.</li>
<li>Slugs can provide a small radius for supporting the battle.</li>
<li>Slugs are produced randomly on pathtile.</li>
<li>Slugs that are defeated will drop a few XP and gold.</li>
<li>Slugs will inflict 1 HP damage each attack.</li>
<li>Slugs have 8 HP.</li>
<li>Slugs have a battle raidus of 5 and a support radius of 5</li>
<li>After a slug is defeated, the Character gains 100 gold and 20 experience points</li>
<li>Slugs move with a 50% chance of clockwise and 50% chance of anticlockwise.
</ol>

<p>- Zombies:</p>
<ol>
<li>Zombies will attack the allied soldier first - provided there is one.</li>
<li>Zombies may drop rare items.</li>
<li>Zombies can provide a medium radius for supporting the battle.</li>
<li>Zombies can do critical bites randomly. The critical bites will transform an allied soldier into zombies.</li>
<li>After the character finishes one cycle of the path, the zombie pit will produce one zombie nearby.</li>
<li>Zombies that are defeated will drop a lot XP and gold, and other items are dropped at a random.</li>
<li>Zombies will deal 3 HP damage, and critical bites deal 9 HP damage.</li>
<li>Zombies have 8 HP.</li>
<li>Zombies have a battle radius of 10 and a support radius of 5</li>
<li>After a Zombie is defeated, the Character gains 200 gold and 30 experience points</li>
<li>Zombies move with 50% chance of clockwise and 50% chance of anticlockwise.
</ol>

<p>- Vampires:</p>
<ol>
<li>Vampires drop rare items with high probability.</li>
<li>Vampires can provide a large radius for supporting the battle.</li>
<li>Vampires can inflict critical bites randomly. The critical bites will do extra damage randomly 1 to 3 times of the normal bites.</li>
<li>Character using stake weapon causes the damage dealt to become 3 times of the normal attack.</li>
<li>If the campfire is adjacent to the battleground, vampires will change its position to get out of range.</li>
<li>After the character finish 5 cycles of the path, the vampire castle will produce one vampire nearby.</li>
<li>Vampires that are defeated will drop amount of XP and coins, must drop potions, cards and equipment randomly.</li>
<li>Vampires will deal 5 HP damage.</li>
<li>Vampires have 15 HP.</li>
<li>Vampires have a battle radius of 10 and a support radius of 15</li>
<li>After a Vampire is defeated, the Character gains 500 gold and 50 experience points</li>
<li>Vampires move with a 50% chance of clockwise and 50% chance of anticlockwise.
</ol>

<p>- Doggies:</p>
<ol>
<li>Randomly create in the path of the game map after 20 cycles.
<li>Doggies drop rare items with high probability and it must drop a doggieCoin after defeated.</li>
<li>Doggies that are defeated will drop amount of XP and coins, must drop potions, cards and equipment randomly.</li>
<li>Doggies will deal 6 HP damage.</li>
<li>Doggies have 20 HP.</li>
<li>Doggies have a battle radius of 5 and a support radius of 5.</li>
<li>Doggie will ramdomly stun the character which makes character and alied soldiers cannot attact for a while.</li>
<li>After a Doggie is defeated, the Character gains 50 gold and 100 experience points</li>
<li>Doggies move with a 50% chance of clockwise and 50% chance of anticlockwise.
</ol>

<p>- Elan Muske:</p>
<ol>
<li>Elan Muske drop rare items and decrease the price of DoggieCoin with high probability.</li>
<li>Elan Muske that are defeated will drop amount of XP and coins, must drop potions, cards and equipment randomly.</li>
<li>Elan Muske will deal 9 HP damage.</li>
<li>Elan Muske have 25 HP.</li>
<li>It would heal the other enemy NPCs with 3 HP each time (randomly occur).</li>
<li>Elan Muske have a battle radius of 5 and a support radius of 5, same as Slug.</li>
<li>After a Elan Muske is defeated, the Character gains 50 gold and 100 experience points.</li>
<li>Elan Muske move with a 50% chance of clockwise and 50% chance of anticlockwise.</li>
</ol>

<p><b>Allied Soldier:</b></p>
<ol>
<li>Allied soldiers are produced by barracks, every 5 cycles it will produce one soldier, for a maximum 3 soldiers exist at the same time near one barrack.</li>
<li>Only when the character is on the barrack grid, if there is a battle, soldiers will join the battle for supporting.</li>
<li>After the allied soldier dies, they will drop some coins.</li>
</ol>

<p><b>Building:</b></p>
<p>All the buildings except Hero's Castle have a card. Building Cards can only be used to build the buildings. The maximum number of cards allowed will be 7, if the numbers are more than 7, the oldest card will transfer to the coins and XP.</p>

<p>- Vampire Castle & Zombie Pit</p>
<ol><li>
It can only be adjacent to the pathtile.</li></ol>

<p>- Village</p>
<ol>
<li> When the character passes through the village, the character will recover 30% of the full HP.</li>
<li>Only can build on a pathtile.</li></ol>

<p>- Tower</p>
<ol><li>
If the enemy is in the radius of the Tower, it will attack automatically.</li>
<li>It can only be built adjacent the pathtile.</li></ol>

<p>- Barracks</p>
<ol><li>
It can only be built adjacent to the pathtile.</li></ol>

<p>- Trap</p>
<ol><li>
Only built on the pathtile.</li>
<li>One trap can deal 8 HP damage.</li>
<li>After the trap kills the eneimes, it will destroyed.</li></ol>

<p>- Campfire</p>
<ol><li> If the Character is within the campfire radius during battle, their damage infliction is increased by 2 fold.</li>
<li>It can only be built adjacent the pathtile.</li></ol>

<p>- Hero's Castle</p>
<ol><li>
Exists at the starting position of the Character (not spawned by a card, always exists).</li>
<li>Character can purchase items in the castle when the character enters the castle.</li></ol>

<p><b>Basic items:</b></p>
<p>All the items except gold can be purchased in the Hero's Castle, loot from enemies, or obtained from cards lost due to being the oldest and replaced.<p>

<p>- Sword</p>
<ol><li>
Basic weapon will deal 3 HP damage.</li></ol>

<p>- Stake</li>
<ol><li>
Lower attack boost than the sword but deals a high damage specifically to vampires.</li>
<li>2 HP damage, if enemies are vampires, increases damage inflicted by the character by 5 HP.</li></ol>

<p>- Staff</p>
<ol><li>
Lower attack than both sword and staff - only boost character damage by 1 HP Damage Point.</li>
<li>A random attack transforms the enemy into an allied soldier and supports the character.</li>
<li>If the trance wears off and the rest of the enemies are still alive, the tranced enemy will return to a normal enemy. If rest of the enemies are defeated, the tranced enemy will die immediately and drop some gold.</li></ol>

<p>- Armour</p>
<ol><li>
Defends against the enemies attack and halves the attack damage taken.</li></ol>

<p>- Shield</p>
<ol><li>Defends against enemy attacks. Critical vampire attacks have a 60% lower chance of occurring.</li></ol>

<p>- Helmet</p>
<ol><li>Defends against enemy attacks, enemy attacks are reduced by 1 HP Damage Point.</li>
<li>If character wears a helmet, then reduce the total damage that can cause by character of 2 HP damage.</li></ol>

<p>- Gold</p>
<ol><li>
Used for purchasing items.</li></ol>

<p>- Health potions</p>
<ol><li>Used for refilling character health.</li></ol>

<p>- The One Ring</p>
<ol><li>Respawns the character to full health only once.</li></ol>

<p>- DoggieCoin</p>
<ol><li>Price fluctuates at a Random state.</li></ol>

<p>- Anduril, Flame of the West</p>
<ol><li>Has 10 basic attack damage to all kinds of enemies, triple damage against the bosses.</li></ol>

<p>- Tree Stump</p>
<ol><li>When the character has a Tree stump and wears it, it increases the defence of the character by 10 points.</li></ol>

<p><b>Goals</b></p>

<p>To win:</p>
<ol>
<li>Standard Mode: 100 Experience Points and 100 Gold to win</li>
<li>Beserker Mode: As there are already limitations to the Player with the increased difficulty, the goals required stays the same - 100 Experience Points and 100 Gold to win and complete the leve</li>
<li>Survival Mode: The hardest level of the modes, similar to berserker, since there are already limitations to the player, the goals required stays the same - 100 Experience Points and 100 Gold to win and complete the level</li>
<li> Confusing Mode: A new level mode, the limitaions carry across from survival mode but with the items and rare items looking the same. Thus, the goals required stays the same - 100 Experience Points and 100 Gold to win and complete the level</li></ol>

<p>To Lose:</p>
<ol>
<li> Loss of HP</li>
</ol>

<p><b>New Game</b></p>
<p> We assume that in order to start a new game, the player must first exit the current game they are in by pressing return to main menu from the game stop menu before transitioning into a new game whether they had saved the previous game or not.</p>

<p><b> Save and Load Game</b></p>
<ol><li> We assume that in order to load a new game, the player must first exit their current game state into the main menu before they can load a new game.</li>
<li> We also assume that in order to start a new game, the player must be within the main menu screen and not within a current game</li></ol>

<p><b> Shop </b></p>
<p> To buy and sell items at the shop, each item have their own individual purchase/selling price.</p>
<ol>
<li> Sword - Costs 5 Gold to buy, 3 to sell</li>
<li> Stake - Costs 4 Gold to buy, 2 to sell</li>
<li> Staff - Costs 4 Gold to buy, 2 to sell</li>
<li> Shield - Costs 3 Gold to buy, 1 to sell</li>
<li> Armour - Costs 3 Gold to buy, 1 to sell</li>
<li> Helmet - Costs 1 Gold to buy, 0 to sell</li>
<li> Health Potion - 8 Gold to buy, 0 to sell. Health potions are consumed immediately upon buying from the shop.</li></ol>
