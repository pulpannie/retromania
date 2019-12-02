Spaceship shooter
INTRODUCTION:
In this game we used the MVP pattern in order to decouple our classes and divide responsibilities into different packages in horizontal layer.
This leads to the structure that we are having have:

"models" package: keeps our model classes, which can only be accessed by presenters
"presenter" package: keeps our presenter (which updates models) classes, which can only be accessed by views
"views" package: keeps our view(which is responsible for rendering, getting input from user, and calling proper presenter method) classes
"utils" package: utilities package

REQUIREMENTS:
Game has same requirements as application

CONFIGURATION
-------------

The game has menu and modifiable settings. Menu screen is called  when game starts in the first time
and when the game play ends. It is responsible for requesting game start and showing up highest and latest score.
Setting screen is called from Pause screen. It is responsible for our game's personifications, which includes
selecting the theme of game, let the user decide whether to enabling/disabling music.


MAINTAINERS
-----------

* Thuy Le Quang Tuong
* Umid Targuliyev