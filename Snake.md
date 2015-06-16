# Summary #

  * Model        : Small World
  * Platform     : RepastS ReLogo
  * Version(s)	: Repast Simphony 2.0
  * Download     : http://repast-demos.googlecode.com/files/Snake-1.0.1.zip
  * SVN Source  : http://repast-demos.googlecode.com/svn/trunk/demos/Snake
  * Documentation : This Wiki Page & PDF in project docs directory
  * Author       : Tim Sweda

![https://repast-demos.googlecode.com/svn/trunk/demos/Snake/docs/snake.png](https://repast-demos.googlecode.com/svn/trunk/demos/Snake/docs/snake.png)

# Contents #



---


# Introduction #

This model is a version of the classic arcade game, _Snake_.  The object of the game is to grow the snake as long as possible by eating food, without running into a wall or the snake's body.  The snake can travel in any of the cardinal directions, and its body grows in length by one unit with each piece of food that it eats.  After the snake eats a piece of food, a new piece appears at a random location.

# Usage #

Before starting a new game, choose a **Difficulty Level** (at higher levels, the snake moves faster).  Click the **Setup** button to initialize and the **Go** button to run the model and begin the game.  Steer the snake by clicking the **Turn Left** and **Turn Right** buttons.  The player's score is shown under **myScore**.

# Exercises #

  * Create special blue food items that add 3 points to the player's score when eaten, appear at random intervals and only for a limited period of time, and add them to the model.  Come up with other clever food items that grant bonuses (or penalties) and implement those as well.

  * Modify the model so that the snake's body is composed of turtles instead of patches.

# ReLogo Features #

The **wait** method is useful for models such as this one that depend on user input as the model runs.  It pauses the simulation for a specified amount of time so that the user can interact with the model before the next time step begins.  Without it, the user can still interact with the model but with much less precision, which can make playing games difficult.  Try commenting out the **wait** method from this model for a real challenge!