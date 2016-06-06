# The Knights of Something Notable, a Text Adventure

## How to Run

### Linux

To compile the application, run the following command from the project root:

```
./gradlew clean build
```


Once the application is built, run it with the following command:

```
java -jar build/libs/KnightsOfSomethingNotable.jar
```


## Commands

### Navigation
e, east
w, west
n, north
s, south

### Other

__prev__
* repeats previous command

__look__
* returns information about the room, including exits, items present, creatures present

__check x__
* returns information about an item or creature (x) in the room

__attack x__
* initiates combat with creature (x) in the room

__get x__
* moves an item (x) from the room into your inventory

__put x__
* moves an item (x) from your inventory to the room

__equip x__
* equips an item (x) from your inventory

__unequip x__
* unequips an item (x) and places it in your inventory

__equipment, equip, eq__
* shows player equipment

__inventory, inv, i__
* shows player inventory

__player, p__
* shows player info, including name, status, inventory, equipment

__quit__
* closes the game



## Future improvements

### Fixes

* ~~Fix the input class/command recognition~~ DONE 1f42c375a987850e50b02c908710d58aacb71ef0
* ~~Genericize the Item/Creatures class~~ DONE

### Enhancements - Features

* ~~Player class~~ DONE
* ~~Basic Combat~~ DONE
* ~~Equipment stats (defense)~~ DONE
* Loot
* Game saves

### Enhancements - Code

* ~~Equipment as a map; unequip from slot if the slot exists in the map~~ DONE
* Separate class? to generate all creatures, loot, items at runtime

### Stretch goal

* Crafting
