# COMP 302: Software Engineering

Please RUN the Game class to see the current stage of the project.

Thank you

# Authors
 İREM ARPAG
 ARDA BAKIR
 BATUHAN ACAR
 HATİCE NUR YILDIZ
 HÜSEYİN BERK KILIÇ
 
KuVid is an interesting game that combines challenge and suspense. It takes place in an alternative reality where there are two alien parties, an evil party trying to destroy the life on earth and a good one trying to prevent that. The evil party has contaminated the atmosphere by a dangerous virus Kuvid-302. Unfortunately, the chemical elements available on earth cannot be used to cure that virus, it rather needs combinations of molecules from the KUripton  -the planet where the aliens live-. However, the required combinations can only be produced in the earth environment. The good aliens have succeeded to figure out the needed molecules from their side and they are sending them to earth. To keep these molecules stable so that they can be later combined to produce the cure, they need to be combined with certain elements(atoms) from the earth as soon as they enter the atmosphere to form stable compounds. We refer to the process of unifying an alien-sent molecule with the suitable atom from earth as “collecting these molecules”. The goal of the player is to collect as much as possible of these molecules  to make the cure in a certain amount of time, while minimizing the effect of the evil aliens who are trying to hinder the submission of these molecules by spreading reaction-blockers that affect the player and the formation of the stable compounds. The game can be played by single or multiple players. In single player mode, the goal is to collect the largest possible amounts of molecules within a certain amount of time. The faster the player collects them, the higher the score is. In multiple players mode, each player tries to collect the cure before the competitor.

The Ultimate Goal of the Player:
The player has a limited amount of time and limited health points. The goal is to collect the largest possible amount of molecules (or to form the largest amount of stable compounds) as fast as possible. The game ends either when the time limit is reached, or when the player’s health points become zero. The game is not a win/lose type of games, it is rather a score-based game. The score of a player is composed of two components: the number of collected compounds (representing the most significant scoring component), and the time to collect them (The least significant component). The player who collects more molecules by the end of the game has the higher score. If two players have collected the same amount of molecules, the player who did so in a shorter time gets a higher score.
The score calculation formula is: Number of collected molecules + (1 / Collection time in seconds)
1- Game Objects:
Here we are listing the objects to be present on the screen. These include the core objects and the objects used to show the statistics of the game. The player actions needed to control some of these objects are detailed in the next section “Player Actions”, while the initial numbers are specified in the “Building Mode” section .
1.1- Core-Objects:
1.1.1- Atom shooter
Atom Shooter
A horizontally-moving vehicle with a gun to shoot atoms on the molecules being sent by aliens. The gun can be rotated 180 degrees. The width of the vehicle is 0.5 L and the height is 1 L. The horizontal movement speed of the shooter is L/sec. The rotation speed of the gun is 90 degrees/sec. The shooter is located at the bottom of the screen (representing the surface of the earth).
The shooter has 100 health points at the beginning of the game that can be affected as will be explained in “Reaction-Blockers” subsection.
The atom in the shooter’s gun is selected randomly from the available atoms, it can be changed as explained in “Player Actions” section.
1.1.2- Molecules:
Molecules
The molecules required to make the cure. They fall in four main categories, we will label these molecules by the names of the atoms they require to stay stable in the earth environment, namely: Alpha-, Beta-, Gamma- and Sigma-. They initially appear at the top of the screen (representing the space) and fall towards the bottom. Due to having different weights, and due to the variable resistance of the air at different heights; these molecules will adopt different movement patterns while falling. We have two  movement patterns:
Straight : falling with a speed of L/sec with angle 90 degrees.
Zig-Zag: Falling with the speed of L/sec as well but with a 45 degrees alternating fashion. First 45 towards right then towards left, then right, then left  …etc. Before altering the direction a distance of L is to be travelled.
Since Alpha- are the lightest molecules, they will follow the zig-zag pattern until reaching the ground. Beta- will fall straight until passing one quarter of the gameview height then start zig-zag. Gamma- falls straight until passing the half of the gameview height then starts zig-zag. While Sigma- falls straight from the beginning till reaching the ground. 
The width of a molecule is 25% of L and the height is 25% of L. For Alpha- and Beta-, the molecule-structures are customizable as will be explained in the “building mode”.
To collect a molecule (form a compound) the atom (section 1.1.3) shot by the shooter should hit the bounding box of that molecule. 
1.1.3- Atoms:
Atoms
There are four types of atoms: Alpha, Beta, Gamma and  Sigma. Each of these atoms can be shot by the shooter. When hitting the corresponding molecule, they form a stable material (the compound - both the atom and the hitted molecule - disappear from the game view). The player score is increased by 1 as well. The atoms travel with the speed of L/sec, in straight lines, and with an initial angle depending on the shooter’s gun direction. When colliding with the borders of the game view they are reflected with an angle of 90 degrees. The diameter of the atom is 10% of L.
1.1.4- Reaction blockers
Reaction blockers
Thrown by the evil aliens, fall from top of the screen. There are four types of harmful materials that can prevent the reaction to form the needed stable materials. They can as well harm the player if they fall on the ground. These blockers do not have to collide with an object to affect it, they rather have a surrounding field represented by a circle centered at the blocker and having the radius of 0.5L. These blockers are Alpha-b, Beta-b, Gamma-b, Sigma-b. Each blocker blocks the unification of the corresponding atom and molecule and destroys them if they enter its field. If the blocker falls on the ground it explodes (or touches the shooter), the explosion field is circular with a radius of 2L. Any molecule or atom in this scope will be destroyed. And the player's health can be reduced by a factor of (gameview width / distance between the shooter and the blocker) if the shooter was in the field of the explosion. Reaction blockers have the same movement patterns of the corresponding molecules. The dimensions of them are also the same.
1.1.5- Powerups
 
Anti-blocker power-upsAre thrown by the good aliens. Each of them can help in destroying certain blockers. They are +Alpha-b, +Beta-b, +Gamma-b, +Sigma-b. To be used, they need to be first catched and saved by the player. To collect a powerup, the shooter needs to be moved to the place in which the powerup is falling. Powerups fall in straight lines always. Using the powerup needs to be initiated by the certain control as explained in “Player Actions” section. The powerups can be shot the same way as atoms. The power-up can destroy the corresponding blocker if it enters its field.
1.1.5.2 Powerups affecting other players: [Details will be added in phase 2]
 
1.1.6: Blender:
Blender
 
As the initial number of atoms is given to the players, they may run out of some atoms. The blender appears as an icon on the screen, the blender can be used to:
Blend atoms: two Alpha atoms to get Beta, 3 to get Gamma, 4 to get Sigma. Or blend 2 Betas to get Gamma, 3 to get Sigma. Or blend 2 Gamma to get Sigma.
Break atoms: the number of atoms produced by breaking an atom can be inferred by reversing the blending rules.
But how is the falling object decided?
In the building mode the total number of every object is specified. The falling objects (molecules, blockers, powerups) fall every certain amount of time determined by the hardship of the game as described in the “building mode”. The type of the object to fall is picked randomly each time.

1.2 Statistics window:
These include :
Player health: showing the player’s health points.
Icons for the available atoms with numbers.
Icons for the available(successfully catched) power-ups with numbers.
Blender Icon.

2- Player Actions:
Shooting the ball/ powerup: Arrow up.
Moving the shooter: left, right arrows.
rotating the shooter gun: “A” rotates to left by 10 degrees, “D” rotates right by 10 degrees.
Picking an atom to shoot: “C” button switches the atom on the top of the gun randomly.
Pausing, resuming the game: “P”, “R”.
Saving or loading a game: “S”, “L” (the game has to be paused to do these actions).
Blending/Breaking atoms: to blend or break atom from a certain rank to another(atoms ranks are: Alpha:1, Beta:2, Gamma:3, Sigma:4), first press “B”, then the type the rank of the source atom then the rank of the atom to be produced.
Picking a powerup: clicking its icon on the screen, it will appear at the top of the shooter like the atom. Then it can be shot using Arrow-up.

3- Save Load:  Details will be added in phase 2

4- Building Mode:
KUvid consists of two modes: building mode and running mode. The game always starts with the building mode. In building mode, the player specifies the initial settings of the game. In order to create a game environment, the user interacts with the system to specify the number of each game object ( atoms, reaction blockers, powerups, and molecules). In addition they specify the length unit L. For Alpha- and Beta-, there are two different molecular structures and the player can select one of the structures to appear in the game. For instance, a player can specify   200 Alpha- molecules  of linear structure. Additionally, if the player selected a linear Alpha- or Beta-, they can choose these molecules to be stationary or spinning around their center while falling. The player can also specify the difficult level of game: easy, medium, and hard. If the level is easy then each object will fall in 1 second, in medium every ½ second, and in hard in every ¼   seconds. By default, the game will have following numbers of objects:  
100 atoms of each type
100 molecules of each type and of any structure 
10 reaction blockers of each type
20 powerups of each type

The Phase II has two changes in the game. The first one involves the behavior of Atoms. The second one is an additional feature : save/load game.

Revised Atom Behavior 
It has been noticed that even when an atom hits a molecule of the corresponding type, the formed compound may not stay stable for a long period of time. This happens due to the fact that (as other elements in nature) Alpha, Beta, Gamma and Sigma have different isotopes and this affects the stability of the formed compound (we will measure the stability in terms of the atom efficiency which is explained in the next paragraph). Hence, we will change the score calculation accordingly. When an atom hits a molecule, the score will increase by the expected stability of the formed compound (which is equal to the efficiency of the atom that has formed that compound). 
The atom efficiency of each Isotope of an atom can be calculated knowing the number of the neutrons and protons of the atom, and a constant called stability constant. The equations, protones, neutrones are provided in the following:

Alpha
Alpha stability constant = 0.85
Efficiency = (1 - (| # of neutrons - # of protons | /  # of protons) ) * Alpha stability constant 
8 protones.
7, 8, 9 neutrons
Beta
Beta stability constant = 0.9
Efficiency = Beta stability constant - ( 0.5 * | # of neutrons - # of protons | /  # of protons) 
16 protons.
15, 16, 17, 18, 21 neutrones.
Gamma

Gamma stability constant = 0.8
Efficiency = Gamma stability constant +  ( | # of neutrons - # of protons | /  (2 * # of protons) )
32 protones.
29, 32, 33 neutrons.
Sigma

Sigma stability constant = 0.7
Efficiency = (1 + Sigma stability constant) / 2 +  ( | # of neutrons - # of protons | /  # of protons)
64 protones.
63, 64, 67 neutrons.

The number of neutrons is picked from the aforementioned values randomly when the atom is created.
To improve the efficiency of an atom, constructs called atomic shields were developed. Four types of these shields have been developed, they are called Eta, Lota, Theta and Zeta. shields. 
Eta shields improve the efficiency of the shielded atom by a factor of:
if # shielded atom neutrones != # shielded atom protones: 
(1 - shielded atom efficiency) * |# shielded atom neutrones - # shielded atom protones| / # shielded atom protones.
Otherwise:
(1 - shielded atom efficiency) * Eta_efficiency_boost 
Eta_efficiency_boost = 0.05 
Lota shields improve the efficiency of the shielded atom by a factor of
(1 - shielded atom efficiency) * Lota_efficiency_boost 
Lota_efficiency_boost = 0.1
Theta shields improve the efficiency of the shielded atom by a factor of
(1 - shielded atom efficiency) * Theta_efficiency_boost
Theta_efficiency_boost = is random between 0.05 and 0.15)
Zeta shields improve the efficiency of the shielded atom by a factor of
(1 - shielded atom efficiency) * Zeta_efficiency_boost 
(Zeta_efficiency_boost = 0.2)
Zeta improves the efficiency iff # shielded atom protons = # shielded atom neutrons

The shields can be applied to all atom types. 
A combination of shields may be applied. 
For example 2 Theta shield and one Lota. In this case the efficiency is calculated by chaining the effects of the shields.
The score increment when a shielded atom hits a corresponding molecule, as for normal one,is equal to its efficiency (Which calculated by chaining the effects of the shields).
The shields affect the speed of the shielded atom as well.
Each Eta shield reduces the speed by 5%.
Each Lota shield reduces the speed by 7%.
Each Theta shield reduces the speed by 9%.
Each Zeta shield reduces the speed by 11%
In the case of combined shields, the speed is calculated by chaining the effects of the shields. 
When to add shields to Atoms
- There will be an initial number of each type of shields given to the player at the beginning of the game. The initial numbers should be specified during the building mode.
-The shield types will appear on the screen as buttons containing the shield name and the remaining numbers.
- The player can add a shield of a specific type by pressing the corresponding button. For example, to add one Lota shield and two Theta shields, the player presses the Lota button once and the Theta button twice.
-The shields will be added to the atom at the top of the shooter.
-If the player did not shoot that atom (he/she changed the atom on the shooter after being shielded rather than shooting it), the shields will still apply to the same atom, meaning that the newly selected atom is unshielded and the already shielded atom will go back to the inventory while keeping its shields. 

