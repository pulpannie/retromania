----------------------
|    TIC-TAC-TOE     |
----------------------

INTRODUCTION
---------------
This game is the traditional TicTacToe game, coded using the Libgdx library.


DESIGN
---------------
It uses the MVP pattern. Therefore there are models, presenters, and views packages.
Furthermore there is a utils package for service classes.

The models package is responsible for the domain logic of TicTacToe.
The presenters package is responsible for the coordination between the models package and views package.
The view package is responsible for the implementation of the user interface.
The utils package provides service for the other classes.

CONFIGURATION
---------------
The User can configure their game in the MenuScreen.
ICONS - They can choose whether to use traditional Cross and Circle icons for the game, or to use different images of Cats.
SIZE - They can choose the size of the TicTacToe board. The minimum size is a 3x3 board.


MAINTAINERS
---------------
Hyokyung Kim