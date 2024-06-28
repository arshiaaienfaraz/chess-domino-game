# Chess Domino Game

The following game is played by two players. Consider a chess table and a set of dominoes each of which occupies two squares on the table. The players must place a domino on the table in turn. The first player always places a domino horizontally, and the second player always vertically, respectively. A domino can be placed on only two adjacent empty squares (i.e., at most one domino is allowed to be placed on each square). The player who can make the last move wins the game.

## Setup

This project implements the Chess Domino Game where:
- Player 1 always places dominoes horizontally.
- Player 2 always places dominoes vertically.
- Moves are made by specifying the coordinates of the two squares to be occupied by the domino.

## Usage

To start the game, run the `ConsoleGame` class. You will be prompted to enter moves in the format `x1 y1 x2 y2`.

### An Example Game

1. PLAYER_1: 1 1 1 2
1. PLAYER_2: 2 1 3 1
1. PLAYER_1: 1 3 1 4
1. PLAYER_2: 2 3 3 3
1. PLAYER_1: 1 5 1 6
1. PLAYER_2: 2 5 3 5
1. PLAYER_1: 1 7 1 8
1. PLAYER_2: 2 7 3 7
1. PLAYER_1: 2 2 2 3
1. PLAYER_2: 3 2 4 2
1. PLAYER_1: 2 4 2 5
1. PLAYER_2: 3 4 4 4
1. PLAYER_1: 2 6 2 7
1. PLAYER_2: 3 6 4 6
1. PLAYER_1: 3 8 4 8
1. PLAYER_2: 4 1 5 1
1. PLAYER_1: 4 2 4 3
1. PLAYER_2: 5 3 6 3
1. PLAYER_1: 4 5 4 6
1. PLAYER_2: 5 5 6 5
1. PLAYER_1: 4 7 4 8
1. PLAYER_2: 5 7 6 7
1. PLAYER_1: 5 1 5 2
1. PLAYER_2: 6 1 7 1
1. PLAYER_1: 5 4 5 5
1. PLAYER_2: 6 4 7 4
1. PLAYER_1: 5 6 5 7
1. PLAYER_2: 6 6 7 6
1. PLAYER_1: 6 2 6 3
1. PLAYER_2: 7 2 8 2
1. PLAYER_1: 6 5 6 6
1. PLAYER_2: 7 5 8 5
1. PLAYER_1: 6 8 7 8
1. PLAYER_2: 7 7 8 7
1. PLAYER_1: 7 3 8 3
1. PLAYER_2: 8 1 8 2
1. PLAYER_1: 7 4 8 4
1. PLAYER_1 wins

## Project Structure

- `src/main/java/dominoGame/DominoState.java`: Implements the game state and rules.
- `src/main/java/dominoGame/ConsoleGame.java`: Main class to run the game in the console.
- `src/test/java/dominoGame/DominoStateTest.java`: Unit tests for the `DominoState` class.

## Maven Configuration

The project uses Maven for dependency management and build automation. Dependencies and plugins are specified in the `pom.xml` file.

## Author

Arshia Aienfaraz  
[arshia.aienfaraz@gmail.com](mailto:arshia.aienfaraz@gmail.com)
