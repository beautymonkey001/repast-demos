# Summary #

  * Model        : Ant
  * Platform     : RepastS ReLogo
  * Version(s)	: Repast Simphony 2.0
  * Download     : http://repast-demos.googlecode.com/files/Ant-1.0.1.zip
  * SVN Source  : http://repast-demos.googlecode.com/svn/trunk/demos/Ant
  * Documentation : This Wiki Page
  * Author       : Mudit Raj Gupta


---


![https://repast-demos.googlecode.com/svn/wiki/ant/main.jpg](https://repast-demos.googlecode.com/svn/wiki/ant/main.jpg)

# Contents #




---


# Before Getting Started #


It is suggested that you go through the following model also, as the guiding algorithm is quite similar and it is also related to swarm robotics.

  1. [GuidingBots](GuidingBots.md)
  1. [FollowTheLeader](FollowTheLeader.md)
  1. [Ant](Ant.md)

# Introduction #

This is a bio-inspired model. The model is a simulation of Ant and their behavior. The model demonstrates how ants move away from their nests in search for food and then come back to the nest with food. In their way back they leave a chemical behind which can be sensed by other ants and they can also reach the food pile. In their way reinforcing the chemical left behind. This behavior is useful to researchers from different field. Specially in Swarm Robotics and Computation Intelligence. The Ant Colony Optimization is used. A more clearer study of the Ant behavior can be done through [FollowTheLeader](FollowTheLeader.md) model. In that model a single ant is studied and how the follower adjusts it's path.

The first paper which used ant behavior was published in the year 1996 was :

> "Ant System: Optimization by a Colony of Cooperating Agents", Marco Dorigo, Vittorio Maniezzo and Alberto Colorni, IEEE TRANSACTIONS ON SYSTEMS, MAN, AND CYBERNETICS-PART B CYBERNETICS, VOL 26, NO 1, FEBRUARY 1996, 1083-4419, IEEE 1996

There where many papers like this since then. An article which provides a comprehensive detail of the algorithm is :

> "Ant Colony Optimization - Artificial Ants as a Computation Intelligence Technique" - Marco Dorigo, Mauro Birattari and Thomas Stutzle, IEEE COMPUTATIONAL INTELLIGENCE MAGAZINE, NOV 2006, 1556-603X/06/, IEEE 2006

The model seeks inspiration form the real world ants. Although it is not a replica and is optimized at places. Please view the description section for complete details.

## Ant (A bit of Biology) ##

Ants are social insects of the family Formicidae and, along with the related wasps and bees, belong to the order Hymenoptera. Ants evolved from wasp-like ancestors in the mid-Cretaceous period between 110 and 130 million years ago and diversified after the rise of flowering plants. More than 12,500 out of an estimated total of 22,000 species have been classified.

Ants communicate with each other using pheromones. Like other insects, ants perceive smells with their long, thin and mobile antennae. The paired antennae provide information about the direction and intensity of scents. Ants use pheromones for more than just making trails. A crushed ant emits an alarm pheromone that sends nearby ants into an attack frenzy and attracts more ants from further away. Several ant species even use "propaganda pheromones" to confuse enemy ants and make them fight among themselves.

## Ant and it's search for food ##

Since most ants live on the ground, they use the soil surface to leave pheromone trails that can be followed by other ants. In species that forage in groups, a forager that finds food marks a trail on the way back to the colony; this trail is followed by other ants, these ants then reinforce the trail when they head back with food to the colony. When the food source is exhausted, no new trails are marked by returning ants and the scent slowly dissipates. This behavior helps ants deal with changes in their environment. For instance, when an established path to a food source is blocked by an obstacle, the foragers leave the path to explore new routes.


---


# Model Description #

The overall ant colony behavior is a cumulative behavior of all the ants. These ants follow a set of simple rules and depending of these rules they move in search of food.

Ant only move a given distance away from their nest. This is because they have to come back to their nest. The ant return to their nest depending on the nest smell, they do so when the nest scent starts dying. If the ants find food, they carry it back to their nest leaving a chemical trail which evaporates with time. The chemical is also diffused in the Moore's neighborhood, although the "level" is less.
If an ant sniffs Active Chemical it moves in the direction where the chemical is for a large number of time. This behavior is a deviation from the actual ants. They normally follow the chemical depending on level, but these computational ants move towards food not nest. The ants reach the food pile, following the chemical and finally the come back to the nest reinforcing the chemical trail.

## Running the Model ##

The user can control the population of the ants and the amount of food in each food pile. Standard Setup, Go and Go Once Options are also present. User Panel looks like this :

![https://repast-demos.googlecode.com/svn/wiki/ant/panel.jpg](https://repast-demos.googlecode.com/svn/wiki/ant/panel.jpg)

### Output ###

Model Outputter is also added. The added model outputter is comma separated values of "Time" and "Food" (in each pile). One of the sample output file can be checked [here](https://code.google.com/p/cscs-repast-demos/source/browse/wiki/Ants/Model%20Output.txt)

**Note:**

> This Output can be fed into various external plug-ins available with RepastS like MATLAB and spreadsheet.

### Analysis ###

Various analysis could be carried out with the model, but for demonstration purpose a graph between, "Food" (in each pile) on y-axis and "Time" on x-axis is plotted. It is an overlay of the graph for each food pile.

![https://repast-demos.googlecode.com/svn/wiki/ant/pilesize.png](https://repast-demos.googlecode.com/svn/wiki/ant/pilesize.png)

For the plot it can be concluded that the ants exploit the food source which is closest to the nest first. _Check with many test runs_

### Variable Evaporation and Diffusion Rates ###

A slider can be added for giving the control for controlling the rate of evaporation of chemical tails. A slider controlling the spread of the neighboring area, till where the chemical is spread would also be useful.


---


## Algorithm and Implementation ##

The model has one turtle type i.e. Ant

There are three types of patches
  1. Cube
  1. Nest
  1. Diffused Chemical
  1. Dropped Chemical

Let's talk about each of these separately :

### Ant ###

An ant can have four basic states :
  * gotCube
The variable is set or reset depending on weather or not the ant is carrying food.

  * nearChem
The variable is set or reset depending on weather or not the ant is near a chemical or not.

  * inNest
The variable is set or reset depending on weather or not the ant is in it's nest or not.

  * away (Going Away or Coming Back)
The variable is set or reset depending on weather or not the ant is going away or coming back to the nest, depending on nest scent

```
        def inNest=1   // (In nest or not) 
	def away=1     // (Going Away or Coming back)
	def gotCube=0  // (Carrying Food)
	def nearChem=0 // (Near Chemical) 

```

At each tick a step() function is called most of the code is commented and self explainable. The functions used are elaborated in details further

```

//Called at each clock tick, decides the motion of the ant depending of state	
	def step()
	{	
		
		//Checks if the ant is near a cube and sets gotCube
		foundCube()
		
		//Checks if the ant is in it's nest and sets inNest
		foundNest()

		//Checks if the ant is near a chemical and sets nearChem
		checkChem()

		//Sets the direction of travel of the Ant by setting away
		setDir()

		//if ants finds food
		if(gotCube)
			//drops Chemical 
			drop()
		
		//movement of the ant, if ant is not near chemiCal 
		if(!nearChem)
		{
			//if ant should move away from the nest
			if(away)
				goAway()
			else
				comeBack()
		}
	}

```

The two functions **goAway()** and **comeBack()** are called depeding on the value of away. If there is no ActiveChem nearby, the ant movement is guided by these functions. The ants move in an area where there is more nest scent. This is illustrated by the given figure.

Since the nest scent is nothing but, distance from the nest. So, the ants can move at any between 0-90 in both direction. This is done using the **move()** function. So they are first oriented towards the nest or away and they move() is called.

The following block of code guides the movement of the Ant, if it is not near chemical.

```

//Makes the Ant move away from the nest
	def goAway()
	{
		facexy(0,0)
		left(180)
		move()
	}

	//Makes the Ant move towards the nest
	def comeBack()
	{
		facexy(0,0)
		move()
	}

	//Randomly sets the angle, while moving on increasing/decreasing scent smell
	def move()
	{
		if(random(2))
			left(random(90))
		else
			left(-1*random(90))
		forward(1)
	}

```

The following code segment and description explains what happens if the ant finds a chemical nearby. Please refer to http://repast.sourceforge.net/docs/api/relogo/ReLogoPrimitives.html for the functions in italics.

```
//Checks if the present patch has chemical 
	def checkChem()
	{
		if((patchHere().level>0)&&(!gotCube))
		{
			nearChem=1
			uphill("level")
		}		
		else
			nearChem=0
	}
```

_patchHere()_ and _uphill()_ functions are built in functions can be checked in the [documentation](http://repast.sourceforge.net/docs/api/relogo/ReLogoPrimitives.html). The variable "level" is a @Diffusible variable of the UserPatch.groovy class. Rest of the code segment is self explanatory.

If gotCube is set, i.e. ant has a cube then it calls **drop()**, check step() function. Two UserPatch.groovy functions are called through this function (set\_main() and set\_diffused(). The patch where the ant was will have the maximum chemical value and the chemical will as well diffuse in other cells also i.e. in the Moore's neighborhood. _ask()_ is a predefined function, check for details.

```
//Drops chemical 
	def drop()
	{
		ask(patchHere())
		{
			//Drops a chemical which will evaporate after long time
			set_main()
		}
		ask(neighbors())
		{
			//The chemical diffuses in the neighborhood
			set_diffused()
		}
	} 

```

foundNest(), foundCube() and setDir() are self-explanatory and can be viewed [here](http://code.google.com/p/repast-demos/source/browse/trunk/demos/Ant/src/ant/relogo/Ant.groovy)

### Patches ###

The following is the variable description of the class.

```

@Diffusible
		def level = 0     //Depics the Chemical Level
		def cubes=0       //Number of Cubes
		def nest=0        //The patch is a part of the nest

		def dropped = 0   //Set if an ant carrying cube passed over it
		def diffused = 0  //Set if an ant carrying food passed through it's neighbor
		def val=10        //Time of evaporation
		def lim=val       //Limit till which chemical can be sniffed

```

The variables which are declared as @Diffusible in the patch can be accessed in other classes. Rest variables are used only in the given class.

# Applications #

## Data Routing ##

Ant behavior or algorithms inspired by such a behavior like Ane Colony Oprimization are used in data routing between nodes. Although they face a problem of high overhead delay in case of ACO application in Mobile Wireless Sensor networks data routing.

## Multi variable Optimizations ##

Algorithms inspired from such a behavior are used in multi variable optimization and solving various problems like the _Traveling Salesman Problem_

## Other Applications ##

Ant behavior is significant to researchers form various fields and the application differs from subject to subject.



---


# References #

  1. "Ant System: Optimization by a Colony of Cooperating Agents"**, Marco Dorigo, Vittorio Maniezzo and Alberto Colorni, IEEE TRANSACTIONS ON SYSTEMS, MAN, AND CYBERNETICS-PART B CYBERNETICS, VOL 26, NO 1, FEBRUARY 1996, 1083-4419, IEEE 1996
  1. "Ant Colony Optimization - Artificial Ants as a Computation Intelligence Technique"** - Marco Dorigo, Mauro Birattari and Thomas Stutzle, IEEE COMPUTATIONAL INTELLIGENCE MAGAZINE, NOV 2006, 1556-603X/06/, IEEE 2006
  1. **"A Multi-Agent Model to simulate Intelligent Wireless Sensor Networks"** - Diana Carolina Resprepo, Alcides de J. Montoya and Demetrio Arturo Ovalle. 384978-1-4244-7173-7, IEEE 2010
  1. http://ccl.northwestern.edu/netlogo/models/Ants
  1. http://en.wikipedia.org/wiki/Ant#Communication
  1. http://en.wikipedia.org/wiki/Ant_colony_optimization
  1. http://repast.sourceforge.net/docs/api/relogo/ReLogoPrimitives.html
