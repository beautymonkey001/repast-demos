# Summary #

  * Model        : Bug Catcher Model
  * Platform     : RepastS ReLogo
  * Version(s)	: Repast Simphony 2.0
  * Download     : http://repast-demos.googlecode.com/files/BugCatcher-1.0.1.zip
  * SVN Source  : http://repast-demos.googlecode.com/svn/trunk/demos/BugCatcher
  * Documentation : This Wiki Page
  * Author       : Mudit Raj Gupta

> ![https://repast-demos.googlecode.com/svn/wiki/bugCatcher/main.jpg](https://repast-demos.googlecode.com/svn/wiki/bugCatcher/main.jpg)


---



# **Content** #




---


# Before Getting Started #

It is suggested that you go through the following models also, as the guiding algorithm is quite similar, although the implementation is entirely different.

  1. [Moth (Basic)](Moth_Basic.md)
  1. [Moth (Advanced)](Moth_Advanced.md)



---


# Introduction #

A fly-killing device is used for pest control. A fly-killing device typically attracts flying insects, for example houseflies, wasps, moths and mosquitoes. The insects are killed or released. We have modeled such a device which kills the bugs after a user specified time. It is based on Bug Zapper.

A bug zapper is a device that attracts and kills insects that are attracted by light. A light source attracts insects to an electrical grid, where they are electrocuted by touching two wires with a high voltage between them. The name stems from the characteristic zap sound produced when an insect is electrocuted.

A similar system is modeled here. Read the Description section to figure out the difference between the Moth (Advanced) model and BugCatcher Model.

The following video illustrates the use of bug zapper and that bugs can be seen dying due to electrocution.

<a href='http://www.youtube.com/watch?feature=player_embedded&v=Edv0v2mOL60' target='_blank'><img src='http://img.youtube.com/vi/Edv0v2mOL60/0.jpg' width='425' height=344 /></a>"/>


---


# Description #

## Why is it different? ##

The Bug Catcher model looks quite similar to the Moth (Advanced) model. Inthe  Moth (Advanced) model the intensity of the light source and the size of the light source are both adjustable, while when BugCatcher was modeled, it was assumed that the machine can catch bugs only in it's Moore's Neighborhood and this is fixed for all bug catching machines. The BugCatcher model can be made from the Moth (Advanced) model, but the way in which the two models are approached are different.

## Algorithm and implementation ##

In the BugCatcher model, bugs move around randomly in an area and the bug catching machines are at various positions. The bugs get attracted towards (the center) the bug catching machines and get caught in them. They are electrocuted after and they die after some time.

The population of the bugs and the number of bug catching machines are both adjustable by the user through the User Panel. The time between electrocution and death can also be changed by the user through the User Panel. The bug speed can also be controlled by the same.

The basic model has a turtle "bug" and two different types of patches are made:

  1. Machines
  1. Adjoining Catching Area

Let us first start by talking about [moth.groovy](http://code.google.com/p/repast-demos/source/browse/trunk/demos/BugCatcher/src/bugcatcher/relogo/Bug.groovy). This defines the characteristics of a bug. Taking the first block in the code.

```

        def step()
	{
		//bug gets attracted towards the machine 
		def dir = maxOneOf(neighbors()){
					
		count(machinesOn(it))}
		face(dir)
		
		//Bug Speed can be adjusted by the user						
		fd(bugSpeed*0.5)

```

In the _step()_ function defined in _bug.groovy_ the bug faces a machine if there is one around. Once the bug is facing the machine it moves forward a distance, half of the value entered by the user.

The bug checks weather the machine is in it's Moore's neighborhood. The task has been accomplished by the help of


If the bug is caught in any of the machines, it is electrocuted. In the process of electrocution the bug dies with a "Zap" sound. The bug although takes some time to get electrocuted and die. This time can be adjusted by a user interface. This behavior is modeled using the following code segment.

```

//If bug gets caught
		if(count(machinesHere())>0)
		{
			
			timeleft--
			
			//If specified time has lapsed bug dies
			if(timeleft==0)
			{
                                label = "Zap"			
				die()
			}	
		}

```

The two new functions used here are machinesHere() and die(). Both are used pretty commonly. You can check out the whole RepastS Relogo API [here](http://repast.sourceforge.net/docs/api/relogo/ReLogoPrimitives.html). Another statement which is worth a mention is the label statement. It is use to create fake "zap" sound or effect which is produced when the bug dies. timeleft is a variable that is declared above.

```

	//Time lapse between getting caught and death
	def timeleft=tym

```

Next we move on to a section which has some important code segment. This can be checked out at [UserObserver.groovy](http://code.google.com/p/repast-demos/source/browse/trunk/demos/BugCatcher/src/bugcatcher/relogo/UserObserver.groovy)

In this we have two basic functions **setup()** and **go()**. In the setup() function clearAll() is used to clear the space. A user-entered number of bugs of "bug" shape are created at random X and Y coordinates.

```

def setup(){
		
		clearAll()
		
		setDefaultShape(Bug,"bug")
		createBugs(numBugs){
			setxy(randomXcor(),randomYcor())
		}
		setDefaultShape(Machine,"circle")
		createMachines(numCatcher){
			setxy(randomXcor(),randomYcor())
			size=3
		}
		}

```

> The details of other functions can be found in the relogo reference [here](http://repast.sourceforge.net/docs/api/relogo/ReLogoPrimitives.html)

If you find the concept of shapes abstract, check out the shapes folder in your project explorer task bar. You will find the details of the shapes that are already made for you. You can also add your custom made shapes. These shapes can be applied to your agents just by using their names. An example of the same is in the code segment above. "bug" is one of the shapes in the shape folder.

Another line that is worth a mention is the "size=3" statement. This sets the size of the circles, i.e. the machines to be 3.


## User Panel ##

![https://repast-demos.googlecode.com/svn/wiki/bugCatcher/panel.jpg](https://repast-demos.googlecode.com/svn/wiki/bugCatcher/panel.jpg)

  * **Push Button (that rebounds)**

> - Set Up : For setting up the motes in the area according to the selected options and settings.

  * **Toggle Button**

> - Go : For running the model unless the button is toggled again

  * **Sliders**

> - Number of Bugs : Population

> - Bug Speed : Distance moved per tick

> - Number of Catchers : Number of machines catching bugs

> - Time : The time taken by electrocuted bugs to die.

## Data Sets, Outputters and External Plugin ##

A data set is added to the model. Same data set is used to create model outputter. Model Outputter is also added. The added model outputter is comma separated values of "Time" and "Bugs Alive". One of the sample output file can be checked [here](https://repast-demos.googlecode.com/svn/wiki/bugCatcher/Model%20Output.txt)

Such Outputter can be fed in various external plug-ins

## Plots ##

Various analysis could be carried out with the model, but for demonstration purpose a graph between, "Bugs Alive" on y-axis and "Time" on x-axis is plotted.

![https://repast-demos.googlecode.com/svn/wiki/bugCatcher/bug_count_plot.png](https://repast-demos.googlecode.com/svn/wiki/bugCatcher/bug_count_plot.png)

For the plot it can be concluded that the bug death is an exponential function. Other plots can also be added, one such example could be an overlay of the graphs showing which machine has killed how many bugs.


---


# Applications #

This model can be useful in studying social behavior. It is also useful in studying what could be most suitable position for placing a sink (machine). The model can be extended to fix the position of machines and can be used to find the optimal time for catching bugs.

The model can be used as reference and a multi-robot de mining simulation can be made on it. The bugs can be replaced by robots and they first find the mines and cluster around them and then drag the mines to a safe area. Go through the Area\_coverage model for more swarm robotics application.


---


# Extending the Model #

## Increasing the Range ##

An feature that can be incorporated in the model is a slider for increasing the catching distance of the bug catcher. In this model the machine only catches bugs in the Moore's neighborhood.

The user can modify the code and use it to catch bugs in even larger area. A similar implementation can be found in both [Moth (Advanced)](Moth_Advanced.md) and [Moth (Basic)](Moth_Basic.md) models.

## Surrounding Forbidden Area ##

An interesting feature that can be added to this model is the surrounding forbidden area. In food joints whenever a zapper or a bug catcher is used it is placed where there are no food items. This is done so that the dead bugs or some particles does not fall in food items.
So the user can add an interesting feature i.e. instead of killing the bug after a specified time user can hatch the bug into dead particles and kill it. These particles will randomly fall in the adjoining area. Thus making is unfit for keeping food items.

Hint : Use the hatch() primitive and make a new turtle - dead particles or may be modify the patch variable their.

## Addition of Plots and Outputters ##

Application specific graphs, outputters and plug in can be incorporated in the given model. One such example could be an overlay of the graphs showing which machine has killed how many bugs. Rate of killing bugs is already incorporated in the description. Other plots can also be made and is left to the reader/user.


---


# References #

  1. http://en.wikipedia.org/wiki/Bug_zapper
  1. http://home.howstuffworks.com/bug-zapper.htm
  1. http://en.wikipedia.org/wiki/Fly-killing_device