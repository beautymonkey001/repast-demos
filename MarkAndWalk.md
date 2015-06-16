# Summary #

  * Model        : Mark And Walk
  * Platform     : RepastS ReLogo
  * Version(s)	: Repast Simphony 2.0
  * Download     : http://repast-demos.googlecode.com/files/MarkAndWalk-1.0.1.zip
  * SVN Source  : http://repast-demos.googlecode.com/svn/trunk/demos/MarkAndWalk
  * Documentation : This Wiki Page
  * Author       : Mudit Raj Gupta


---


# Description #

The model demonstrates a Mark and Walk algorithm. It is an improvement on using RandomWalk Algorithm in area coverage. This can be used to predict how many times a cell is revisited by the agents and how much time an agent colony takes takes for complete Area Coverage

https://repast-demos.googlecode.com/svn/wiki/markAndWalk/WSN(Index).JPG


---


# User Control #

The user can assign a "Coverage Range". The number of agents are also adjustable. Standard Setup, Go and Go Once Options are also present. User Panel looks like this :

https://repast-demos.googlecode.com/svn/wiki/markAndWalk/WSN(panel).JPG



---


# Output #

Model Outputter is also added. The added model outputter is comma separated values of "Time" and "Area Covered". Another Outputter which is added is between "Time" and "Number of times each cell is visited".

**Note:**

This Output can be fed into various external plug-ins available with RepastS like MATLAB and spreadsheet.


---


# Analysis #

Various analysis could be carried out with the model, but for demonstration purpose a graph between, "Number of Visits" on y-axis and "Time" on x-axis is plotted.

![https://repast-demos.googlecode.com/svn/wiki/markAndWalk/WSNp2.png](https://repast-demos.googlecode.com/svn/wiki/markAndWalk/WSNp2.png)

Another graph of area coverage with time is plotted.

![https://repast-demos.googlecode.com/svn/wiki/markAndWalk/WSNp1.png](https://repast-demos.googlecode.com/svn/wiki/markAndWalk/WSNp1.png)


---


# Extending #

## Adding Constrains ##

Try adding constrains like, at each time all agents should be in n-hop connectivity to the base station located at (0,0) or all agents should move in a single cluster.

## Optimization ##

Try to add a few optimization conditions so that the number of time a cell is visited twice or thrice is reduced.


---


# Applications #

The algorithm is used in path planning application for swarm robotics. Specially, in cases of De-mining operations and detecting Gas Leakage.