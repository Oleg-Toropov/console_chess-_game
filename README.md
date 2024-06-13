# Console Chess Game in OOP Style on Java

This program is a console version of the chess game, developed using the principles of object-oriented programming (OOP) in Java. The game provides an interactive interface for two players, allowing them to compete in a classic chess match. The program is built on a modular architecture, making it easily extendable and maintainable.

## Key Features

### Object-Oriented Design

The program is organized into classes representing various elements of the chess game, such as the chessboard (`Board`), pieces (`Piece`, `King`, `Queen`, `Rook`, `Bishop`, `Knight`, `Pawn`), coordinates (`Coordinates`), moves (`Move`, `HistoryMoves`), and a set of classes responsible for the game rules.

### Console Interface

Players interact with the game through a console interface, entering commands to move pieces and perform special actions such as castling and pawn promotion.

### Chess Rules Support

The program implements the basic rules of chess, including piece movement, capturing, check and checkmate, castling, pawn promotion, and stalemate.

### Move History

A record of all moves made is kept, allowing players to track the game's progress and verify rules such as the possibility of castling.

### Attacked Squares Check

Logic is implemented to check attacked squares, which is necessary for the correct execution of castling and check verification.

## Usage Example

### Starting the Game

When the game starts, users see the initial state of the chessboard and are prompted to enter commands.

### Entering Commands

Players enter commands to move pieces, for example, `e2 e4` to move a pawn from `e2` to `e4`.

### Moves and Rules

The program checks the validity of each move according to the chess rules and updates the board state.

### State Display

After each move, the board state is updated and displayed in the console.

## Conclusion

The program represents a full-fledged chess game, providing all the essential aspects of classic chess, implemented in Java using object-oriented programming principles.
