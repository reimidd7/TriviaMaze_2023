# Team13_TriviaMaze_2023

Trivia Maze is a group project that focuses on building a maze game with trivia questions. The game incorporates the Model-View-Controller (MVC) design pattern and utilizes a SQLite database to store questions and answers.

## Team Members
- Reilly Middlebrooks
- Danie Oum
- Kevin Than

## Game Description

In Trivia Maze, the user navigates through a maze composed of rooms from the entrance to the exit. Each room has one or more doors, and to pass through a door, the user must correctly answer a trivia question. The questions can be multiple choice, true/false, or short answer (one or two words/numbers) and are stored in a SQLite database.

If the user is unable to answer a question, the corresponding door is permanently locked. The game is lost if the user cannot reach the exit due to locked doors. The game interface may display one room at a time, the entire maze, or the current room and the entire maze.

Variations on the theme, such as placing helpful items in rooms (e.g., magic keys, hints) can be implemented with prior approval. The maze should be at least 4x4 rooms in size.

## Technical Requirements

- Incorporate the MVC design pattern
- Implement a Factory pattern for building objects (e.g., QuestionFactory)
- Ensure all instance data is private
- Utilize packages and visibility modifiers on methods for proper encapsulation
- Use a SQLite database to store questions and answers
- Implement the ability to save and load the current game state using serialization or writing to a text file

## Project Goals

- Build a project from scratch
- Coordinate code with team members using version control (git and GitHub) and project planning (Pivotal Tracker)
- Create a Software Requirements Specifications (SRS) document
- Implement unit testing
- Work with a SQLite database
- Incorporate the MVC design pattern and implement a Factory pattern for building objects
- Ensure proper encapsulation by keeping instance data private and utilizing packages and visibility modifiers on methods
- Conduct code reviews with the instructor

## User Interface

The user interface includes:

1. A menu system with options for:
   - File (Save Game, Load Game, Exit)
   - Help (About, Game Play Instructions)
2. A display for the current room information
3. Navigation options for valid room choices
4. A section that dynamically displays the current question based on its type (multiple choice, T/F, short answer)
