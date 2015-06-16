# Summary #

  * Model        : Area Coverage
  * Platform     : RepastS ReLogo
  * Version(s)	: Repast Simphony 2.0
  * Download     : http://repast-demos.googlecode.com/files/areaCoverage-1.0.1.zip
  * SVN Source  : http://repast-demos.googlecode.com/svn/trunk/demos/areaCoverage
  * Documentation : This Wiki Page
  * Author       : Mudit Raj Gupta


---



![https://repast-demos.googlecode.com/svn/wiki/areacoverage/main.jpg](https://repast-demos.googlecode.com/svn/wiki/areacoverage/main.jpg)

# Contents #




---


# Before Getting Started #


It is suggested that you go through the following model also, as the guiding algorithm is quite similar and it is also related to swarm robotics.

  1. [GuidingBots](GuidingBots.md)
  1. [FollowTheLeader](FollowTheLeader.md)
  1. [Ant](Ant.md)

# Introduction #

The model describes the area coverage pattern of a group of Agents, initially kept in a given geometry. The model is useful in studying the spread, the agent density at different radii and comparing the spread given different geometries. The model can be further extended and
modified according to specific application.

The model is inspired by the following work.

> K Cheng, P. Dasgupta " Distributed Coverage of Environments using Multi-robot swarms with Memory and Communication Constrains." UNO Technical Report no. cst-2009-1, 2009

It implements an initial stage of the model. It is a general implementation and can be changed depending on application. The model is modified and new features are added.

A presentation of the work can be found [here](http://docs.google.com/viewer?a=v&q=cache:rP_3HLYWTIUJ:disalw3.epfl.ch/Miscellaneous/Events_News/Docs/Dasgupta_seminar_17062009.pdf+Area+Coverage+npde+counting&hl=en&gl=in&pid=bl&srcid=ADGEESi2rlCzZdWo9tXTCaiw_XnCwWmjkBi5HNACd0W-pDRNgi9nULXZmAPspzA8wuQ8i7fUnEcNQpTnCCRNyYawja1i4X0kDzQ3LzaFPEwMy7yMwhEjxVjztBgtGDNYiQoXXD1_BmS&sig=AHIEtbTZSsgGpYDDJxR2XHJ3Eua9XjERyQ&pli=1):

The "Dispersing" section of this video was also an inspiration for making the model

<a href='http://www.youtube.com/watch?feature=player_embedded&v=rYIkgG1nX4E' target='_blank'><img src='http://img.youtube.com/vi/rYIkgG1nX4E/0.jpg' width='425' height=344 /></a>


---

# Model Description #

The guiding algorithm is fairly simple. Each agents, lets call it a "Mote", tries to move in the area where there are less number of motes initially available. This forces the overall colony of motes to expand and thus covering a given area.

## Running the Model ##

User can select the initial geometry (from Line, Circle, Square and Hexagon) in which the motes are deployed. The parameters of the geometry can also be changed by the user. The user can also control the population of the motes and the step size. Standard Setup, Go and Go Once Options are also present. User Panel looks like this :

![https://repast-demos.googlecode.com/svn/wiki/areacoverage/panel.jpg](https://repast-demos.googlecode.com/svn/wiki/areacoverage/panel.jpg)

### Output ###

Model Outputter is also added. The added model outputter is comma separated values of "Time" and "Number of Motes" (Outside 14 units radius). One of the sample output file can be checked [here](https://repast-demos.googlecode.com/svn/wiki/areacoverage/Model%20Output.txt)

**Note:**

  1. This Output can be fed into various external plug-ins available with RepastS like MATLAB and spreadsheet.
  1. The number 14 used in the model has no relevance, it was chosen to get a good output. The user can modify the source code and set it to some other value.


---


### Analysis ###

Various analysis could be carried out with the model, but for demonstration purpose a graph between, "Number of Motes" (outside 14 units radius from the center) on y-axis and "Time" on x-axis is plotted.

![https://repast-demos.googlecode.com/svn/wiki/areacoverage/mote_plot.png](https://repast-demos.googlecode.com/svn/wiki/areacoverage/mote_plot.png)

For the plot it can be concluded that the number of motes outside a given radius grow exponentially. _Check with many test runs_


---


# Algorithm and Implementation #

The guiding algorithm is fairly simple. Each agents, lets call it a "Mote", tries to move in the area where there are less number of motes initially available. This forces the overall colony of motes to expand and thus covering a given area.

The initial position of motes lie inside a regular figure/geometry. The dimensions of which can be entered by the user. The initial geometries which come with the model are -

  1. Circle
  1. Square
  1. Hexagon
  1. Line

All the Mote positions are random values generated within the boundary of the selected geometries. The code had embedded comments to aptly explain the formation of these initial geometries and random position generation. The following page in the Description Section details about the initial geometry formation.

Each mote moves depending on the value of their Moore's neighbor. depending on the number of neighbours they select a direction (where the value is less). After selecting the value they face that direction and finally move to that position. The following code segment describes the
behavior of the motes.

```

class Mote extends BaseTurtle 
{
	//For giving behavior to a mote

	def step()
	{

		//Finds the direction witch points to least number of motes

		def dir = minOneOf(neighbors()) 
		{
			count(motesOn(it))
		}
		
		//Faces the direction witch points to least number of motes

		face(dir)
		
		//Moves forward number of steps as specified by the user
		forward(stepSize)
		
		}
		
		
}

```

The number of steps the motes take at a given clock tick can also be changed by the user. The user can also select how many motes he/she wants in a simulation.

The data sets are generated by the model which can be transferred to a spreadsheet and analyzed. The data is analyzed in the model and a few plots are added that are useful for comparative and time based study.

The rest of the code which is not explained here is self explanatory and well documented.

## Initial Geometry ##

The model generates four initial geometries as mentioned above. The initial geometry helps in selecting a way for robot deployment. Depending on the selected geometries the area coverage with respect to  time changes. The difference can be observed in the plots and is prominent in the initial stages. After a point of time the area coverages reaches a fixed value for all geometries.

The method used for creating geometries is quite simple and is based on basic coordinate geometry.

According to this method, a random number is generated that lies on the principle axis of the figure. All figures are made at Origin (0,0) and x - axis is the principal axis. The y-axis limit is the y - coordinate that lie in the area below the plot of the equation of the figure. A very common principle of Coordinate geometry guides this:

  * f(x,y)<0 , for points inside a 2-Dimensional figure on the x-y plane

  * f(x,y)>0 , for points outside a 2-Dimensional figure on the x-y plane

  * f(x,y)=0 , for points on a 2-Dimensional figure on the x-y plane

Given, f(x,y) is the equation of the figure and x, y are the respective x and y coordinates.

For Example, Consider a circle with center on the origin and radius r units

  * Equation             : x<sup>2</sup> + y<sup>2</sup> - r<sup>2</sup> = 0

  * Points on the circle : x<sup>2</sup> + y<sup>2</sup> - r<sup>2</sup> = 0

  * Inside               : x<sup>2</sup> + y<sup>2</sup> - r<sup>2</sup> < 0

  * Outside              : x<sup>2</sup> + y<sup>2</sup> - r<sup>2</sup> > 0

One one fourth of the figure is generated, it is randomly reflected or not reflected. If reflected it can be randomly reflected along any of the axes (x axis or y axis). This algorithm can be used to create any symmetric figure and fill turtles randomly in it.

The following is the code that explains the circle formulation.

```

	//Creates the number of motes specified by the user
		createMotes(numMotes)
		{

			//generates a random value less than radius

			x=random((initVal/2)+1)

                        //shifting of origin

			theta=((initVal/2)-x)
                        generating random y value withing the circle
			y =random(sqrt(((initVal/2)*(initVal 2))-(theta*theta))+1)
                       
                         //shifting of origin
			
                         x=x-(initVal/2)

                        //randomly setting sign to the coordinates

			if(random(2))
			{
				x=(-1)*x
			}
			if(random(2))
			{
				y=(-1)*y
			}

			//Sets the coordinate where the mote will be created
			
                       setxy(x,y)
		}

```

## User Panel ##

![https://repast-demos.googlecode.com/svn/wiki/areacoverage/panel.jpg](https://repast-demos.googlecode.com/svn/wiki/areacoverage/panel.jpg)

The section briefly describes the elements of the User Panel and the parameters they control.

  * **Push Buttons (that rebounds)**

> - Set Up : For setting up the motes in the area according to the selected options and settings.

> - Go Once : For running the simulation for one clock tick.

  * **Toggle Button**

> - Go : For running the model unless the button is toggled again

  * **Sliders**

> - Number of Motes : self explainatory

> - Step Size :Distance moved by the motes in a given direction

> - Initial Side Length/Radius : Parameters of the initial geometry

  * **Chooser**

> - User can select the inital geometry from a drop down list.

  * **Check Box**

> - Automatically Fill Initial Geometry: Automatically fills the selected geometry with maximum

number of number of motes.

## Data Sets, Outputters and External Plugin ##

Model Outputter is also added. The added model outputter is comma separated values of "Time" and "Number of Motes" (Outside 14 units radius). One of the sample output file can be checked [here](https://code.google.com/p/cscs-repast-demos/source/browse/wiki/AreaCoverage/Model%20Output.txt)

## Plots ##

Various analysis could be carried out with the model, but for demonstration purpose a graph between, "Number of Motes" (outside 14 units radius from the center) on y-axis and "Time" on x-axis is plotted.

![https://repast-demos.googlecode.com/svn/wiki/areacoverage/plot.jpg](https://repast-demos.googlecode.com/svn/wiki/areacoverage/plot.jpg)

For the plot it can be concluded that the number of motes outside a given radius grow exponentially. _Check with many test runs_

An interesting observation would be to see the  time taken to reach the asymptotic count, if :

  1. Population changed
  1. Stepsize changed


---


# Applications #

## Swarm Robotics and Mobile Wireless Sensor Networks ##

Multi Agent Robotics/ Swarm Robotics is used in many fields. Research has been in progress.

Some of the Applications of Multi Agent Robotic Systems are:

  1. Automatic Target Recognition

> 2. Automatic Landmine detection

> 3. Space Robotics, like fleet of Mars Rovers


This model can be used in swarm robotics application for studying optimum number of agents for a given area coverage in a given time frame.

It can also be used for calculating robot density at a given time in a given radius. It will also be instrumental in deciding the initial geometry of the fleet.

Each "Mote" or agent will decide its' movement on a local basis so no global communication between the robots is required and they can move and cover the area based on their neighbors.

## Other Application ##

It will be useful for social simulation. It can be used to study the behavior of colonies when each individual is given such a characteristic.


---


# Extending the Model #


## Adding Initial Geometry ##

The model can be extended to give mote density of different area(shapes) and even the user can add an irregular geometry or some other geometry according to project specific requirements. This can be done using the
same concept as described in the description section (generating geometries)

## Distributed Area Coverage ##

The Motes can be given more specific properties and the indivisual behavior can be changed slightly to see observable change in the colonial behavior. Try decreasing the speed of all the motes after they have traveled a specific distance (Assuming battery level falls)

## Addition of Plots and Outputters ##

Application specific graphs, outputters and plug in can be incorporated in the given model.


---


# References #

**http://en.wikipedia.org/wiki/Swarm_robotics**

